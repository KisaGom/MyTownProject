import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "@/views/HomeView.vue";
import LifeView from "@/views/LifeView";
import MemberView from "@/views/MemberView.vue";
import MemberLogin from "@/components/member/MemberLogin.vue";
import MemberMyPage from "@/components/member/MemberMyPage";
import MemberRegister from "@/components/member/MemberRegister";
import MemberModify from "@/components/member/MemberModify";
import MemberLogout from "@/components/member/MemberLogout";
import CommunityView from "@/views/CommunityView";
import BoardList from "@/components/community/BoardList.vue";

import store from "@/store/index.js";
Vue.use(VueRouter);

const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    next({ name: "signIn" });
    // router.push({ name: "signIn" });
  } else {
    // console.log("로그인 했다.");
    next();
  }
};

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
    redirect: "/member/signin",
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
        beforeEnter: onlyAuthUser,
        component: MemberMyPage,
      },
      {
        path: "modify",
        name: "modify",
        beforeEnter: onlyAuthUser,
        component: MemberModify,
      },
      {
        path: "logout",
        name: "logout",
        component: MemberLogout,
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
        path: "list/:code",
        name: "board_list_by_code",
        component: BoardList,
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
