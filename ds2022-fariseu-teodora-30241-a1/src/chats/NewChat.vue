<template>
  <div id="all">
    <div id="buttons">
      <button @click="this.clearAll">Clear All Participants</button>
      <button :disabled='isDisabled' @click="this.createChat">Create</button>
    </div>
    <div class="users">
      <div  id = "search">
        <generic-searchbar :apiUrl = this.searchPath :fieldName = "'username'" :showresults="true" :additional-params = "'&isAdmin='+this.searchAdmins">
          <template #item="user">
            <participant-cell :user="user" :button-text="ADD" @user-clicked = "this.addUserToList"></participant-cell>
          </template>
        </generic-searchbar>
      </div>

      <ul id = "selected">
        <li v-if="!this.selectedUsers.length">
          {{this.emptyListString}}
        </li>
        <li v-for="item in this.selectedUsers" :key = item.id>
          <participant-cell :user="item" :button-text="REMOVE" @user-clicked = "this.removeUserFromList"></participant-cell>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>

import ParticipantCell from "@/customs/cells/ParticipantCell";
import GenericSearchbar from "@/customs/lists/GenericSearchbar";
import {create} from "./chat-api";
import axios from "axios";
import {HOST, TOKEN} from "@/common/constants";
export default {
  name: "NewChat",
  components: { GenericSearchbar, ParticipantCell},
  props: {
    viewerRole: String
  },
  data() {
    return {
      selectedUsers: [],
      emptyListString: "",
      maxUsersAllowed: 1,
      searchPath: 'users/' + "searchUsers",
      searchAdmins: false
    }
  },
  methods: {
    addUserToList(user){
      if(this.selectedUsers.length>=this.maxUsersAllowed)
        return;
      this.selectedUsers = this.selectedUsers.filter( function(item) {
        return item.id !== user.id;
      });
      this.selectedUsers.push(user)
    },
    removeUserFromList(user) {
      this.selectedUsers = this.selectedUsers.filter( function(item) {
        return item.id !== user.id;
      });
    },
    createChat() {
      if(!this.isDisabled) {
        create(this.selectedUsers.map(function (i) { return i.id }))
        this.selectedUsers = []
      }
    },
    clearAll() {
      this.selectedUsers = []
    }
  },
  computed: {
    isDisabled() {
      // you can  check your form is filled or not here.
      return this.selectedUsers.length < 1
    },
  },
  beforeCreate() {
    axios.get(HOST + "getSessionDetails",{
      headers: {
        energy_token: localStorage.getItem(TOKEN)
      }
    }).then(response => {
      console.log(response.data.role)
      if(response.data.role ==='ADMIN'){
        this.maxUsersAllowed = 30;
        this.emptyListString = "Select at least a participant to start a new chat";
        this.searchAdmins = false;
      } else {
        this.maxUsersAllowed = 1;
        this.emptyListString = "Select a conversation partner"
        this.searchAdmins = true;
      }
    }).catch(err => {console.log(err); this.SESSION = null;});
  }
}
</script>

<style scoped>

@import "@/assets/vertical-list-style.css";
.users{
  display:flex;
  flex-direction: row;
}
#search {
  margin: auto;
  width: 35%;
  display: flex;
  flex-direction: column;
}
#selected {
  margin: auto;
  width: 35%;
}
#all {
  flex-direction: column;
  display: flex;
}
#buttons{
  flex-direction: row;
  display: flex;
}
</style>