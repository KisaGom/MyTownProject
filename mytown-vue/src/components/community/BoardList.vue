<template>
  <div>
    <b-input-group v-if="!editmode">
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
        <b-button type="button" @click="toggleEditMode"
          >변경</b-button
        ></b-input-group-append
      >
    </b-input-group>
    <b-input-group v-else>
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
        <!-- 댓글 테이블 -->
        <template slot="row-details" slot-scope="row">
          <b-card>
            <comment-list :board-item="row.item"></comment-list>
            <span v-if="userInfo != null && userInfo.userid == row.item.userid">
              <b-dropdown
                id="board-modify"
                size="sm"
                offset="-200"
                text="수정"
                ref="dropdown"
              >
                <b-dropdown-form style="width: 30rem">
                  <b-form-group label-for="board-modify" @submit.stop.prevent>
                    <b-form-input
                      id="board-modify"
                      size="sm"
                      v-model="row.item.content"
                      type="text"
                      required
                      placeholder="내용을 입력해주세요"
                    ></b-form-input>
                    <b-button
                      variant="primary"
                      size="sm"
                      @click="modifyBoard(row.item)"
                      >수정</b-button
                    >
                  </b-form-group>
                </b-dropdown-form>
              </b-dropdown>
              <b-button size="sm" @click="deleteBoard(row.item.id)"
                >삭제</b-button
              >
            </span>
            <!-- 댓글 달기 dropdown 버튼 -->
            <span v-if="userInfo != null">
              <b-dropdown
                id="comment-register"
                size="sm"
                offset="-200"
                text="댓글 달기"
                ref="dropdown"
              >
                <b-dropdown-form style="width: 30rem">
                  <b-form-group
                    label-for="comment-register"
                    @submit.stop.prevent
                  >
                    <b-form-input
                      id="comment-register"
                      size="sm"
                      v-model="comment.content"
                      type="text"
                      required
                      placeholder="내용을 입력해주세요"
                    ></b-form-input>
                    <b-button
                      variant="primary"
                      size="sm"
                      @click="registComment(row.item.id, row.item)"
                      >댓글 등록</b-button
                    >
                  </b-form-group>
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
    <b-dropdown
      id="board-register"
      block
      size="sm"
      text="우리 동네 방명록 작성하기"
      ref="dropdown"
      v-if="this.userInfo"
    >
      <b-dropdown-form style="width: 40rem">
        <b-form-group label-for="board-register" @submit.stop.prevent>
          <b-form-input
            id="board-register"
            size="sm"
            v-model="board.content"
            type="text"
            required
            placeholder="내용을 입력해주세요"
          ></b-form-input>
          <b-button variant="primary" size="sm" @click="registBoard"
            >{{ user.sidoName }} {{ user.gugunName }} {{ user.dongName }} 방명록
            작성하기</b-button
          >
        </b-form-group>
      </b-dropdown-form>
    </b-dropdown>
  </div>
</template>

<script>
import CommentList from "@/components/community/CommentList.vue";
import { getAddrByCode } from "@/api/baseAddr";
// import { findById } from "@/api/member";
import {
  listBoard,
  listDongBoard,
  registBoard,
  modifyBoard,
  deleteBoard,
} from "@/api/board";
import { registComment } from "@/api/comment";
import { mapState, mapActions, mapMutations } from "vuex";
const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  components: { CommentList },
  data() {
    return {
      sidoName: null,
      gugunName: null,
      dongName: null,
      editmode: false,
      perPage: 15,
      currentPage: 1,
      fields: [
        { key: "username", label: "작성자" },
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
      board: {
        userid: "",
        content: "",
      },
      user: {
        sidoName: null,
        gugunName: null,
        dongName: null,
      },
    };
  },
  created() {
    this.getAddr();
    this.CLEAR_SIDO_LIST();
    this.getSido();
    if (this.$route.params.code != undefined) {
      //url로 넘겨준 동코드 있으면 거기로 이동
      // console.log("board list param", this.$route.params.code);
      listDongBoard(this.$route.params.code, ({ data }) => {
        this.items = data;
      });
    } else if (this.userInfo) {
      //로그인 정보 있으면 사용자 지역 게시글 보여주기
      listDongBoard(this.userInfo.dongCode, ({ data }) => {
        this.items = data;
      });
    } else {
      //로그인 정보 없으면 모든 게시글 보여주기
      listBoard(({ data }) => {
        this.items = data;
      });
    }
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
    getAddr() {
      if (this.userInfo) {
        //시군구동 초기값 사용자 정보에서 가져오기
        // console.log("this.userinfo", this.userInfo);
        getAddrByCode(this.userInfo.dongCode, ({ data }) => {
          this.sidoName = data.sidoName;
          this.gugunName = data.gugunName;
          this.dongName = data.dongName;
          this.user.sidoName = data.sidoName;
          this.user.gugunName = data.gugunName;
          this.user.dongName = data.dongName;
        });
      } else {
        getAddrByCode(this.$route.params.dongCode, ({ data }) => {
          this.sidoName = data.sidoName;
          this.gugunName = data.gugunName;
          this.dongName = data.dongName;
        });
      }
      if (this.$route.params.code != undefined) {
        //시군구동 초기값 url param에서 가져오기
        console.log("board list param", this.$route.params.code);
        getAddrByCode(this.$route.params.code, ({ data }) => {
          this.sidoName = data.sidoName;
          this.gugunName = data.gugunName;
          this.dongName = data.dongName;
        });
      }
    },
    //시군구동 선택할 수 있게 전환
    toggleEditMode() {
      // console.log(this.editmode);
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

    //시군구동 선택
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
        listDongBoard(this.gugunCode + this.dongCode, (response) => {
          this.items = response.data;
        });
      }
    },

    //게시글 등록
    registBoard() {
      // this.$router.push("/community/regist");
      this.board.userid = this.userInfo.userid;
      registBoard(this.board, () => {
        alert("방명록이 등록되었습니다");
        listBoard(this.userInfo.dongCode, (response) => {
          this.board.content = "";
          this.$refs.dropdown.hide(true);
          this.items = response.data;
        });
      });
    },

    //게시물 수정
    modifyBoard(item) {
      // console.log("modified item", item);
      modifyBoard(item, () => {
        alert("방명록이 수정되었습니다");
        listBoard(this.userInfo.dongCode, (response) => {
          this.items = response.data;
        });
      });
    },

    //게시물 삭제
    deleteBoard(id) {
      // console.log(id);
      if (confirm("게시물을 삭제할까요?")) {
        deleteBoard(id, () => {
          alert("게시물이 삭제되었습니다");
          listBoard(this.userInfo.dongCode, (response) => {
            this.items = response.data;
          });
        });
      }
    },

    //댓글 등록
    registComment(board_id, item) {
      this.comment.userid = this.userInfo.userid;
      this.comment.board_id = board_id;
      registComment(this.comment, () => {
        this.comment.content = "";
        item._showDetails = false;
        // Close the menu and (by passing true) return focus to the toggle button
        this.$refs.dropdown.hide(true);
        alert("댓글이 등록되었습니다");
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

.input-group .custom-select {
  border-radius: 0 !important;
}
.input-group .form-control {
  border-radius: 0 !important;
}
.input-group button {
  border-radius: 0 !important;
}
.input-group {
  margin-bottom: 10px;
}
</style>
