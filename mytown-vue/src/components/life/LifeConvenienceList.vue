<template>
  <div>
    <b-table
      v-if="items"
      hover
      fixed
      :fields="fields"
      :items="items"
      @row-clicked="(item) => $set(item, '_showDetails', !item._showDetails)"
    >
      <template slot="row-details" slot-scope="row">
        <b-card>
          <!-- {{ row.item }} -->
          <b-row>
            <b-col cols="2"><b-icon icon="info-circle"></b-icon></b-col>
            <b-col v-if="row.item.place_name" cols="10">{{
              row.item.place_name
            }}</b-col>
            <b-col v-else cols="10">-</b-col>
          </b-row>
          <b-row>
            <b-col cols="2"><b-icon icon="geo-alt"></b-icon></b-col>
            <b-col v-if="row.item.road_address_name" cols="10">{{
              row.item.road_address_name
            }}</b-col>
            <b-col v-else cols="10">-</b-col>
          </b-row>
          <b-row>
            <b-col cols="2"><b-icon icon="telephone"></b-icon></b-col>
            <b-col v-if="row.item.phone" cols="10">{{ row.item.phone }}</b-col>
            <b-col v-else cols="10">-</b-col>
          </b-row>
          <b-row>
            <b-col cols="2"><b-icon icon="globe"></b-icon></b-col>
            <b-col v-if="row.item.place_url" cols="10">{{
              row.item.place_url
            }}</b-col>
            <b-col v-else cols="10">-</b-col>
          </b-row>
        </b-card>
      </template>
    </b-table>
    <div v-else>시설 정보가 없습니다.</div>
  </div>
</template>

<script>
// import { getAddrByCode, getAddrDetail } from "@/api/baseAddr";
// import { searchCategory } from "@/api/convenience";

export default {
  data() {
    return {
      fields: [
        { key: "place_name", label: "시설명" },
        { key: "category_name", label: "카테고리" },
      ],
      items: [],
      latlng: { x: 128.598409092694, y: 35.8383244008836 },
    };
  },
  props: ["gugunCode", "dongCode"],
  methods: {
    // searchCategory(category) {
    //   // console.log("dongCode", this.gugunCode, this.dongCode);
    //   getAddrByCode(this.gugunCode + this.dongCode, ({ data }) => {
    //     // console.log("addr", data);
    //     getAddrDetail(
    //       { query: data.sidoName + data.gugunName + data.dongName },
    //       ({ data }) => {
    //         // console.log("addr detail", data.documents[0]);
    //         if (data.documents[0]) {
    //           let x = data.documents[0].x,
    //             y = data.documents[0].y;
    //           let params = {
    //             category_group_code: category,
    //             x: x,
    //             y: y,
    //             radius: "2000",
    //             sort: "distance",
    //           };
    //           searchCategory(params, ({ data }) => {
    //             this.items = data.documents;
    //             // console.log("search list", this.items);
    //             // this.$emit("convlist");
    //           });
    //         }
    //       }
    //     );
    //   });
    // },
  },
};
</script>

<style>
#category {
  position: relative;
  padding: 0;
  background: #fff;
  font-size: 0.9rem;
  overflow: hidden;
  z-index: 2;
}
#category li {
  float: left;
  list-style: none;
  width: 70px;
  border-right: 1px solid rgba(0, 0, 0, 0.15);
  border-bottom: 1px solid rgba(0, 0, 0, 0.15);
  padding: 4px;
  text-align: center;
  cursor: pointer;
}
#category li.on {
  background: #eee;
}
#category li:hover {
  background: #0475f4;
}
#category li:last-child {
  margin-right: 0;
}
</style>
