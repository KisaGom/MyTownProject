<template>
  <div>
    <ul id="category">
      <li id="MT1" data-order="0" @click="onClickCategory">마트</li>
      <li id="CS2" data-order="" @click="onClickCategory">편의점</li>
      <li id="PS3" data-order="" @click="onClickCategory">유치원</li>
      <li id="SC4" data-order="" @click="onClickCategory">학교</li>
      <li id="AC5" data-order="" @click="onClickCategory">학원</li>
      <li id="PK6" data-order="" @click="onClickCategory">주차장</li>
      <li id="OL7" data-order="" @click="onClickCategory">주유소</li>
      <li id="SW8" data-order="" @click="onClickCategory">지하철역</li>
      <li id="BK9" data-order="" @click="onClickCategory">은행</li>
      <li id="CT1" data-order="" @click="onClickCategory">문화시설</li>
      <li id="AG2" data-order="" @click="onClickCategory">중개업소</li>
      <li id="PO3" data-order="" @click="onClickCategory">공공기관</li>
      <li id="AT4" data-order="" @click="onClickCategory">관광명소</li>
      <li id="AD5" data-order="" @click="onClickCategory">숙박</li>
      <li id="FD6" data-order="" @click="onClickCategory">음식점</li>
      <li id="CE7" data-order="" @click="onClickCategory">카페</li>
      <li id="HP8" data-order="" @click="onClickCategory">병원</li>
      <li id="PM9" data-order="" @click="searchCategory">약국</li>
    </ul>
  </div>
</template>

<script>
import { searchCategory } from "@/api/convenience";
import { eventBus } from "@/main";

export default {
  data() {
    return {
      latlng: { x: 128.598409092694, y: 35.8383244008836 },
    };
  },
  //   created() {
  //     eventBus.$on("setLatLng", (data) => {
  //       console.log("eventBus data", data);
  //       this.latlng = data;
  //       console.log("created this.latlng", this.latlng);
  //     });
  //   },
  mounted() {
    eventBus.$on("setLatLng", (data) => {
      console.log("eventBus data", data);
      this.latlng = data;
      console.log("mounted this.latlng", this.latlng);
    });
  },
  methods: {
    searchCategory() {
      console.log("search this.latlng", this.latlng);
      let x = this.latlng.x,
        y = this.latlng.y;
      let params = {
        category_group_code: "PM9",
        x: x,
        y: y,
        radius: "2000",
        sort: "distance",
      };
      searchCategory(params, (response) => {
        console.log(response.data);
      });
    },
    onClickCategory() {},
  },
};
</script>

<style>
/* 카테고리별 장소 검색하기 */
#category {
  position: relative;
  padding: 0;
  background: #fff;
  font-size: 0.9rem;
  overflow: hidden;
  z-index: 2;
}
#category li {
  float: left;
  list-style: none;
  width: 70px;
  border-right: 1px solid rgba(0, 0, 0, 0.15);
  border-bottom: 1px solid rgba(0, 0, 0, 0.15);
  padding: 4px;
  text-align: center;
  cursor: pointer;
}
#category li.on {
  background: #eee;
}
#category li:hover {
  background: #0475f4;
}
#category li:last-child {
  margin-right: 0;
}
</style>
