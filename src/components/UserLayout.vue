<template>
  <div class="user-layout">
    <el-container>
      <el-header class="cr-header-container" style="padding: 0;">
        <Header/>
      </el-header>

      <el-container>

        <el-aside width="9vw">
          <Sidebar/>
        </el-aside>

        <el-main style="padding: 0;">
          <slot/>
        </el-main>

      </el-container>
    </el-container>
  </div>
</template>

<script>

import Sidebar from "@/components/navigation/SideMenu.vue";
import Header from "@/components/navigation/Header.vue";

export default {
  name: 'UserLayout',
  data() {
    return {
      sidebar_width: "200px",
    }
  },
  components: {Header, Sidebar}
}
</script>

<style>

:root {
  --cr-header-height: 60px;
  --cr-sidebar-width: 300px;
}

* {
  padding: 0;
  margin: 0;
}
</style>

