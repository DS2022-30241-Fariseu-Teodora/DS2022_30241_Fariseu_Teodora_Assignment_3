<template>

  <div id = "all" v-if="this.$props.discussion !=null">
    <div id="title"><h1>{{this.$props.discussion.name}}</h1></div>
    <ul id = "selected">
      <li v-for="item in this.messages" :key = item.id>
        <message-cell :message="item"></message-cell>
      </li>
    </ul>
    <p>{{this.whoSaw}}</p>
    <div id = "new-message">
      <img id="typing" v-show="this.isAnyoneTyping" src='../assets/typing-dots.gif'>
      <textarea id = "input-message" v-model="this.newMessage" placeholder="Type a message" @keydown="this.sendTypingStatus(true)" @keyup="this.sendTypingStatus(false)"></textarea>
      <button id="a-button" @click ="this.sendMessage">SEND</button>
    </div>
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
var stompClient2 = null;
export default {
  name: "ConversationView",
  components: {MessageCell},
  props: {
    discussion: Object,
    sessionId: String,
    username: String
  },
  data() {
    return {
      messages: [],
      newMessage:"",
      path: "",
      isAnyoneTyping: false,
      whoSaw:""
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
          this.seenStatusConnect()
          axios.get(HOST + "message/lastMessages?discussion="+val.id ,{
            headers: {energy_token: localStorage.getItem(TOKEN)}
          }).then(response => {

            this.messages = response.data.map(m => {
              var mi = m
              console.log(this.$props.sessionId===mi.senderId)
              mi.isFromMe = m.senderId === this.$props.sessionId
            return mi})
            console.log(this.messages)
          });
        }
      }
    }
  },
  methods: {
    connect() {
      this.socket = new SockJS(HOST+'ws-message')
      stompClient = Stomp.over(this.socket);
      // eslint-disable-next-line no-unused-vars
      stompClient.connect({}, frame => {
        stompClient.subscribe('/topic/conversation/'+this.$props.discussion.id, val => {
          var decoded = JSON.parse(val.body);
          decoded.isFromMe = decoded.userId === this.$props.sessionId
          console.log("Is from me:"+decoded.userId)
          this.messages.push(decoded)
        });
      });
      this.socket.onclose = function() {
        stompClient.disconnect();
      };
    },
    sendMessage(){
      if(this.newMessage.length>0 && stompClient !==undefined &&stompClient !==null) {
        stompClient.send("/app/messageSent", JSON.stringify({
          message: this.newMessage,
          userId: this.$props.sessionId,
          username: this.$props.username,
          chatId: this.$props.discussion.id,
          sentDate: (new Date()).getTime()
        }))
      }
    },
    typingStatusConnect() {
      this.typingSocket = new SockJS(HOST+'ws-message')
      stompClient1 = Stomp.over(this.typingSocket);
      // eslint-disable-next-line no-unused-vars
      stompClient1.connect({}, frame => {

        stompClient1.subscribe('/topic/conversation/typeStatus/'+this.$props.discussion.id, val => {
          var decoded = JSON.parse(val.body);
          this.isAnyoneTyping = decoded
        });
      });
      this.typingSocket.onclose = function() {
        stompClient1.disconnect();
      };
    },
    seenStatusConnect() {
      this.seenSocket = new SockJS(HOST+'ws-message')
      stompClient2 = Stomp.over(this.seenSocket);
      // eslint-disable-next-line no-unused-vars
      stompClient2.connect({}, frame => {

        stompClient2.subscribe('/topic/conversation/seen/'+this.$props.discussion.id, val => {
          var decoded = JSON.parse(val.body);
          console.log("Seeners: "+decoded)
          this.whoSaw = "Seen by:"+decoded[decoded.length-1].whoSaw.join(',')
        });
      });
      this.seenSocket.onclose = function() {
        stompClient2.disconnect();
      };
    },
    disconnect: function() {
      if (this.socket !== null && this.socket !== undefined) {
        this.socket.close()
      }
      if (this.typingSocket !== null && this.typingSocket !== undefined) {
        this.typingSocket.close()
      }
      if (this.seenSocket !== null && this.seenSocket !== undefined) {
        this.seenSocket.close()
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
        this.messages = response.data
        this.whoSaw = "Seen by:"+this.messages[this.messages.length-1].whoSaw.join(',')
        console.log(this.whoSaw)
      });
    },
    markSeen() {
      var d = this.messages[this.messages.length-1].sentDate;
      if(d===null){
        d=(new Date()).getTime();
      }
      stompClient2.send('/app/messageSeen/'+this.$props.discussion.id, JSON.stringify({
        until: d,
        user: this.sessionId
      }));
    }
  },
  created() {
    window.addEventListener('scroll', this.markSeen);
  },
  beforeUnmount() {
    window.removeEventListener('scroll', this.markSeen);
    this.disconnect()
    console.log("Closing connection to WebSocket Server")
  }

}
</script>

<style scoped>
#input-message {
  height: 100px;
  width: 600px;
  border-radius: 5px;
}
#typing {
  height: 30px;
  width: 70px;
}
#a-button{
  height: 50px;
  width: 90px;
  border-radius: 5px;
}
#all {
  padding: 0px;
  margin: 0px;
}
#new-message {
  position: fixed;
  bottom: 0;
  right: 0;
  display: flex;
  flex-direction: column;
  align-content: flex-end;
}
#title {
  position: fixed;
  top: 60px;
  left: 460px;
}
</style>