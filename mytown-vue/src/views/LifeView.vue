<template>
  <div class="life">
    <div class="sidemenu side">
      <ul>
        <li @click="switchTab(0)">
          <b-icon icon="geo-alt-fill"></b-icon>
          <div class="menu-text">내위치</div>
        </li>
        <li :class="{ isActive: isActivated(1) }" @click="switchTab(1)">
          <b-icon icon="house-door-fill"></b-icon>
          <div class="menu-text">아파트</div>
        </li>
        <li :class="{ isActive: isActivated(2) }" @click="switchTab(2)">
          <b-icon icon="basket2-fill"></b-icon>
          <div class="menu-text">상권</div>
        </li>
        <li :class="{ isActive: isActivated(3) }" @click="switchTab(3)">
          <b-icon icon="exclamation-circle-fill"></b-icon>
          <div class="menu-text">편의시설</div>
        </li>
        <li :class="{ isActive: isActivated(4) }" @click="switchTab(4)">
          <b-icon icon="signpost-fill"></b-icon>
          <div class="menu-text">관광행사</div>
        </li>
        <li
          :class="{ isActive: isActivated(5) }"
          @click="switchTab(5)"
          v-if="this.userInfo"
        >
          <b-icon icon="currency-dollar"></b-icon>
          <div class="menu-text">경제규모</div>
        </li>
        <li
          :class="{ isActive: isActivated(6) }"
          @click="switchTab(6)"
          v-if="this.userInfo"
        >
          <b-icon icon="star-fill"></b-icon>
          <div class="menu-text">관심지역</div>
        </li>
      </ul>
    </div>
    <div class="sidecontent side" v-bind:class="{ isHidden: isHidden }">
      <div class="b-input-group">
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
          <b-input-group-append>
            <b-button><b-icon icon="star-fill"></b-icon></b-button>
          </b-input-group-append>
        </b-input-group>
        <life-toolbar v-if="selsectedTab === 1"></life-toolbar>
        <life-commercial-toolbar
          v-if="selsectedTab === 2"
        ></life-commercial-toolbar>
        <life-convenience
          :gugun-code="gugunCode"
          :dong-code="dongCode"
          v-if="selsectedTab === 3"
        ></life-convenience>
        <life-business-vue v-if="selsectedTab === 5"></life-business-vue>
        <life-favorite-vue v-if="selsectedTab === 6"></life-favorite-vue>
      </div>
    </div>
    <map-view ref="childMap"></map-view>
  </div>
</template>

<script>
import MapView from "@/components/map/MapView.vue";
import LifeToolbar from "@/components/life/LifeToolbar.vue";
import LifeCommercialToolbar from "@/components/life/LifeCommercialToolbar.vue";
import LifeBusinessVue from "@/components/life/LifeBusinessVue.vue";
import LifeConvenience from "@/components/life/LifeConvenience";
import LifeFavoriteVue from "@/components/life/LifeFavoriteVue.vue";
import { houseDealList } from "@/api/houseDeal";
import { commercialListDong } from "@/api/commercialInfo";
import { mapState, mapActions, mapMutations } from "vuex";
const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  name: "LifeView",
  components: {
    MapView,
    LifeToolbar,
    LifeCommercialToolbar,
    LifeBusinessVue,
    LifeConvenience,
    LifeFavoriteVue,
  },
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      dealYear: 2022,
      dealMonth: null,
      items: [],
      isLeftSided: false,
      selsectedTab: "0",
      isHidden: true,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
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
    switchTab(tab) {
      // console.log(tab);
      if (tab == 0) {
        this.selsectedTab = -1;
        this.$refs.childMap.moveMapCenter();
        this.isHidden = true;
      } else if (tab == this.selsectedTab) {
        this.selsectedTab = -1;
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
          // console.log("childMap", this.$refs.childMap);
          let dongCode = this.gugunCode + this.dongCode;
          //state 저장용
          this.getHouseList(dongCode);

          //overlay 표시용
          houseDealList(dongCode, (response) => {
            // console.log("searchApt", dongCode, response.data);
            this.items = response.data;

            //TODO 오버레이 몇 개 보여줄 지 정하기(임시로 20개) -> pagination 이후에??????????????
            let len = 20 < this.items.length ? 20 : this.items.length;
            if (len > 0) {
              this.$refs.childMap.showOverlay(
                this.items.slice(0, len),
                this.selsectedTab
              );
            } else {
              //거래 내역이 없을 때
              this.$refs.childMap.moveDongAddr(dongCode);
            }
            // console.log("len", len);
          });
        } else if (this.selsectedTab == "2") {
          let dongCode = this.gugunCode + this.dongCode;
          //state 저장용
          this.getCommercialListDong(dongCode);

          //overlay 표시용
          commercialListDong(dongCode, (response) => {
            this.items = response.data;

            //TODO 오버레이 몇 개 보여줄 지 정하기(임시로 20개) -> pagination 이후에??????????????
            let len = 20 < this.items.length ? 20 : this.items.length;
            if (len > 0) {
              this.$refs.childMap.showOverlay(
                this.items.slice(0, len),
                this.selsectedTab
              );
            } else {
              //가게 정보가 없을 때
              this.$refs.childMap.moveDongAddr(dongCode);
            }
          });
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
  border-right: 1px solid rgba(0, 0, 0, 0.15);
}

.sidemenu {
  position: absolute;
  z-index: 3;
  background-color: #fff;
}

.menu-text {
  font-size: 0.75rem;
}

ul {
  list-style: none;
  padding-left: 0px;
}

li {
  padding: 10px;
  border-bottom: 1px solid rgba(0, 0, 0, 0.15);
}

li:hover {
  color: #0475f4;
}

.sidecontent {
  position: absolute;
  width: 390px;
  margin-left: 68px;
  z-index: 2;
  background-color: #fff;
  overflow: scroll;
}

.sidecontent::-webkit-scrollbar {
  display: none;
}

.sidemenu a.router-link-exact-active {
  color: black;
}

.input-group select {
  font-size: 0.85rem;
}

.input-group button {
  font-size: 0.85rem;
}
.side-content-button {
  vertical-align: middle;
}

.isActive {
  background-color: #0475f4;
  color: white !important;
}

.isHidden {
  transform: translateX(-100%);
}
</style>
