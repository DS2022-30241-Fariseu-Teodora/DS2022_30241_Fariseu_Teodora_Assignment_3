import {HOST, TOKEN} from "@/common/constants";
import axios from "axios";
export const path="chat/"

export function create(participants) {
    axios.post(HOST + path + "newChat", JSON.stringify(participants)
    , {
        headers:{'Content-Type': 'application/json',energy_token: localStorage.getItem(TOKEN)}})
        .then(response => console.log(response.data));
}