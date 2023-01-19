<template>
  <div @click="this.enterOption" id=this.optionName>
    <h1> {{this.$props.notification.location}}</h1>
    <h2>{{this.$props.notification.message}}</h2>
    <h3>At {{this.$props.notification.timestamp}}</h3>
  </div>
</template>

<script>
export default {
  name: "NotificationCell",
  props: {
    notification: Object
  },
  methods: {
    enterOption() {
      this.$router.push("/energy/"+this.$props.deviceId);
    }
  }
}
</script>

<style scoped>
@import "@/assets/vertical-list-style.css";
</style>