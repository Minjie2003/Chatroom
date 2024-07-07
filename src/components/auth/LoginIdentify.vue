<template>
  <div>
    <el-card class="login-card">
      <h2 class="login-title">用户登录</h2>
      <el-form
          :model="loginForm"
          label-width="60px"
          label-position="left"
          class="login-form"
      >
        <el-row>
          <el-col :span="24">
            <el-form-item label="账号" prop="username">
              <el-input v-model="loginForm.username" placeholder="请输入账号"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="密码" prop="password">
              <el-input type="password" v-model="loginForm.password" placeholder="请输入密码"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="14">
            <el-form-item label="验证码" prop="captcha">
              <el-input v-model="loginForm.captcha" placeholder="请输入验证码" maxlength="5"
                        @keyup.enter="handleLoginSubmit"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <div class="right-content">
              <captcha-input></captcha-input>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="18">
            <div class="left-content">
              <router-link to="/auth/reset" class="link" @mouseover="hoverLink('forgotPassword', true)"
                           @mouseleave="hoverLink('forgotPassword', false)">
                忘记密码?
              </router-link>
              <span class="divider">|</span>
              <router-link to="/auth/register" class="link" @mouseover="hoverLink('register', true)"
                           @mouseleave="hoverLink('register', false)">
                马上注册
              </router-link>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="right-content">
              <el-button type="primary" @click="handleLoginSubmit">{{ $t('auth.login') }}</el-button>
            </div>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import {reactive} from 'vue';
import CaptchaInput from './CaptchaInput.vue'
import {ElMessage} from 'element-plus'
import axios from 'axios';
import {useRouter} from 'vue-router'
import {useStore} from 'vuex'
import {serialize} from "object-to-formdata";
import {crStore} from "@/store/crStore.js";

const store = useStore()
const loginForm = reactive({
  username: '',
  password: '',
  captcha: ''
});

const emit = defineEmits(['refreshData'])

const router = useRouter()
//验证基础的字段是否为空
const validateEmpty = () => {
  if (loginForm.username.trim() === '') {
    ElMessage.error('请输入账号');
    return false;
  }
  if (loginForm.password.trim() === '') {
    ElMessage.error('请输入密码');
    return false;
  }
  if (loginForm.captcha.trim() === '') {
    ElMessage.error('请输入验证码');
    return false;
  }
  return true;
};
const handleLoginSubmit = async () => {
  if (!validateEmpty()) {
    return; // 如果有字段为空，则直接返回，不执行后续逻辑
  }

  const renamedLoginData = {
    accountNum: loginForm.username,
    password: loginForm.password,
    verify: loginForm.captcha
  };

  ElMessage.info('Waiting for response.');
  const loginSuccess = await fetchUserInfo(renamedLoginData);

  if (loginSuccess) {
    await router.push("/home");
  }
};

const hoverLink = (linkName, isHover) => {
  const link = document.querySelector(`.${linkName}`);
  if (isHover) {
    link.style.color = '#1890ff'; // 悬浮时的颜色
    link.style.textDecoration = 'underline'; // 悬浮时的下划线
  } else {
    link.style.color = ''; // 恢复默认颜色
    link.style.textDecoration = ''; // 恢复默认下划线
  }
};

const fetchUserInfo = async (query_data) => {
  try {
    const res = await axios.post("/my_chatroom/user/login", query_data, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });

    const tem = res.data.code;
    if (tem === 1 || tem === 3) {
      ElMessage.error('账号或密码错误');
      return false;
    } else if (tem === 2) {
      ElMessage.error('该账号已被封禁');
      return false;
    } else if (tem === 4) {
      ElMessage.error('验证码错误');
      return false;
    } else if (tem === 200) {
      ElMessage.success('登录成功');

      await store.dispatch('myinfos/updateMyInfos', res.data.data);
      crStore.setUserInfo(res.data.data);
      console.log('fetchUserInfo');
      console.log(crStore.userInfo);
      return true;
    }
  } catch (err) {
    console.error(err);
    ElMessage.error('登录失败，请稍后再试');
    return false;
  }
};

</script>
<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: rgba(255, 255, 255, 0.1); /* Slightly transparent background */
  backdrop-filter: blur(10px); /* Blur effect for a frosted glass look */
}

.login-card {
  width: 400px;
  padding: 20px;
  border-radius: 10px;
  background: rgba(255, 255, 255, 0.8); /* Slightly transparent card background */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Subtle shadow */
}

.login-title {
  text-align: center;
  margin-bottom: 20px;
  font-size: 24px;
  color: #333;
}

.login-form {
  display: flex;
  flex-direction: column;
}

.el-form-item {
  margin-bottom: 15px;
}

.right-content {
  display: flex;
  align-items: center;
  justify-content: center;
}

.left-content {
  display: flex;
  align-items: center;
}

.link {
  color: #409eff;
  text-decoration: none;
}

.link:hover {
  text-decoration: underline;
}

.divider {
  margin: 0 10px;
  color: #ccc;
}

.el-input {
  width: 100%;
}

.el-button {
  width: 100%;
}

.el-card {
  padding: 20px;
}

.el-form-item__label {
  color: #666;
}

.el-input__inner {
  border-radius: 5px;
  border: 1px solid #dcdfe6;
}

.el-input__inner:focus {
  border-color: #409eff;
}

.el-button--primary {
  background-color: #409eff;
  border-color: #409eff;
}

.el-button--primary:hover {
  background-color: #66b1ff;
  border-color: #66b1ff;
}
</style>
