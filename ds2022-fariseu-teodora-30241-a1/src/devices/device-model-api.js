import {HOST, TOKEN} from "@/common/constants";
import axios from "axios";

export const path = "devices/models/"
export function addModel(model) {
    axios.post(HOST + path + "addDeviceModel", model,{headers:{energy_token: localStorage.getItem(TOKEN)}})
        .then(response => console.log(response.data))
        .catch(error => console.log(error));
}