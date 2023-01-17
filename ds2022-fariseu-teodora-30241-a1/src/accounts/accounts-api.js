import {HOST, TOKEN} from "@/common/constants";
import axios from "axios";
export const path="users/"
import {router} from "@/main"
export function deleteAccount(account) {
    axios.delete(HOST + path + "deleteUser/"+account.username, {headers:{energy_token: localStorage.getItem(TOKEN)}})
        .then(response => {
            router.push("/users")
            console.log(response);
            router.go(1)
        });
}
export function editAccount(newDetails) {
    axios.put(HOST + path + "editUser",newDetails, {headers:{energy_token: localStorage.getItem(TOKEN)}})
        .then(response => console.log(response.data));
}