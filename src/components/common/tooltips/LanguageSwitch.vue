<template>
  <el-select
      v-model="locale"
      placeholder="选择语言"
      size="large"
      style="width: 240px"
      @change="onLanguageChange"
  >
    <el-option
        v-for="lang in languages"
        :key="lang.value"
        :label="lang.label"
        :value="lang.value"
    />
  </el-select>
</template>

<script>
import {i18n} from "@/main.js";
import supported from "@/i18n/config.js";

export default {
  data() {
    return {
      name: 'LanguageSwitch',
      locale: 'ch',
      languages: supported.languages,
    }
  },
  methods: {
    onLanguageChange() {
      i18n.global.locale = this.locale
    }
  }
}
</script>

<style scoped>

</style>
