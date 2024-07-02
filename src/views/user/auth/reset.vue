<template>
  <div class="forgot-password">
    <h2>忘记密码</h2>
    <el-form :model="forgotPasswordForm" @submit.prevent="handleSubmit">
      <el-form-item label="用户名">
        <el-input v-model="forgotPasswordForm.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="验证码">
        <el-input v-model="forgotPasswordForm.captcha" placeholder="请输入验证码"></el-input>
      </el-form-item>
      <el-button type="primary" @click="handleSubmit">提交</el-button>
    </el-form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { ElMessage } from 'element-plus';

const forgotPasswordForm = ref({
  username: '',
  captcha: ''
});

const handleSubmit = () => {
  if (!forgotPasswordForm.value.username || !forgotPasswordForm.value.captcha) {
    ElMessage.error('请填写所有字段');
    return;
  }
  // 处理忘记密码逻辑
  ElMessage.success('请求已提交');
};
</script>

<style scoped>
.forgot-password {
  max-width: 300px;
  margin: 0 auto;
}
</style>

