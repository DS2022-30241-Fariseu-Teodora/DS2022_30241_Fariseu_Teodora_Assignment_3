<template>
  <template>
    <generic-list :apiUrl = this.path>
      <template #item="device">
        <device-cell :device="device" @device-clicked = "this.navigate"></device-cell>
      </template>
    </generic-list>
  </template>
</template>

<script>
import DeviceCell from "@/customs/cells/DeviceCell";
import GenericList from "@/customs/lists/GenericList";
import {path} from "@/devices/devices-api";

export default {
  name: "MyDevices",
  components: {DeviceCell, GenericList},
  data() {
    return {
      path: path +"myDevices"
    };
  },
  methods:{
    navigate(device) {
      this.$router.push('/energy/'+device.id)
    }
  }
}
</script>

<style scoped>
@import "@/assets/vertical-list-style.css";
</style>