<template>
<div class="cell" v-if="model!=null" @click="this.$emit('modelClicked',this.model)">
  <h1>{{this.$props.model.modelName}}</h1>
  <h2>{{this.$props.model.serialNumber}}</h2>
  <p>Max consumption:<span>{{model.maxDeviceConsumption}}</span> kW/H</p>
</div>
</template>

<script>
export default {
  name: "DeviceModelCell",
  props: {
    model: Object,
    onClick: Function
  }
}
</script>

<style scoped>
.cell {
  margin: 0px;
  padding: 0px;
}
</style>