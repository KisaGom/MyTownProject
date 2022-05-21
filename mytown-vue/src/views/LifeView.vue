<template>
  <div class="life-parent">
    <div style="position: relative">
      <nav-bar></nav-bar>
      <div class="sidemenu side">
        <ul>
          <li :class="{ isActive: isActivated(1) }" @click="switchTab(1)">
            <b-icon icon="house-fill"></b-icon>
            <div class="menu-text">매매정보</div>
          </li>
          <li :class="{ isActive: isActivated(2) }" @click="switchTab(2)">
            <b-icon icon="house-fill"></b-icon>
            <div class="menu-text">상권</div>
          </li>
          <li :class="{ isActive: isActivated(3) }" @click="switchTab(3)">
            <b-icon icon="house-fill"></b-icon>
            <div class="menu-text">구성원</div>
          </li>
        </ul>
      </div>
      <div class="sidecontent side" v-bind:class="{ isHidden: isHidden }">
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
          <b-form-select
            v-model="dongCode"
            :options="dongs"
            @change="doSearch"
          ></b-form-select>
        </b-input-group>
        <life-toolbar v-if="selsectedTab === 1"></life-toolbar>
        <life-commercial-toolbar
          v-if="selsectedTab === 2"
        ></life-commercial-toolbar>
      </div>
    </div>
    <map-view></map-view>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import MapView from "@/components/map/MapView.vue";
import LifeToolbar from "@/components/life/LifeToolbar.vue";
import LifeCommercialToolbar from "@/components/life/LifeCommercialToolbar.vue";
import { mapState, mapActions, mapMutations } from "vuex";
const houseStore = "houseStore";

export default {
  name: "LifeView",
  components: { NavBar, MapView, LifeToolbar, LifeCommercialToolbar },
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      isLeftSided: false,
      selsectedTab: "0",
      isHidden: true,
    };
  },
  computed: {
    ...mapState(houseStore, [
      "sidos",
      "guguns",
      "dongs",
      "houses",
      "house",
      "comms",
    ]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
      "getCommList",
      "getCommercialListDong",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_HOUSE_LIST",
      "CLEAR_COMM_LIST",
    ]),
    gugunList() {
      this.CLEAR_HOUSE_LIST();
      this.CLEAR_GUGUN_LIST();
      this.CLEAR_DONG_LIST();
      this.gugunCode = null;
      this.dongCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_HOUSE_LIST();
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },

    searchApt() {
      this.getHouseList(this.gugunCode + this.dongCode);
    },
    searchComm() {
      this.getCommercialListDong(this.gugunCode + this.dongCode);
    },
    switchTab(tab) {
      if (tab == this.selsectedTab) {
        this.selsectedTab = 0;
        this.isHidden = true;
      } else {
        this.selsectedTab = tab;
        this.isHidden = false;
        this.doSearch();
      }
    },
    doSearch() {
      if (this.dongCode) {
        if (this.selsectedTab == "1") {
          this.searchApt();
        } else if (this.selsectedTab == "2") {
          this.searchComm();
        }
      }
    },
    isActivated(tab) {
      if (tab == this.selsectedTab) return true;
      else return false;
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
  overflow: auto;
}

.sidecontent::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
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

li:hover {
  background-color: skyblue;
}

.side-content-button {
  vertical-align: middle;
}

.isActive {
  background-color: skyblue;
}

.isHidden {
  transform: translateX(-100%);
}
</style>
