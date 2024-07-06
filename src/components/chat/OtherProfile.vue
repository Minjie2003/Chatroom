<template>

  <profile :profile-data="otherInfo"></profile>

</template>

<script setup>
import {DefaultUserData} from "@/store/cr_config.js";
import {ref} from "vue";
import Profile from "@/components/Profile.vue";

const otherInfo = ref({
  ...DefaultUserData()
})


</script>


<style scoped>

</style>
