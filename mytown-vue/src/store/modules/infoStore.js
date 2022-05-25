import {
  avgDealAmount,
  dealCount,
  avgMRentAmount,
  avgTRentAmount,
  rentCount,
  avgAge,
} from "@/api/mapinfoapi.js";

const infoStore = {
  namespaced: true,
  state: {
    dealAmountItem: null,
    dealCountItem: null,
    mrentAmountItem: null,
    trentAmountItem: null,
    rentCountItem: null,
    ageItem: null,
  },
  getters: {},
  mutations: {
    SET_DEAL_AMOUNT: (state, dealAmountItem) => {
      state.dealAmountItem = dealAmountItem;
    },
    SET_DEAL_COUNT: (state, dealCountItem) => {
      state.dealCountItem = dealCountItem;
    },
    SET_MRENT_AMOUNT: (state, mrentAmountItem) => {
      state.mrentAmountItem = mrentAmountItem;
    },
    SET_TRENT_AMOUNT: (state, trentAmountItem) => {
      state.trentAmountItem = trentAmountItem;
    },
    SET_RENT_COUNT: (state, rentCountItem) => {
      state.rentCountItem = rentCountItem;
    },
    SET_AGE: (state, ageItem) => {
      state.ageItem = ageItem;
    },
  },
  actions: {
    async dealAmount({ commit }, dongCode) {
      await avgDealAmount(dongCode, (response) => {
        if (response) {
          commit("SET_DEAL_AMOUNT", response.data);
        }
      });
    },
    async dealCount({ commit }, dongCode) {
      await dealCount(dongCode, (response) => {
        if (response) {
          console.log(response.data);
          commit("SET_DEAL_COUNT", response.data);
        }
      });
    },
    async mrentAmount({ commit }, dongCode) {
      await avgMRentAmount(dongCode, (response) => {
        if (response) {
          console.log(response.data);
          commit("SET_MRENT_AMOUNT", response.data);
        }
      });
    },
    async trentAmount({ commit }, dongCode) {
      await avgTRentAmount(dongCode, (response) => {
        if (response) {
          console.log(response.data);
          commit("SET_TRENT_AMOUNT", response.data);
        }
      });
    },
    async rentCount({ commit }, dongCode) {
      await rentCount(dongCode, (response) => {
        if (response) {
          console.log(response.data);
          commit("SET_RENT_COUNT", response.data);
        }
      });
    },
    async getAvgAge({ commit }, dongCode) {
      await avgAge(dongCode, (response) => {
        if (response) {
          console.log(response.data);
          commit("SET_AGE", response.data);
        }
      });
    },
  },
};

export default infoStore;
