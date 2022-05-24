<template>
  <div>
    <b-container
      ><b-row class="mt-2"
        ><b-col cols="11" /><b-icon icon="info-circle" id="popover1"></b-icon
      ></b-row>
      <b-alert v-b-toggle.collapse-1 class="mt-2" show variant="dark">
        <b-row class="pt-2"
          ><b-col cols="12">
            <h4 class="mb-2">구/군별 평균 세입 (억 원)</h4>
            <b-collapse id="collapse-1"
              ><b-row>
                <b-col cols="9" class="text-right">전국 평균</b-col
                ><b-col cols="3" class="text-left">9489.6</b-col></b-row
              ><b-row
                class="mt-2"
                v-for="(tc, index) in this.incomeItems"
                :key="index"
              >
                <!-- vfor 드가야할듯 -->
                <b-col cols="9" class="text-right"
                  >{{ tc.sidoName }} {{ tc.gugunName }}</b-col
                ><b-col cols="3" class="text-left">{{
                  tc.income
                }}</b-col></b-row
              ></b-collapse
            >
          </b-col></b-row
        ></b-alert
      >
      <b-alert v-b-toggle.collapse-2 class="mt-2" show variant="dark">
        <b-row class="pt-2"
          ><b-col cols="12">
            <h4 class="mb-2">동별 아파트 매매 거래</h4>
            <b-collapse id="collapse-2"
              ><b-row>
                <b-col cols="9" class="text-right">지역 단위 (법정동)</b-col
                ><b-col cols="3" class="text-left">거래량</b-col></b-row
              >
              <b-row>
                <b-col cols="9" class="text-right">전국 평균</b-col
                ><b-col cols="3" class="text-left">221</b-col></b-row
              ><b-row
                class="mt-2"
                v-for="(dc, index) in this.dealCountItems"
                :key="index"
              >
                <!-- vfor 드가야할듯 -->
                <b-col cols="9" class="text-right">{{ dc.addr }}</b-col
                ><b-col cols="3" class="text-left">{{ dc.count }}</b-col></b-row
              ></b-collapse
            >
          </b-col></b-row
        ></b-alert
      >
      <b-alert v-b-toggle.collapse-3 class="mt-2" show variant="dark">
        <b-row class="pt-2"
          ><b-col cols="12">
            <h4 class="mb-2">동별 아파트 전월세 (회)</h4>
            <b-collapse id="collapse-3"
              ><b-row>
                <b-col cols="9" class="text-right">전국 평균</b-col
                ><b-col cols="3" class="text-left">15.4</b-col></b-row
              ><b-row
                class="mt-2"
                v-for="(rc, index) in this.rentCountItems"
                :key="index"
              >
                <!-- vfor 드가야할듯 -->
                <b-col cols="9" class="text-right">{{ rc.sigunguName }}</b-col
                ><b-col cols="3" class="text-left">{{ rc.count }}</b-col></b-row
              ></b-collapse
            >
          </b-col></b-row
        ></b-alert
      >
      <b-alert v-b-toggle.collapse-4 class="mt-2" show variant="dark">
        <b-row class="pt-2"
          ><b-col cols="12">
            <h4 class="mb-2">지역별 평균 연령</h4>
            <b-collapse id="collapse-4"
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
        ></b-alert
      >
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
} from "@/api/statistics.js";
const memberStore = "memberStore";

export default {
  data() {
    return {
      dealCountItems: null,
      incomeItems: null,
      rentCountItems: null,
      ageAverageItems: null,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.getDealCount(this.userInfo.userid);
    this.getIncomeList(this.userInfo.userid);
    this.getRentCount(this.userInfo.userid);
    this.getAgeAverage(this.userInfo.userid);
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
    getRentCount(userid) {
      rentCountList(userid, (response) => {
        this.rentCountItems = response.data;
      });
    },
    getAgeAverage(userid) {
      ageAverageList(userid, (response) => {
        console.log("age", response);
        this.ageAverageItems = response.data;
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
</style>
