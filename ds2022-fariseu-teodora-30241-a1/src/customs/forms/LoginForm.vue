<template>
  <div class="content">
    <form @submit.prevent="loginWithPassword">
      <label>Username</label>
      <input type="text" v-model="username" placeholder="username" />
      <label>Password</label>
      <input type="password" v-model="password" placeholder="password" />

      <button type="submit">Log in</button>
    </form>
  </div>
</template>

<script>
import {login} from "@/home/home-api";
import {TOKEN} from "@/common/constants";
export default {
  data() {
    return {
      username: "",
      password: "",
      alert: "",
      token: null
    };
  },
  methods: {
    loginWithPassword() {
      this.alert = "";
      login({
        username: this.username,
        password: this.password,
      });
    },
  },
  beforeCreate() {
    this.token = localStorage.getItem(TOKEN)
    if(this.token!==undefined && this.token !== null && this.token !== 'null' && this.token!=='undefined') {
      this.$router.push('/')
    }
  }
};
</script>

<style >
  @import "@/assets/form-style.css";
  .content {
    padding: 20px;
    display: flex;
    justify-content: center;
  }
</style>