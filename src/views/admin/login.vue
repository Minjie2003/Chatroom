<template>
  <div class="background" :style="{ backgroundImage: `url(${imageUrl})` }">
    <div class="container">
      <LoginManagerIdentify></LoginManagerIdentify>
    </div>
  </div>
</template>

<script setup>
import imageUrl from "@/assets/images/homepage.jpg";
import LoginManagerIdentify from '@/components/Identify/LoginMangerIdentify.vue'
</script>

<style scoped>
.background {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-size: cover;
  background-position: center;
}

.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}


</style>
