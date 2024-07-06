<template>
  <profile :profile-data="toProfileData(userInfo)"></profile>
</template>

<script setup>
import Profile from "@/components/Profile.vue";
import {computed, onMounted, ref} from "vue";
import {thisUser, toProfileData, getUserInfo} from "@/store/cr_config.js";

const userInfo = thisUser

onMounted(() => {
  getUserInfo()
})

</script>
