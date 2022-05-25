<template>
  <div>
    <b-container v-if="comms && comms.length != 0">
      <b-table
        fixed
        hover
        :items="this.comms"
        :fields="fields"
        :per-page="perPage"
        :current-page="currentPage"
        small
        @row-clicked="(item) => $set(item, '_showDetails', !item._showDetails)"
        ><template slot="row-details" slot-scope="row">
          <!-- {{ row.item }} -->
          <b-card>
            <b-row>
              <b-col cols="2"><b-icon icon="shop-window"></b-icon></b-col>
              <b-col v-if="row.item.storeName" cols="10">{{
                row.item.storeName
              }}</b-col>
              <b-col v-else cols="10">-</b-col>
            </b-row>
            <b-row>
              <b-col cols="2"><b-icon icon="geo-alt"></b-icon></b-col>
              <b-col v-if="row.item.streetAddress" cols="10">{{
                row.item.streetAddress
              }}</b-col>
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
    <b-container v-else><b-col>가게 정보가 없습니다.</b-col></b-container>
  </div>
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";

export default {
  name: "LifeCommercialList",
  components: {},
  data() {
    return {
      perPage: 15,
      currentPage: 1,
      fields: [
        { key: "storeName", label: "가게명" },
        { key: "storeType", label: "업종" },
      ],
    };
  },
  computed: {
    ...mapState(houseStore, ["comms"]),
    rows() {
      return this.comms.length;
    },
  },
};
</script>

<style></style>
