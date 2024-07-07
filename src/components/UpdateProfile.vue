<template>
    <el-dialog v-model="isEdit" width="400px" @close="falseEdit">
        <el-form :model="form" label-width="60px" label-position="left">
            <el-form-item class="avatar-container ">
                <el-upload
                    class="upload-demo"
                    :limit="1"
                    :before-upload="beforeUpload"
                    :auto-upload="false"
                    :show-file-list="false"
                    :on-change="handleChange"
                >
                <el-avatar
                    :size="100"
                    :src="photoUrl"
                    class="custom-avatar"
                />
                </el-upload>
            </el-form-item>
            <el-form-item label="用户名">
                <el-input v-model="form.username" autocomplete="off" :disabled="!isEdit" />
            </el-form-item>
            <el-form-item label="账号">
                <el-input :value="form.accountNum" autocomplete="off" disabled />
            </el-form-item>
            <el-form-item label="邮箱">
                <el-input :value="form.mail" autocomplete="off" disabled />
            </el-form-item>
            <el-form-item label="性别">
            <el-radio-group v-model="form.sex" :disabled="!isEdit">
                <el-radio label="male" value="男">男</el-radio>
                <el-radio label="female" value="女">女</el-radio>
            </el-radio-group>
            </el-form-item>
            <el-form-item label="地址">
                <el-input v-model="form.location" autocomplete="off" :disabled="!isEdit" @keyup.enter="confirmEdit"/>
            </el-form-item>
            <el-form-item>
                <div class="dialog-footer">
                    <el-button @click="assignData">取消</el-button>
                    <el-button type="primary" @click="confirmEdit">确认</el-button>
                </div>
            </el-form-item>
        </el-form>
    </el-dialog>
  </template>
  
  <script setup>
  import { reactive, computed } from 'vue';
  import { useStore } from 'vuex';
  import axios from 'axios';
  import { ElMessage } from 'element-plus';
  import getuserinfos  from "@/utils/getuserinfos"; 
  const store = useStore();
  
  // 获取 Vuex store 中的 isEdit 状态
  const isEdit = computed(() => store.state.flag.isEdit);
  const myinfos = computed(() => store.state.myinfos.myinfos);
  const photoUrl = '/my_chatroom/'+myinfos.value.photo
  

  // 改变 isEdit 的值为 false
  const trueEdit = () => {
    store.dispatch('flag/updateIsEdit', true);
  };
  // 改变 isEdit 的值为 false
  const falseEdit = () => {
    store.dispatch('flag/updateIsEdit', false);
  };
  
  // 表单数据
  const form = reactive({
    username: myinfos.value.username,
    accountNum:myinfos.value.accountNum,
    mail: myinfos.value.mail,
    sex: myinfos.value.sex,
    location: myinfos.value.location
  });
  
  function assignData(){
    form.username = myinfos.value.username,
    form.accountNum=myinfos.value.accountNum,
    form.mail= myinfos.value.mail,
    form.sex= myinfos.value.sex,
    form.location=myinfos.value.location
  }
  
  //校验非空数据
  function isNull(){
    if(form.username === ''){
        ElMessage.error("请输入用户名")
        return false;
    }else if(form.location === ''){
        ElMessage.error("请输入地址")
        return false;
    }
    return true;
  }
  const confirmEdit=() =>{
    if(!isNull()){
        return;
    }
    axios.post("/my_chatroom/user/update_userinfo", {
        sex: form.sex,
        location: form.location,
        username: form.username,
        id: myinfos.value.id
    }, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    }).then(res=>{
        if(res.data.code === 200){
            ElMessage.success("修改成功")
            getuserinfos(store)
            // myinfos = computed(() => store.state.myinfos.myinfos);
            falseEdit()
        }else if(res.data.code === 0){
            ElMessage.error("输入参数有误")
        }
    }).catch(err => {
        console.error(err); // 打印错误信息
    });
  }
  const beforeUpload = (file) => {
      // 文件上传前的验证逻辑
      const isJPG = file.type === 'image/jpeg';
      const isPNG = file.type === 'image/png';
      const isLt2M = file.size / 1024 / 1024 < 1;
  
      if (!isJPG && !isPNG) {
        ElMessage.error('上传头像图片只能是 JPG 或 PNG 格式!');
        return false;
      }
      if (!isLt2M) {
        ElMessage.error('上传头像图片大小不能超过 1MB!');
        return false;
      }
      return true;
    };


    //实现文件上传的主要函数
const handleChange = (file) => {
    // 文件选择发生变化的处理逻辑
    const formData = new FormData();
    formData.append('photo', file.raw);

    axios.post('/my_chatroom/user/update_head', formData, {
    headers: {
        'Content-Type': file.type
    }
    }).then(response => {
        if(response.data.code === 200){
            ElMessage.success("头像更新成功,请等待响应")
            getuserinfos(store)
            console.log(myinfos.value.photo)
        }else if(response.data.data === 0){
            ElMessage.error("文件数据为空")
        }
    }).catch(error => {
        console.log(error)
    });
};
  </script>
  
  
  <style scoped>
  .avatar-container {
    display: flex;
    justify-content: center; /* 水平居中 */
    align-items: center; /* 垂直居中 */
    height: 100%; /* 适应容器高度 */
  }
  </style>
//   const handleChange = (file, fileList) => {
//     // 文件选择发生变化的处理逻辑
//     const formData = new FormData();
//     formData.append('file', file.raw);

//     axios.post('/api/upload', formData, {
//       headers: {
//         'Content-Type': 'multipart/form-data'
//       }
//     }).then(response => {
//       handleSuccess(response.data, file, fileList);
//     }).catch(error => {
//       console.error('上传失败:', error);
//       this.$message.error('上传失败，请重试');
//     });
//   };
