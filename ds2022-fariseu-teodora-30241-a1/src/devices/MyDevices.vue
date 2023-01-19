<template>
  <ul style="color: #2c3e50">
    <li v-if="items.length<1">
      You don't have any devices at the moment. Ask an admin to add some for you
    </li>
    <li v-for="item in items" :key = item.id>
      <device-cell :device="item" @device-clicked="this.navigate"></device-cell>
    </li>
  </ul>
</template>

<script>
import DeviceCell from "@/customs/cells/DeviceCell";
import axios from "axios";
import {HOST, TOKEN} from "@/common/constants";

export default {
  name: "MyDevices",
  components: {DeviceCell},
  data() {
    return {
      items: []
    }
  },
  methods:{
    navigate(device) {
      this.$router.push('/energy/'+device.id)
    },
    refreshItems() {
      axios.get(HOST + 'devices/myDevices' ,{
        headers: {energy_token: localStorage.getItem(TOKEN)}
      }).then(response => {
        this.items = response.data
        console.log(this.items)
      });
    }
  },
  created() {
    this.refreshItems();
  }
}
</script>

<style scoped>
@import "@/assets/vertical-list-style.css";
.st {
  color: #2c3e50;
}
</style>