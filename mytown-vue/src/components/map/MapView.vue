<template>
  <div>
    <div id="map"></div>
    <div class="centerAddr">
      <div>지도중심기준 행정동 주소정보</div>
      <div id="centerAddr"></div>
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
      //카테고리별 장소 검색하기------------------------------
      ps: null,
      placeOverlay: null,
      contentNode: null,
      curCategory: "",
      markers: [],
      //여러개 오버레이 표시하기--------------------------------
      overlays: [],
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

      //카테고리별 장소 검색하기------------------------------
      // 장소 검색 객체를 생성합니다
      this.ps = new kakao.maps.services.Places(this.map);
      // console.log("ps", this.ps);
      // 마커를 클릭했을 때 해당 장소의 상세정보를 보여줄 커스텀오버레이입니다
      this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
      this.contentNode = document.createElement("div"); // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다

      // 지도에 idle 이벤트를 등록합니다
      kakao.maps.event.addListener(this.map, "idle", this.searchPlaces);

      // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
      this.contentNode.className = "placeinfo_wrap";

      // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
      // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
      this.addEventHandle(
        this.contentNode,
        "mousedown",
        kakao.maps.event.preventMap
      );
      this.addEventHandle(
        this.contentNode,
        "touchstart",
        kakao.maps.event.preventMap
      );

      // 커스텀 오버레이 컨텐츠를 설정합니다
      this.placeOverlay.setContent(this.contentNode);
    },

    //좌표로 주소를 얻어내기------------------------------
    searchAddrFromCoords(coords, callback) {
      // 좌표로 행정동 주소 정보를 요청합니다
      this.geocoder.coord2RegionCode(
        coords.getLng(),
        coords.getLat(),
        callback
      );
    },

    searchDetailAddrFromCoords(coords, callback) {
      // 좌표로 법정동 상세 주소 정보를 요청합니다
      this.geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
    },

    // 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
    displayCenterInfo(result, status) {
      if (status === kakao.maps.services.Status.OK) {
        var infoDiv = document.getElementById("centerAddr");

        for (var i = 0; i < result.length; i++) {
          // 행정동의 region_type 값은 'H' 이므로
          if (result[i].region_type === "H") {
            infoDiv.innerHTML = result[i].address_name;
            break;
          }
        }
      }
    },

    //여러개 오버레이 표시하기--------------------------------
    //오버레이 보이기
    showOverlay(items, selsectedTab) {
      // console.log("called showOverlay");
      // console.log("items", items);
      if (items) {
        this.y = items[0].lat;
        this.x = items[0].lng;
        this.map.panTo(new kakao.maps.LatLng(this.y, this.x));
      }
      this.removeOverlay();
      //오버레이 생성
      items.forEach((item) => {
        let position = new kakao.maps.LatLng(item.lat, item.lng),
          content;
        //아파트 오버레이
        if (selsectedTab == "1") {
          content = `<div class ="label"><span class="left"></span><span class="center">${item.apartmentName}</span><span class="right"></span></div>`;
        }
        //상권 오버레이
        else {
          content = `<div class ="label"><span class="left"></span><span class="center">${item.storeName}</span><span class="right"></span></div>`;
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
      console.log("navigator.geolocation", navigator.geolocation);
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

    //카테고리별 장소 검색하기------------------------------
    // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
    addEventHandle(target, type, callback) {
      // console.log("called addEventHandle", type);
      if (target.addEventListener) {
        target.addEventListener(type, callback);
      } else {
        target.attachEvent("on" + type, callback);
      }
    },

    // 카테고리 검색을 요청하는 함수입니다
    searchPlaces() {
      // console.log("called searchPlaces");
      if (!this.curCategory) {
        return;
      }

      // 커스텀 오버레이를 숨깁니다
      this.placeOverlay.setMap(null);

      // 지도에 표시되고 있는 마커를 제거합니다
      this.removeMarker();

      this.ps.categorySearch(this.curCategory, this.placesSearchCB, {
        useMapBounds: true,
      });
    },

    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    placesSearchCB(data, status) {
      // console.log("called placesSearchCB");
      if (status === kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
        this.displayPlaces(data);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
      } else if (status === kakao.maps.services.Status.ERROR) {
        // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
      }
    },

    // 지도에 마커를 표출하는 함수입니다
    displayPlaces(places) {
      // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
      // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
      var order = document
        .getElementById(this.curCategory)
        .getAttribute("data-order");

      for (var i = 0; i < places.length; i++) {
        // 마커를 생성하고 지도에 표시합니다
        this.addMarker(new kakao.maps.LatLng(places[i].y, places[i].x), order);

        // 마커와 검색결과 항목을 클릭 했을 때
        // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
        let placeParam = places[i];
        kakao.maps.event.addListener(this.markers[i], "click", () => {
          // console.log("placeParam", placeParam);
          this.displayPlaceInfo(placeParam);
        });
      }
      // console.log("markers len", this.markers.length);
    },

    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    addMarker(position, order) {
      let imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
        imgOptions = {
          spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
          spriteOrigin: new kakao.maps.Point(46, order * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imgOptions
        ),
        marker = new kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage,
        });

      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.markers.push(marker); // 배열에 생성된 마커를 추가합니다
    },

    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    removeMarker() {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },

    // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
    displayPlaceInfo(place) {
      var content =
        '<div class="placeinfo">' +
        '   <a class="title" href="' +
        place.place_url +
        '" target="_blank" title="' +
        place.place_name +
        '">' +
        place.place_name +
        "</a>";

      if (place.road_address_name) {
        content +=
          '    <span title="' +
          place.road_address_name +
          '">' +
          place.road_address_name +
          "</span>" +
          '  <span class="jibun" title="' +
          place.address_name +
          '">(지번 : ' +
          place.address_name +
          ")</span>";
      } else {
        content +=
          '    <span title="' +
          place.address_name +
          '">' +
          place.address_name +
          "</span>";
      }

      content +=
        '    <span class="tel">' +
        place.phone +
        "</span>" +
        "</div>" +
        '<div class="after"></div>';

      this.contentNode.innerHTML = content;
      // console.log("this.contentNode", this.contentNode);
      this.placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
      this.placeOverlay.setMap(this.map);
    },

    // 카테고리를 클릭했을 때 호출되는 함수입니다
    onClickCategory() {
      // console.log("called onClickCategory");
      // console.log("event.currentTarget", event.currentTarget);
      var id = event.currentTarget.id,
        className = event.currentTarget.className;

      this.placeOverlay.setMap(null);

      console.log("id, className", id, className);

      if (className === "on") {
        // console.log("className on");
        this.curCategory = "";
        this.changeCategoryClass();
        this.removeMarker();
      } else {
        // console.log("className undefined");
        this.curCategory = id;
        this.changeCategoryClass(event.currentTarget);
        this.searchPlaces();
      }
    },

    // 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
    changeCategoryClass(el) {
      // console.log("called changeCategoryClass");
      var category = document.getElementById("category"),
        children = category.children,
        i;

      for (i = 0; i < children.length; i++) {
        children[i].className = "";
      }

      if (el) {
        console.log("el.className", el.className);
        el.className = "on";
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

/* 좌표로 주소를 얻어내기 */
div.centerAddr {
  position: absolute;
  width: auto;
  height: auto;
  right: 5px;
  bottom: 5px;
  z-index: 3;
  background-color: white;
  padding: 5px;
}

/* 카테고리별 장소 검색하기 */
.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}
.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}
.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}
.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #44d8e5;
  background: #44d8e5
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.placeinfo .tel {
  color: #0f7833;
}
.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}

/* 여러개 오버레이 표시하기 */
div.overlay {
  position: absolute;
  width: auto;
  height: auto;
  right: 5px;
  bottom: 70px;
  z-index: 3;
}

.label {
  margin-bottom: 96px;
}
.label * {
  display: inline-block;
  vertical-align: top;
}
.label .left {
  background: url("https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_l.png")
    no-repeat;
  display: inline-block;
  height: 24px;
  overflow: hidden;
  vertical-align: top;
  width: 7px;
}
.label .center {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_bg.png)
    repeat-x;
  display: inline-block;
  height: 24px;
  font-size: 12px;
  line-height: 24px;
}
.label .right {
  background: url("https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_r.png") -1px
    0 no-repeat;
  display: inline-block;
  height: 24px;
  overflow: hidden;
  width: 6px;
}
</style>
