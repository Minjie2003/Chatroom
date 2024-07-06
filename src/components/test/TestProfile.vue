<script setup>
import Profile from "@/components/Profile.vue";
import {DefaultProfileData} from "@/store/crStore.js";
import {ref} from "vue";

const thisUserProfile = ref({
  ...DefaultProfileData(),
  username: 'Renamed-TEST',
})

</script>

<template>
  <profile :profile-data="thisUserProfile"/>
</template>

<style scoped>

</style>
