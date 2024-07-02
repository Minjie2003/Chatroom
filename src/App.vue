<template>
  <user-layout :hasLoggedIn="hasLoggedIn">
    <router-view></router-view>
  </user-layout>
</template>

<style>
:root {
  overflow: hidden;
}
</style>

<script setup>
import userLayout from "@/components/user/UserLayout.vue";
import {ref} from "vue";
const hasLoggedIn = ref('false')
</script>
