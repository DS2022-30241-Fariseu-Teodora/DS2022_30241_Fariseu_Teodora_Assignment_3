<template>
  <generic-list :apiUrl = this.path>
    <template #item="device">
      <device-cell :model="device"></device-cell>
    </template>
  </generic-list>
</template>

<script>
import DeviceCell from "@/customs/cells/DeviceCell";
import {path} from "@/devices/device-model-api";

export default {
  name: "UserDevices",
  components: {DeviceCell},
  data(){
    return{
      path: path
    }
  }
}
</script>

<style scoped>

</style>