<template>
    <router-view></router-view>
</template>

<style>
:root {
  overflow: hidden;
}
</style>

<script setup>

import {ref} from "vue";
const hasLoggedIn = ref('false')
</script>
