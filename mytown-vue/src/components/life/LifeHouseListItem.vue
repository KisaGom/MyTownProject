<template>
  <b-row
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <!-- <b-col cols="2" class="text-center align-self-center"> </b-col>
    <b-col cols="10" class="align-self-center"> -->
    <b-col cols="12">
      {{ house.apartmentName }} {{ house.dealAmount }} {{ house.dealYear }}.{{
        house.dealMonth
      }}.{{ house.dealDay }}
    </b-col>
  </b-row>
</template>

<script>
import { mapActions } from "vuex";

const houseStore = "houseStore";
export default {
  name: "LifeHouseListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    selectHouse() {
      this.detailHouse(this.house);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
