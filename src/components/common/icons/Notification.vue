<template>
  <el-dropdown>
    <span>
      <el-tooltip
          class="box-item"
          effect="light"
          content="好友添加 "
          placement="left-start"
      >
        <el-icon
            class="interactive-icon"
            color="#50b5ff" :size="25">
          <Bell/>
        </el-icon>
      </el-tooltip>
    </span>
    <template #dropdown>
      <el-scrollbar max-height="400px">

        <el-dropdown-menu style="min-width: 500px;">
          <el-dropdown-item
              v-for="notify in notifications">
            <el-row justify="space-evenly">
              <el-col :span="4">
                <mini-profile class="notify-profile"></mini-profile>
              </el-col>
              <el-col :span="12" style="padding-top: 3em;">
                <el-text multiple>{{notify.remark}}</el-text>
              </el-col>
              <el-col :span="3" style="align-self: center">
                <el-button type="primary">Yes</el-button>
              </el-col>
              <el-col :span="3" style="align-self: center">
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
import {Bell} from "@element-plus/icons-vue";
import MiniProfile from "@/components/common/navigation/header/MiniProfile.vue";

export default {
  components: {MiniProfile, Bell},
  data () {
    return {
      notifications: [
        {
          type: 'friend application',
          sender: {
            name: 'Bob',
            id: 'boblikeschatting',
          },
          time: '2024/7/11',
          remark: 'I want to make a friend with you!'
        },
        {
          type: 'friend application',
          sender: {
            name: 'Bob',
            id: 'boblikeschatting',
          },
          time: '2024/7/11',
          remark: 'I want to make a friend with you!'
        },{
          type: 'friend application',
          sender: {
            name: 'Bob',
            id: 'boblikeschatting',
          },
          time: '2024/7/11',
          remark: 'I want to make a friend with you!'
        },{
          type: 'friend application',
          sender: {
            name: 'Bob',
            id: 'boblikeschatting',
          },
          time: '2024/7/11',
          remark: 'I want to make a friend with you!'
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

.drop-down-menu {
  border-color: red !important;
}

</style>
