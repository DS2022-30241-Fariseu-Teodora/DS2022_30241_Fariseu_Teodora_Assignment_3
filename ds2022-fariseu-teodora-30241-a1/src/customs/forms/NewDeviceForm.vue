<template>
<div>
  <form @submit.prevent="newDevice">
    <label>Model:</label>
    <device-model-cell v-if="this.selectedModel != null" :model="this.selectedModel"></device-model-cell>
    <generic-searchbar :apiUrl = this.searchPath :fieldName = "'modelName'" :showresults="true">
      <template #item="model">
        <device-model-cell :model="model" @model-clicked = "this.selectItem"></device-model-cell>
      </template>
    </generic-searchbar>
    <label>Description:</label>
    <textarea v-model="description"></textarea>
    <label>Address</label>
    <input type="text" v-model="address">
    <div class = "footer-buttons">
      <button type="submit">Add</button>
      <button type="reset">Clear</button>
    </div>
  </form>
</div>
</template>

<script>
import {addDevice} from "@/devices/devices-api";
import {path} from "@/devices/device-model-api";
import GenericSearchbar from "@/customs/lists/GenericSearchbar";
import DeviceModelCell from "@/customs/cells/DeviceModelCell";
export default {
  name: "NewDeviceForm",
  components: {DeviceModelCell, GenericSearchbar},
  data() {
    return {
      selectedModel: null,
      model:"",
      possibleModels: Array(0),
      description: "",
      address:"",
      searchPath:  path + "searchModel",
      params: { modelName: this.model}
    }
  },
  methods: {
    newDevice() {
      addDevice({
        description: this.description,
        deviceModel: JSON.stringify(this.selectedModel),
        address: this.address
      })
    },
    selectItem(model) {
      this.selectedModel = model;
    }
  }
}
</script>

<style >
@import "@/assets/form-style.css";
</style>