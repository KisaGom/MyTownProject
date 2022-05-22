import { sidoList, gugunList, dongList } from "@/api/baseAddr";
import { commercialListDong } from "@/api/commercialInfo";
import { houseDealList } from "@/api/houseDeal";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "시/도" }],
    guguns: [{ value: null, text: "구/군" }],
    dongs: [{ value: null, text: "읍/면/동" }],
    houses: [],
    house: null,
    comms: [],
  },
  mutations: {
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "시/도" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "구/군" }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ value: null, text: "읍/면/동" }];
    },
    CLEAR_HOUSE_LIST: (state) => {
      state.houses = null;
    },
    SET_HOUSE_LIST: (state, houses) => {
      //   console.log(houses);
      state.houses = houses;
      console.log(houses);
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
    },
    CLEAR_COMM_LIST: (state) => {
      state.houses = null;
    },
    SET_COMM_LIST: (state, comms) => {
      //   console.log(houses);
      state.comms = comms;
      console.log(state.comms);
    },
  },
  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = {
        sido: sidoCode,
      };
      gugunList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDong: ({ commit }, gugunCode) => {
      const params = {
        gugun: gugunCode,
      };
      dongList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getHouseList: ({ commit }, dongCode) => {
      const params = {
        dongCode,
      };
      console.log("dongCode -> " + dongCode);
      houseDealList(
        params,
        (response) => {
          //console.log(response.data);
          //   console.log(response.data.response.body.items.item);
          commit("SET_HOUSE_LIST", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    detailHouse: ({ commit }, house) => {
      commit("SET_DETAIL_HOUSE", house);
    },
    getCommercialListDong: ({ commit }, dongCode) => {
      const params = {
        dongCode,
      };
      commercialListDong(
        params,
        (response) => {
          //console.log(response.data);
          //   console.log(response.data.response.body.items.item);
          commit("SET_COMM_LIST", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default houseStore;
