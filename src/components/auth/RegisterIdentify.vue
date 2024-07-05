<template>
    <div>
      <el-card class="register-card">
        <h2 class="register-title">用户注册</h2>
        <el-form
          :model="registerForm"
          label-width="80px"
          label-position="left"
          class="register-form"
        >
          <el-row>
            <el-col :span="24">
              <el-form-item label="账号" prop="username">
                <el-input v-model="registerForm.accountNum" placeholder="请输入账号"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="registerForm.password" placeholder="请输入密码"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="确认密码" prop="confirmpassword">
                <el-input type="password" v-model="registerForm.confirm_password" placeholder="请确认密码"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="邮箱" prop="email">
                <el-input type="email" v-model="registerForm.email" placeholder="请输入邮箱"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="14">
              <el-form-item label="验证码" prop="verify">
                <el-input v-model="registerForm.mailverify" placeholder="请输入验证码" maxlength="6"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9">
                <div class="right-content">
                    <el-button type="success" @click="emailverify">获取验证码</el-button>
                </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="18">
              <div class="left-content">
                <router-link to="/auth/login" class="link" @mouseover="hoverLink('forgotPassword', true)" @mouseleave="hoverLink('forgotPassword', false)">
                  已有账号，点击登录
                </router-link>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="right-content">
                <el-button type="primary" @click="handleSubmit">注册</el-button>
              </div>
            </el-col>
          </el-row>
        </el-form>
      </el-card>
    </div>
  </template>

<script setup>
import { ref } from 'vue'
import { reactive } from 'vue';
import { ElMessage } from 'element-plus'
import {useRouter } from 'vue-router'
import axios from 'axios';

const router = useRouter();
const registerForm = reactive({
  accountNum: '',
  password: '',
  confirm_password:'',
  email:'',
  mailverify:'',
});

const isButtonDisabled = ref(false)  //计时器的启用
const countdown = ref(0) //计时器的时间,后续里面调整
const time = ref(null)
//验证基础的字段是否为空或者合法
const validateInput = () => {
  if (registerForm.accountNum.trim() === '') {
    ElMessage.error('请输入账号');
    return false;
  }
  if (registerForm.password.trim() === '') {
    ElMessage.error('请输入密码');
    return false;
  }
  if (registerForm.confirm_password.trim() === '') {
    ElMessage.error('请输入确认的密码');
    return false;
  }
  if (registerForm.confirm_password.trim() !== registerForm.password.trim()) {
    ElMessage.error('两次密码不一致');
    return false;
  }
  if (registerForm.email.trim() === '') {
    ElMessage.error('请输入邮箱');
    return false;
  }
  if (!validateEmail(registerForm.email.trim())) {
    ElMessage.error('请输入合法的邮箱');
    return false;
  }
  if (registerForm.mailverify.trim() === '') {
    ElMessage.error('请输入验证码');
    return false;
  }
  return true;
};
//检验邮箱的正则表达式
function validateEmail(email) {
    const re = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return re.test(email);
}

const emailverify = () =>{
    if(isButtonDisabled.value){
        ElMessage.error("请勿重复获取验证码,"+countdown.value+"秒后重试");
        return;
    }
    if (registerForm.email.trim() === '') {
        ElMessage.error('请输入邮箱');
        return;
    }
    if (!validateEmail(registerForm.email.trim())) {
        ElMessage.error('请输入合法的邮箱');
        return ;
    }
    axios.get('/my_chatroom/user/get_reg_mail_verify?mail=' + registerForm.email)
    .then( res => {
        const temp = res.data.code
        if(temp === 200){
            ElMessage.success('验证码已发送！')
            startTimer();
        }else if(temp === 1){
            ElMessage.error('邮箱不存在')
        }else if(temp === 2){
            ElMessage.error('邮箱已被注册');
        }
    }).catch(err => {
        console.log(err);
    })
}
//待实现的计时器
const startTimer = () => {
  countdown.value = 60; // 设置倒计时时间（单位：秒）
  isButtonDisabled.value=true;
  time.value = setInterval(() => {
    countdown.value--;
    if (countdown.value > 0) {
    } else {
      clearInterval(time.value);
      isButtonDisabled.value = false;
    }
  }, 1000);
};

//处理提交信息
const handleSubmit = () => {

  if (!validateInput()) {
    return; // 如果有字段为空或者输入无效，则直接返回，不执行后续逻辑
  }

  axios.post("/my_chatroom/user/reg", registerForm,{
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
    .then(function(res){
      let tem = res.data.code
      if(tem === 1){
        ElMessage.error('账号已被注册，请重新输入');
      }else if(tem === 2){
        ElMessage.error('验证码错误');
      }else if(tem === 200){
        ElMessage.success('注册成功')
        clearInterval(time.value);
        router.push("/auth/login")
      }
    })
    .catch(err => {
      console.error(err); // 打印错误信息
    });
};


//样式
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
</script>

<style scoped>

.register-card {
  width: 400px;
  padding: 0px;
}

.register-title {
  text-align: center;
  margin-bottom: 20px;
}

.register-form {
  max-width: 300px;
  margin: 0 auto;
}

/* 栅格布局调整 */
.el-row {
  display: flex;
  justify-content: space-between;
}

.el-col {
  margin-bottom: 10px;
}

.divider {
  margin: 0 5px;
}

.link {
  cursor: pointer;
}

.link:hover {
  color: #e3ba4c; /* 悬浮时的颜色 */
  text-decoration: underline; /* 悬浮时的下划线 */
}
.left-content {
  text-align: left;
}

.right-content {
  text-align: right;
}
</style>
