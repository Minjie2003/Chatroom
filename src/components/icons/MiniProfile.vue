<template>
  <div class="profile-container">
    <div class="avatar-wrapper">
      <el-avatar :size="50" :src="avatar_url" class="custom-avatar"/>
    </div>
    <div class="user-info">
      <span class="user-name">{{ name }}</span>
      <span class="user-id">{{ user_id }}</span>
    </div>
  </div>
</template>

<script>
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
    user_id: {
      type: String,
      required: true
    }
  },
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
