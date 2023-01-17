<template>
  <custom-chart :dataUrl="this.path"
  :device-id="this.$route.params.id"></custom-chart>
</template>

<script>
import CustomChart from "@/customs/CustomChart";
export default {
  name: "DeviceConsumption",
  components:{CustomChart},
  data() {
    return {
      path: 'deviceConsumption/'+ this.$route.params.id
    }
  }
}
</script>

<style scoped>

</style>