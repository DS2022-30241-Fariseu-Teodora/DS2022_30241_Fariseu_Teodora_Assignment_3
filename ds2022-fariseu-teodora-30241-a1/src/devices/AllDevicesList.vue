<template>
  <generic-list :apiUrl = this.path>
    <template #item="device">
     <device-cell :device="device" @device-clicked = "this.navigate"></device-cell>
    </template>
  </generic-list>
</template>

<script>
import {path} from "@/devices/devices-api";
import GenericList from "@/customs/lists/GenericList";
import DeviceCell from "@/customs/cells/DeviceCell";

export default {
  name: "AllDevicesList",
  components: {DeviceCell, GenericList},
  data() {
    return {
      path: path + "modelDevices/" + this.$route.params.id
    };
  },
  methods:{
    navigate(device) {
      this.$router.push('/device/'+device.id)
    }
  }
}
</script>

<style scoped>
@import "@/assets/vertical-list-style.css";
</style>