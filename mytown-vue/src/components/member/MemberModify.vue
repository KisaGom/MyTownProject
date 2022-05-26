<template>
  <b-container class="bv-example-row mt-5">
    <b-row>
      <b-col />
      <b-col cols="8">
        <div class="card">
          <h3>Update Profile</h3>
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="hasError"
              >항목은 빈 항목일 수 없습니다.</b-alert
            >
            <b-form-group label="아이디">
              <b-form-input
                id="userid"
                required
                v-model="userid"
                placeholder="아이디"
                readonly
              ></b-form-input
            ></b-form-group>
            <b-form-group label="비밀번호">
              <b-form-input
                id="userpwd"
                required
                v-model="userpwd"
                placeholder="새로운 비밀번호"
              ></b-form-input
            ></b-form-group>
            <b-form-group label="이름">
              <b-form-input
                id="username"
                required
                v-model="username"
                placeholder="이름"
              ></b-form-input
            ></b-form-group>
            <b-form-group label="이메일">
              <b-form-input
                id="email"
                required
                v-model="email"
                placeholder="이메일"
              ></b-form-input>
            </b-form-group>

            <b-input-group v-show="!editmode">
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
                <b-button
                  type="button"
                  variant="primary"
                  @click="toggleEditMode"
                  >수정</b-button
                ></b-input-group-append
              >
            </b-input-group>
            <b-input-group v-show="editmode">
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
              ></b-form-select>
              <b-input-group-append>
                <b-button
                  type="button"
                  variant="primary"
                  @click="toggleEditMode"
                  >취소</b-button
                ></b-input-group-append
              >
            </b-input-group>
            <hr />
            <b-row class="text-center pt-3">
              <b-col @click="checkForm" style="cursor: pointer">수정하기</b-col>
              <b-col @click="goback" style="cursor: pointer">취소</b-col>
            </b-row>
          </b-form>
        </div>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import { getAddrByCode } from "@/api/baseAddr";
import { modify } from "@/api/member.js";
const houseStore = "houseStore";
const memberStore = "memberStore";
export default {
  name: "MemberModify",
  data() {
    return {
      sidoName: null,
      gugunName: null,
      dongName: null,

      userid: null,
      userpwd: null,
      username: null,
      email: null,

      editmode: false,
      sidoCode: null,
      gugunCode: null,
      dongCode: null,

      hasError: false,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs"]),
    ...mapState(memberStore, ["userInfo", "isLogin"]),
  },
  created() {
    this.userid = this.userInfo.userid;
    this.username = this.userInfo.username;
    this.email = this.userInfo.email;
    this.getAddr();
  },
  methods: {
    ...mapActions(houseStore, ["getSido", "getGugun", "getDong"]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
    ]),
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
    toggleEditMode() {
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
    checkForm() {
      if (
        !this.userpwd ||
        !this.username ||
        !this.email ||
        (this.editmode && !this.dongCode)
      ) {
        this.hasError = true;
        return;
      } else {
        this.hasError = false;
        this.onClickModify();
      }
    },
    onClickModify() {
      let user = {
        userid: this.userid,
        userpwd: this.userpwd,
        username: this.username,
        email: this.email,
        dongCode: this.userInfo.dongCode,
      };
      if (this.editmode) {
        user.dongCode = this.gugunCode + this.dongCode;
      }
      modify(
        user,
        (response) => {
          console.log(response);
          if (response.data === "success") {
            alert("회원 정보가 수정되었습니다. 다시 로그인 해주세요.");
            this.onModifiedLogout();
          } else {
            console.log("Fail");
          }
        },
        () => {}
      );
    },
    onModifiedLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      console.log(this.isLogin);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "signIn" });
    },
    goback() {
      this.$router.push("mypage");
    },
  },
};
</script>

<style>
div.card {
  margin-top: 100px;
  padding: 50px;
  border-radius: 0;
  background-color: #fff;
}

h3 {
  font-weight: bold;
  margin-bottom: 40px;
}
</style>
