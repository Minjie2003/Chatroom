<template>
  <div class="common-layout">
    <el-container>
      <el-header >Header</el-header>
      <el-container>
        <el-aside width="200px">
          <Sidebar></Sidebar>
        </el-aside>
        <el-main>Main</el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>

import Sidebar from "@/components/Reusabl/Sidebar.vue";

</script>

<style scoped>
.common-layout .el-container {
  height: 500px;
}
.common-layout .el-aside {
  background-color: #00adef;
}
.common-layout .el-header {
  background-color: #0b2e13;
}

</style>
