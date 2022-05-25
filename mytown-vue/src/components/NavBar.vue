<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-brand
        ><router-link to="/life"
          ><b-icon icon="house"></b-icon> MYTown</router-link
        ></b-navbar-brand
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
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto">
          <b-nav-item>
            <router-link to="/member" v-if="!this.userInfo"
              >로그인</router-link
            ></b-nav-item
          >
          <b-nav-item
            ><router-link to="/member/mypage" v-if="this.userInfo"
              >내정보</router-link
            ></b-nav-item
          ><b-nav-item>
            <router-link to="/member/logout" v-if="this.userInfo"
              >로그아웃</router-link
            ></b-nav-item
          >
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

<style>
/* nav {
  padding: 30px;
}*/

nav a {
  color: #fff;
  text-decoration: none !important;
}
nav a:hover {
  color: #fff;
  text-decoration: none !important;
}

nav {
  font-size: 0.9rem;
}
/*
nav a.router-link-exact-active {
  color: #42b983;
} */
</style>
