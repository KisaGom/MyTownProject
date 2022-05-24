<template>
  <b-tab title="관심 지역">
    <b-container class="mt-4">
      <b-row> </b-row>
      <b-row>
        <b-col></b-col>
        <b-col cols="8">
          <b-jumbotron>
            <template #header>My Page</template>

            <template #lead> 내 정보 확인페이지입니다. </template>

            <hr class="my-4" />
            <b-input-group>
              <b-form-select
                v-model="sidoCode"
                :options="sidos"
                @change="gugunList"
              ></b-form-select>
              <b-form-select
                v-model="gugunCode"
                :options="guguns"
                @change="dongList"
              ></b-form-select>
              <b-form-select
                v-model="dongCode"
                :options="dongs"
              ></b-form-select>
              <b-input-group-append
                ><b-button>추가</b-button></b-input-group-append
              >
            </b-input-group>
            <hr class="my-4" />
          </b-jumbotron>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
  </b-tab>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
const houseStore = "houseStore";
const memberStore = "memberStore";
export default {
  name: "MemberUserFavorite",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
    };
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_DONG_LIST();
    this.getSido();
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(houseStore, ["getSido", "getGugun", "getDong"]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
    ]),
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.CLEAR_DONG_LIST();
      this.gugunCode = null;
      this.dongCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
  },
};
</script>

<style></style>
