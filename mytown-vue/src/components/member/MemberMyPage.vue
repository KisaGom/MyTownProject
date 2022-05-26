<template>
  <b-container class="mt-5">
    <b-tabs fill>
      <member-user-info></member-user-info>
      <member-user-favorite
        v-if="userInfo.userid != 'admin'"
      ></member-user-favorite>
      <member-user-manager
        v-if="userInfo.userid === 'admin'"
      ></member-user-manager>
    </b-tabs>
  </b-container>
</template>

<script>
import MemberUserInfo from "@/components/member/MemberUserInfo.vue";
import MemberUserFavorite from "@/components/member/MemberUserFavorite.vue";
import MemberUserManager from "@/components/member/MemberUserManager.vue";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  components: {
    MemberUserInfo,
    MemberUserFavorite,
    MemberUserManager,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style></style>
