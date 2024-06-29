<template>
  <el-radio-group v-model="isCollapse" style="margin-bottom: 20px">
    <el-radio-button :value="false">expand</el-radio-button>
    <el-radio-button :value="true">collapse</el-radio-button>
  </el-radio-group>

  <el-menu
      default-active="1"
      class="el-menu-vertical-demo"
      :collapse="isCollapse"
  >
    <el-menu-item index="1" @click="$router.push('/welcome')">
      <el-icon>
        <HomeFilled/>
      </el-icon>
      <template #title>{{ $t('sidebar.home') }}</template>
    </el-menu-item>
    <el-sub-menu index="2">
      <template #title>
        <el-icon>
          <User/>
        </el-icon>
        <span>{{ $t('sidebar.profile') }}</span>
      </template>
      <el-menu-item-group>
        <template #title><span>Group One</span></template>
        <el-menu-item index="1-1">item one</el-menu-item>
        <el-menu-item index="1-2">item two</el-menu-item>
      </el-menu-item-group>
      <el-menu-item-group title="Group Two">
        <el-menu-item index="1-3">item three</el-menu-item>
      </el-menu-item-group>
      <el-sub-menu index="1-4">
        <template #title><span>item four</span></template>
        <el-menu-item index="1-4-1">item one</el-menu-item>
      </el-sub-menu>
    </el-sub-menu>
    <el-menu-item index="3" @click="$router.push('/chat')">
      <el-icon>
        <ChatLineRound/>
      </el-icon>
      <template #title>{{ $t('sidebar.chat') }}</template>
    </el-menu-item>
    <el-menu-item index="4" @click="$router.push('/friends')">
      <el-icon>
        <Star/>
      </el-icon>
      <template #title>{{ $t('sidebar.friends') }}</template>
    </el-menu-item>
    <el-menu-item index="5" @click="$router.push('/about')">
      <el-icon>
        <More/>
      </el-icon>
      <template #title>{{ $t('sidebar.about') }}</template>
    </el-menu-item>
    <el-menu-item index="6" @click="$router.push('/settings')">
      <el-icon>
        <setting/>
      </el-icon>
      <template #title>{{ $t('sidebar.setting') }}</template>
    </el-menu-item>

  </el-menu>
</template>

<script setup>
import {ref} from 'vue'
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting, User, ChatLineRound, More, Star, HomeFilled,
} from '@element-plus/icons-vue'
import Home from "@/views/common/application.vue";

const isCollapse = ref(true)

</script>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>
