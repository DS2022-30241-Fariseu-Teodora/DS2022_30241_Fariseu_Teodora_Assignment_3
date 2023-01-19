<template>
  <div v-if="this.$props.message.isFromMe" class="container darker">

    <h2 class="right">{{this.$props.message.message}}</h2>
  </div>
  <div v-else class="container">
    <p class="left person" >{{this.$props.message.username}}</p>
   <h2 class="left">{{this.$props.message.message}}</h2>
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
.container {
  border: 2px solid #dedede;
  background-color: #f1f1f1;
  border-radius: 5px;
  padding: 10px;
  margin: 10px 0;
}

.darker {
  border-color: #ccc;
  background-color: #ddd;
}
.person {
  color: black;
  padding: 3px;
}
.container::after {
  content: "";
  clear: both;
  display: table;
}

.right {
  float: right;
}

.left {
  float: left;
}
</style>