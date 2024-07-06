<template>
  <el-dialog v-model="dialogVisible" width="400px" @close="CloseDialog()">
    <el-form :model="formData" label-width="60px" label-position="left">
      <el-form-item class="avatar-container ">
        <el-avatar
            :size="100"
            :src="formData.avatar_url"
            class="custom-avatar"
            @click="handleAvatarClick"
        />
      </el-form-item>
      <el-form-item label="用户名">
        <el-input v-model="formData.username" autocomplete="off"/>
      </el-form-item>
      <el-form-item label="账号">
        <el-input :value="formData.accountNum" autocomplete="off" disabled/>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input :value="formData.mail" autocomplete="off" disabled/>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="formData.sex">
          <el-radio label="male" value="男">男</el-radio>
          <el-radio label="female" value="女">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="formData.location" autocomplete="off"/>
      </el-form-item>
      <el-form-item>
        <div class="dialog-footer">
          <el-button @click="CloseDialog()">Cancel</el-button>
          <el-button type="primary" @click="handleFormDataSubmit">Submit</el-button>
        </div>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script setup>
import {reactive, computed, ref} from 'vue';
import {useStore} from 'vuex';
import {CR_DEBUG_ON, modifyProfileDialogVisible, thisUser, toProfileData} from "@/store/cr_config.js";
import {ElMessage} from "element-plus";
import axios from "axios";

const dialogVisible = ref(modifyProfileDialogVisible)

const OpenDialog = () => dialogVisible.value = true
const CloseDialog = () => dialogVisible.value = false

const handleAvatarClick = () => {

}

/*sex: 男
location: 中国
username: 张三
id: 1*/
const updateUserInfo = (formData) => {
  axios.post('my_chatroom/user/update_userinfo', {
    id: thisUser.value.id,
    username: formData.username,
    location: formData.location,
    sex: formData.sex
  },{
    headers: {
      "Content-Type": "multipart/form-data"
    }
  })
  .then(res => {
    thisUser.value = res.data.data
    if (CR_DEBUG_ON) {
      ElMessage.warning('Updated User Info.')
    }
  })
  .catch(err => console.log(err))
}

const handleFormDataSubmit = () => {
  updateUserInfo(formData.value)
}

// 表单数据
const formData = ref({
  ...toProfileData(thisUser.value)
});

</script>


<style scoped>
.avatar-container {
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
  height: 100%; /* 适应容器高度 */
}
</style>
