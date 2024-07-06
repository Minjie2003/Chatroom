<template>
  <div class="home-container">

    <div class="main-content">
      <el-carousel class="home-carousel"
          :interval="5000" arrow="always" height="400px">
        <el-carousel-item v-for="item in carouselItems" :key="item.id">
          <el-image :src="item.image" class="carousel-image" />
        </el-carousel-item>
      </el-carousel>

      <el-row :gutter="20" class="info-section">
        <el-col :span="8">
          <el-card class="user-card">
            <el-image src="src/assets/images/avatar-purple.png" class="user-avatar" />
            <div class="user-details">
              <h3>{{ user.name }}</h3>
              <p>{{ user.email }}</p>
              <el-button type="primary" @click="editProfile">Edit Profile</el-button>
            </div>
          </el-card>
        </el-col>

        <el-col :span="16">
          <el-card class="features-card">
            <h3>Features</h3>
            <el-row :gutter="20">
              <el-col :span="8">
                <el-card class="feature-item">
                  <el-icon><ChatDotRound /></el-icon>
                  <span>Real-time Chat</span>
                </el-card>
              </el-col>
              <el-col :span="8">
                <el-card class="feature-item">
                  <el-icon><User /></el-icon>
                  <span>User Profiles</span>
                </el-card>
              </el-col>
              <el-col :span="8">
                <el-card class="feature-item">
                  <el-icon><Bell /></el-icon>
                  <span>Notifications</span>
                </el-card>
              </el-col>
            </el-row>
          </el-card>
        </el-col>
      </el-row>
    </div> <!--main-->

  </div>
</template>

<script setup>
import {reactive, ref} from 'vue';
import { ChatDotRound, User, Bell } from '@element-plus/icons-vue';
import {thisUser} from "@/store/cr_config.js";

const user = reactive({
  ...thisUser.value
})

const carouselItems = [
  { id: 1, image: 'src/assets/images/avatar-purple.png' },
  { id: 2, image: 'src/assets/images/miku-green.jpg' },
  { id: 3, image: 'src/assets/images/miku-pink.jpg' }
];


const editProfile = () => {
  console.log('Edit Profile clicked');
};
</script>

<style scoped>
.home-container {
  width: 100%;
  height: 100%;
}

.home-carousel {
  border-radius: 1em;
  box-shadow: 0 0 1em var(--cr-static-light-color);
}

.main-content {
  padding: 20px;
}

.carousel-image {
  width: 100%;
  height: 100%;
  object-fit: scale-down;
}

.info-section {
  margin-top: 20px;
}

.user-card {
  text-align: center;
}

.user-avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  margin-bottom: 10px;
}

.user-details h3 {
  margin: 10px 0;
}

.features-card {
  padding: 20px;
}

.feature-item {
  text-align: center;
  padding: 20px;
}

.footer {
  text-align: center;
  padding: 10px;
}

.el-card{
  margin: 0;
  padding: 0;
}
</style>
