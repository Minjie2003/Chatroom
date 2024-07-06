<template>

  <profile :profile-data="otherInfo"></profile>

</template>

<script setup>
import {DefaultUserData} from "@/store/crStore.js";
import {ref} from "vue";
import Profile from "@/components/Profile.vue";

const otherInfo = ref({
  ...DefaultUserData()
})


</script>


<style scoped>

</style>
