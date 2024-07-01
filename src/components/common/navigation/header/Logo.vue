<template>
  <div class="logo-display" :span="6">
    <el-image
        class="custom-logo"
        :src="app_logo"
        alt="App Logo"
    />
    <el-text
        class="app-name"
        size="large"
    >
      Chatroom
    </el-text>
  </div>
</template>

<script>
export default {
  name: 'Logo',
  props: {
    app_logo: {
      type: String,
      required: true,
    },
    app_name: {
      type: String,
      required: true
    }
  },
};
</script>

<style scoped>
.logo-display {
  display: flex;
  align-items: center;
  gap: 10px; /* Space between logo and text */
  padding: 10px;
  border: 2px solid #50b5ff;
  border-radius: 10px;
  background-color: #f9f9f9;
  transition: all 0.3s ease;
}

.logo-display:hover {
  border-color: #007BFF;
  box-shadow: 0 0 10px rgba(0, 123, 255, 0.5);
}

.custom-logo {
  width: 50px;
  height: 50px;
  border-radius: 50%; /* Circular logo */
  transition: all 0.3s ease;
}

.custom-logo:hover {
  box-shadow: 0 0 10px rgba(0, 123, 255, 0.5);
}

.app-name {
  font-size: 18px;
  font-weight: bold;
  color: #333;
  transition: color 0.3s ease;
}

.logo-display:hover .app-name {
  color: #007BFF;
}
</style>
