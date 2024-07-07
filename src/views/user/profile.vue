<template>
  <profile :profile-data="toProfileData(userInfo)"></profile>
</template>

<script setup>
import Profile from "@/components/Profile.vue";
import {computed, onMounted, ref} from "vue";
import {thisUser, toProfileData, refreshUserInfo} from "@/store/crStore.js";

const userInfo = thisUser

onMounted(() => {
  refreshUserInfo()
})

</script>
