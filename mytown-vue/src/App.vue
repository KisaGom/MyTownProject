<template>
  <div id="app">
    <!-- <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/life">개인용</router-link>
    </nav> -->
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

/* nav {
  padding: 30px;
}*/

nav a {
  font-weight: bold;
  color: #2c3e50;
  text-decoration: none !important;
}
/*
nav a.router-link-exact-active {
  color: #42b983;
} */
</style>
