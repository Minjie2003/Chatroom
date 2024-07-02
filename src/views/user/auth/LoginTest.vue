<template>
  <div class="login">
    <h2>登录</h2>
    <form @submit.prevent="handleSubmit">
      <div>
        <label for="accountNum">账号：</label>
        <input type="text" v-model="accountNum" id="accountNum" required />
      </div>
      <div>
        <label for="password">密码：</label>
        <input type="password" v-model="password" id="password" required />
      </div>
      <div>
        <label for="verify">验证码：</label>
        <input type="text" v-model="verify" id="verify" required />
        <img :src="captchaUrl" @click="refreshCaptcha" alt="captcha">
      </div>
      <button type="submit">登录</button>
    </form>
    <p v-if="error">{{ error }}</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      accountNum: '',
      password: '',
      verify: '',
      captchaUrl: '/my_chatroom/user/get_verify',
      error: ''
    };
  },
  methods: {
    // In your login component
    handleSubmit() {
      axios.post('/my_chatroom/user/login', {
        accountNum: this.accountNum,
        password: this.password,
        verify: this.verify
      })
          .then(response => {
            if (response.data.success) {
              // 登录成功，执行后续逻辑
              alert('登录成功！');
              // 你可以将用户信息存储到本地存储或 Vuex 状态管理
            } else {
              // 登录失败，显示错误信息
              this.error = response.data.message;
            }
          })
          .catch(error => {
            console.error('登录请求失败:', error);
            this.error = '登录请求失败，请稍后再试。';
          });
    },
    refreshCaptcha() {
      this.captchaUrl = '/my_chatroom/user/get_verify?' + new Date().getTime();
    }
  }
};
</script>

<style>
/* 添加一些样式使页面更美观 */
.login {
  max-width: 400px;
  margin: 0 auto;
  padding: 1em;
  border: 1px solid #ccc;
  border-radius: 4px;
}
.login h2 {
  margin-bottom: 1em;
}
.login form div {
  margin-bottom: 1em;
}
.login form label {
  display: block;
  margin-bottom: 0.5em;
}
.login form input {
  width: 100%;
  padding: 0.5em;
  box-sizing: border-box;
}
.login form button {
  width: 100%;
  padding: 0.5em;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
.login form button:hover {
  background-color: #45a049;
}
.login p {
  color: red;
}
</style>
