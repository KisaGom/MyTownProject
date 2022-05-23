<template>
  <div>
    <b-table
      striped
      borderless
      outlined
      small
      hover
      head-variant="dark"
      :items="items"
      :fields="fields"
    ></b-table>
  </div>
</template>

<script>
import { listBoard } from "@/api/board";

export default {
  data() {
    return {
      fields: [
        //TODO user의 username으로 바꾸기
        { key: "userid", label: "작성자" },
        { key: "content", label: "내용" },
        { key: "regtime", lable: "작성일" },
      ],
      items: [],
    };
  },
  created() {
    listBoard(
      //TODO (지도, 현화면에서) b-form-select로 설정한 dongCode로 요청하게 바꾸기
      "2720010200",
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

<style></style>
