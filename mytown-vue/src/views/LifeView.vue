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
            v-model="sidoCode"
            :options="sidos"
            @change="gugunList"
          ></b-form-select>
          <b-form-select
            v-model="gugunCode"
            :options="guguns"
            @change="dongList"
          ></b-form-select>
          <b-form-select v-model="dongCode" :options="dongs"></b-form-select>
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
import { mapState, mapActions, mapMutations } from "vuex";
const houseStore = "houseStore";

export default {
  name: "LifeView",
  components: { NavBar, MapView, LifeToolbar },
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      isLeftSided: false,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, ["getSido", "getGugun", "getDong"]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
    ]),
    gugunList() {
      console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      console.log(this.gugunCode);
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
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
