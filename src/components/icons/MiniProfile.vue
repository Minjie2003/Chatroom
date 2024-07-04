<template>
  <div class="profile-container">
    <div class="avatar-wrapper">
      <el-avatar :size="50" :src="phototUrl" class="custom-avatar" @click="handleAvatarClick"/>
    </div>
    <div class="user-info">
      <span class="user-name">{{ myinfos.username }}</span>
      <span class="user-id">{{ myinfos.mail }}</span>
    </div>
  </div>
</template>

<script>
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { computed } from 'vue'
export default {
  props: {
    avatar_url: {
      type: String,
      required: true
    },
    name: {
      type: String,
      required: true
    },
    id: {
      type: String,
      required: true
    }
  },
  //... 操作符用于将 mapState 返回的对象展开到当前组件的 computed 计算属性中,myinfos：模块名
  setup(){
    const store = useStore()
    const router = useRouter()  //创建一个router实例
    const handleAvatarClick = () =>{
      router.push("/profile");
    }
    const myinfos = computed(() => store.state.myinfos.myinfos);
    const phototUrl = '/my_chatroom/'+myinfos.value.photo
    return{
      handleAvatarClick,myinfos,phototUrl
    }
  }
}
</script>

<style scoped>
/* Container for the profile section */
.profile-container {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 10px;
  border-radius: 10px;
  transition: all 0.3s ease;
}

/* Avatar styling */
.custom-avatar {
  border: 2px solid #50b5ff;
  border-radius: 50%;
  transition: all 0.3s ease;
}

.custom-avatar:hover {
  border-color: #007BFF;
  box-shadow: 0 0 10px rgba(0, 123, 255, 0.5);
}

/* User information styling */
.user-info {
  display: flex;
  flex-flow: column;
}

.user-name {
  font-size: 16px;
  font-weight: bold;
  color: #333;
  transition: color 0.3s ease;
}

.user-id {
  font-size: 14px;
  color: #666;
  transition: color 0.3s ease;
}

.profile-container:hover .user-name {
  color: #007BFF;
}

.profile-container:hover .user-id {
  color: #50b5ff;
}
</style>
