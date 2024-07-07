<template>
  <div class="login-view-container" :style="{ backgroundImage: `url(${welcome})` }">
    <div class="header-container">
      <div class="home-page">
        <h1 style="color:white">Welcome ChatRoom</h1>
        <div class="links">
          <router-link to="/auth/register" class="button">注册</router-link>
          <router-link to="/auth/login" class="button">用户登录</router-link>
          <router-link to="/admin/login" class="button">管理员登录</router-link>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
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

.home-page {
  text-align: center;
  margin-top: 50px;
}

.links {
  display: flex;
  justify-content: center; /* 让按钮水平居中 */
  gap: 10px;
  margin-top: 20px;
}

.button {
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  text-decoration: none;
  border-radius: 5px;
  transition: background-color 0.3s;
  margin-right: 20px;
}

.button:hover {
  background-color: #0056b3;
}
</style>
