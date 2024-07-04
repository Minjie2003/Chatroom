<template>
  <el-card class="profile" id="MainProfile">
    <div class="background" :style="{ backgroundImage: `url(${backgroundImage})` }">
      <el-image
          class="avatar"
          :src="avatar_url"
          :alt="$t('profile.avatar')"
          fit="cover"
      ></el-image>
    </div>
    <!-- accountNum: "Minjie2003",
            birthday: "2024-07-03",
            createTime: "2024-07-03T18:03:39",
            id: 16,
            location: "中国",
            mail: "1276679294@qq.com",
            password: "123",
            photo: "head/默认头像.png",
            sex: "男",
            state: 2,
            updateTime: null,
            username: "新用户" -->
    <div class="userinfo">
      <el-text class="cr-title user-name">{{ store.myinfos.username }}</el-text>
      <el-descriptions>
        <el-descriptions-item :label="$t('profile.gender')">{{ store.myinfos.sex }}</el-descriptions-item>
        <el-descriptions-item :label="$t('profile.email')">{{ store.myinfos.mail }}</el-descriptions-item>
        <el-descriptions-item :label="$t('profile.address')">{{ store.myinfos.location }}</el-descriptions-item>
        <el-descriptions-item label="生日">{{ store.myinfos.birthday }}</el-descriptions-item>
        <el-descriptions-item label="注册时间">{{ store.myinfos.createTime || 'NULL' }}</el-descriptions-item>
      </el-descriptions>
    </div>
    <div class="additional-info">
      <el-card>
        <h3>{{ $t('profile.signature') }}</h3>
        <p>{{ userInfo.signature }}</p>
      </el-card>
    </div>
  </el-card>
</template>

<script setup>
import { reactive } from 'vue';
import store from '@/store/store.js'

const backgroundImage = 'src/assets/images/lake.png';
const avatar_url = 'src/assets/images/avatar-yellow.png';

const userInfo = reactive({
  name: 'John Doe',
  id: 'johndoe001',
  gender: 'Male',
  email: 'john.doe@example.com',
  address: 'Sichuan, China',
  telephone: '333444455555',
  signature: 'This is a sample note.',
});

</script>

<style scoped>
.profile {
  text-align: center;
  height: 100vh;
  padding: 5px 5px;
}

#MainProfile {
  border-style: none;
}

.background {
  position: relative;
  width: 100%;
  height: 50vh;
  border-radius: 0.5rem 0.5rem 0 0;
  border-bottom-style: none;
  background-size: cover;
  background-position: center;
  margin-bottom: 5px;
}

.avatar {
  position: absolute;
  bottom: -50px;
  left: 50%;
  transform: translateX(-50%);
  width: 150px;
  height: 150px;
  border-radius: 50%;
  border: 3px solid #fffefe;
}

.userinfo {
  margin-top: 60px;
}

.additional-info {
  margin-top: 15px;
  text-align: left;
}

.additional-info h3 {
  margin-bottom: 5px;
}

.additional-info p {
  margin-bottom: 20px;
}
</style>
