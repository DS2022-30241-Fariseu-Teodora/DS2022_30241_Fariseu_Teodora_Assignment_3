<template>
  <div style="padding: 40px">
    <div style="font-size: 25px;color: red" v-if="!this.hasData">There aren't any values registered for the selected day</div>
    <Datepicker v-model="date" :format="'MM/dd/yyyy'" @update:modelValue="this.daySelected"></Datepicker>
    <Line
        id = "chart"
        :height="100"
        :chart-options="chartOptions"
        :chart-data="chartData"
    />
  </div>
</template>

<script>
import { Line } from 'vue-chartjs'
import { Chart as ChartJS, Title, Tooltip, Legend, LineElement, CategoryScale, LinearScale, PointElement } from 'chart.js'
import axios from "axios";
import {HOST, TOKEN} from "@/common/constants";
import Datepicker from '@vuepic/vue-datepicker';
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import '@vuepic/vue-datepicker/dist/main.css';
import {path} from "@/consumption/energy-api";
import moment from 'moment';
ChartJS.register(Title, Tooltip, Legend, LineElement, CategoryScale, LinearScale, PointElement)
var stompClient = null;
export default {
  name: 'CustomChart',
  components: { Line,Datepicker },
  props:{
    chartTitle:String,
    dataUrl: String,
    deviceId: String
  },
  data() {
    return {
      xVals:[],
      yVals: [],
      maxVals: 20,
      hasData: true,
      path: "",
      date: null,
      socket: null,
      chartData: {
        labels: [],
        datasets: [{
          label: this.$props.chartTitle,
          data: [],
          fill: false,
          borderColor: 'rgb(75, 192, 192)',
          tension: 0.1
        }]
      },
      chartOptions: {
        responsive: true
      }
    }
  },
  methods: {
    daySelected(date) {

      this.path = path + this.$props.dataUrl  +  moment(String(date)).format('DD-MM-yyyy')
      this.getData()
    },
    getData() {
      this.chartData.datasets[0].data = []
      this.chartData.labels = []
      axios.get(HOST+ this.path, {headers:{energy_token: localStorage.getItem(TOKEN)}})
          .then( response => {
            this.xVals = response.data.map(point => point.label)
            this.yVals = response.data.map(point => point.amount)
            if (this.xVals.length >= this.maxVals) {
              this.xVals = this.xVals.slice(this.yVals.length-this.maxVals,this.xVals.length)
              this.yVals = this.yVals.slice(this.yVals.length-this.maxVals,this.yVals.length)
            }
            this.chartData.datasets[0].data = this.yVals
            this.chartData.labels = this.xVals
            this.hasData = response.data.length > 0
          })
    },
    connect() {
      this.socket = new SockJS(HOST+'ws-message')
      stompClient = Stomp.over(this.socket);
      stompClient.connect({}, frame => {
        console.log("Connected to "+ frame);
        stompClient.subscribe('/topic/device/'+this.$props.deviceId, val => {
          console.log();
          var decoded = JSON.parse(val.body);
          if (this.xVals.length === this.maxVals){
            this.xVals.shift()
            this.yVals.shift()
          }
          this.xVals.push(decoded.label)
          this.yVals.push(decoded.amount)
          this.chartData.datasets[0].data = this.yVals
          this.chartData.labels = this.xVals
        });
      });
      this.socket.onclose = function() {
        console.log('close');
        stompClient.disconnect();
      };
    },
    disconnect: function(){
      if(this.socket !== null) {
        this.socket.close()
      }
    }
  },
  beforeMount() {
    this.path = path + this.$props.dataUrl
    this.getData()
  },
  created: function() {
    if(this.$props.deviceId !== undefined)
      this.connect()
  },
  beforeUnmount() {
    this.disconnect()
    console.log("Closing connection to WebSocket Server")
  }
}
</script>
