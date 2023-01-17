<template>
  <custom-chart :data-url = "this.path" :chart-title="'Device Energy'"></custom-chart>
</template>

<script>
import CustomChart from "@/customs/CustomChart";

export default {
  name: "DailyEnergy",
  components: {CustomChart},
  data() {
    return {
      path: "",
      session:null
    };
  },
  methods:{

  },
  beforeMount() {
    this.path = 'dayConsumption/'
  }
}
</script>

<style scoped>

</style>