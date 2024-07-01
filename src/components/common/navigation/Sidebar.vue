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

    <el-menu-item index="2" @click="$router.push('/profile')">
      <el-icon>
        <User/>
      </el-icon>
      <template #title>
        {{ $t('sidebar.profile') }}
      </template>
    </el-menu-item>

    <el-menu-item index="3" @click="$router.push('/chat')">
      <el-icon>
        <ChatLineRound/>
      </el-icon>
      <template #title>{{ $t('sidebar.chat') }}</template>
    </el-menu-item>

    <el-sub-menu index="4">
      <template #title>
        <el-icon>
          <Star/>
        </el-icon>
        <span>{{ $t('sidebar.list') }}</span>
      </template>
      <el-menu-item index="4-1" @click="$router.push('/list/friends')">
        <el-icon>
          <ChatLineRound/>
        </el-icon>
        <template #title>{{ $t('sidebar.friends') }}</template>
      </el-menu-item>
      <el-menu-item index="4-2" @click="$router.push('/list/groups')">
        <el-icon>
          <ChatDotRound/>
        </el-icon>
        <template #title>{{ $t('sidebar.groups') }}</template>
      </el-menu-item>
    </el-sub-menu>

    <el-menu-item index="5" @click="$router.push('/about')">
      <el-icon>
        <More/>
      </el-icon>
      <template #title>{{ $t('sidebar.about') }}</template>
    </el-menu-item>

    <el-menu-item index="6" @click="$router.push('/setting')">
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
  Setting, User, ChatLineRound, More, Star, HomeFilled
} from '@element-plus/icons-vue'

const isCollapse = ref(true)

</script>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 600px;
}
</style>
