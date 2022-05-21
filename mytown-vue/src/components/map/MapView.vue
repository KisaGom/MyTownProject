<template>
  <div>
    <div id="map"></div>
    <div>
      <div>지도중심기준 행정동 주소정보</div>
      <div id="centerAddr"></div>
    </div>
    <div>
      <b-button @click="showPosMarkers">마커 보이기</b-button>
      <b-button @click="removePosMarkers">마커 감추기</b-button>
    </div>
    <div class="map_wrap">
      <div
        id="map"
        style="width: 100%; height: 100%; position: relative; overflow: hidden"
      ></div>
      <ul id="category">
        <li id="BK9" data-order="0" @click="onClickCategory">
          <span class="category_bg bank"></span>
          은행
        </li>
        <li id="MT1" data-order="1" @click="onClickCategory">
          <span class="category_bg mart"></span>
          마트
        </li>
        <li id="PM9" data-order="2" @click="onClickCategory">
          <span class="category_bg pharmacy"></span>
          약국
        </li>
        <li id="OL7" data-order="3" @click="onClickCategory">
          <span class="category_bg oil"></span>
          주유소
        </li>
        <li id="CE7" data-order="4" @click="onClickCategory">
          <span class="category_bg cafe"></span>
          카페
        </li>
        <li id="CS2" data-order="5" @click="onClickCategory">
          <span class="category_bg store"></span>
          편의점
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      map: null,
      //좌표로 주소를 얻어내기------------------------------
      geocoder: null,
      //여러개 마커 표시하기--------------------------------
      positions: [
        {
          apartmentName: "카카오",
          lat: "33.450705",
          lng: "126.570677",
        },
        {
          apartmentName: "생태연못",
          lat: "33.450936",
          lng: "126.569477",
        },
        {
          apartmentName: "텃밭",
          lat: "33.450879",
          lng: "126.56994",
        },
        {
          apartmentName: "근린공원",
          lat: "33.451393",
          lng: "126.570738",
        },
      ],
      posMarkers: [],
      //카테고리별 장소 검색하기------------------------------
      ps: null,
      placeOverlay: null,
      contentNode: null,
      markers: [],
      curCategory: "",
    };
  },
  methods: {
    initMap() {
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3,
      };

      //지도 객체 생성
      this.map = new kakao.maps.Map(container, options);

      //좌표로 주소를 얻어내기------------------------------
      this.geocoder = new kakao.maps.services.Geocoder();

      // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
      this.searchAddrFromCoords(this.map.getCenter(), this.displayCenterInfo);

      // 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
      // kakao.maps.event.addListener(this.map, "idle", function () {
      kakao.maps.event.addListener(this.map, "idle", () => {
        this.searchAddrFromCoords(this.map.getCenter(), this.displayCenterInfo);
      });

      //여러개 마커 표시하기--------------------------------
      for (var i = 0; i < this.positions.length; i++) {
        // 마커를 생성합니다
        let marker = new kakao.maps.Marker({
          // map: this.map, // 마커를 표시할 지도
          position: new kakao.maps.LatLng(
            this.positions[i].lat,
            this.positions[i].lng
          ), // 마커를 표시할 위치
          title: this.positions[i].apartmentName, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        });
        this.posMarkers.push(marker);
      }

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

    //여러개 마커 표시하기--------------------------------
    //마커 보이기
    showPosMarkers() {
      this.setPosMarkers(this.map);
    },

    //마커 감추기
    removePosMarkers() {
      this.setPosMarkers(null);
    },

    setPosMarkers(map) {
      for (var i = 0; i < this.posMarkers.length; i++) {
        this.posMarkers[i].setMap(map);
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

        // TODO: 마커에 오버레이 클릭 이벤트 등록
        // 마커와 검색결과 항목을 클릭 했을 때
        // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
        // (function (marker, place) {
        //   kakao.maps.event.addListener(marker, "click", function () {
        //     this.displayPlaceInfo(place);
        //   });
        // })(marker, places[i]);
      }
      console.log("markers len", this.markers.length);
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

    // TODO: 마커에 오버레이 등록
    // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
    // displayPlaceInfo(place) {
    //   var content =
    //     '<div class="placeinfo">' +
    //     '   <a class="title" href="' +
    //     place.place_url +
    //     '" target="_blank" title="' +
    //     place.place_name +
    //     '">' +
    //     place.place_name +
    //     "</a>";

    //   if (place.road_address_name) {
    //     content +=
    //       '    <span title="' +
    //       place.road_address_name +
    //       '">' +
    //       place.road_address_name +
    //       "</span>" +
    //       '  <span class="jibun" title="' +
    //       place.address_name +
    //       '">(지번 : ' +
    //       place.address_name +
    //       ")</span>";
    //   } else {
    //     content +=
    //       '    <span title="' +
    //       place.address_name +
    //       '">' +
    //       place.address_name +
    //       "</span>";
    //   }

    //   content +=
    //     '    <span class="tel">' +
    //     place.phone +
    //     "</span>" +
    //     "</div>" +
    //     '<div class="after"></div>';

    //   this.contentNode.innerHTML = content;
    //   this.placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
    //   this.placeOverlay.setMap(this.map);
    // },

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

<style scoped>
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
  position: relative;
  width: 100%;
  height: 350px;
}
#category {
  position: absolute;
  top: 10px;
  left: 10px;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 2;
}
#category li {
  float: left;
  list-style: none;
  width: 50px;
  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}
#category li.on {
  background: #eee;
}
#category li:hover {
  background: #ffe6e6;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}
#category li:last-child {
  margin-right: 0;
  border-right: 0;
}
#category li span {
  display: block;
  margin: 0 auto 3px;
  width: 27px;
  height: 28px;
}
#category li .category_bg {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png)
    no-repeat;
}
#category li .bank {
  background-position: -10px 0;
}
#category li .mart {
  background-position: -10px -36px;
}
#category li .pharmacy {
  background-position: -10px -72px;
}
#category li .oil {
  background-position: -10px -108px;
}
#category li .cafe {
  background-position: -10px -144px;
}
#category li .store {
  background-position: -10px -180px;
}
#category li.on .category_bg {
  background-position-x: -46px;
}
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
  background: #d95050;
  background: #d95050
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
</style>
