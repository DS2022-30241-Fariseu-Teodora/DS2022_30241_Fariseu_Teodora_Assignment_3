<template>
<div id = "page">
  <div id = "chats">
    <button @click="this.newChat">New Chat</button>
    <generic-list :apiUrl = this.path>
      <template #item="chat">
        <chat-cell :chat = "chat" @chat-clicked="this.currentChat"></chat-cell>
      </template>
    </generic-list>
  </div>
  <div class="left-part" v-if="newChatMode">
    <NewChat></NewChat>
  </div>
  <div v-else class="left-part">
  <ConversationView :discussion="this.selectedChat" :session-id="this.SESSION" :username = "this.sname"></ConversationView>
  </div>
</div>
</template>

<script>
import Chat from '@/grpc/chat.ts';
import NewChat from "@/chats/NewChat";
import ChatCell from "@/customs/cells/ChatCell";
import GenericList from "@/customs/lists/GenericList";
import ConversationView from "@/chats/ConversationView";
import {HOST, TOKEN} from "@/common/constants";
import axios from "axios";
import {router} from "@/main";
export default {
  name: "MyChats",
  components: {ConversationView, ChatCell, NewChat, GenericList},
  data() {
    return {
      message: "",
      newChatMode: false,
      path: "chat/myChats",
      selectedChat: null,
      chat: new Chat(),
      SESSION: "",
      sname: "",
    }
  },
  methods: {
    sendMessage() {
      console.log(this.message)
      this.chat.send(this.message)
    },
    currentChat(chat) {
      console.log(chat.id)
      this.newChatMode = false
      this.selectedChat = chat
    },
    newChat(){
      this.newChatMode = true
    }
  },
  beforeMount() {console.log(localStorage.getItem(TOKEN))
    axios.get(HOST + "getSessionDetails",{
      headers: {
        energy_token: localStorage.getItem(TOKEN)
      }
    }).then(response => {
      this.SESSION = response.data.id
      this.sname = response.data.username
    })
        .catch(err => {console.log(err); this.SESSION = null;
          router.push('/login')
          router.go(1)});
  }
}
</script>

<style scoped>
#chats {
  width: 400px;
  height: 100%;
  margin: 0px;
  display: flex;
  flex-direction: column;
  align-content: flex-start;
}

.left-part {
  margin: auto;
  display: flex;
  flex-direction: column;
  width: 75%;
  height: 100%;
}
#page {
  display: flex;
  flex-direction: row;
  width: 100%;
  margin: auto;
  position: center;
  padding: 0px;
}
ul{
  background-color: white;
  color: #2c3e50;
}
li {
  background-color: white;
  color: #2c3e50;
}
</style>