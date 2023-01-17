<template>
  <h2>Here's a list of all available device models</h2>
  <h3>Select any of them to manage the individual devices from the respective model</h3>
  <h4>Can't see the model you're looking for? Try creating it <router-link to="/models/add"> here</router-link></h4>
  <h4>See the model, but can't find a device for it? <router-link to="/devices/add">Create an instance for any device</router-link></h4>
  <generic-list :apiUrl = this.path>
    <template #item="model">
      <device-model-cell :model="model" @model-clicked="this.modelClicked"></device-model-cell>
    </template>
  </generic-list>
</template>

<script>
import {path} from "@/devices/device-model-api";
import DeviceModelCell from "@/customs/cells/DeviceModelCell";
import GenericList from "@/customs/lists/GenericList";
export default {
  name: "AllDeviceModels",
  components: {DeviceModelCell, GenericList},
  data() {
    return {
      path: path
    }
  },
  methods: {
    modelClicked(model) {
      this.$router.push("/models/"+model.id);
    }
  }
}
</script>

<style scoped>

</style>