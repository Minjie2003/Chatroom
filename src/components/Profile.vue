<template>
  <el-card class="profile" id="MainProfile">
    <div class="background" :style="{ backgroundImage: `url(${backgroundImage})` }">
      <el-avatar
          class="avatar"
          :src="phototUrl"  
          @mouseover="isHover = true"
          @mouseleave="isHover = false"
          :class="{'hover-image':isHover}"       
      />
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
      <el-text class="cr-title user-name">{{ myinfos.username }}</el-text>
          <div class="edit-icon" @click="trueEdit">
            <el-icon size="25" class="icon-hover"><Edit /></el-icon>
            <span>点击修改</span>
          </div>
          <update-profile></update-profile>
      <el-descriptions>
        <el-descriptions-item :label="$t('profile.gender')">{{ myinfos.sex }}</el-descriptions-item>
        <el-descriptions-item :label="$t('profile.email')">{{ myinfos.mail }}</el-descriptions-item>
        <el-descriptions-item :label="$t('profile.address')">{{ myinfos.location }}</el-descriptions-item>
        <el-descriptions-item label="生日">{{ myinfos.birthday }}</el-descriptions-item>
        <el-descriptions-item label="注册时间">{{ myinfos.createTime || 'NULL' }}</el-descriptions-item>
        <el-descriptions-item label="图片">{{ myinfos.photo }}</el-descriptions-item>
      </el-descriptions>
    </div>
    <div class="additional-info">
      <el-card>
        <h3>{{ $t('profile.signature') }}</h3>
        <p>{{ signature }}</p>
      </el-card>
    </div>
  </el-card>
  
</template>

<script setup>
import {ref,reactive,computed } from 'vue'
import { useStore } from 'vuex'
import UpdateProfile from './UpdateProfile.vue'
const store = useStore()   //创建一个store的实例
const isHover = ref(false)
const backgroundImage = 'src/assets/images/lake.png';
const avatar_url = 'src/assets/images/avatar-yellow.png';
const myinfos = computed(()=>store.state.myinfos.myinfos)
const phototUrl = '/my_chatroom/'+myinfos.value.photo
const signature = "田文镜，我*****"
  // 获取 Vuex store 中的 isEdit 状态
const isEdit = computed(() => store.state.flag.isEdit);

  // 改变 isEdit 的值为 true
  const trueEdit = () => {
    store.dispatch('flag/updateIsEdit', true);
  };
  
  // 改变 isEdit 的值为 false
  const falseEdit = () => {
    store.dispatch('flag/updateIsEdit', false);
  };
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
.hover-image {
  scale: 1.1;
}
.edit-icon {
  margin: 10px; /* 可以根据需要调整图标和文本之间的间距 */
  text-align: right;
  align-items: center;
}
h2{
  text-align: center;
  margin: 0%;
}
.edit-icon:hover{
  color: green;
}
</style>