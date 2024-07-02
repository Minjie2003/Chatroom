<template>
  <img :src="captchaSrc" alt="captcha" @click="refreshCaptcha" style="width: 110px; height: 40px;">
</template>

<script setup>
import axios from 'axios';
import { ref } from 'vue';
const captchaSrc = ref('');

function refreshCaptcha() {
  axios.get("my_chatroom/user/get_verify", {
      responseType: 'blob',// 设置响应类型为 blob，表示获取的是二进制数据
    })
    .then(response => {
      captchaSrc.value = URL.createObjectURL(response.data); // 使用this.captchaSrc访问Vue组件中的数据
    })
    .catch(error => {
      console.error('Failed to refresh captcha:', error); // 输出更详细的错误信息
    });
}

// 初始化加载验证码
refreshCaptcha();
</script>
