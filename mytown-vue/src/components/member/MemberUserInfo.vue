<template>
  <b-tab title="회원 정보">
    <b-container class="mt-4" v-if="userInfo">
      <b-row> </b-row>
      <b-row>
        <b-col></b-col>
        <b-col cols="8">
          <b-jumbotron>
            <template #header>My Page</template>

            <template #lead> 내 정보 확인페이지입니다. </template>

            <hr class="my-4" />

            <b-container class="mt-4">
              <b-row>
                <b-col cols="2"></b-col>
                <b-col cols="3" align-self="end">아이디</b-col
                ><b-col cols="6" align-self="start">{{
                  userInfo.userid
                }}</b-col>
                <b-col cols="1"></b-col>
              </b-row>
              <b-row>
                <b-col cols="2"></b-col>
                <b-col cols="3" align-self="end">이름</b-col
                ><b-col cols="6" align-self="start">{{
                  userInfo.username
                }}</b-col>
                <b-col cols="1"></b-col>
              </b-row>
              <b-row>
                <b-col cols="2"></b-col>
                <b-col cols="3" align-self="end">지역</b-col
                ><b-col cols="6" v-if="!dongName" align-self="start">{{
                  userInfo.dongCode
                }}</b-col>
                <b-col cols="6" v-if="dongName" align-self="start"
                  >{{ sidoName }} {{ gugunName }} {{ dongName }}</b-col
                >
                <b-col cols="1"></b-col>
              </b-row>
              <b-row>
                <b-col cols="2"></b-col>
                <b-col cols="3" align-self="end">이메일</b-col
                ><b-col cols="6" align-self="start">{{ userInfo.email }}</b-col>
                <b-col cols="1"></b-col>
              </b-row>
              <b-row>
                <b-col cols="2"></b-col>
                <b-col cols="3" align-self="end">가입일</b-col
                ><b-col cols="6" align-self="start">{{
                  userInfo.regtime
                }}</b-col>
                <b-col cols="1"></b-col>
              </b-row>
            </b-container>
            <hr class="my-4" />
            <b-button
              variant="success"
              @click.prevent="onClickLogout"
              class="mr-1"
              >로그아웃</b-button
            >
            <b-button variant="primary" href="#" class="mr-1" @click="goModify"
              >정보수정</b-button
            >
            <b-button variant="danger" @click="onClickDelete"
              >회원탈퇴</b-button
            >
          </b-jumbotron>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
  </b-tab>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import { getAddrByCode } from "@/api/baseAddr";
import { deleteUser } from "@/api/member";

const memberStore = "memberStore";

export default {
  name: "MemberMyPage",
  data() {
    return {
      sidoName: null,
      gugunName: null,
      dongName: null,
    };
  },
  created() {
    this.getAddr();
  },
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    getAddr() {
      getAddrByCode(
        this.userInfo.dongCode,
        ({ data }) => {
          this.sidoName = data.sidoName;
          this.gugunName = data.gugunName;
          this.dongName = data.dongName;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_USER_INFO(null);
      this.SET_IS_LOGIN(false);
      console.log(this.isLogin);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "signIn" });
    },
    goModify() {
      this.$router.push("modify");
    },
    onClickDelete() {
      if (confirm("정말로 탈퇴하시겠습니까?")) {
        deleteUser(
          this.userInfo.userid,
          (response) => {
            console.log(response);
            if (response.data === "success") {
              alert("회원 정보가 삭제되었습니다.");
              this.onDeleteLogout();
            } else {
              console.log("Fail");
            }
          },
          () => {}
        );
      } else {
        return;
      }
    },
    onDeleteLogout() {
      this.SET_USER_INFO(null);
      this.SET_IS_LOGIN(false);
      console.log(this.isLogin);
      sessionStorage.removeItem("access-token");
      this.$router.push({ name: "home" });
    },
  },
};
</script>

<style></style>
