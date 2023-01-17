<template>
  <div>
    <div v-if="SESSION==null">
      <h1>You are not logged in</h1>
      <h2>Log in or create an account to get started</h2>
    </div>
    <div v-else>
      <h1>Notifications</h1>
      <generic-list :apiUrl = "'notifications/'+this.SESSION.id" >
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
import {SESSION} from "@/common/constants";

export default {
  name: "NotificationsPage",
  components: {HomeOptionsCell, GenericList},
  data() {
    return {
      SESSION: null
    }
  },
  created() {
    console.log(JSON.parse(localStorage.getItem(SESSION)))
    this.SESSION = JSON.parse(localStorage.getItem(SESSION));
  }
}
</script>

<style scoped>

</style>