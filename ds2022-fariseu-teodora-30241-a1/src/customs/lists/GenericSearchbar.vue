<template>
  <input type="text" v-model="searchInput" @input = refreshItems>
  <div v-if="this.$props.showresults">
    <div v-if="this.noResults">
      There aren't any results for your input
    </div>
    <ul v-else>
      <li v-for="item in items" :key = item.id>
        <slot name="item" v-bind="item"/>
      </li>
    </ul>
  </div>
</template>

<script>
import {HOST, TOKEN} from "@/common/constants";
import axios from 'axios';

export default {
  name:"GenericSearchbar",
  props: { apiUrl: String,
    fieldName: String,
    additionalParams:String,
    showresults: Boolean
  },
  data() {
    return {
      items: [],
      searchInput: "",
      noResults: false,
      params: Object()
    }
  },
  created() {
  },
  methods: {
    refreshItems() {
      if(this.$props.additionalParams === undefined || this.$props.additionalParams === 'undefined'){
        this.$props.additionalParams = ""
      }
      if(this.searchInput.length>0) {
        this.params[this.$props.fieldName] = this.searchInput;
        axios.get(HOST + this.$props.apiUrl + "?" + this.fieldName + "=" + this.searchInput+this.$props.additionalParams,{headers:{energy_token: localStorage.getItem(TOKEN)}})
            .then(response => {
              this.items = response.data;
              console.log(response.data);
              this.noResults = (response.data.length === 0);
            });
      } else {
        this.items=[];
        this.noResults=false;
      }
    },

  }
}
</script>

<style scoped>
@import "@/assets/vertical-list-style.css";
</style>