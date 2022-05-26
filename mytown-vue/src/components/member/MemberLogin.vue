<template>
  <b-container class="bv-example-row mt-5">
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-form class="text-center">
          <div class="card">
            <h3>Login</h3>
            <div class="text-left">
              <b-alert show variant="danger" v-if="isLoginError"
                >아이디 또는 비밀번호를 확인하세요.</b-alert
              >
              <b-form-group label="아이디">
                <b-form-input
                  id="userid"
                  v-model="user.userid"
                  placeholder="아이디"
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>
              <b-form-group label="비밀번호">
                <b-form-input
                  type="password"
                  id="userpwd"
                  v-model="user.userpwd"
                  placeholder="비밀번호"
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>
            </div>
            <b-button block type="button" class="py-2 my-3" @click="confirm"
              >로그인</b-button
            >
            <hr />
            <b-row class="text-center pt-3">
              <b-col @click="movePage" style="cursor: pointer">회원가입</b-col>
              <b-col v-b-modal.modal-1>비밀번호 찾기</b-col>
            </b-row>
          </div>
        </b-form>
      </b-col>
      <b-col></b-col>
    </b-row>

    <b-modal id="modal-1" title="비밀번호 찾기" centered hide-footer>
      <b-container>
        <b-row>
          <b-col class="text-center mt-5 mb-5">
            <b-icon icon="lock" scale="5"></b-icon>
          </b-col>
        </b-row>
        <b-row
          ><b-col v-if="!foundPwd" class="text-center mt-2 mb-4"
            >아이디와 이메일 주소를 입력해주세요.</b-col
          ></b-row
        >

        <b-row
          ><b-col class="text-center mt-2 mb-4" v-if="foundPwd"
            >{{ keyId }}님의 비밀번호는 {{ foundPwd }}입니다.</b-col
          ></b-row
        >
        <b-row>
          <b-form-input
            name="user_id"
            v-model="keyId"
            required
            placeholder="아이디 입력"
          ></b-form-input>
          <b-form-input
            name="user_email"
            v-model="keyEmail"
            type="email"
            class="mt-3"
            required
            placeholder="이메일 입력"
          ></b-form-input>
        </b-row>
        <b-row>
          <b-button
            block
            type="button"
            variant="success"
            class="mt-3"
            @click="onClickFindPwd"
            >전송</b-button
          >
        </b-row>
      </b-container>
    </b-modal>
  </b-container>
</template>
<script>
import { mapState, mapActions } from "vuex";
import { findPwd } from "@/api/member";

const memberStore = "memberStore";

export default {
  name: "MemberLogin",
  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
        hasError: false,
      },
      keyId: null,
      keyEmail: null,
      foundPwd: null,
    };
  },
  created() {},
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  mounted() {
    this.$root.$on("bv::modal::hidden", (bvEvent, modalId) => {
      console.log("hide!", bvEvent, modalId);
      this.keyId = null;
      this.keyEmail = null;
      this.foundPwd = null;
    });
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "life" });
      }
    },
    movePage() {
      this.$router.push({ name: "signUp" });
    },
    onClickFindPwd() {
      let finder = {
        userid: this.keyId,
        email: this.keyEmail,
      };
      findPwd(
        finder,
        (response) => {
          console.log(response);
          if (response.data != "fail") {
            this.foundPwd = response.data;
          } else {
            console.log("Fail");
          }
        },
        () => {}
      );
    },
  },
};
</script>

<style scoped>
b-form-input {
  background-color: transparent;
}

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
