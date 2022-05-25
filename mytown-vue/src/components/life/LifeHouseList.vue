<template>
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
        @row-clicked="(item) => $set(item, '_showDetails', !item._showDetails)"
        ><template slot="row-details" slot-scope="row">
          <!-- {{ row.item }} -->
          <b-card>
            <b-row>
              <b-col cols="2"><b-icon icon="building"></b-icon></b-col>
              <b-col v-if="row.item.apartmentName" cols="10">{{
                row.item.apartmentName
              }}</b-col>
              <b-col v-else cols="10">-</b-col>
            </b-row>
            <b-row>
              <b-col cols="2"><b-icon icon="calendar2-day"></b-icon></b-col>
              <b-col v-if="row.item.dealYear" cols="10"
                >{{ row.item.dealYear }}년 {{ row.item.dealMonth }}월
                {{ row.item.dealDay }}일</b-col
              >
              <b-col v-else cols="10">-</b-col>
            </b-row>
            <b-row>
              <b-col cols="2"><b-icon icon="cash-coin"></b-icon></b-col>
              <b-col v-if="row.item.dealAmount" cols="10"
                >{{ row.item.dealAmount }}만원</b-col
              >
              <b-col v-else cols="10">-</b-col>
            </b-row>
            <b-row>
              <b-col cols="2"><b-icon icon="fullscreen"></b-icon></b-col>
              <b-col v-if="row.item.area" cols="10"
                >{{ row.item.area }}m<sup>3</sup></b-col
              >
              <b-col v-else cols="10">-</b-col>
            </b-row>
            <b-row>
              <b-col cols="2"><b-icon icon="bar-chart-steps"></b-icon></b-col>
              <b-col v-if="row.item.dealAmount" cols="10"
                >{{ row.item.floor }}층</b-col
              >
              <b-col v-else cols="10">-</b-col>
            </b-row>
          </b-card>
        </template>
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
