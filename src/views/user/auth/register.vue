<template>
  <div class="user-register">
    <h2>用户注册</h2>
    <el-form :model="registerForm" @submit.prevent="handleSubmit">
      <el-form-item label="用户名">
        <el-input v-model="registerForm.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input type="password" v-model="registerForm.password" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item label="确认密码">
        <el-input type="password" v-model="registerForm.confirmPassword" placeholder="请确认密码"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="registerForm.email" placeholder="请输入邮箱"></el-input>
        <el-button type="primary" @click="handleSubmit">注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { ElMessage } from 'element-plus';

const registerForm = ref({
  username: '',
  password: '',
  confirmPassword: '',
  email: ''
});

const handleSubmit = () => {
  if (!registerForm.value.username || !registerForm.value.password || !registerForm.value.confirmPassword || !registerForm.value.email) {
    ElMessage.error('请填写所有字段');
    return;
  }
  if (registerForm.value.password !== registerForm.value.confirmPassword) {
    ElMessage.error('两次输入的密码不一致');
    return;
  }
  // 处理注册逻辑
  ElMessage.success('注册成功');
};
</script>

<style scoped>
.user-register {
  max-width: 300px;
  margin: 0 auto;
}
</style>
