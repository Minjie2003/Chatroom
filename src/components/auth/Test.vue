<template>
  <el-menu
      default-active="1"
      class="side-menu"
      :collapse="isCollapse"
  >
    <el-menu-item index="1" @click="change1">
      <el-icon>
        <User/>
      </el-icon>
      <template #title>{{$t('manager.user_management')}}</template>
    </el-menu-item>

    <el-menu-item index="2" @click="change2">
      <el-icon><ChatRound /></el-icon>
      <template #title>
        {{$t('manager.user_management')}}
      </template>
    </el-menu-item>

    <el-menu-item index="3" @click="change3">
      <el-icon><Warning /></el-icon>
      <template #title>{{$t('manager.report_management')}}</template>
    </el-menu-item>

  </el-menu>
</template>

<script setup>
import {ref,computed} from 'vue'
import {useStore} from 'vuex'
const isCollapse = ref(false)
const store = useStore()
const adminFlag = computed(() => store.state.flag.adminFlag)
const change1=()=>{
  store.dispatch("flag/updateAdminFlag",1)
}
const change2=()=>{
  store.dispatch("flag/updateAdminFlag",2)
}
const change3=()=>{
  store.dispatch("flag/updateAdminFlag",3)
}
</script>

<style>
.side-menu{
  flex-basis: 9vw;
  min-width: 50px;
  width: 9vw;
  flex-shrink: 1;
  min-height: 100vh;
  border-style: none;
}
</style>
