<template>
  <profile :profile-data="toProfileData(userInfo)"></profile>
</template>

<script setup>
import Profile from "@/components/Profile.vue";
import {computed, onMounted} from "vue";
import {thisUser, toProfileData, refreshUserInfo, crStore} from "@/store/crStore.js";

const userInfo = computed(() => crStore.getUserInfo())

onMounted(() => {
  refreshUserInfo()
})

</script>
