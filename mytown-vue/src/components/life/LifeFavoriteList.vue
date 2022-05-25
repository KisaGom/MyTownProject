<template>
  <div>
    <b-row class="mt-3"
      ><b-col cols="11" /><b-icon icon="info-circle" id="popover1"></b-icon
    ></b-row>
    <hr />

    <b-row v-b-toggle.collapse-0
      ><b-col class cols="12">
        <h6>구/군별 평균 세입</h6>
        <b-collapse id="collapse-0"
          ><b-row>
            <b-col cols="8" class="text-right">전국 평균</b-col
            ><b-col cols="4" class="text-left">9489.6억원</b-col></b-row
          ><b-row
            class="mt-2"
            v-for="(tc, index) in this.incomeItems"
            :key="index"
          >
            <!-- vfor 드가야할듯 -->
            <b-col cols="8" class="text-right">{{ tc.address }}</b-col
            ><b-col cols="4" class="text-left"
              >{{ tc.income }}억원</b-col
            ></b-row
          ></b-collapse
        >
      </b-col></b-row
    >
    <hr />
    <b-row v-b-toggle.collapse-1
      ><b-col cols="12">
        <h6>지역 평균 연령</h6>
        <b-collapse id="collapse-1"
          ><b-row>
            <b-col cols="8" class="text-right">전국 평균</b-col
            ><b-col cols="4" class="text-left">42.9세</b-col></b-row
          ><b-row
            class="mt-2"
            v-for="(ag, index) in this.ageAverageItems"
            :key="index"
          >
            <!-- vfor 드가야할듯 -->
            <b-col cols="8" class="text-right">{{ ag.address }}</b-col
            ><b-col cols="4" class="text-left"
              >{{ ag.totalAvg }}세</b-col
            ></b-row
          ></b-collapse
        >
      </b-col></b-row
    >
    <hr />
    <b-row v-b-toggle.collapse-2
      ><b-col cols="12">
        <h6>아파트 매매량</h6>
        <b-collapse id="collapse-2"
          ><b-row>
            <b-col cols="8" class="text-right">전국 평균</b-col
            ><b-col cols="4" class="text-left">221회</b-col></b-row
          ><b-row
            class="mt-2"
            v-for="(dc, index) in this.dealCountItems"
            :key="index"
          >
            <!-- vfor 드가야할듯 -->
            <b-col cols="8" class="text-right">{{ dc.address }}</b-col
            ><b-col cols="4" class="text-left">{{ dc.count }}회</b-col></b-row
          ></b-collapse
        >
      </b-col></b-row
    >
    <hr />
    <b-row v-b-toggle.collapse-3
      ><b-col cols="12">
        <h6>아파트 평균 매매가</h6>
        <b-collapse id="collapse-3"
          ><b-row>
            <b-col cols="8" class="text-right">전국 평균</b-col
            ><b-col cols="4" class="text-left">31950.2만원</b-col></b-row
          ><b-row
            class="mt-2"
            v-for="(ada, index) in this.avgDealAmountItems"
            :key="index"
          >
            <!-- vfor 드가야할듯 -->
            <b-col cols="8" class="text-right">{{ ada.address }}</b-col
            ><b-col cols="4" class="text-left"
              >{{ ada.avgDealAmount }}만원</b-col
            ></b-row
          ></b-collapse
        >
      </b-col></b-row
    >
    <hr />
    <b-row v-b-toggle.collapse-4
      ><b-col cols="12">
        <h6>전월세 계약건수</h6>
        <b-collapse id="collapse-4"
          ><b-row>
            <b-col cols="8" class="text-right">전국 평균</b-col
            ><b-col cols="4" class="text-left">15.4회</b-col></b-row
          ><b-row
            class="mt-2"
            v-for="(rc, index) in this.rentCountItems"
            :key="index"
          >
            <!-- vfor 드가야할듯 -->
            <b-col cols="8" class="text-right">{{ rc.address }}</b-col
            ><b-col cols="4" class="text-left">{{ rc.count }}회</b-col></b-row
          ></b-collapse
        >
      </b-col></b-row
    >
    <hr />
    <b-row v-b-toggle.collapse-5
      ><b-col cols="12">
        <h6>전세 계약금</h6>
        <b-collapse id="collapse-5"
          ><b-row>
            <b-col cols="8" class="text-right">전국 평균</b-col
            ><b-col cols="4" class="text-left">32127.0만원</b-col></b-row
          ><b-row
            class="mt-2"
            v-for="(atr, index) in this.avgTRentAmountItems"
            :key="index"
          >
            <!-- vfor 드가야할듯 -->
            <b-col cols="8" class="text-right">{{ atr.address }}</b-col
            ><b-col cols="4" class="text-left"
              >{{ atr.avgDeposit }}만원</b-col
            ></b-row
          ></b-collapse
        >
      </b-col></b-row
    >
    <hr />
    <b-row v-b-toggle.collapse-6
      ><b-col cols="12">
        <h6 class="mb-2">월세 보증금</h6>
        <b-collapse id="collapse-6"
          ><b-row>
            <b-col cols="8" class="text-right">전국 평균</b-col
            ><b-col cols="4" class="text-left">9982.7만원</b-col></b-row
          ><b-row
            class="mt-2"
            v-for="(amr, index) in this.avgMRentAmountItems"
            :key="index"
          >
            <!-- vfor 드가야할듯 -->
            <b-col cols="8" class="text-right">{{ amr.address }}</b-col
            ><b-col cols="4" class="text-left">{{ amr.avgDeposit }}만원</b-col>
          </b-row></b-collapse
        >
      </b-col></b-row
    >
    <b-row class="pt-4" v-b-toggle.collapse-6
      ><b-col cols="12">
        <h6 class="mb-2">월세</h6>
        <b-collapse id="collapse-6"
          ><b-row>
            <b-col cols="8" class="text-right">전국 평균</b-col
            ><b-col cols="4" class="text-left">62.6만원</b-col></b-row
          ><b-row
            class="mt-2"
            v-for="(amr, index) in this.avgMRentAmountItems"
            :key="index"
          >
            <!-- vfor 드가야할듯 -->
            <b-col cols="8" class="text-right">{{ amr.address }}</b-col
            ><b-col cols="4" class="text-left"
              >{{ amr.avgRentAmount }}만원</b-col
            >
          </b-row></b-collapse
        >
        <hr /> </b-col
    ></b-row>

    <b-popover target="popover1" triggers="hover focus">
      <template #title>도움말</template>
      <div>읍/면/리의 경우 데이터가</div>
      <div>조회되지 않을 수 있습니다.</div>
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

<style scoped>
.popover {
  max-width: 270px !important;
  width: 270px !important;
}

h6 {
  font-weight: bold;
}
</style>
