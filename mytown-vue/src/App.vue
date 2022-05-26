<template>
  <div id="app">
    <nav-bar class="nav-bar"></nav-bar>
    <router-view />
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
export default {
  name: "App",
  components: { NavBar },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /* height: 100vh; */
}

/* 스크롤 안 보이게(스크롤바 없애기) */
body {
  -ms-overflow-style: none;
}
::-webkit-scrollbar {
  display: none;
}

.input-group .custom-select {
  border-radius: 0 !important;
}
.input-group .form-control {
  border-radius: 0 !important;
}
.input-group button {
  border-radius: 0 !important;
}
.input-group {
  margin-bottom: 10px;
}
</style>
