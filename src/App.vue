<template>
  <div>
    <h1>{{ $t('sidebar.home')}}</h1>
    <button @click="toEnglish">English</button>
    <button @click="toChinese">中文</button>
    <button @click="toJapanese">日本語</button>

    <RouterLink to="/">Home</RouterLink>
    <RouterView/>
  </div>

</template>

<script>

export default {
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

