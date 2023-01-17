<template>
  <div class = "cell" >
    <h2>{{this.$props.user.username}}</h2>
    <button class="cell-button" @click="this.$emit('userClicked',this.user)">{{this.$props.buttonText}}</button>
  </div>
</template>

<script>

export default {
  name: "UserCell",
  props: {
    user: Object,
    buttonText: String,
  },
  emits: ['userClicked']
}
</script>

<style scoped>
.cell {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
}
.cell-button{
  background-color: white;
  color: #2c3e50;
}
</style>