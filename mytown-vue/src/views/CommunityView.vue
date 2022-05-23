<template>
  <div>
    <nav-bar></nav-bar>
    <b-container>
      <b-card no-body>
        <b-tabs card>
          <b-tab title="방명록" active>
            지역별 방명록
            <board-list></board-list>
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
import BoardList from "@/components/community/BoardList.vue";

export default {
  name: "CommunityView",
  components: { NavBar, BoardList },
};
</script>

<style scoped>
.container {
  margin-top: 20px;
}
</style>
