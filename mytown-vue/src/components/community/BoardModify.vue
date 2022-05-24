<template>
  <div>
    <b-form-input
      id="content"
      v-model="item.content"
      type="text"
      required
      placeholder="내용을 입력해주세요"
    ></b-form-input>
    <b-button block squared variant="light" @click="registBoard"
      >방명록 수정하기</b-button
    >
  </div>
</template>

<script>
import { modifyBoard } from "@/api/board";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  data() {
    return {
      item: {
        userid: "",
        content: "",
      },
    };
  },
  props: ["boardItem"],
  created() {
    console.log(this.boardItem, this.item);
    this.item = this.boardItem;
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    //게시글 수정
    modifyBoard() {
      this.item.userid = this.userInfo.userid;
      modifyBoard(this.item, () => {
        alert("방명록이 등록되었습니다");
        this.$router.push("/community");
      });
    },
  },
};
</script>

<style></style>
