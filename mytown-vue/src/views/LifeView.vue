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
      <div class="sidecontent side">
        <b-input-group>
          <b-form-select
            v-model="selected1"
            :options="options1"
            value-field="sidocode"
            text-field="sidoname"
          ></b-form-select>
          <b-form-select
            v-model="selected2"
            :options="options2"
            value-field="guguncode"
            text-field="gugunname"
          ></b-form-select>
          <b-form-select
            v-model="selected3"
            :options="options3"
            value-field="dongcode"
            text-field="dongname"
          ></b-form-select>
        </b-input-group>
        <!-- <div class="sideopener side">버튼</div> -->

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
      options1: [{ sidocode: null, sidoname: "서울특별시" }],
      selected2: null,
      options2: [{ guguncode: null, gugunname: "시작군" }],
      selected3: null,
      options3: [{ dongcode: null, dongname: "구의동로3가" }],
    };
  },
  mounted() {},
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
  transform: translateX(0%);
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

.isActive {
  background-color: aqua;
}
</style>
