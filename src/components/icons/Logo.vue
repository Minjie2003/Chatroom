<template>
  <div class="logo-container">
    <el-image
        class="cr-logo"
        :src="app_logo"
        alt="App Logo"
    />
    <el-text class="cr-title">
      {{ app_name }}
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


.logo-container {
  display: flex;
  flex-flow: row nowrap;
  place-content: center;
  align-items: center;
  gap: 10px; /* Space between logo and text */
  padding: 10px;
  border-radius: 10px;
  transition: all 0.3s ease;
}

.cr-logo {
  --cr-logo-size: 45px;
  width: var(--cr-logo-size);
  height: var(--cr-logo-size);
  border-radius: 50%; /* Circular logo */
  transition: all 0.3s ease;
  box-shadow: 0 0 1rem rgba(150, 211, 211, 0.61);
  border: 2px solid rgba(125, 214, 222, 0.82)
}

@media (max-width: 600px) {
  .logo-container{
    flex-flow: column;
    padding: 3px;
    gap: 0;
  }
  .cr-logo {
    --cr-logo-size: 2rem;
    transition: all 0.5s ease;
  }
  .cr-title {
    font-size: 0.6rem;
    transition: all 0.5s ease;
  }
}

.cr-logo:hover {
  box-shadow: 0 0 10px rgba(0, 123, 255, 0.5);
}

.cr-title {
  --el-text-font-size: 24px;
  font-weight: bold;
  color: var(--cr-text-color);
  transition: color 0.3s ease;
}

.logo-container:hover .cr-title {
  color: var(--cr-text-hover-color);
}
</style>
