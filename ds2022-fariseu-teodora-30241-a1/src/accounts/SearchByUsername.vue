<template>
  <generic-searchbar :apiUrl = this.path :fieldName = "'username'" ref="userList" :showresults="this.showResults"
  :additional-params = "'&isAdmin='+this.$props.searchAdmins">
    <template #item="user">
      <user-cell :user="user"></user-cell>
    </template>
  </generic-searchbar>
</template>

<script>
import {path} from "./accounts-api"
import GenericSearchbar from "@/customs/lists/GenericSearchbar";
import UserCell from "@/customs/cells/UserCell";

export default {
  name: "SearchByUsername",
  components: {UserCell, GenericSearchbar},
  props:{
    searchAdmins: Boolean
  },
  data() {
    return {
      showResults: true,
      path: path + "searchUsers"
    }
  }
}
</script>

<style scoped>
@import "@/assets/vertical-list-style.css";
</style>