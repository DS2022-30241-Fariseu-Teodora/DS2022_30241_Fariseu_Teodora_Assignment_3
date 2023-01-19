<template>
<div @click="this.enterOption" id=this.optionName class="cell">
<h1> {{this.$props.option.optionName}}</h1>
  <h2>{{this.$props.option.description}}</h2>
</div>
</template>

<script>
export default {
  name: "HomeOptionsCell",
  props: {
    option: Object
  },
  methods: {
    enterOption() {
      this.$router.push(this.$props.option.redirectURL);
    }
  }
}
</script>

<style scoped>
@import "@/assets/vertical-list-style.css";
</style>