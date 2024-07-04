<template>
  <el-dropdown @command="onLanguageChange">
    <span>
      <el-tooltip
          class="box-item"
          effect="light"
          content="切换语言"
          placement="left"
          :visible="tip_visible"
      >
      <el-icon class="effected-icon" :size="25" color="#50b5ff"
          @mouseenter="tip_visible=true"
               @mouseleave="tip_visible=false"
      >
        <Guide/>
      </el-icon>
    </el-tooltip>
    </span>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item v-for="lang in languages" :command="lang.value">{{ lang.label }}</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>

<script>
import {i18n} from "@/main.js";
import supported from "@/i18n/config.js";
import {Guide} from "@element-plus/icons-vue";

export default {
  components: {Guide},
  data() {
    return {
      tip_visible: false,
      name: 'LanguageSwitch',
      locale: 'ch',
      languages: supported.languages,
    }
  },
  methods: {
    onLanguageChange(command) {
      this.locale = command
      i18n.global.locale = this.locale
    }
  }
}
</script>

<style scoped>

</style>
