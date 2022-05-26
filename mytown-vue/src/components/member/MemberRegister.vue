<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col></b-col>
      <b-col cols="8" lg="8" md="10">
        <div class="card">
          <h3>Sign up</h3>
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="hasError"
              >항목은 빈 항목일 수 없습니다.</b-alert
            >
            <b-form-group>
              <b-form-input
                id="userid"
                required
                v-model="userid"
                class="mb-3"
                placeholder="아이디"
              ></b-form-input>
              <b-form-input
                id="userpwd"
                required
                v-model="userpwd"
                class="mb-3"
                placeholder="비밀번호"
              ></b-form-input>
              <b-form-input
                id="username"
                required
                v-model="username"
                class="mb-3"
                placeholder="이름"
              ></b-form-input>
              <b-form-input
                id="username"
                required
                v-model="email"
                placeholder="이메일"
              ></b-form-input>
            </b-form-group>
            <b-input-group class="mb-4">
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
            <hr />
            <b-row class="text-center pt-3">
              <b-col @click="checkForm" style="cursor: pointer">회원가입</b-col>
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

<style scoped>
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
