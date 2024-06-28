<template>
  <div>
    <h1>{{ $t('sidebar.home')}}</h1>
    <button @click="toEnglish">English</button>
    <button @click="toChinese">中文</button>
    <button @click="toJapanese">日本語</button>

    <RouterLink to="/">Home</RouterLink>
    <Sidebar></Sidebar>
    <RouterView/>
  </div>

</template>

<script>
import Sidebar from "@/components/Reusabl/Sidebar.vue";

export default {
  components: {Sidebar},
  methods: {
    toEnglish () {
      this.$i18n.locale = 'en'
    },
    toChinese () {
      this.$i18n.locale = 'ch'
    },
    toJapanese () {
      this.$i18n.locale = 'ja'
    }
  }
}

</script>

