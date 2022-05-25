<template>
  <div>
    <b-container
      ><b-row class="mt-3"
        ><b-col cols="11" /><b-icon icon="info-circle" id="popover1"></b-icon
      ></b-row>
      <hr />

      <b-row v-b-toggle.collapse-0
        ><b-col cols="12">
          <h4>구/군별 평균 세입 (억 원)</h4>
          <b-collapse id="collapse-0"
            ><b-row>
              <b-col cols="9" class="text-right">전국 평균</b-col
              ><b-col cols="3" class="text-left">9489.6</b-col></b-row
            ><b-row
              class="mt-2"
              v-for="(tc, index) in this.incomeItems"
              :key="index"
            >
              <!-- vfor 드가야할듯 -->
              <b-col cols="9" class="text-right">{{ tc.address }}</b-col
              ><b-col cols="3" class="text-left">{{ tc.income }}</b-col></b-row
            ></b-collapse
          >
        </b-col></b-row
      >
      <hr />
      <b-row v-b-toggle.collapse-1
        ><b-col cols="12">
          <h4>지역 평균 연령</h4>
          <b-collapse id="collapse-1"
            ><b-row>
              <b-col cols="9" class="text-right">전국 평균</b-col
              ><b-col cols="3" class="text-left">42.9</b-col></b-row
            ><b-row
              class="mt-2"
              v-for="(ag, index) in this.ageAverageItems"
              :key="index"
            >
              <!-- vfor 드가야할듯 -->
              <b-col cols="9" class="text-right">{{ ag.address }}</b-col
              ><b-col cols="3" class="text-left">{{
                ag.totalAvg
              }}</b-col></b-row
            ></b-collapse
          >
        </b-col></b-row
      >
      <hr />
      <b-row v-b-toggle.collapse-2
        ><b-col cols="12">
          <h4>아파트 매매량 (회)</h4>
          <b-collapse id="collapse-2"
            ><b-row>
              <b-col cols="9" class="text-right">전국 평균</b-col
              ><b-col cols="3" class="text-left">221</b-col></b-row
            ><b-row
              class="mt-2"
              v-for="(dc, index) in this.dealCountItems"
              :key="index"
            >
              <!-- vfor 드가야할듯 -->
              <b-col cols="9" class="text-right">{{ dc.address }}</b-col
              ><b-col cols="3" class="text-left">{{ dc.count }}</b-col></b-row
            ></b-collapse
          >
        </b-col></b-row
      >
      <hr />
      <b-row v-b-toggle.collapse-3
        ><b-col cols="12">
          <h4>아파트 평균 매매가 (만 원)</h4>
          <b-collapse id="collapse-3"
            ><b-row>
              <b-col cols="9" class="text-right">전국 평균</b-col
              ><b-col cols="3" class="text-left">31950.2</b-col></b-row
            ><b-row
              class="mt-2"
              v-for="(ada, index) in this.avgDealAmountItems"
              :key="index"
            >
              <!-- vfor 드가야할듯 -->
              <b-col cols="9" class="text-right">{{ ada.address }}</b-col
              ><b-col cols="3" class="text-left">{{
                ada.avgDealAmount
              }}</b-col></b-row
            ></b-collapse
          >
        </b-col></b-row
      >
      <hr />
      <b-row v-b-toggle.collapse-4
        ><b-col cols="12">
          <h4>전월세 계약건수 (회)</h4>
          <b-collapse id="collapse-4"
            ><b-row>
              <b-col cols="9" class="text-right">전국 평균</b-col
              ><b-col cols="3" class="text-left">15.4</b-col></b-row
            ><b-row
              class="mt-2"
              v-for="(rc, index) in this.rentCountItems"
              :key="index"
            >
              <!-- vfor 드가야할듯 -->
              <b-col cols="9" class="text-right">{{ rc.address }}</b-col
              ><b-col cols="3" class="text-left">{{ rc.count }}</b-col></b-row
            ></b-collapse
          >
        </b-col></b-row
      >
      <hr />
      <b-row v-b-toggle.collapse-5
        ><b-col cols="12">
          <h4>전세 계약금 (만 원)</h4>
          <b-collapse id="collapse-5"
            ><b-row>
              <b-col cols="9" class="text-right">전국 평균</b-col
              ><b-col cols="3" class="text-left">32127.0</b-col></b-row
            ><b-row
              class="mt-2"
              v-for="(atr, index) in this.avgTRentAmountItems"
              :key="index"
            >
              <!-- vfor 드가야할듯 -->
              <b-col cols="9" class="text-right">{{ atr.address }}</b-col
              ><b-col cols="3" class="text-left">{{
                atr.avgDeposit
              }}</b-col></b-row
            ></b-collapse
          >
        </b-col></b-row
      >
      <hr />
      <b-row v-b-toggle.collapse-6
        ><b-col cols="12">
          <h4 class="mb-2">월세 보증금 (만 원)</h4>
          <b-collapse id="collapse-6"
            ><b-row>
              <b-col cols="9" class="text-right">전국 평균</b-col
              ><b-col cols="3" class="text-left">9982.7</b-col></b-row
            ><b-row
              class="mt-2"
              v-for="(amr, index) in this.avgMRentAmountItems"
              :key="index"
            >
              <!-- vfor 드가야할듯 -->
              <b-col cols="9" class="text-right">{{ amr.address }}</b-col
              ><b-col cols="3" class="text-left">{{ amr.avgDeposit }}</b-col>
            </b-row></b-collapse
          >
        </b-col></b-row
      >
      <b-row class="pt-4" v-b-toggle.collapse-6
        ><b-col cols="12">
          <h4 class="mb-2">월세 (만 원)</h4>
          <b-collapse id="collapse-6"
            ><b-row>
              <b-col cols="9" class="text-right">전국 평균</b-col
              ><b-col cols="3" class="text-left">62.6</b-col></b-row
            ><b-row
              class="mt-2"
              v-for="(amr, index) in this.avgMRentAmountItems"
              :key="index"
            >
              <!-- vfor 드가야할듯 -->
              <b-col cols="9" class="text-right">{{ amr.address }}</b-col
              ><b-col cols="3" class="text-left">{{ amr.avgRentAmount }}</b-col>
            </b-row></b-collapse
          >
          <hr /> </b-col
      ></b-row>
    </b-container>

    <b-popover target="popover1" triggers="hover focus">
      <template #title>도움말</template>
      <h6>읍/면/리의 경우 데이터가</h6>
      <h6>조회되지 않을 수 있습니다.</h6>
    </b-popover>
  </div>
</template>

<script>
import { mapState } from "vuex";
import {
  dealCountList,
  incomeList,
  rentCountList,
  ageAverageList,
  avgDealAmountList,
  avgMRentAmount,
  avgTRentAmount,
} from "@/api/statistics.js";
const memberStore = "memberStore";

export default {
  name: "LifeFavoriteVue",
  data() {
    return {
      dealCountItems: null,
      incomeItems: null,
      rentCountItems: null,
      ageAverageItems: null,
      avgDealAmountItems: null,
      avgMRentAmountItems: null,
      avgTRentAmountItems: null,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.getDealCount(this.userInfo.userid);
    this.getIncomeList(this.userInfo.userid);
    this.getRentCountList(this.userInfo.userid);
    this.getAgeAverage(this.userInfo.userid);
    this.getDealAmount(this.userInfo.userid);
    this.getMRentAmount(this.userInfo.userid);
    this.getTRentAmount(this.userInfo.userid);
  },
  methods: {
    getDealCount(userid) {
      dealCountList(userid, (response) => {
        this.dealCountItems = response.data;
      });
    },
    getIncomeList(userid) {
      incomeList(userid, (response) => {
        this.incomeItems = response.data;
      });
    },
    getRentCountList(userid) {
      rentCountList(userid, (response) => {
        this.rentCountItems = response.data;
      });
    },
    getAgeAverage(userid) {
      ageAverageList(userid, (response) => {
        this.ageAverageItems = response.data;
      });
    },
    getDealAmount(userid) {
      avgDealAmountList(userid, (response) => {
        this.avgDealAmountItems = response.data;
      });
    },
    getMRentAmount(userid) {
      avgMRentAmount(userid, (response) => {
        this.avgMRentAmountItems = response.data;
      });
    },
    getTRentAmount(userid) {
      avgTRentAmount(userid, (response) => {
        console.log("log", response.data);
        this.avgTRentAmountItems = response.data;
      });
    },
  },
};
</script>

<style>
.popover {
  max-width: 270px !important;
  width: 270px !important;
}
</style>
