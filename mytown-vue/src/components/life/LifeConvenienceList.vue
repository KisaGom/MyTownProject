<template>
  <div>
    <b-table
      v-if="items"
      hover
      fixed
      small
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
export default {
  data() {
    return {
      fields: [
        { key: "place_name", label: "시설명" },
        { key: "category_name", label: "카테고리" },
      ],
      items: [],
    };
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
  border: 1px solid rgba(0, 0, 0, 0.15);
  padding: 3px 1px;
  margin: 2px;
  text-align: center;
  cursor: pointer;
}
#category li.on {
  background: #eee;
}
#category {
  padding: 0px 9px;
  margin-bottom: 8px;
}
/* #category li:last-child {
  margin-right: 0;
} */
</style>
