<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="info">
      <b-navbar-brand
        ><router-link to="/life">NavBar</router-link></b-navbar-brand
      >

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item
            ><router-link to="/life">동네정보</router-link></b-nav-item
          >
          <b-nav-item
            ><router-link to="/community">커뮤니티</router-link></b-nav-item
          >
          <b-nav-item
            ><router-link to="/member" v-if="!this.userInfo"
              >로그인</router-link
            ></b-nav-item
          >
          <b-nav-item
            ><router-link to="/member/mypage" v-if="this.userInfo"
              >내정보</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto">
          <!-- 로그인, 로그아웃 등 -->
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style></style>
