<template>
  <div class="homepage">
    <el-container>
      <el-header>
        <el-select
            v-model="locale"
            placeholder="选择语言"
            size="large"
            style="width: 240px"
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
        <el-main>Main</el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>

import Sidebar from "@/components/Reusabl/Sidebar.vue";
import {watch} from "../../../.vite/deps/vue.js";

export default {
  components: {Sidebar},
  data() {
    return {
      languages: [
        {
          value: 'en',
          label: 'English'
        },
        {
          value: 'zh',
          label: '中文'
        },
        {
          value: 'ja',
          label: '日本語'
        }
      ],
      locale: 'zh'
    }
  },
  methods: {}
}
</script>
