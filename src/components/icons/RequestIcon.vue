<template>
  <el-dropdown>
    <span>
      <el-tooltip
          class="box-item"
          effect="light"
          content="邀请通知"
          placement="left-start"
          :visible="tip_visible"
      >
        <el-icon
            class="effected-icon"
            color="#50b5ff" :size="25"
            @mouseenter="tip_visible = true"
            @mouseleave="tip_visible = false"
        >
          <Message/>
        </el-icon>
      </el-tooltip>
    </span>
    <template #dropdown>
      <el-scrollbar max-height="300px">

        <el-dropdown-menu style="min-width: 500px;">
          <el-dropdown-item
              v-for="notify in invitations">
            <el-row justify="space-evenly">
              <el-col :span="4">
                <mini-profile class="notify-profile"
                              :avatar_url="sendUser.avatar_url"
                              :name="sendUser.username"
                              :user_id="sendUser.accountNum"
                ></mini-profile>
              </el-col>
              <el-col :span="12" style="padding-top: 3em;">
                <el-text multiple>{{ notify.remark }}</el-text>
              </el-col>
              <el-col :span="4" style="align-self: center">
                <el-button type="primary">Yes</el-button>
              </el-col>
              <el-col :span="4" style="align-self: center">
                <el-button type="danger">No</el-button>
              </el-col>
            </el-row>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-scrollbar>
    </template>
  </el-dropdown>
</template>

<script setup>
import {Bell, Message} from "@element-plus/icons-vue";
import MiniProfile from "@/components/icons/MiniProfile.vue";
import {computed, ref} from "vue";
import {DefaultProfileData} from "@/store/crStore.js";

const tip_visible = ref(false)
const sendUser = computed(() => {
      return DefaultProfileData()
    }
)

</script>

<style>

.notify-profile .user-info {
  flex-flow: row nowrap !important;
  align-self: start;
  margin-top: 3px;
}

</style>
