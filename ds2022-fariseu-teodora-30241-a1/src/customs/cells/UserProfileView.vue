<template>
<div>
  <h1>{{this.$props.user.username}}</h1>
  <p v-if="this.$props.user.hasOwnProperty('email')">e-mail:{{this.$props.user.email}}</p>
  <p v-if="this.$props.user.hasOwnProperty('joinedAt')">Joined at: {{this.$props.user.joinedAt}}</p>
</div>
</template>

<script>
export default {
  name: "UserProfileView",
  props: {
    user: Object
  }
}
</script>

<style scoped>

</style>