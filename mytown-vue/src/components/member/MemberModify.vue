<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col />
      <h2>회원 정보 수정</h2>
      <b-col
    /></b-row>
    <b-row>
      <b-col />
      <b-col cols="6">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="hasError"
              >항목은 빈 항목일 수 없습니다.</b-alert
            >
            <b-form-group>
              <b-form-input
                id="userid"
                required
                v-model="userid"
                placeholder="아이디"
                readonly
              ></b-form-input>
              <b-form-input
                id="userpwd"
                required
                v-model="userpwd"
                class="mt-3"
                placeholder="새로운 비밀번호"
              ></b-form-input>
              <b-form-input
                id="username"
                required
                v-model="username"
                class="mt-3"
                placeholder="이름"
              ></b-form-input>
              <b-form-input
                id="email"
                required
                v-model="email"
                placeholder="이메일"
                class="mt-3"
              ></b-form-input>
            </b-form-group>

            <b-input-group class="mt-3" v-show="!editmode">
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
            <b-button
              block
              type="button"
              variant="primary"
              class="mt-3"
              @click="checkForm"
              >수정하기</b-button
            >
            <b-button
              block
              type="button"
              variant="secondary"
              class="mt-3"
              @click="goback"
              >취소</b-button
            >
          </b-form>
        </b-card>
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

<style></style>
