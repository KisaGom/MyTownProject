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
          <b-icon icon="info-circle-fill"></b-icon>
          <div class="menu-text">편의시설</div>
        </li>
        <!-- <li :class="{ isActive: isActivated(4) }" @click="switchTab(4)">
          <b-icon icon="signpost-fill"></b-icon>
          <div class="menu-text">관광행사</div>
        </li> -->
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
        </b-input-group>
        <ul v-if="selsectedTab === 3" id="category">
          <li id="MT1" data-order="" @click="searchCategory('MT1')">마트</li>
          <li id="CS2" data-order="" @click="searchCategory('CS2')">편의점</li>
          <li id="PS3" data-order="" @click="searchCategory('PS3')">유치원</li>
          <li id="SC4" data-order="" @click="searchCategory('SC4')">학교</li>
          <li id="AC5" data-order="" @click="searchCategory('AC5')">학원</li>
          <li id="PK6" data-order="" @click="searchCategory('PK6')">주차장</li>
          <li id="OL7" data-order="" @click="searchCategory('OL7')">주유소</li>
          <li id="SW8" data-order="" @click="searchCategory('SW8')">
            지하철역
          </li>
          <li id="BK9" data-order="" @click="searchCategory('BK9')">은행</li>
          <li id="CT1" data-order="" @click="searchCategory('CT1')">
            문화시설
          </li>
          <li id="AG2" data-order="" @click="searchCategory('AG2')">
            중개업소
          </li>
          <li id="PO3" data-order="" @click="searchCategory('PO3')">
            공공기관
          </li>
          <li id="AT4" data-order="" @click="searchCategory('AT4')">
            관광명소
          </li>
          <li id="AD5" data-order="" @click="searchCategory('AD5')">숙박</li>
          <li id="FD6" data-order="" @click="searchCategory('FD6')">음식점</li>
          <li id="CE7" data-order="" @click="searchCategory('CE7')">카페</li>
          <li id="HP8" data-order="" @click="searchCategory('HP8')">병원</li>
          <li id="PM9" data-order="" @click="searchCategory('PM9')">약국</li>
        </ul>
        <life-house-list v-if="selsectedTab === 1"></life-house-list>
        <life-commercial-list v-if="selsectedTab === 2"></life-commercial-list>
        <life-convenience-list
          v-if="selsectedTab === 3"
          ref="childConv"
        ></life-convenience-list>
        <life-business-list
          :dong-code="dongCode"
          v-if="selsectedTab === 5"
        ></life-business-list>
        <life-favorite-list v-if="selsectedTab === 6"></life-favorite-list>
      </div>
    </div>
    <map-view ref="childMap"></map-view>
  </div>
</template>

<script>
import MapView from "@/components/map/MapView.vue";
import LifeHouseList from "@/components/life/LifeHouseList.vue";
import LifeCommercialList from "@/components/life/LifeCommercialList.vue";
import LifeBusinessList from "@/components/life/LifeBusinessList.vue";
import LifeConvenienceList from "@/components/life/LifeConvenienceList";
import LifeFavoriteList from "@/components/life/LifeFavoriteList.vue";
import { houseDealList } from "@/api/houseDeal";
import { commercialListDong } from "@/api/commercialInfo";
import { getAddrByCode, getAddrDetail } from "@/api/baseAddr";
import { searchCategory } from "@/api/convenience";
import { mapState, mapActions, mapMutations } from "vuex";
const houseStore = "houseStore";
const memberStore = "memberStore";
const infoStore = "infoStore";

export default {
  name: "LifeView",
  components: {
    MapView,
    LifeHouseList,
    LifeCommercialList,
    LifeBusinessList,
    LifeConvenienceList,
    LifeFavoriteList,
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
    ...mapActions(infoStore, [
      "dealAmount",
      "dealCount",
      "mrentAmount",
      "trentAmount",
      "rentCount",
      "getAvgAge",
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
      let fulldongcode = this.gugunCode + this.dongCode;
      this.dealAmount(fulldongcode);
      this.dealCount(fulldongcode);
      this.mrentAmount(fulldongcode);
      this.trentAmount(fulldongcode);
      this.rentCount(fulldongcode);
      this.getAvgAge(fulldongcode);
      if (this.selsectedTab == "3") {
        if (this.$refs.childConv) {
          this.$refs.childConv.items = [];
        }
      } else if (this.dongCode) {
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
        } else if (this.selsectedTab == "5") {
          let dongCode = this.gugunCode + this.dongCode;
          this.$refs.childMap.moveDongAddr(dongCode);
        }
      }
    },
    //편의시설------------------------------
    searchCategory(category) {
      let dongCode = this.gugunCode + this.dongCode;
      // console.log("dongCode", dongCode);
      getAddrByCode(dongCode, ({ data }) => {
        // console.log("addr", data);
        getAddrDetail(
          { query: data.sidoName + data.gugunName + data.dongName },
          ({ data }) => {
            console.log("addr detail", data.documents[0]);
            if (data.documents[0]) {
              let x = data.documents[0].x,
                y = data.documents[0].y;
              let params = {
                category_group_code: category,
                x: x,
                y: y,
                radius: "2000",
                sort: "distance",
              };
              searchCategory(params, ({ data }) => {
                let items = data.documents;
                this.$refs.childConv.items = items;

                if (items.length > 0) {
                  this.$refs.childMap.showOverlay(items, this.selsectedTab);
                } else {
                  //시설 정보가 없을 때
                  this.$refs.childMap.moveDongAddr(dongCode);
                }
                // console.log("search list", this.items);
              });
            }
          }
        );
      });
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
