<template>
<user-list
></user-list>
</template>

<script>
import UserList from "@/customs/lists/UserList";
export default {
  name: "UserSearch",
  components: {UserList},
  data(){
    return {
      results: Array(0)
    }
  }
}
</script>

<style scoped>

</style>