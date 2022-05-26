<template>
  <div>
    <b-container>
      <!-- <b-tabs fill>
        <b-tab title="방명록" active> -->
      <div class="card">
        <router-view></router-view>
        <div v-if="userInfo != null"></div>
        <div v-else>
          <a href="/member/signin">로그인 후 우리 동네 방명록을 작성하세요!</a>
        </div>
      </div>
      <!-- </b-tab>
        <b-tab title="(임시)">
            <b-card-text>행사/관광 정보 또는 워드 클라우드</b-card-text>
          </b-tab>
      </b-tabs> -->
    </b-container>
  </div>
</template>

<script>
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "CommunityView",
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style scoped>
/* .container {
  margin-top: 20px;
} */

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
