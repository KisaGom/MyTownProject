<template>
  <div>
    <div>{{ this.dongCode }}</div>
  </div>
</template>

<script>
import {
  avgDealAmount,
  dealCount,
  avgMRentAmount,
  avgTRentAmount,
  rentCount,
  avgAge,
} from "@/api/mapinfoapi.js";

export default {
  data() {
    return {
      avgDealAmountItem: null,
      dealCountItem: null,
      avgMRentAmountItem: null,
      avgTRentAmountItem: null,
      rentCountItem: null,
      avgAgeItem: null,
      dongCode: null,
    };
  },
  methods: {
    getDealCount(dongCode) {
      dealCount(dongCode, (response) => {
        this.avgDealAmountItem = response.data;
      });
    },
    getAvgDealAmount(dongCode) {
      avgDealAmount(dongCode, (response) => {
        this.dealCountItem = response.data;
      });
    },
    getAvgMRentAmount(dongCode) {
      avgMRentAmount(dongCode, (response) => {
        this.avgMRentAmountItem = response.data;
      });
    },
    getAvgTRentAmount(dongCode) {
      avgTRentAmount(dongCode, (response) => {
        this.avgTRentAmountItem = response.data;
      });
    },
    getRentCount(dongCode) {
      rentCount(dongCode, (response) => {
        this.rentCountItem = response.data;
      });
    },
    getAvgAge(dongCode) {
      avgAge(dongCode, (response) => {
        this.avgAgeItem = response.data;
      });
    },
    showStats(dongCode) {
      this.getAvgDealAmount(dongCode);
      this.getDealCount(dongCode);
      this.getAvgMRentAmount(dongCode);
      this.getAvgTRentAmount(dongCode);
      this.getRentCount(dongCode);
      this.getAvgAge(dongCode);
    },
  },
};
</script>

<style></style>
