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

        <b-button block variant="light" @click="registBoard"
          >방명록 작성하기</b-button
        >
        <template slot="row-details" slot-scope="row">
          <b-card>
            <comment-list :board-item="row.item"></comment-list>
            <b-button-group size="sm">
              <div
                v-if="userInfo != null && userInfo.userid == row.item.userid"
              >
                <b-button @click="modifyBoard" :board-item="row.item"
                  >수정</b-button
                >
                <b-button @click="deleteBoard(row.item.id)">삭제</b-button>
              </div>
              <div v-if="userInfo != null">
                <b-button>댓글 달기</b-button>
              </div>
            </b-button-group>
          </b-card>
        </template>
      </b-table>
    </div>
    <div v-else>아직 이 동네의 방명록이 존재하지 않아요!</div>
    <b-button block squared variant="light" @click="registBoard"
      >방명록 작성하기</b-button
    >
  </div>
</template>

<script>
import CommentList from "@/components/community/CommentList.vue";
import { listBoard, deleteBoard } from "@/api/board";
import { mapState, mapActions, mapMutations } from "vuex";
const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  components: { CommentList },
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

    //게시글 등록 컴포넌트로 이동
    registBoard() {
      this.$router.push("/community/regist");
    },

    //게시물 수정 컴포넌트로 이동
    modifyBoard() {
      this.$router.push("/community/modify");
    },

    //게시물 삭제
    deleteBoard(id) {
      console.log(id);
      if (confirm("게시물을 삭제할까요?")) {
        deleteBoard(id, () => {
          alert("게시물이 삭제되었습니다");
          this.doSearch();
        });
      }
    },

    //
  },
};
</script>

<style></style>
