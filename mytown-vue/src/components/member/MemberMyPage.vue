<template>
  <b-tabs fill>
    <member-user-info></member-user-info>
    <member-user-favorite></member-user-favorite>
  </b-tabs>
</template>

<script>
import MemberUserInfo from "@/components/member/MemberUserInfo.vue";
import MemberUserFavorite from "./MemberUserFavorite.vue";
export default {
  components: {
    MemberUserInfo,
    MemberUserFavorite,
  },
};
</script>

<style></style>
