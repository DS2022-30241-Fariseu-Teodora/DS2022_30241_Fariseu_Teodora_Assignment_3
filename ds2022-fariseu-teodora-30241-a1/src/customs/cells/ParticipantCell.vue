<template>
  <div class = "cell" >
    <h2>{{this.$props.user.username}}</h2>
    <button id="le-button" class="cell-button" @click="this.$emit('userClicked',this.user)">{{this.$props.buttonText}}</button>
  </div>
</template>

<script>

export default {
  name: "UserCell",
  props: {
    user: Object,
    buttonText: String,
  },
  emits: ['userClicked'],
  watch: {
    discussion: {
      buttonText: true,
      handler (val) {
        document.getElementById('le-button').textContent = val
      }
    }
  }
}
</script>

<style scoped>
@import "@/assets/vertical-list-style.css";
.cell-button{
  background-color: white;
  color: #2c3e50;
}

</style>