<template>
    <el-dialog v-model="isEdit" width="400px" @close="falseEdit">
        <el-form :model="form" label-width="60px" label-position="left">
            <el-form-item class="avatar-container ">
                <el-avatar
                    :size="100"
                    :src="phototUrl"
                    class="custom-avatar"
                    @click="handleAvatarClick"
                />
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
                <el-input v-model="form.location" autocomplete="off" :disabled="!isEdit" />
            </el-form-item>
            <el-form-item>
                <div class="dialog-footer">
                    <el-button @click="cancelEdit">取消</el-button>
                    <el-button type="primary" @click="confirmEdit">确认</el-button>
                </div>
            </el-form-item>
        </el-form>
    </el-dialog>
  </template>
  
  <script setup>
  import { reactive, computed } from 'vue';
  import { useStore } from 'vuex';
  
  const store = useStore();
  
  // 获取 Vuex store 中的 isEdit 状态
  const isEdit = computed(() => store.state.flag.isEdit);
  const myinfos = computed(() => store.state.myinfos.myinfos);

  const phototUrl = '/my_chatroom/'+myinfos.value.photo
  // 改变 isEdit 的值为 true
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
  </script>
  
  
  <style scoped>
  .avatar-container {
    display: flex;
    justify-content: center; /* 水平居中 */
    align-items: center; /* 垂直居中 */
    height: 100%; /* 适应容器高度 */
  }
  </style>