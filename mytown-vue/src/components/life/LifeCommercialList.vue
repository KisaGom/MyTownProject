<template>
  <div>
    <b-container v-if="comms && comms.length != 0">
      <b-table
        stacked
        hover
        :items="this.comms"
        :fields="fields"
        small
      ></b-table>
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
      fields: [
        { key: "storeName", label: "가게명" },
        { key: "detailClass", label: "업종" },
        { key: "streetAddress", label: "주소" },
      ],
    };
  },
  computed: {
    ...mapState(houseStore, ["comms"]),
  },
};
</script>

<style></style>
