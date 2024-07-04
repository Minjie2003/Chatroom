<template>
  <div class="background" :style="{ backgroundImage: `url(${welcome})` }">
    <div class="header-container">
      <PasswordReset></PasswordReset>
    </div>
  </div>
</template>

<script setup>
import PasswordReset from '@/components/auth/PasswordReset.vue'
import welcome from "@/assets/images/welcome.jpg"
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

.header-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}


</style>

