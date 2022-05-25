<template>
  <b-container>
    <b-card
      >{{ this.dealAmountItem.address }}
      <hr />
      평균 거래가(만 원) : {{ this.dealAmountItem.avgDealAmount }}
      <hr />
      거래량 : {{ this.dealCountItem.count }}
      <hr />
      평균 보증금 : {{ this.mrentAmountItem.avgDeposit }} <br />
      평균 월세 :
      {{ this.mrentAmountItem.avgRentAmount }}
      <hr />
      평균 전세금 : {{ this.trentAmountItem.avgDeposit }}
      <hr />
      전월세 계약건 : {{ this.rentCountItem.count }}
      <hr />
      평균 연령 : {{ this.ageItem.totalAvg }}
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

<style></style>
