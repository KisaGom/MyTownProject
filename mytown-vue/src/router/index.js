import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "@/views/HomeView.vue";
import LifeView from "@/views/LifeView";
import MemberView from "@/views/MemberView.vue";
import MemberLogin from "@/components/user/MemberLogin.vue";
import MemberMyPage from "@/components/user/MemberMyPage";
import MemberRegister from "@/components/user/MemberRegister";

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
    path: "/user",
    name: "user",
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
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
