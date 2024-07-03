<template>
    <user-layout has-logged-in="true">
      <router-view></router-view>
    </user-layout>
</template>

<style>
:root {
  overflow: hidden;
}
</style>

<script setup>

import {ref} from "vue";
import Layout from "@/components/component/layout.vue";
import UserLayout from "@/components/user/UserLayout.vue";
const hasLoggedIn = ref('false')
</script>
