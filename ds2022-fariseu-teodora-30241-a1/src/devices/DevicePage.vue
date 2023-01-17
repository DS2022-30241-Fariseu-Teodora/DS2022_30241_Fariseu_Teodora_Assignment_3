<template>
<div v-if="this.device !=null">
  <h1>{{this.device.description}}</h1>
  <h2>{{this.device.address}}</h2>
  <h3>{{this.device.deviceModel.modelName}}</h3>
  <p v-if="this.device.owner !=null">Owned by {{this.device.owner.username}}</p>
  <button v-if="!editingMode" @click = this.toggleEditing>ASSOCIATE USER</button>
  <div v-else>
    <user-cell v-if="this.selectedUser!=null" :user = this.selectedUser></user-cell>
    <generic-searchbar :apiUrl = this.searchPath :fieldName = "'username'" :showresults="this.showResults" :search-admin="false">
      <template #item="user">
        <user-cell :user="user" @user-clicked = "this.setSelectedUser"></user-cell>
      </template>
    </generic-searchbar>
    <button @click = this.associateDevice>CONFIRM</button>
    <button @click = this.toggleEditing >CANCEL</button>
  </div>

</div>
</template>

<script>
import axios from "axios";
import {HOST, TOKEN} from "@/common/constants";
import {path} from "@/devices/devices-api";
import {associateDevice} from "@/devices/devices-api";
import UserCell from "@/customs/cells/UserCell";
import GenericSearchbar from "@/customs/lists/GenericSearchbar";

export default {
  name: "DevicePage",
  components: {GenericSearchbar, UserCell},
  data() {
    return{
      device: null,
      editingMode: false,
      showResults: true,
      selectedUser: null,
      searchPath: 'users/' + "searchUsers"
    }
  },
  methods: {
    toggleEditing() {
      this.editingMode = !this.editingMode
    },
    setSelectedUser(user) {
      this.selectedUser = user
    },
    associateDevice() {
      this.device.owner = this.selectedUser
      associateDevice(this.device)
    }
  },
  beforeCreate() {
    axios.get(HOST+path+this.$route.params.id,
        {headers:{energy_token: localStorage.getItem(TOKEN)}})
        .then(response => {
          this.device = response.data;
          this.selectedUser = response.data.owner;
        });
  }
}
</script>

<style scoped>

</style>