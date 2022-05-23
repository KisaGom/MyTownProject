<template>
  <div>
    <div>
      <div v-if="items && items.length != 0">
        <b-table striped hover fixed :items="items" :fields="fields"></b-table>
      </div>
      <div v-else>아직 이 방명록의 댓글이 존재하지 않아요!</div>
    </div>
  </div>
</template>

<script>
import { listComment } from "@/api/comment";

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
  props: ["boardId"],
  created() {
    listComment(
      this.boardId,
      (response) => {
        this.items = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style scoped>
.table {
  margin-bottom: 0;
}
</style>
