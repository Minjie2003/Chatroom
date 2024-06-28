<template>
  <div>
    <h1>Home Page</h1>
    <p>This a online chatting application.</p>
    <br>
    <button @click="goToLogin">Login</button>
  </div>
</template>

<script>
export default {
  methods: {
    goToLogin() {
      this.$router.push('/login');
    }
  }
}
</script>
