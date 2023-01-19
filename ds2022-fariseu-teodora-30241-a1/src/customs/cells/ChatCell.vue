<template>
<div class="cell cell2" @click="this.$emit('chatClicked',this.chat)">
  <h2>{{this.$props.chat.name}}</h2>
  <h3>{{this.$props.chat.previewMessage}}</h3>
  <p v-if="this.$props.chat.unreadMessages > 0">{{this.$props.chat.unreadMessages}}</p>
</div>
</template>

<script>
export default {
  name: "ChatCell",
  props: {
    chat: Object
  },
  emits: ['chatClicked']
}
</script>

<style scoped>
.cell {
  padding: 10px;
  margin:auto;
  width: 80%;
  color: #2c3e50;
  background-color: white;
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
}
.cell:hover {
  color: #42b983;
}
.cell2 {
  padding: 0px !important;
  margin: 0px !important;
  border-bottom: 1px solid black;
}
</style>