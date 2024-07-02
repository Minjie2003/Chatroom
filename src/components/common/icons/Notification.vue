<script>
import {Bell} from "@element-plus/icons-vue";
import MiniProfile from "@/components/common/navigation/header/MiniProfile.vue";
export default {
  components: {Bell, MiniProfile},
  data() {
    return {
      notifications: [
        {
          type: 'friend application',
          sender: {
            name: 'Bob',
            id: 'boblikeschatting',
            avatar_url: 'src/assets/images/chatroom_logo.png'
          },
          time: '2024/7/11',
          remark: 'I want to make friends with you!'
        },
      ]
    }
  }
}
</script>

<template>
  <el-dropdown>
    <span>
      <el-tooltip
          class="box-item"
          effect="light"
          content="系统消息"
          placement="left"
      >
        <el-icon class="interactive-icon" :size="25" color="#50b5ff">
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
                <mini-profile class="notify-profile"></mini-profile>
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
