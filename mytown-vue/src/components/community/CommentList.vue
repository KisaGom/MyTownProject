<template>
  <div>
    <div>
      <div v-if="items && items.length != 0">
        <b-table striped hover fixed :items="items" :fields="fields">
          <template #cell(change)="row">
            <!-- <b-icon
              icon="pencil-square"
              style="cursor: pointer"
              @click="modifyComment(row.item)"
            ></b-icon> -->
            <b-dropdown
              id="dropdown-form"
              size="sm"
              right
              text="수정"
              ref="dropdown"
            >
              <b-dropdown-form style="width: 30rem">
                <b-form-group label-for="dropdown-form" @submit.stop.prevent>
                  <b-form-input
                    id="dropdown-form"
                    size="sm"
                    v-model="row.item.content"
                    type="text"
                    required
                    placeholder="내용을 입력해주세요"
                  ></b-form-input>
                  <b-button
                    variant="primary"
                    size="sm"
                    @click="modifyComment(row.item)"
                    >수정</b-button
                  >
                </b-form-group>
              </b-dropdown-form>
            </b-dropdown>
            <b-button size="sm" @click="deleteComment(row.item.id)"
              >삭제</b-button
            >

            <!-- <b-icon icon="blank"></b-icon>
            <b-icon
              icon="x-square"
              style="cursor: pointer"
              @click="deleteComment(row.item.id)"
            ></b-icon> -->
          </template>
        </b-table>
      </div>
      <div v-else>아직 이 방명록의 댓글이 존재하지 않아요!</div>
    </div>
  </div>
</template>

<script>
import { listComment, modifyComment, deleteComment } from "@/api/comment";
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
        { key: "change", label: "수정/삭제" },
      ],
      items: [],
    };
  },
  props: ["boardItem"],
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    listComment(this.boardItem.id, (response) => {
      this.items = response.data;
    });
  },
  methods: {
    //댓글 수정
    modifyComment(item) {
      console.log("called modifyComment", item);
      modifyComment(item, () => {
        this.$refs.dropdown.hide(true);
        alert("댓글이 수정되었습니다");
      });
    },

    //댓글 삭제
    deleteComment(id) {
      // console.log("called deleteComment");
      // console.log("parents", this.$parent);
      if (confirm("댓글을 삭제할까요?")) {
        deleteComment(id, () => {
          alert("댓글이 삭제되었습니다");
          listComment(this.boardItem.id, (response) => {
            this.items = response.data;
          });
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
