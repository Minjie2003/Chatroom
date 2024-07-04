<template>
  <el-dropdown>
    <span>
      <el-tooltip
          class="box-item"
          effect="light"
          content="切换账号"
          placement="left"
      >
      <el-icon
          class="effected-icon"
          :size="25" color="#50b5ff">
        <SwitchButton/>
      </el-icon>
    </el-tooltip>
    </span>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item>
          <el-icon size="25" color="#50b5ff">
            <Switch></Switch>
          </el-icon>
          <el-text>切换</el-text>
        </el-dropdown-item>
        <el-dropdown-item>
          <el-icon size="25" color="#50b5ff">
            <SwitchButton></SwitchButton>
          </el-icon>
          <el-text>注销</el-text>
        </el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>

<script setup>

import {Switch, SwitchButton} from "@element-plus/icons-vue";
</script>


<style scoped>

</style>
