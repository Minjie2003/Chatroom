<script setup>
import {Bell} from "@element-plus/icons-vue";
import MiniProfile from "@/components/icons/MiniProfile.vue";
import {ref} from "vue";
import {thisUser} from "@/store/cr_config.js";

const tip_visible = ref(false)
const tu = thisUser

</script>

<template>
  <el-dropdown>
    <span>
      <el-tooltip
          class="box-item"
          effect="light"
          content="系统消息"
          placement="left"
          :visible="tip_visible"
      >
        <el-icon class="effected-icon" :size="25" color="#50b5ff"
                 @mouseenter="tip_visible = true"
                 @mouseleave="tip_visible = false">
          <Bell/>
        </el-icon>
      </el-tooltip>
    </span>
    <template #dropdown>
      <el-scrollbar max-height="300px">
        <el-dropdown-menu style="min-width: 500px;">
          <el-dropdown-item
              v-for="notify in notifications">
            <el-row justify="space-evenly">
              <el-col :span="4">
                <mini-profile class="notify-profile"
                              :avatar_url="this.tu.avatar_url"
                              :name="this.tu.username"
                              :user_id="this.tu.accountNum"
                ></mini-profile>
              </el-col>
              <el-col :span="12" style="padding-top: 3em;">
                <el-text multiple>{{ notify.remark }}</el-text>
              </el-col>
              <el-col :span="4" style="align-self: center">
                <el-button type="primary">知晓</el-button>
              </el-col>
              <el-col :span="4" style="align-self: center">
                <el-button type="danger">处理</el-button>
              </el-col>
            </el-row>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-scrollbar>
    </template>
  </el-dropdown>
</template>

<style scoped>

</style>
