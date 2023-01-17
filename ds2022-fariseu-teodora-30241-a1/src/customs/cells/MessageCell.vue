<template>
  <div id = "container">
    <p v-if="!this.$props.message.isFromMe">{{this.$props.message.username}}</p>
    <h2 class="my-message" v-if="this.$props.message.isFromMe">{{this.$props.message.message}}</h2>
    <h2 class="normal-message" v-else>{{this.$props.message.message}}</h2>
  </div>
</template>

<script>
export default {
  name: "messageCell",
  props: {
    message: Object
  }
}
</script>

<style scoped>
.normal-message {
  align-self: self-start;
  background-color: #2c3e50;
  color: white;
  width: 200px;
  border-radius: 3px;
}
.my-message {
  align-self: self-end;
  background-color: #647eff;
  color: white;
  width: 200px;
  border-radius: 3px;
}
</style>