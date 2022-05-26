<template>
  <div>
    <div id="map"></div>
    <div class="centerAddr" @click="moveCommunity">
      <div id="centerAddr"></div>
      <div>이 동네 커뮤니티 구경가기</div>
    </div>
    <div class="map_wrap">
      <div
        id="map"
        style="width: 100%; height: 100%; position: relative; overflow: hidden"
      ></div>
    </div>
  </div>
</template>

<script>
import { getAddrByCode } from "@/api/baseAddr";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  data() {
    return {
      map: null,
      //좌표로 주소를 얻어내기------------------------------
      geocoder: null,
      dongCode: "",
      //여러개 오버레이 표시하기--------------------------------
      overlays: [],
      items: [],
      //위도, 경도
      x: 128.598409092694,
      y: 35.8383244008836,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    initMap() {
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(this.y, this.x),
        level: 3,
      };
      //지도 객체 생성
      this.map = new kakao.maps.Map(container, options);

      this.moveMapCenter();

      //좌표로 주소를 얻어내기------------------------------
      this.geocoder = new kakao.maps.services.Geocoder();

      // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
      this.searchAddrFromCoords(this.map.getCenter(), this.displayCenterInfo);

      // 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
      kakao.maps.event.addListener(this.map, "idle", () => {
        this.searchAddrFromCoords(this.map.getCenter(), this.displayCenterInfo);
      });
    },

    //좌표로 주소를 얻어내기------------------------------
    searchAddrFromCoords(coords, callback) {
      // 좌표로 법정동 주소 정보를 요청합니다
      this.geocoder.coord2RegionCode(
        coords.getLng(),
        coords.getLat(),
        callback
      );
    },

    // 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
    displayCenterInfo(result, status) {
      if (status === kakao.maps.services.Status.OK) {
        // console.log("centerAddr", result);
        var infoDiv = document.getElementById("centerAddr");

        for (var i = 0; i < result.length; i++) {
          if (result[i].region_type === "B") {
            infoDiv.innerHTML = result[i].address_name;
            this.dongCode = result[i].code;
            break;
          }
        }
      }
    },

    moveCommunity() {
      // console.log("called moveCommunity", this.dongCode);
      this.$router.push(`/community/list/${this.dongCode}`);
    },

    //여러개 오버레이 표시하기--------------------------------
    //오버레이 보이기
    showOverlay(items, selsectedTab) {
      // console.log("called showOverlay");
      // console.log("items", items);
      if (items) {
        if (selsectedTab == 3) {
          this.y = items[0].y;
          this.x = items[0].x;
        } else {
          this.y = items[0].lat;
          this.x = items[0].lng;
        }
        // console.log(this.y, this.x);
        this.map.panTo(new kakao.maps.LatLng(this.y, this.x));
      }
      this.removeOverlay();
      //오버레이 생성
      items.forEach((item) => {
        let position, content;
        //아파트 오버레이
        if (selsectedTab == "1") {
          position = new kakao.maps.LatLng(item.lat, item.lng);
          content = `<div class="label"><div>${item.apartmentName}</div><div>${item.dealAmount}만원</div></div>`;
        }
        //상권 오버레이
        else if (selsectedTab == "2") {
          position = new kakao.maps.LatLng(item.lat, item.lng);
          content = `<div class="label"><div>${item.storeName}</div></div>`;
        }
        //편의시설 오버레이
        else if (selsectedTab == "3") {
          position = new kakao.maps.LatLng(item.y, item.x);
          content = `<div class="label"><div>${item.place_name}</div></div>`;
        }
        // 오버레이를 생성합니다
        let overlay = new kakao.maps.CustomOverlay({
          position: position, // 오버레이를 표시할 위치
          content: content,
        });
        this.overlays.push(overlay);
      });
      // console.log("overlays", this.overlays);
      this.setOverlay(this.map);
    },

    //오버레이 감추기
    removeOverlay() {
      // console.log("called removeOverlay");
      this.setOverlay(null);
      this.overlays = [];
    },

    //오버레이 표시 여부 설정
    setOverlay(map) {
      for (var i = 0; i < this.overlays.length; i++) {
        this.overlays[i].setMap(map);
      }
    },

    //지도 이동 관련----------------------------------------
    moveMapCenter() {
      if (this.userInfo) {
        //로그인 정보 있으면 사용자 거주 지역으로 지도 이동
        this.moveDongAddr(this.userInfo.dongCode);
      } else {
        //로그인 정보 없으면 현위치로 지도 이동
        this.moveLocation();
      }
    },

    //법정동코드에 해당하는 주소로 이동
    moveDongAddr(dongCode) {
      // console.log("called moveDongAddr", dongCode);
      this.removeOverlay();
      getAddrByCode(dongCode, ({ data }) => {
        // console.log("address", data);
        let address = data.sidoName + data.gugunName + data.dongName;
        this.geocoder.addressSearch(address, (result, status) => {
          // 정상적으로 검색이 완료됐으면
          if (status === kakao.maps.services.Status.OK) {
            // console.log("addr search result", result);
            this.x = result[0].x;
            this.y = result[0].y;
            var coords = new kakao.maps.LatLng(this.y, this.x);

            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            this.map.setCenter(coords);
          }
        });
      });
    },

    //geolocation 이용 현위치로 지도 이동
    moveLocation() {
      let locPosition = new kakao.maps.LatLng(33.450701, 126.570667);
      // console.log("navigator.geolocation", navigator.geolocation);
      // HTML5의 geolocation으로 사용할 수 있는지 확인합니다
      if (navigator.geolocation) {
        // GeoLocation을 이용해서 접속 위치를 얻어옵니다
        navigator.geolocation.getCurrentPosition((position) => {
          this.y = position.coords.latitude; // 위도
          this.x = position.coords.longitude; // 경도

          locPosition = new kakao.maps.LatLng(this.y, this.x);

          this.map.setCenter(locPosition);
        });
      }
    },
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&libraries=services&appkey=703da60a86e5e83c5accc64f51991199";
      document.head.appendChild(script);
    }
  },
};
</script>

<style>
#map {
  width: 100%;
  height: 100vh;
}

.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap {
  position: absolute;
  width: auto;
  height: auto;
  left: 455px;
  top: 50px;
}

div.overlay {
  position: absolute;
  width: auto;
  height: auto;
  right: 5px;
  bottom: 70px;
  z-index: 3;
}

div.label {
  background-color: #fff;
  border: 1px solid rgba(0, 0, 0, 0.15);
  border-radius: 0.5rem;
  font-size: 0.75rem;
  display: inline-block;
  padding: 2px 6px;
}

/* 좌표로 주소를 얻어내기 */
div.centerAddr {
  position: absolute;
  width: auto;
  height: auto;
  right: 10px;
  top: 70px;
  z-index: 1;
  background-color: #fff;
  border: 1px solid rgba(0, 0, 0, 0.15);
  padding: 14px 20px;
  cursor: pointer;
  font-size: 0.9rem;
}

div#centerAddr {
  font-weight: bold;
  font-size: 1rem;
  margin-bottom: 5px;
}
</style>
