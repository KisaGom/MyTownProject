<template>
  <div>
    <nav-bar></nav-bar>
    <b-container>
      <b-card no-body>
        <b-tabs card>
          <b-tab title="방명록" active>
            <router-view></router-view>
            <div v-if="userInfo != null"></div>
            <div v-else>
              <a href="/member/signin"
                >로그인 후 우리 동네 방명록을 작성하세요!</a
              >
            </div>
          </b-tab>
          <b-tab title="(임시)">
            <b-card-text>행사/관광 정보 또는 워드 클라우드</b-card-text>
          </b-tab>
        </b-tabs>
      </b-card>
    </b-container>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "CommunityView",
  components: { NavBar },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style scoped>
.container {
  margin-top: 20px;
}
</style>
