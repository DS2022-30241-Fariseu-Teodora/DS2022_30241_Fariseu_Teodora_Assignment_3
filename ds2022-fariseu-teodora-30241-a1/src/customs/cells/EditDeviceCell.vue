<template>
<DeviceCell :device = this.device></DeviceCell>
  <div v-if="!(editingMode)" class="footer-buttons">
    <button @click="this.editingMode = !this.editingMode">EDIT</button>
    <button @click="this.editingMode = !this.editingMode">ASSIGN USER</button>
  </div>
  <div v-else>
    <button @click="this.editingMode != editingMode"></button>
  </div>
<SearchByUsername v-if="assignMode"  ></SearchByUsername>
</template>

<script>
import DeviceCell from "@/customs/cells/DeviceCell";
import Device from "./DeviceCell";
import User from "./UserCell";
import SearchByUsername from "@/accounts/SearchByUsername";
class EditableDevice extends Device{
  constructor(obj) {
    super(obj);
    this.owner = User(obj.owner);
  }
}
export default {
  name: "EditDeviceCell",
  components: {SearchByUsername, DeviceCell},
  props: {
    device: EditableDevice
  },
  data() {
    return {
      editingMode: false,
      assignMode: false
    }
  }

}
</script>

<style scoped>
@import "@/assets/form-style.css";
</style>