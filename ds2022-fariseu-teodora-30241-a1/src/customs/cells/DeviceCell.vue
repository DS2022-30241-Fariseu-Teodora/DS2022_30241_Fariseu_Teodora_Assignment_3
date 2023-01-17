<template>
<div class = "cell" @click = "this.$emit('deviceClicked', this.device)">
  <device-model-cell :model = this.$props.device.deviceModel> </device-model-cell>
  <div id="other-info">
    <p>Location: {{this.$props.device.address}}</p>
    <p v-if="this.$props.device.description!=null &&this.$props.device.description !==''">Description: {{this.$props.device.description}}</p>
  </div>
</div>
</template>

<script>
import DeviceModelCell from "@/customs/cells/DeviceModelCell";
export default {
  name: "DeviceCell",
  components: {DeviceModelCell},
  props: {
    device: Object
  },
  emits: ['deviceClicked']
}
</script>

<style scoped>
.cell {
  padding-bottom: 10px;
  color: azure;
  background-color: #2c3e50;
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
}
#other-info {
  display: flex;
  flex-direction: column;
}
</style>