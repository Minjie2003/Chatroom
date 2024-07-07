<template>
  <el-dropdown>
    <span>
      <el-tooltip
          class="box-item"
          effect="light"
          content="切换账号"
          placement="left"
          :visible="tip_visible"
      >
      <el-icon
          class="effected-icon"
          :size="25" color="#50b5ff"
          @mouseenter="tip_visible=true"
          @mouseleave="tip_visible=false"
      >
        <SwitchButton/>
      </el-icon>
    </el-tooltip>
    </span>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item>
          <div @click="switchAccount">
            <el-icon size="25" color="#50b5ff">
              <Switch></Switch>
            </el-icon>
            <el-text>切换</el-text>
          </div>
        </el-dropdown-item>
        <el-dropdown-item>
          <div @click="logoutAccount">
            <el-icon size="25" color="#50b5ff">
            <SwitchButton></SwitchButton>
            </el-icon>
            <el-text>注销</el-text>
          </div>
        </el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>

<script setup>

import {Switch, SwitchButton} from "@element-plus/icons-vue";
import {ref} from "vue";
import { useRouter } from "vue-router";
import axios, { Axios } from "axios";
import { ElMessage } from "element-plus";
const router = useRouter()
const switchAccount=()=>{
  router.push("/auth/login")
}
const tip_visible = ref(false)
const logoutAccount=()=>{
  axios.get("/my_chatroom/user/logout")
  .then(res=>{
    const tem = res.data.code
    if(tem === 200){
      ElMessage.success("注销成功");
      switchAccount()
    }
  }).catch(err=>{
    ElMessage.error(err.data)
  })
}
</script>


<style scoped>

</style>
