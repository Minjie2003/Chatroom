<template>
  <div class="application">
    <el-container>
      <el-header>
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
      </el-header>
      <el-container>
        <el-aside width="200px">
          <Sidebar></Sidebar>
        </el-aside>
        <el-main>
          <RouterView>
          </RouterView>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>

import Sidebar from "@/components/Reusabl/Sidebar.vue";
import {i18n} from "@/main.js";
import supported from "@/i18n/supported.js";
export default {
  components: {Sidebar},
  data() {
    return {
      languages: supported.languages,
      locale: 'ch'
    }
  },
  methods: {
    onLanguageChange () {
      i18n.global.locale = this.locale
    }
  }
}
</script>
