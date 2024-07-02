<template>
  <el-card class="profile">
    <div class="background" :style="{ backgroundImage: `url(${backgroundImage})` }">
      <el-image
          class="avatar"
          :src="avatar_url"
          :alt="$t('profile.avatar')"
          fit="cover"
      ></el-image>
    </div>
    <div class="userinfo">
      <h2>{{ userInfo.name }}</h2>
      <el-descriptions>
        <el-descriptions-item :label="$t('profile.gender')">{{ userInfo.gender }}</el-descriptions-item>
        <el-descriptions-item :label="$t('profile.email')">{{ userInfo.email }}</el-descriptions-item>
        <el-descriptions-item :label="$t('profile.address')">{{ userInfo.address }}</el-descriptions-item>
        <el-descriptions-item :label="$t('profile.telephone')">{{ userInfo.telephone }}</el-descriptions-item>
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

<script>

export default {
  data() {
    return {
      backgroundImage: 'src/assets/images/lake.png',
      avatar_url: 'src/assets/images/avatar-yellow.png',
      userInfo: {
        name: 'John Doe',
        id: 'johndoe001',
        gender: 'Male',
        email: 'john.doe@example.com',
        address: 'Sichuan, China',
        telephone: '333444455555',
        signature: 'This is a sample note.',
      },
    };
  }
};
</script>

<style scoped>
.profile {
  text-align: center;
  padding: 20px 50px;
}

.background {
  position: relative;
  width: 100%;
  height: 300px;
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
  margin-top: 20px;
  text-align: left;
}

.additional-info h3 {
  margin-bottom: 5px;
}

.additional-info p {
  margin-bottom: 20px;
}
</style>
