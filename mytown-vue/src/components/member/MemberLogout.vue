<template><div></div></template>

<script>
import { mapMutations } from "vuex";
const memberStore = "memberStore";
export default {
  created() {
    alert("로그아웃되었습니다.");
    this.SET_USER_INFO(null);
    this.SET_IS_LOGIN(false);
    console.log(this.isLogin);
    sessionStorage.removeItem("access-token");
    this.$router.push({ name: "life" });
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
  },
};
</script>

<style></style>
