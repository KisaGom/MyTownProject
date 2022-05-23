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
      <b-table
        hover
        fixed
        :items="this.houses"
        :per-page="perPage"
        :current-page="currentPage"
        :fields="fields"
        small
      >
      </b-table>
      <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="my-table"
        align="center"
        limit="3"
      ></b-pagination>
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
      perPage: 15,
      currentPage: 1,
      fields: [
        { key: "apartmentName", label: "아파트" },
        { key: "dealAmount", label: "거래가", sortable: "true" },
      ],
    };
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
    rows() {
      return this.houses.length;
    },
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
