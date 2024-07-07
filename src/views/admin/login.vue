<template>
  <div class="login-view-container" :style="{ backgroundImage: `url(${welcome})` }">
    <div class="header-container">
      <LoginManagerIdentify></LoginManagerIdentify>
    </div>
  </div>
</template>

<script setup>
import LoginManagerIdentify from '@/components/auth/LoginManagerIdentify.vue'
import welcome from "@/assets/images/welcome.jpg"
</script>

<style scoped>
.login-view-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-size: cover;
  background-position: center;
}

.header-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}


</style>

