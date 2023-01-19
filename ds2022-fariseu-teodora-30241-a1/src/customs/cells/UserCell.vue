<template>
<div class = "cell" @click="this.$emit('userClicked',this.user)">
<h2>{{this.$props.user.username}}</h2>
  <h3 v-if="this.$props.user.hasOwnProperty('email')">e-mail:{{this.$props.user.email}}</h3>
  <p v-if="this.$props.user.hasOwnProperty('joinedAt')">Joined at: {{this.$props.user.joinedAt}}</p>
</div>
</template>

<script>

export default {
  name: "UserCell",
  props: {
    user: Object
  },
  emits: ['userClicked']
}
</script>

<style scoped>
@import "@/assets/vertical-list-style.css";
</style>