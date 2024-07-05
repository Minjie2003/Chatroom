<template>
  <user-layout has-logged-in=true>
    <div class="about-container">
      <h2>{{ $t('about.title')}}</h2>
      <p>{{ $t('about.p1') }}</p>

      <h1>{{ $t('about.h_group_info') }}</h1>
      <p>{{ $t('about.p_group_info')}}</p>

      <ul>
        <li><strong>{{ $t('about.b_github')}}</strong>
          <a :href="$t('about.github_link')" target="_blank">
            {{ $t('about.github_link') }}</a></li>
      </ul>
    </div>
  </user-layout>
</template>

<script>
import UserLayout from "@/components/UserLayout.vue";
export default {
  name: 'About',

  components:{
    UserLayout
  }
};
</script>

<style scoped>
.about-container {
  display: grid;
  height: 60vh;
  align-content: center;
  padding-left: 4em;
  gap: 2em;
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
