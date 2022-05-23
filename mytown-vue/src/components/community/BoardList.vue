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
        @row-clicked="(item) => $set(item, '_showDetails', !item._showDetails)"
      >
        <!-- <template #cell(userid)>
          어떻게 이름 맵핑해서 넣지..
        </template> -->

        <b-button block variant="light" v-b-modal.writeModal
          >방명록 작성하기</b-button
        >
        <template slot="row-details" slot-scope="row">
          <b-card>
            <board-list-comment :board-item="row.item"></board-list-comment>
          </b-card>
        </template>
      </b-table>
    </div>
    <div v-else>아직 이 동네의 방명록이 존재하지 않아요!</div>

    <div v-if="userInfo != null">
      <b-button block squared variant="light" v-b-modal.writeModal
        >방명록 작성하기</b-button
      >
    </div>
    <div v-else>
      <a href="/member/signin">로그인 후 우리 동네 방명록을 작성하세요!</a>
    </div>

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
const memberStore = "memberStore";

export default {
  components: { BoardListComment },
  data() {
    return {
      fields: [
        //TODO user의 username으로 바꾸기
        { key: "userid", label: "작성자" },
        { key: "content", label: "내용" },
        { key: "regtime", label: "작성일" },
      ],
      items: [],
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      //방명록 작성(수정 필요)
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
  },
};
</script>

<style></style>
