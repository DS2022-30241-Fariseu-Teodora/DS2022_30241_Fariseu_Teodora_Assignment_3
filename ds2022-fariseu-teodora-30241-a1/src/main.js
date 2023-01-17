import { createApp } from 'vue'
import App from './App.vue'
import RegisterPage from "@/home/RegisterPage";
import LoginForm from "@/customs/forms/LoginForm";
import { createWebHistory, createRouter } from "vue-router";
import WelcomePage from "@/home/WelcomePage";
import NewDeviceForm from "@/customs/forms/NewDeviceForm";
import ModelForm from "@/customs/forms/ModelForm";
import AllDevicesList from "@/devices/AllDevicesList";
import AllUsers from "@/accounts/AllUsers";
import ManageUserPage from "@/accounts/ManageUserPage";
import AllDeviceModels from "@/devices/AllDeviceModels";
import DevicePage from "@/devices/DevicePage";
import DailyEnergy from "@/consumption/DailyEnergy";
import DeviceConsumption from "@/consumption/DeviceConsumption";
import NotificationsPage from "@/notifications/NotificationsPage";
import MyDevices from "@/devices/MyDevices";
import TestPage from "@/chats/MyChats";
import MyChats from "@/chats/MyChats";

const routes = [
    { path: '/login', component: LoginForm },
    { path: '/register', component: RegisterPage },
    { path: '/', component: WelcomePage},
    { path: '/users', component: AllUsers},
    { path: '/users/:id', component: ManageUserPage},
    { path: '/models', component: AllDeviceModels},
    { path: '/models/:id',component: AllDevicesList},
    { path: '/devices', component: MyDevices },
    { path: '/device/:id', component: DevicePage },
    { path: '/devices/add', component: NewDeviceForm},
    { path: '/models/add', component: ModelForm},
    { path: '/energy', component: DailyEnergy},
    { path: '/notifications/:id', component: NotificationsPage},
    { path: '/energy/:id', component:DeviceConsumption},
    { path: '/chats', component: MyChats},
    { path: '/test', component: TestPage}
]

export const router = createRouter({
    history: createWebHistory(),
    routes,
})
createApp(App)
    .use(router).mount('#app')


