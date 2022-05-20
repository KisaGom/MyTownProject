<template>
  <div class="life-parent">
    <div style="position: relative">
      <nav-bar></nav-bar>
      <div class="sidemenu side">
        <h3>매매</h3>
        <h3>상권</h3>
        <h3>구성원</h3>
      </div>
      <div class="sidecontent side">
        <b-form-select
          v-model="selected1"
          :options="options1"
          value-field="sidocode"
          text-field="sidoname"
        ></b-form-select>
        <b-form-select v-model="selected2" :options="options2"></b-form-select>
        <b-form-select v-model="selected3" :options="options3"></b-form-select>
        <!-- <div class="sideopener side">버튼</div> -->
      </div>
    </div>
    <map-view></map-view>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import MapView from "@/components/map/MapView.vue";

export default {
  components: { NavBar, MapView },
  data() {
    return {
      selected1: null,
      options1: [{ sidocode: null, sidoname: "시" }],
      selected2: null,
      options2: [{ value: null, text: "군" }],
      selected3: null,
      options3: [{ value: null, text: "동" }],
    };
  },
  mounted() {},
};
</script>
<style>
.side {
  height: 100vh;
}

.sidemenu {
  position: absolute;
  z-index: 3;
  background-color: azure;
}

.sidecontent {
  position: absolute;
  margin-left: 105px;
  z-index: 2;
  background-color: aquamarine;
  transform: translateX(0%);
}
</style>
