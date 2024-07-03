<template>
  <user-layout has-logged-in="true">
    <div class="about">
      <h1>About This Project</h1>
      <p>
        This project is designed to showcase the power and flexibility of Vue 3. It includes various features and functionalities to help developers build modern web applications.
      </p>

      <h2>Developer Information</h2>
      <ul>
        <li><strong>Name:</strong> John Doe</li>
        <li><strong>Email:</strong> john.doe@example.com</li>
        <li><strong>GitHub:</strong> <a :href="developer.github" target="_blank">{{ developer.github }}</a></li>
        <li><strong>LinkedIn:</strong> <a :href="developer.linkedin" target="_blank">{{ developer.linkedin }}</a></li>
      </ul>

      <h2>Project Details</h2>
      <ul>
        <li><strong>Version:</strong> 1.0.0</li>
        <li><strong>License:</strong> MIT</li>
        <li><strong>Repository:</strong> <a :href="project.repo" target="_blank">{{ project.repo }}</a></li>
      </ul>
    </div>
  </user-layout>
</template>

<script>
import UserLayout from "@/components/user/UserLayout.vue";
export default {
  name: 'About',
  data() {
    return {
      developer: {
        name: 'John Doe',
        email: 'john.doe@example.com',
        github: 'https://github.com/johndoe',
        linkedin: 'https://linkedin.com/in/johndoe'
      },
      project: {
        version: '1.0.0',
        license: 'MIT',
        repo: 'https://github.com/johndoe/vue3-project'
      }
    };
  },
  components:{
    UserLayout
  }
};
</script>

<style scoped>
.about {
  padding: 20px;
}

h1, h2 {
  color: #333;
}

a {
  color: #42b983;
  text-decoration: none;
}

a:hover {
  text-decoration: underline;
}
</style>
