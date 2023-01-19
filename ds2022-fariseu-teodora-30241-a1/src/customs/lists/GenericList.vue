<script>
import {HOST, TOKEN} from "@/common/constants";
import axios from 'axios';
export default {
  name:"GenericList",
  props: { apiUrl: String,
  params: Object},
  data() {
    return {
      items: []
    }
  },
  created() {
    this.refreshItems();
  },
  methods: {
    refreshItems() {
      axios.get(HOST + this.$props.apiUrl ,{
        headers: {energy_token: localStorage.getItem(TOKEN)},
        params: this.$props.params
      }).then(response => {
        this.items = response.data
        console.log(this.items)
      });
    }
  }
}
</script>

<template>
  <ul>
    <li v-if="items.length<1">
      No items
    </li>
    <li v-for="item in items" :key = item.id>
      <slot name="item" v-bind="item"/>
    </li>
  </ul>
</template>

<style scoped>
@import "@/assets/vertical-list-style.css";
</style>