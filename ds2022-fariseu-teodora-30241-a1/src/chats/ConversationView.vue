<template>

  <div v-if="this.$props.discussion !=null">
    <div><h1>{{this.$props.discussion.name}}</h1></div>
    <ul id = "selected">
      <li v-for="item in this.messages" :key = item.id>
        <message-cell :message="item"></message-cell>
      </li>
    </ul>

    <textarea v-model="this.newMessage" placeholder="Type a message" @keydown="this.sendTypingStatus(true)" @keyup="this.sendTypingStatus(false)"></textarea>
    <button @click ="this.sendMessage">SEND</button>
    <img v-show="this.isAnyoneTyping" src='../assets/typing-dots.gif'>
  </div>
  <div v-else>
    Open a chat and start talking
  </div>
</template>

<script>
import MessageCell from "@/customs/cells/MessageCell";
import SockJS from "sockjs-client";
import {HOST, TOKEN} from "@/common/constants";
import Stomp from "webstomp-client";
import axios from "axios";
var stompClient = null;
var stompClient1 = null;
export default {
  name: "ConversationView",
  components: {MessageCell},
  props: {
    discussion: Object
  },
  data() {
    return {
      messages: [],
      newMessage:"",
      path: "",
      isAnyoneTyping: false
    }
  },
  watch: {
    discussion: {
      immediate: true,
      handler (val) {
        if(val !== null && val != undefined) {
          console.log(this.$props.discussion)
          this.initMsg()
          this.disconnect()
          this.messages = []
          this.connect()
          this.typingStatusConnect()
          axios.get(HOST + "message/lastMessages?discussion="+val.id ,{
            headers: {energy_token: localStorage.getItem(TOKEN)}
          }).then(response => {
            this.items = response.data
            console.log(this.items)
          });
        }
      }
    }
  },
  methods: {
    connect() {
      this.socket = new SockJS(HOST+'ws-message')
      stompClient = Stomp.over(this.socket);
      stompClient.connect({}, frame => {
        console.log("Connected to "+ frame);
        stompClient.subscribe('/topic/conversation/'+this.$props.discussion.id, val => {
          var decoded = JSON.parse(val.body);
          this.messages.push(decoded)
        });
      });
      this.socket.onclose = function() {
        console.log('close');
        stompClient.disconnect();
      };
    },
    sendMessage(){
      if(this.newMessage.length>0 && stompClient !==undefined &&stompClient !==null) {
        axios.get(HOST + "getSessionDetails",{
          headers: {
            energy_token: localStorage.getItem(TOKEN)
          }
        }).then(response => {
          stompClient.send("/app/messageSent", JSON.stringify({
            message: this.newMessage,
            userId: response.data.id,
            chatId: this.$props.discussion.id,
            timestamp: (new Date()).getTime()
          }));
        }).catch(err => {console.log(err); this.SESSION = null;});
      }
    },
    typingStatusConnect() {
      this.typingSocket = new SockJS(HOST+'ws-message')
      stompClient1 = Stomp.over(this.typingSocket);
      stompClient1.connect({}, frame => {
        console.log("Connected to "+ frame);

        stompClient1.subscribe('/topic/conversation/typeStatus/'+this.$props.discussion.id, val => {
          var decoded = JSON.parse(val.body);
          console.log("Stat:"+decoded)
          this.isAnyoneTyping = decoded
          console.log(this.isAnyoneTyping)
        });
      });
      this.typingSocket.onclose = function() {
        stompClient1.disconnect();
      };
    },
    disconnect: function() {
      if (this.socket !== null && this.socket !== undefined) {
        this.socket.close()
      }
      if (this.typingSocket !== null && this.typingSocket !== undefined) {
        this.typingSocket.close()
      }
    },
    sendTypingStatus(status) {
      stompClient1.send('/app/typingStatus', JSON.stringify({
        isTyping: status,
        chatId: this.$props.discussion.id
      }));
    },
    initMsg() {
      axios.get(HOST + "message/lastMessages?discussion="+this.$props.discussion.id ,{
        headers: {energy_token: localStorage.getItem(TOKEN)}
      }).then(response => {
        this.messages = response.data.messages
        console.log(this.messages)
      });
    }
  },
  beforeUnmount() {
    this.disconnect()
    console.log("Closing connection to WebSocket Server")
  }
}
</script>

<style scoped>

</style>