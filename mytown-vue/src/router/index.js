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
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
