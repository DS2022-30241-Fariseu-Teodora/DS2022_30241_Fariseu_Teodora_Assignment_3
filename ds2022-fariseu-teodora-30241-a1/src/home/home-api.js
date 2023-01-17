import {HOST, TOKEN} from "@/common/constants"
import axios from "axios";
import {router} from "@/main";

export function login(credentials) {
    axios.post(HOST +"login",credentials )
        .then(response => {
            localStorage.setItem(TOKEN, response.data);
            router.push("/")
            router.go(1)
        })
        .catch(error => {
            console.log(error)
        });
}
export function register(details) {
    axios.post(HOST +"registerAccount", details)
        .then(response => response)
        .catch(error => error.message)
}