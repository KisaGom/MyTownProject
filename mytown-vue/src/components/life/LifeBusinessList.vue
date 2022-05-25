<template>
  <b-container>
    <b-card>
      <h6 v-if="this.dealAmountItem.address">
        {{ this.dealAmountItem.address }}
      </h6>
      <h6 v-else>-</h6>
      <hr />
      <b-row>
        <b-col cols="2"></b-col>
        <b-col>평균 거래가</b-col>
        <b-col v-if="this.dealAmountItem.avgDealAmount"
          >{{ this.dealAmountItem.avgDealAmount }}만원</b-col
        >
        <b-col v-else>-</b-col>
        <b-col cols="2"></b-col>
      </b-row>
      <hr />
      <b-row>
        <b-col cols="2"></b-col>
        <b-col>거래량</b-col>
        <b-col v-if="this.dealCountItem.count != 0"
          >{{ this.dealCountItem.count }}회</b-col
        >
        <b-col v-else>-</b-col>
        <b-col cols="2"></b-col>
      </b-row>
      <hr />
      <b-row>
        <b-col cols="2"></b-col>
        <b-col>평균 보증금</b-col>
        <b-col v-if="this.mrentAmountItem.avgDeposit"
          >{{ this.mrentAmountItem.avgDeposit }}만원</b-col
        >
        <b-col v-else>-</b-col>
        <b-col cols="2"></b-col>
      </b-row>
      <b-row>
        <b-col cols="2"></b-col>
        <b-col>평균 월세</b-col>
        <b-col v-if="this.mrentAmountItem.avgRentAmount"
          >{{ this.mrentAmountItem.avgRentAmount }}만원</b-col
        >
        <b-col v-else>-</b-col>
        <b-col cols="2"></b-col>
      </b-row>
      <hr />
      <b-row>
        <b-col cols="2"></b-col>
        <b-col>평균 전세금</b-col>
        <b-col v-if="this.trentAmountItem.avgDeposit"
          >{{ this.trentAmountItem.avgDeposit }}만원</b-col
        >
        <b-col v-else>-</b-col>
        <b-col cols="2"></b-col>
      </b-row>
      <hr />
      <b-row>
        <b-col cols="2"></b-col>
        <b-col>전월세 계약건</b-col>
        <b-col v-if="this.rentCountItem.count != 0"
          >{{ this.rentCountItem.count }}건</b-col
        >
        <b-col v-else>-</b-col>
        <b-col cols="2"></b-col>
      </b-row>
      <hr />
      <b-row>
        <b-col cols="2"></b-col>
        <b-col>평균 연령</b-col>
        <b-col v-if="this.ageItem.totalAvg"
          >{{ this.ageItem.totalAvg }}세</b-col
        >
        <b-col v-else>-</b-col>
        <b-col cols="2"></b-col>
      </b-row>
    </b-card>
  </b-container>
</template>

<script>
const infoStore = "infoStore";
import { mapState } from "vuex";
export default {
  data() {
    return {};
  },
  computed: {
    ...mapState(infoStore, [
      "dealAmountItem",
      "dealCountItem",
      "mrentAmountItem",
      "trentAmountItem",
      "rentCountItem",
      "ageItem",
    ]),
  },
  created() {},
  methods: {},
};
</script>

<style scoped>
.card-body {
  padding: 1.25rem 0;
}

h6 {
  font-weight: bold;
}
</style>
