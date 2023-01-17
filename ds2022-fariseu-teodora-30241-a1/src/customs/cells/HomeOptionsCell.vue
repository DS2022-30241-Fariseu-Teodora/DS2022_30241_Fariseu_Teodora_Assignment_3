<template>
<div @click="this.enterOption" id=this.optionName>
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
.cell {
  background-color: #2c3e50;
  color: white;
  padding: 10px;
}
</style>