<template>

  <div v-if="!editMode">
    <user-profile-view :user="this.user"></user-profile-view>
    <div> <button @click = toggleEditing >EDIT</button> <button @click = deleteUser>DELETE</button></div>
  </div>
  <div v-else>
    <register-form :user = "this.user" :submit-name="'confirm'" @submit-form="this.editAccount"></register-form>
    <button @click = toggleEditing>CANCEL</button>
  </div>
</template>

<script>
import UserProfileView from "@/customs/cells/UserProfileView";
import axios from "axios";
import {HOST, TOKEN} from "@/common/constants";
import {path} from "@/accounts/accounts-api";
import {deleteAccount,editAccount} from "@/accounts/accounts-api";
import RegisterForm from "@/customs/forms/RegisterForm";

export default {
  name: "ManageUserPage",
  components: {RegisterForm, UserProfileView},
  data() {
    return {user: undefined,
    editMode: false
    }
  },
  methods: {
    getUser(){
      axios.get(HOST+path+"profile",{headers:{energy_token: localStorage.getItem(TOKEN)}
        ,params: { userID: this.$route.params.id}})
          .then(response => this.user=response.data);
    },
    deleteUser() {
      deleteAccount(this.user);
    },
    toggleEditing() {
      this.editMode = !this.editMode;
    },
    editAccount(user) {
      this.user = user;
      editAccount(this.user);
    }
  },
  created() {
    this.getUser();
  }
}
</script>

<style scoped>

</style>