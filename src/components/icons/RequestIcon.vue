<template>
  <el-dropdown>
    <span>
      <el-tooltip
          class="box-item"
          effect="light"
          content="邀请通知"
          placement="left-start"
      >
        <el-icon
            class="effected-icon"
            color="#50b5ff" :size="25">
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
                <mini-profile class="notify-profile"></mini-profile>
              </el-col>
              <el-col :span="12" style="padding-top: 3em;">
                <el-text multiple>{{notify.remark}}</el-text>
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
<script>
import {Bell, Message} from "@element-plus/icons-vue";
import MiniProfile from "@/components/icons/MiniProfile.vue";

export default {
  components: {Message, MiniProfile, Bell},
  data () {
    return {
      invitations: [
        {
          type: 'friend application',
          sender: {
            name: 'Bob',
            id: 'boblikeschatting',
          },
          time: '2024/7/11',
          remark: 'I want to make friends with you!'
        },
      ]
    }
  }
}
</script>

<style>

.notify-profile .user-info {
  flex-flow: row nowrap !important;
  align-self: start;
  margin-top: 3px;
}

</style>
