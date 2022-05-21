<template>
  <div>
    <div id="map"></div>
    <!-- <vue-daum-map
      :appKey="appKey"
      :center.sync="center"
      :level.sync="level"
      :mapTypeId="mapTypeId"
      :libraries="libraries"
      style="width: 100%; height: 100vh"
    /> -->
    <div>
      <div>지도중심기준 행정동 주소정보</div>
      <div id="centerAddr"></div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      map: null,
      geocoder: null,
    };
  },
  methods: {
    initMap() {
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3,
      };

      this.map = new kakao.maps.Map(container, options);
      this.geocoder = new kakao.maps.services.Geocoder();

      // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
      this.searchAddrFromCoords(this.map.getCenter(), this.displayCenterInfo);
    },

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
// import VueDaumMap from "vue-daum-map";

// export default {
//   components: { VueDaumMap },
//   data() {
//     return {
//       appKey: "703da60a86e5e83c5accc64f51991199",
//       center: { lat: 33.450701, lng: 126.570667 }, // 지도의 중심 좌표
//       level: 3, // 지도의 레벨(확대, 축소 정도),
//       mapTypeId: VueDaumMap.MapTypeId.NORMAL, // 맵 타입
//       libraries: ["services"], // 추가로 불러올 라이브러리
//       map: null, // 지도 객체. 지도가 로드되면 할당됨.
//       geocoder: null,
//       kakao: null, // 다음 API 객체. 지도가 로드되면 할당됨.
//     };
//   },
//   methods: {
//     // 지도가 로드 완료되면 load 이벤트 발생
//     onLoad(map, daum) {
//       this.map = map;
//       this.kakao = daum;
//       this.geocoder = new this.kakao.maps.services.Geocoder();
//       // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
//       this.searchAddrFromCoords(this.map.getCenter(), this.displayCenterInfo);
//     },

//     searchAddrFromCoords(coords, callback) {
//       // 좌표로 행정동 주소 정보를 요청합니다
//       this.geocoder.coord2RegionCode(
//         coords.getLng(),
//         coords.getLat(),
//         callback
//       );
//     },

//     searchDetailAddrFromCoords(coords, callback) {
//       // 좌표로 법정동 상세 주소 정보를 요청합니다
//       this.geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
//     },

//     // 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
//     displayCenterInfo(result, status) {
//       console.log(status);
//       if (status === this.kakao.maps.services.Status.OK) {
//         var infoDiv = document.getElementById("centerAddr");

//         for (var i = 0; i < result.length; i++) {
//           // 행정동의 region_type 값은 'H' 이므로
//           if (result[i].region_type === "H") {
//             infoDiv.innerHTML = result[i].address_name;
//             break;
//           }
//         }
//       }
//     },
//   },
// };
</script>

<style scoped>
#map {
  width: 100%;
  height: 100vh;
}
</style>
