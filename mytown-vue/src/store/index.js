import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

import houseStore from "@/store/modules/houseStore.js";
import memberStore from "@/store/modules/memberStore.js";
import infoStore from "@/store/modules/infoStore.js";

const store = new Vuex.Store({
  modules: {
    houseStore,
    memberStore,
    infoStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
  state: {},
  getters: {},
  mutations: {},
  actions: {},
});

export default store;
