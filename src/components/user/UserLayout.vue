<template>
  <div class="main-layout">
    <el-container style="padding: 0;margin: 0;">
      <el-header :height="header_height" style="padding: 0;" v-if="hasLoggedIn">
        <el-affix position="top" :offset="0">
          <Header></Header>
        </el-affix>
      </el-header>

      <el-container>
        <el-affix position="top" :offset="sidebar_offset">
          <el-aside :width="sidebar_width" v-if="hasLoggedIn">
            <Sidebar></Sidebar>
          </el-aside>
        </el-affix>

        <el-main style="padding: 0; margin: 0;" >
          <slot></slot>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>

import Sidebar from "@/components/common/navigation/Sidebar.vue";
import Header from "@/components/common/navigation/header/Header.vue";
import {boolean} from "mockjs/src/mock/random/basic.js";
export default {
  props: {
    hasLoggedIn: {
      type: boolean,
    }
  },
  data() {
    return {
      header_height: "80px",
      sidebar_width: "200px",
      sidebar_offset: 80,
    }
  },
  components: {Header, Sidebar}
}
</script>

