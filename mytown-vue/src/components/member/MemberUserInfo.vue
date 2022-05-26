<template>
  <b-tab title="회원 정보">
    <b-container v-if="userInfo">
      <b-row>
        <b-col>
          <div class="card">
            <h3>My Page</h3>
            <h6>내 정보 확인 페이지입니다.</h6>
            <hr class="my-4" />
            <b-container class="my-4">
              <b-row class="mb-1">
                <b-col cols="2"></b-col>
                <b-col cols="3" align-self="end">아이디</b-col
                ><b-col cols="6" class="text-left">{{ userInfo.userid }}</b-col>
                <b-col cols="1"></b-col>
              </b-row>
              <b-row class="mb-1">
                <b-col cols="2"></b-col>
                <b-col cols="3" align-self="end">이름</b-col
                ><b-col cols="6" class="text-left">{{
                  userInfo.username
                }}</b-col>
                <b-col cols="1"></b-col>
              </b-row>
              <b-row class="mb-1">
                <b-col cols="2"></b-col>
                <b-col cols="3" align-self="end">지역</b-col
                ><b-col cols="6" v-if="!dongName" class="text-left">{{
                  userInfo.dongCode
                }}</b-col>
                <b-col cols="6" v-if="dongName" class="text-left"
                  >{{ sidoName }} {{ gugunName }} {{ dongName }}</b-col
                >
                <b-col cols="1"></b-col>
              </b-row>
              <b-row class="mb-1">
                <b-col cols="2"></b-col>
                <b-col cols="3" align-self="end">이메일</b-col
                ><b-col cols="6" class="text-left">{{ userInfo.email }}</b-col>
                <b-col cols="1"></b-col>
              </b-row>
              <b-row class="mb-1">
                <b-col cols="2"></b-col>
                <b-col cols="3" align-self="end">가입일</b-col
                ><b-col cols="6" class="text-left">{{
                  userInfo.regtime
                }}</b-col>
                <b-col cols="1"></b-col>
              </b-row>
            </b-container>
            <hr class="my-4" />
            <b-row class="text-center pt-3">
              <b-col @click.prevent="onClickLogout" style="cursor: pointer"
                >로그아웃</b-col
              >
              <b-col @click="goModify" style="cursor: pointer">정보수정</b-col>
              <b-col @click="onClickDelete" style="cursor: pointer"
                >회원탈퇴</b-col
              >
            </b-row>
          </div>
        </b-col>
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
      this.$router.push("logout");
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
              alert("탈퇴 되셨습니다. 이용해주셔서 감사합니다.");
              this.$router.push("logout");
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
