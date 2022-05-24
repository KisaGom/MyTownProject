<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
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
              ></b-form-input>
              <b-form-input
                id="userpwd"
                required
                v-model="userpwd"
                class="mt-3"
                placeholder="비밀번호"
              ></b-form-input>
              <b-form-input
                id="username"
                required
                v-model="username"
                class="mt-3"
                placeholder="이름"
              ></b-form-input>
              <b-form-input
                id="username"
                required
                v-model="email"
                class="mt-3"
                placeholder="이메일"
              ></b-form-input>
            </b-form-group>
            <b-input-group>
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
            </b-input-group>
            <b-button
              block
              type="button"
              variant="primary"
              class="mt-3"
              @click="checkForm"
              >회원가입</b-button
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
import { register } from "@/api/member.js";
const houseStore = "houseStore";
export default {
  name: "MemberRegister",
  data() {
    return {
      userid: null,
      userpwd: null,
      username: null,
      email: null,
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      hasError: false,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
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
    checkForm() {
      if (
        this.sidoCode &&
        this.dongCode &&
        this.gugunCode &&
        this.userid &&
        this.userpwd &&
        this.username &&
        this.email
      ) {
        this.hasError = false;
        console.log("no error");
        this.onClickRegister();
      } else this.hasError = true;
    },
    onClickRegister() {
      console.log(this.dongCode);
      let user = {
        userid: this.userid,
        userpwd: this.userpwd,
        username: this.username,
        email: this.email,
        dongCode: this.gugunCode + this.dongCode,
      };
      console.log(user);
      register(
        user,
        (response) => {
          console.log(response);
          if (response.data === "success") {
            console.log("Success");
            this.$router.push({ name: "signIn" });
          } else {
            console.log("Fail");
          }
        },
        () => {}
      );
    },
    goback() {
      this.$router.push({ name: "signIn" });
    },
  },
};
</script>

<style></style>
