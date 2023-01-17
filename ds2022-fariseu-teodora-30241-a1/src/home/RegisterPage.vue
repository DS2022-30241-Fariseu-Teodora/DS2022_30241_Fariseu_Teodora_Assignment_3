<template class="content">
<register-form :user="this.user" :submit-name="'register'" @submit-form='this.registerAcc'></register-form>
</template>

<script>
import {register} from "@/home/home-api";
import RegisterForm from "@/customs/forms/RegisterForm";
export default {
  name: "RegisterPage",
  components: {RegisterForm},
  data() {
    return {
      user: {
        username:"",
        password:"",
        email:""
      }
    }
  },
  methods: {
    registerAcc(details) {
      register(details);
    }
  },
  created() {
    console.log(this.user)
    this.user = {
      username:"",
      password:"",
      email:""
    }
  }
}
</script>

<style scoped>
.content {
  padding: 20px;

}
</style>