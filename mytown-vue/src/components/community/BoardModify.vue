<template>
  <div>
    <b-form-input
      id="content"
      v-model="item.content"
      type="text"
      required
      placeholder="내용을 입력해주세요"
    ></b-form-input>
    <b-button block squared variant="light" @click="modifyBoard"
      >방명록 수정하기</b-button
    >
  </div>
</template>

<script>
import { detailBoard, modifyBoard } from "@/api/board";
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
  created() {
    detailBoard(this.$route.params.id, (response) => {
      this.item = response.data;
      //   console.log("item", this.item);
    });
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    //게시글 수정
    modifyBoard() {
      //   console.log("modified item", this.item);
      modifyBoard(this.item, () => {
        alert("방명록이 수정되었습니다");
        this.$router.push("/community");
      });
    },
  },
};
</script>

<style></style>
