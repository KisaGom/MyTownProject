<template>
  <div>
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
        @change="doSearch"
      ></b-form-select>
    </b-input-group>
    <b-button block squared variant="light" v-b-modal.writeModal
      >방명록 작성하기</b-button
    >
    <div v-if="items && items.length != 0">
      <b-table
        striped
        borderless
        outlined
        small
        hover
        fixed
        head-variant="dark"
        :items="items"
        :fields="fields"
      >
        <template #cell(show_details)="row">
          <b-button size="sm" @click="row.toggleDetails">
            댓글 {{ row.detailsShowing ? "숨기기" : "보기" }}
          </b-button>
        </template>

        <template #row-details="row">
          <b-card>
            <board-list-comment :board-id="row.item.id"></board-list-comment>
          </b-card>
        </template>
      </b-table>
    </div>
    <div v-else>아직 이 지역의 방명록이 존재하지 않아요!</div>
    <b-modal id="writeModal" title="방명록 작성">
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <b-form-group
          label="Name"
          label-for="name-input"
          invalid-feedback="내용을 입력해주세요!"
        >
          <b-form-input id="name-input" v-model="name" required></b-form-input>
        </b-form-group>
      </form>
    </b-modal>
  </div>
</template>

<script>
import BoardListComment from "@/components/community/BoardListComment.vue";
import { listBoard } from "@/api/board";
import { mapState, mapActions, mapMutations } from "vuex";
const houseStore = "houseStore";

export default {
  components: { BoardListComment },
  data() {
    return {
      fields: [
        //TODO user의 username으로 바꾸기
        { key: "userid", label: "작성자" },
        { key: "content", label: "내용" },
        { key: "regtime", label: "작성일" },
        { key: "like", label: "좋아요" },
        { key: "show_details", label: "댓글" },
      ],
      items: [],
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      name: "",
      submittedNames: [],
    };
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs"]),
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

    doSearch() {
      if (this.dongCode) {
        // console.log("called doSearch", this.gugunCode + this.dongCode);
        listBoard(
          this.gugunCode + this.dongCode,
          (response) => {
            this.items = response.data;
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },

    writeBoard() {},
  },
};
</script>

<style></style>
