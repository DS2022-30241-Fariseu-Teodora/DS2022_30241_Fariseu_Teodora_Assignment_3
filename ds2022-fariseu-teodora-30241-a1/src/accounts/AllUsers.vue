<template>
  <generic-list :apiUrl = this.path>
    <template #item="user">
      <user-cell :user="user" @user-clicked = "toProfileFor"></user-cell>
    </template>
  </generic-list>
</template>

<script>
import GenericList from "@/customs/lists/GenericList";
import UserCell from "@/customs/cells/UserCell";
import {path} from "@/accounts/accounts-api";

export default {
  name: "AllUsers",
  components: {UserCell, GenericList},
  data(){
    return {
      path: path
    }
  },
  methods: {
    toProfileFor(user) {
      this.$router.push("/users/"+user.id);
    }
  }
}
</script>

<style scoped>

</style>