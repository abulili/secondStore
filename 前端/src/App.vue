<template>
  <div id="app">

    <Header></Header>
    <router-view />
  </div>
</template>

<style lang="scss">
@import './assets/css/base.css';


#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  // overflow-y: scroll;
  // scrollbar-width: thin;
  // overflow-x: hidden;
  // -ms-overflow-style: none;
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>

<script>
import Header from '@/components/Header'
import Footer from '@/components/Footer'
export default {
  data() {
    return {

    }
  },
  components: {
    Header,
    Footer
  },
  methods: {

  },
}
</script>
