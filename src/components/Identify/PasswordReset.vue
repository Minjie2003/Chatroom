<template>
    <div v-show="!boolVerify">
      <el-card class="find-card">
        <h2 class="find-title">找回密码</h2>
        <el-form
          :model="findForm"
          label-width="80px"
          label-position="left"
          class="find-form"
        >
          <el-row>
            <el-col :span="24">
              <el-form-item label="账号" prop="username">
                <el-input v-model="findForm.username" placeholder="请输入账号"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="14">
              <el-form-item label="验证码" prop="verify">
                <el-input v-model="findForm.verify" placeholder="请输入验证码" maxlength="6"></el-input>
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
                  退回登录
                </router-link>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="right-content">
                <el-button type="primary" @click="handleSubmit">提交</el-button>
              </div>
            </el-col>
          </el-row>
        </el-form>
      </el-card>
    </div>
    <div v-show="boolVerify">
      <el-card class="find-card">
        <h2 class="find-title">更改密码</h2>
        <el-form
          :model="findForm"
          label-width="80px"
          label-position="left"
          class="find-form"
        >
        <el-row>
            <el-col :span="24">
                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="findForm.password" placeholder="请输入密码"></el-input>
                </el-form-item>
            </el-col>
            <el-col :span="24">
                <el-form-item label="确认密码" prop="confirmpassword">
                <el-input type="password" v-model="findForm.confirmpassword" placeholder="请确认密码"></el-input>
                </el-form-item>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="18">
                <div class="left-content">
                <router-link to="/auth/login" class="link" @mouseover="hoverLink('forgotPassword', true)" @mouseleave="hoverLink('forgotPassword', false)">
                    退回登录
                </router-link>
                </div>
            </el-col>
            <el-col :span="6">
                <div class="right-content">
                    <el-button type="primary" @click="changePassword">更改密码</el-button>
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
const findForm = reactive({
  username: '',   //账号，取错名字了，懒得改了
  verify:'',
  password:'',
  confirmpassword:''
});

const boolVerify = ref(false)
const isButtonDisabled = ref(false)  //计时器的启用
const countdown = ref(0) //计时器的时间,后续里面调整
const time = ref(null)
//验证基础的字段是否为空或者合法
const isNull = () => {
  if (findForm.username.trim() === '') {
    ElMessage.error('请输入账号');
    return false;
  }
  if (findForm.verify.trim() == '') {
    ElMessage.error('请输入验证码');
    return false;
  }
  return true;
};

const emailverify = () =>{
    if(isButtonDisabled.value){
        ElMessage.error("请勿重复获取验证码,"+countdown.value+"秒后重试");
        return;
    }
    if (findForm.username.trim() === '') {
        ElMessage.error('请输入账号');
        return;
    }

    axios.get('/my_chatroom/user/get_update_mail_verify?accountNum='+findForm.username)
    .then(res => {
        const temp = res.data.code
        console.log(temp);
        if(temp === 200){
            ElMessage.success('验证码已发送！')
            startTimer();
        }else if(temp === 1){
            ElMessage.error('账号不存在')
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
    const formData = new FormData();
    formData.append('accountNum', findForm.username.trim());
    formData.append('verify', findForm.verify.trim());

    if (!isNull()) {
        return; // 如果有字段为空或者输入无效，则直接返回，不执行后续逻辑
    }

    axios.post("/my_chatroom/user/identify", formData,{
        headers: {
        'Content-Type': 'multipart/form-data'
        }
    }).then(function(res){
      let tem = res.data.code
      if(tem === 1){
        ElMessage.error('验证码错误');
      }else if(tem === 2){
        ElMessage.error('用户未获取验证码');
      }else if(tem === 3){
        ElMessage.error('用户前后输入的验证码不一致');
      }else if(tem === 200){
        ElMessage.success('验证成功')
        clearInterval(time.value);
        boolVerify.value = true;
      }
    })
    .catch(err => {
      console.error(err); // 打印错误信息
    });
};

//验证密码
const isPassword = () => {
  if (findForm.password.trim() === '') {
    ElMessage.error('请输入密码');
    return false;
  }
  if (findForm.confirmpassword.trim() === '') {
    ElMessage.error('请重复输入密码');
    return false;
  }
  if (findForm.confirmpassword.trim() != findForm.confirmpassword.trim()) {
    ElMessage.error('两次输入的密码不一致');
    return false;
  }
  return true;
};

const changePassword = () =>{
    if(!isPassword){
        return;
    }
    const formData = new FormData()
    formData.append('password', findForm.password.trim());

    axios.post("/my_chatroom/user/update_password",formData,{
        headers: {
        'Content-Type': 'multipart/form-data'
        }
    }).then(function(res){
      let tem = res.data.code
      if(tem == 1){
            ElMessage.error("没有修改权限！");
        }else if(tem === 0){
            ElMessage.error("有输入框未输入！");
        }else if(tem === 3){
            ElMessage.error("原密码和新密码不能相同！")
        }else if(tem === 200){
            ElMessage.success('修改成功')
            router.push("/auth/login")
      }
    })
    .catch(err => {
      console.error(err); // 打印错误信息
    });
}
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

.find-card {
  width: 400px;
  padding: 0px;
}

.find-title {
  text-align: center;
  margin-bottom: 20px;
}

.find-form {
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
