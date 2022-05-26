<template>
  <b-tab title="유저 관리">
    <b-container v-if="userInfo.userid === 'admin'">
      <b-row> </b-row>
      <b-row>
        <b-col></b-col>
        <b-col cols="8" lg="8" md="10">
          <div class="card">
            <h3>ADMIN Page</h3>

            <h6>유저 정보 관리 페이지입니다.</h6>

            <b-table
              class="mt-3"
              hover
              fixed
              :items="items"
              :fields="fields"
              small
            >
              <template #cell(del)>
                <b-icon icon="x-circle" style="cursor: pointer"></b-icon>
              </template>
            </b-table>
          </div>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
  </b-tab>
</template>

<script>
import { listUser } from "@/api/member";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "MemberUserManager",
  data() {
    return {
      items: null,
      fields: [
        { key: "userid", label: "아이디" },
        { key: "username", label: "이름" },
        { key: "email", label: "이메일" },
        { key: "dongCode", label: "법정동 코드" },
        { key: "regtime", label: "가입일" },
      ],
    };
  },
  created() {
    this.getList();
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    getList() {
      listUser(
        (response) => {
          this.items = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style scoped>
div.card {
  margin-top: 50px;
  padding: 50px;
  border-radius: 0;
  background-color: #fff;
}
h3 {
  font-weight: bold;
}
</style>
