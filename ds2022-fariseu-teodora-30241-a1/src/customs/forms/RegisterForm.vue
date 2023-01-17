<template>
    <form v-if="user!==undefined" @submit.prevent="this.$emit('submitForm' ,this.$props.user)">
      <label>Username</label>
      <input type="text" v-model="this.$props.user.username"/>
      <label>E-mail</label>
      <input type="text" v-model="this.$props.user.email" />
      <label></label>
      <label v-if="this.$props.user.hasOwnProperty('password')">Password</label>
      <input type="password" v-if="this.$props.user.hasOwnProperty('password')" v-model="this.$props.user.password" />

      <button type="submit">{{this.$props.submitName.toUpperCase()}}</button>
      <button type="reset">CLEAR</button>
    </form>
</template>

<script>

export default {
  props: {
    user: Object,
    submitName: String
  },
  emits: ['submitForm']
};
</script>

<style >
@import "@/assets/form-style.css";

</style>