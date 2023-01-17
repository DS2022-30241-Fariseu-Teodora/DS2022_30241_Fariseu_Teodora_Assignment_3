import {HOST, TOKEN} from "@/common/constants";
import axios from "axios";

export const path = "devices/"
export function addDevice(device) {
    device.deviceModel = JSON.parse(device.deviceModel);
    console.log(device);
    axios.post(HOST + path + "addDevice", device,
        {headers:{energy_token: localStorage.getItem(TOKEN)}})
        .then(response => console.log(response.data))
        .catch(error => console.log(error));
}

export function associateDevice(data) {
    axios.put(HOST + path + "associateDevice", data,
        {headers:{energy_token: localStorage.getItem(TOKEN)}})
        .then(response => response.data)
        .catch(error => console.log(error));
}