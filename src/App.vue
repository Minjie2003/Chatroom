<template>
  <debug></debug>
  <cr-layout>
    <router-view class="has-bg"></router-view>
  </cr-layout>
</template>

<script setup>
import Debug from "@/components/debug/Debug.vue";
import CrLayout from "@/components/CrLayout.vue";
</script>

<style>

:root {
  --cr-text-color: #444F;
  --cr-basic-hover-color: rgba(0, 123, 255, 0.5);
  --cr-hot-hover-color: rgba(236, 125, 145, 0.78);
  --cr-light-hover-color: rgba(84, 164, 255, 0.5);
  --cr-box-shadow-color: rgba(0, 123, 255, 0.5);
  --cr-text-hover-color: #007BFF;
  --cr-static-light-color: rgba(117, 225, 243, 0.46);

  --cr-default-opacity: 0.8;

  --cr-icon-size: 50px;
}
</style>
