import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "@/views/HomeView.vue";
import LifeView from "@/views/LifeView";
import MemberView from "@/views/MemberView.vue";
import MemberLogin from "@/components/member/MemberLogin.vue";
import MemberMyPage from "@/components/member/MemberMyPage";
import MemberRegister from "@/components/member/MemberRegister";
import MemberModify from "@/components/member/MemberModify";
import CommunityView from "@/views/CommunityView";
import BoardList from "@/components/community/BoardList.vue";
import BoardRegist from "@/components/community/BoardRegist";
import BoardModify from "@/components/community/BoardModify";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/life",
    name: "life",
    component: LifeView,
  },
  {
    path: "/member",
    name: "member",
    component: MemberView,
    children: [
      {
        path: "signin",
        name: "signIn",
        component: MemberLogin,
      },
      {
        path: "singup",
        name: "signUp",
        component: MemberRegister,
      },
      {
        path: "mypage",
        name: "mypage",
        component: MemberMyPage,
      },
      {
        path: "modify",
        name: "modify",
        component: MemberModify,
      },
    ],
  },
  {
    path: "/community",
    name: "community",
    component: CommunityView,
    redirect: "/community/list",
    children: [
      {
        path: "list",
        name: "board_list",
        component: BoardList,
      },
      {
        path: "regist",
        name: "board_regist",
        component: BoardRegist,
      },
      {
        path: "modify/:id",
        name: "board_modify",
        component: BoardModify,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
