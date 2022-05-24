<template>
  <div>
    <b-container
      ><b-row class="mt-2"
        ><b-col cols="11" /><b-icon icon="info-circle" id="popover1"></b-icon
      ></b-row>
      <b-alert v-b-toggle.collapse-1 class="mt-2" show variant="dark">
        <b-row class="pt-2"
          ><b-col cols="12">
            <h4 class="mb-2">지역 평균 세입 (억 원)</h4>
            <b-collapse id="collapse-1"
              ><b-row>
                <b-col cols="9" class="text-right">전국 평균</b-col
                ><b-col cols="3" class="text-left">18171</b-col></b-row
              ><b-row
                class="mt-2"
                v-for="(dc, index) in this.dealCounts"
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
      <b-alert v-b-toggle.collapse-2 class="mt-2" show variant="dark">
        <b-row class="pt-2"
          ><b-col cols="12">
            <h4 class="mb-2">동별 아파트 매매 거래(회)</h4>
            <b-collapse id="collapse-2"
              ><b-row>
                <b-col cols="9" class="text-right">전국 평균</b-col
                ><b-col cols="3" class="text-left">221</b-col></b-row
              ><b-row
                class="mt-2"
                v-for="(dc, index) in this.dealCounts"
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
    </b-container>

    <b-popover target="popover1" triggers="hover focus">
      <template #title>전국 평균</template>
      <b-table
        borderless
        id="natTable"
        block
        :items="natStats"
        stacked
        small
      ></b-table>
    </b-popover>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { dealCountList } from "@/api/statistics.js";
const memberStore = "memberStore";

export default {
  data() {
    return {
      natStats: [
        {
          "평균 세입": "18171억 원",
          "매매 거래": "221회",
          "전월세 거래": "18171",
        },
      ],
      dealCounts: null,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.getDealCount(this.userInfo.userid);
  },
  methods: {
    getDealCount(userid) {
      console.log(this.userInfo);
      dealCountList(userid, (response) => {
        this.dealCounts = response.data;
        console.log(this.dealCounts);
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
