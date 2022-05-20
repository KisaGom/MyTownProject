<template>
  <div class="life-parent">
    <div style="position: relative">
      <nav-bar></nav-bar>
      <div class="sidemenu side">
        <ul>
          <li class="isActive">
            <a href="/life">
              <b-icon icon="house-fill"></b-icon>
              <div class="menu-text">매매정보</div>
            </a>
          </li>
          <li>
            <b-icon icon="house-fill"></b-icon>
            <div class="menu-text">상권</div>
          </li>
          <li>
            <b-icon icon="house-fill"></b-icon>
            <div class="menu-text">구성원</div>
          </li>
        </ul>
      </div>
      <div class="sidecontent side" v-bind:class="{ leftSided: isLeftSided }">
        <b-input-group>
          <b-form-select
            v-model="selected1"
            :options="options1"
            value-field="sidoCode"
            text-field="sidoName"
          ></b-form-select>
          <b-form-select
            v-model="selected2"
            :options="options2"
            value-field="gugunCode"
            text-field="gugunName"
          ></b-form-select>
          <b-form-select
            v-model="selected3"
            :options="options3"
            value-field="dongCode"
            text-field="dongName"
          ></b-form-select>
        </b-input-group>
        <div class="side-content-button">
          <b-icon icon="arrow-right-square-fill"></b-icon>
        </div>

        <life-toolbar></life-toolbar>
      </div>
    </div>
    <map-view></map-view>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import MapView from "@/components/map/MapView.vue";
import LifeToolbar from "@/components/life/LifeToolbar.vue";

export default {
  components: { NavBar, MapView, LifeToolbar },
  data() {
    return {
      selected1: null,
      options1: [{ sidoCode: null, sidoName: "서울특별시" }],
      selected2: null,
      options2: [{ gugunCode: null, gugunName: "시작군" }],
      selected3: null,
      options3: [{ dongCode: null, dongName: "구의동로3가" }],
      isLeftSided: false,
    };
  },
};
</script>
<style scoped>
a:hover {
  text-decoration: none;
}

.side {
  height: 100vh;
}

.sidemenu {
  position: absolute;
  z-index: 3;
  background-color: azure;
}

.menu-text {
  font-size: 9pt;
}

.sidecontent {
  position: absolute;
  width: 390px;
  margin-left: 68px;
  z-index: 2;
  background-color: aquamarine;
}

.sidemenu a.router-link-exact-active {
  color: black;
}

ul {
  list-style: none;
  padding-left: 0px;
}

li {
  padding: 10px;
}

.side-content-button {
  vertical-align: middle;
}

.isActive {
  background-color: aqua;
}

.leftSided {
  transform: translateX(-100%);
}
</style>
