<template>
  <b-tab title="관심 지역">
    <b-container>
      <b-row> </b-row>
      <b-row>
        <b-col></b-col>
        <b-col cols="8" lg="8" md="10">
          <div class="card">
            <h3>My Page</h3>

            <h6>관심 지역을 설정하세요.</h6>

            <hr class="my-4" />
            <b-input-group class="mt-3">
              <b-form-select
                v-model="sidoCode"
                :options="sidos"
                @change="gugunList"
              ></b-form-select>
              <b-form-select
                v-model="gugunCode"
                :options="guguns"
                @change="dongList"
              ></b-form-select>
              <b-form-select
                v-model="dongCode"
                :options="dongs"
              ></b-form-select>
              <b-input-group-append
                ><b-button @click="onClickInsert"
                  >추가</b-button
                ></b-input-group-append
              >
            </b-input-group>

            <b-table class="mt-3" hover fixed :items="items" :fields="fields">
              <template #cell(del)="row">
                <b-icon
                  icon="x-circle"
                  style="cursor: pointer"
                  @click="onClickDelete(row)"
                ></b-icon>
              </template>
            </b-table>
          </div>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
  </b-tab>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import { list, insert, deleteFav } from "@/api/favorite";

const houseStore = "houseStore";
const memberStore = "memberStore";
export default {
  name: "MemberUserFavorite",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      items: null,
      fields: [
        { key: "sidoName", label: "시/도" },
        { key: "gugunName", label: "구/군" },
        { key: "dongName", label: "동" },
        { key: "del", label: "삭제" },
      ],
    };
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_DONG_LIST();
    this.getSido();

    this.getFavoriteList();
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(houseStore, ["getSido", "getGugun", "getDong"]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
    ]),
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.CLEAR_DONG_LIST();
      this.gugunCode = null;
      this.dongCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    getFavoriteList() {
      list(
        this.userInfo.userid,
        (response) => {
          console.log(response);
          this.items = response.data;
        },
        () => {}
      );
    },
    onClickInsert() {
      if (this.dongCode) {
        let fav = {
          userid: this.userInfo.userid,
          dongCode: this.gugunCode + this.dongCode,
        };
        insert(
          fav,
          () => {
            this.getFavoriteList();
          },
          (error) => {
            console.log(error);
          }
        );
      } else {
        alert("지역 정보를 입력해주십시오.");
      }
    },
    onClickDelete(row) {
      let fav = {
        userid: this.userInfo.userid,
        dongCode: row.item.dongCode,
      };
      deleteFav(
        fav,
        () => {
          this.getFavoriteList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style scoped>
div.card {
  margin-top: 50px;
  padding: 50px;
  border-radius: 0;
  background-color: #fff;
}
h3 {
  font-weight: bold;
}
</style>
