<template>
  <el-menu
      default-active="1"
      class="side-menu"
      :collapse="isCollapse"
  >
    <el-menu-item index="1" @click="$router.push('/home')">
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
        <Message/>
      </el-icon>
      <template #title>{{ $t('sidebar.chat') }}</template>
    </el-menu-item>

    <el-menu-item index="4" @click="$router.push('/list')">
      <el-icon>
        <Star/>
      </el-icon>
      <template #title>{{ $t('sidebar.list') }}</template>
    </el-menu-item>

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

<!--    <el-menu-item index="7" @click="isCollapse=!isCollapse">
      <el-icon v-if="isCollapse">
        <TurnOff></TurnOff>
      </el-icon>
      <el-icon v-else>
        <Open></Open>
      </el-icon>
      <template #title>Collapse/Expand</template>
    </el-menu-item>-->

  </el-menu>
</template>

<script setup>
import {ref} from 'vue'
import {
  Setting, User, More, Star, HomeFilled, TurnOff, Open, Message
} from '@element-plus/icons-vue'

const isCollapse = ref(false)

</script>

<style>
.side-menu{
  flex-basis: 15vw;
  min-width: 50px;
  width: 15vw;
  flex-shrink: 1;
  min-height: 100vh;
  border-style: none;
  background-color: transparent;
}

.side-menu > .el-menu-item:hover {
  background-color: rgba(238, 238, 238, 0.75);
  border-radius: 5px;
}
</style>
