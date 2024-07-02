<template>
  <div class="background" :style="{ backgroundImage: `url(${imageUrl})` }">
    <div class="container">
      <LoginIdentify></LoginIdentify>
    </div>
  </div>
</template>

<script setup>
import LoginIdentify from '@/components/Identify/LoginIdentify.vue'
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

