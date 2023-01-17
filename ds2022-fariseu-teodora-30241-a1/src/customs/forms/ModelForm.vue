<template>
  <div>
    <form @submit.prevent="newModel">
      <label>Model name</label>
      <input type="text" v-model="model">
      <label>Device serial number:</label>
      <input type = "text" v-model="serialNumber">
      <label>Max consumption/hour:</label>
      <input type="number" v-model="maxConsumption">
      <div class = "footer-buttons">
        <button type="submit">Add</button>
        <button type="reset">Clear</button>
      </div>
    </form>
  </div>
</template>

<script>
import {addModel} from "@/devices/device-model-api";

export default {
  name: "ModelForm",
  data() {
    return {
      model:"",
      description: "",
      serialNumber: "",
      maxConsumption: 0.0,
      address:""
    }
  },
  methods: {
    newModel() {
      addModel({
        modelName: this.model,
        serialNumber: this.serialNumber,
        maxDeviceConsumption: this.maxConsumption,
      })
    }
  }
}
</script>

<style >
@import "@/assets/form-style.css";
</style>