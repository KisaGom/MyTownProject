<template>
  <!-- <b-container v-if="houses && houses.length != 0" class="bv-example-row mt-3">
    <life-house-list-item
      v-for="(house, index) in houses"
      :key="index"
      :house="house"
    />
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container> -->
  <div>
    <b-container v-if="houses && houses.length != 0">
      <b-table hover :items="this.houses" :fields="fields" small></b-table>
    </b-container>
    <b-container v-else><b-col>거래 정보가 없습니다.</b-col></b-container>
  </div>
</template>

<script>
// 아파트 정보 호출하는 함수는 너무 간단해서 LifeToolbar에 툴바 단에서 호출하는 그대로 둠
// 다음에 아파트 정보 탭 is activated and dongcode is not null일때 조회하는걸로 수정하긴 해야할듯
import { mapState } from "vuex";

const houseStore = "houseStore";
export default {
  name: "LifeHouseList",
  components: {},
  data() {
    return {
      fields: [
        { key: "apartmentName", label: "아파트" },
        { key: "dealAmount", label: "거래가" },
        { key: "dealYear", label: "거래일" },
      ],
    };
  },

  computed: {
    ...mapState(houseStore, ["houses"]),
  },
};
</script>

<style>
table {
  font-size: 0.9rem;
}

.col {
  font-size: 0.9rem;
}
</style>
