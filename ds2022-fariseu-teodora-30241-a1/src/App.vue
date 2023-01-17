<template id="app">
  <NavBar></NavBar>
   <div style="padding: 10px;margin:0px;display:flex;flex-direction:column;justify-content: center">
     <router-view></router-view>

   </div>

</template>


<script>

import NavBar from "@/common/NavBar";

export default {
  name: 'App',
  components: {
    NavBar
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  color: #2c3e50;
  margin: 0px;
}
</style>
