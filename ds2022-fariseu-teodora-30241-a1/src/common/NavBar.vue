<template>
      <div class="nav" v-if="this.SESSION !==null">
        <router-link to="/" class="nav-item">{{this.SESSION.username}}</router-link>
        <router-link to="/" class="nav-item" @click =this.logOut  >LOGOUT</router-link>
      </div>
      <div class="nav" v-else>
         <router-link to="/login" class="nav-item">LOGIN</router-link>
         <router-link to="/register" class="nav-item">REGISTER</router-link>
      </div>

</template>
<script>
import {HOST,TOKEN} from "@/common/constants";
import Swal from 'sweetalert2'
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import {router} from "@/main";
import axios from "axios";
var stompClient;
export default {
  name: "NavBar",
  data() {
    return {
      SESSION: null
    }
  },
  methods: {
    logOut() {
      console.log('logout')
      localStorage.setItem(TOKEN, null);
      this.disconnect()
      axios.post(HOST + 'logout',{headers:{energy_token: localStorage.getItem(TOKEN)}})
          .then(response => {
            this.SESSION = response.data;
            this.connect();
          })
          .catch(this.SESSION = null);
      this.$router.push("/")
      this.$router.go(1)
    },
    connect() {
      this.socket = new SockJS(HOST+'ws-message')
      stompClient = Stomp.over(this.socket);
      stompClient.connect({}, frame => {
        console.log("Connected to "+ frame);
        stompClient.subscribe('/topic/notifications/'+this.SESSION.id, val => {
          var decoded = JSON.parse(val.body);
          console.log(decoded.message)
          Swal.fire({
              title: 'Warning',
              text: decoded.message,
              confirmButtonText: 'See device',
              cancelButtonText: 'Cancel',
              buttonsStyling: true}).then(function (isConfirm) {
            if(isConfirm.value === true) {
              router.push("/energy/"+decoded.deviceID);
            }
          })
        });
      });
      this.socket.onclose = function() {
        console.log('close');
        stompClient.disconnect();
      };
    },
    disconnect: function(){
      this.socket.close()
    }
  },
  created() {
    if(localStorage.getItem(TOKEN) !== undefined && localStorage.getItem(TOKEN) !== 'null' ) {
      axios.get(HOST + 'getSessionDetails',{ headers:{energy_token: localStorage.getItem(TOKEN)}})
          .then(response => {
            this.SESSION = response.data;
            this.connect();
          })
          .catch( err =>{
            console.log(err);
            this.SESSION = null;
            localStorage.setItem(TOKEN,null);
            router.push('/login')
            router.go(1)
          });
    }
  }
}
</script>
<style lang="css">
.nav {
  margin:0px;
  width: 100%;
  background-color: #2c3e50;
  display: flex;
  justify-content: flex-end;

}
.nav-item {
  text-decoration: none;
  padding: 30px;
  color: white;

}
.nav-item:hover {
  color: #42b983;
}
</style>
