<template>
  <div>
    <div>
      <div v-if="items && items.length != 0">
        <b-table striped hover fixed :items="items" :fields="fields"></b-table>
      </div>
      <div v-else>아직 이 방명록의 댓글이 존재하지 않아요!</div>
      <b-button-group size="sm">
        <div v-if="userInfo != null && userInfo.userid == boardItem.userid">
          <b-button>수정</b-button>
          <b-button @click="deleteBoard">삭제</b-button>
        </div>
        <div v-if="userInfo != null">
          <b-button>댓글 달기</b-button>
        </div>
      </b-button-group>
    </div>
  </div>
</template>

<script>
import { modifyBoard, deleteBoard } from "@/api/board";
import { listComment } from "@/api/comment";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  data() {
    return {
      fields: [
        //TODO user의 username으로 바꾸기
        { key: "userid", label: "작성자" },
        { key: "content", label: "내용" },
        { key: "regtime", label: "작성일" },
      ],
      items: [],
    };
  },
  props: ["boardItem"],
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    listComment(
      this.boardItem.id,
      (response) => {
        this.items = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    //게시물 수정
    modifyBoard() {
      modifyBoard(
        this.boardItem,
        (response) => {
          console.log(response);
        },
        (error) => {
          console.log(error);
        }
      );
    },

    //게시물 삭제
    deleteBoard() {
      if (confirm("게시물을 삭제할까요?")) {
        deleteBoard(this.boardItem.id, () => {
          alert("게시물이 삭제되었습니다");
          this.$forceUpdate();
        });
      }
    },
  },
};
</script>

<style scoped>
.table {
  margin-bottom: 0;
}
</style>
