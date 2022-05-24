<template>
  <div>
    <b-input-group v-show="!editmode">
      <b-form-input
        id="sido"
        required
        v-model="sidoName"
        readonly
      ></b-form-input>
      <b-form-input
        id="gugun"
        required
        v-model="gugunName"
        readonly
      ></b-form-input>
      <b-form-input
        id="dong"
        required
        v-model="dongName"
        readonly
      ></b-form-input>
      <b-input-group-append>
        <b-button type="button" variant="primary" @click="toggleEditMode"
          >수정</b-button
        ></b-input-group-append
      >
    </b-input-group>
    <b-input-group v-show="editmode">
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
      <b-input-group-append> </b-input-group-append>
    </b-input-group>
    <!-- 게시물 테이블 -->
    <div v-if="items && items.length != 0">
      <b-table
        id="board-table"
        striped
        borderless
        outlined
        small
        hover
        fixed
        head-variant="dark"
        :per-page="perPage"
        :current-page="currentPage"
        :items="items"
        :fields="fields"
        @row-clicked="(item) => $set(item, '_showDetails', !item._showDetails)"
      >
        <!-- <template #cell(userid)>
          어떻게 이름 맵핑해서 넣지..
        </template> -->

        <template slot="row-details" slot-scope="row">
          <!-- 댓글 테이블 -->
          <b-card>
            <comment-list :board-item="row.item"></comment-list>
            <!-- <b-button-group size="sm"> -->
            <span v-if="userInfo != null && userInfo.userid == row.item.userid">
              <b-button @click="modifyBoard(row.item.id)">수정</b-button>
              <b-button @click="deleteBoard(row.item.id)">삭제</b-button>
            </span>
            <!-- </b-button-group> -->

            <span v-if="userInfo != null">
              <b-dropdown id="dropdown-form" text="댓글 달기" ref="dropdown">
                <b-dropdown-form>
                  <b-form-group label-for="dropdown-form" @submit.stop.prevent>
                    <b-form-input
                      id="dropdown-form"
                      size="sm"
                      v-model="comment.content"
                      type="text"
                      required
                      placeholder="내용을 입력해주세요"
                    ></b-form-input>
                  </b-form-group>

                  <b-button
                    variant="primary"
                    size="sm"
                    @click="registComment(row.item.id, row)"
                    >댓글 등록</b-button
                  >
                </b-dropdown-form>
              </b-dropdown>
            </span>
          </b-card>
        </template>
      </b-table>
      <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="board-table"
        align="center"
        limit="3"
      ></b-pagination>
    </div>
    <div v-else>아직 이 동네의 방명록이 존재하지 않아요!</div>
    <b-button block variant="light" @click="registBoard"
      >방명록 작성하기</b-button
    >
  </div>
</template>

<script>
import CommentList from "@/components/community/CommentList.vue";
import { listBoard, deleteBoard } from "@/api/board";
import { registComment } from "@/api/comment";
import { mapState, mapActions, mapMutations } from "vuex";
const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  components: { CommentList },
  data() {
    return {
      editmode: false,
      perPage: 15,
      currentPage: 1,
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
      comment: {
        userid: "",
        content: "",
        board_id: "",
      },
      idx: 0,
    };
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs"]),
    ...mapState(memberStore, ["userInfo"]),
    rows() {
      return this.items.length;
    },
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

    toggleEditMode() {
      console.log(this.editmode);
      this.editmode = !this.editmode;
      if (this.editmode) {
        this.CLEAR_SIDO_LIST();
        this.CLEAR_GUGUN_LIST();
        this.CLEAR_DONG_LIST();
        this.sidoCode = null;
        this.dongCode = null;
        this.gugunCode = null;

        this.getSido();
      }
    },

    //게시글 등록 컴포넌트로 이동
    registBoard() {
      this.$router.push("/community/regist");
    },

    //게시물 수정 컴포넌트로 이동
    modifyBoard(id) {
      this.$router.push(`/community/modify/${id}`);
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

    //댓글 등록
    registComment(board_id, row) {
      this.comment.userid = this.userInfo.userid;
      this.comment.board_id = board_id;
      registComment(this.comment, () => {
        this.comment.content = "";
        row.item._showDetails = false;
        // Close the menu and (by passing true) return focus to the toggle button
        this.$refs.dropdown.hide(true);
        alert("댓글이 등록되었습니다");
        // this.$router.push(`/community?${this.idx++}`);
      });
    },
  },
};
</script>

<style>
/* 스크롤 안 보이게(스크롤바 없애기) */
body {
  -ms-overflow-style: none;
}
::-webkit-scrollbar {
  display: none;
}
</style>
