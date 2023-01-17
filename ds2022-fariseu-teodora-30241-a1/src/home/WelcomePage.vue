<template>
<div>
  <div v-if="SESSION==null">
    <h1>Welcome to a platform where you can monitor your energy consumption</h1>
    <h2>Log in or create an account to get started</h2>
  </div>
  <div v-else>
    <h1>Welcome, {{this.SESSION.username}}!</h1>
    <h2>Here's a list of things that you can try</h2>
    <generic-list :apiUrl = "'homeRoles'" :params = '{role:this.SESSION.role,userID:this.SESSION.id}' >
      <template #item="option">
        <home-options-cell :option="option"></home-options-cell>
      </template>
    </generic-list>
  </div>
</div>
</template>

<script>
import GenericList from "@/customs/lists/GenericList";
import HomeOptionsCell from "@/customs/cells/HomeOptionsCell";
import {HOST, TOKEN} from "@/common/constants";
import axios from "axios";

export default {
  name: "WelcomePage",
  components: {HomeOptionsCell, GenericList},
  data() {
    return {
      SESSION: null,
      token: null,
    }
  },
  created() {
    axios.get(HOST + "getSessionDetails",{
      headers: {
        energy_token: localStorage.getItem(TOKEN)
      }
    }).then(response => this.SESSION = response.data)
        .catch(err => {console.log(err); this.SESSION = null;});
  }
}
</script>

<style scoped>

</style>