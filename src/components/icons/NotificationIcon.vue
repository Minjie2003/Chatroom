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
        <el-icon class="effected-icon"
                 :calss="{unreadMessage: hasNewEntry}"
                 :size="25" color="#50b5ff"
                 @mouseenter="tip_visible = true"
                 @mouseleave="tip_visible = false">
          <Bell/>
        </el-icon>
      </el-tooltip>
    </span>
    <template #dropdown>
      <el-scrollbar max-height="300px">

        <el-dropdown-menu style="min-width: 500px;">
          <el-dropdown-item>
            <el-text class="notification-title">Notifications</el-text>
          </el-dropdown-item>

          <el-dropdown-item
              v-for="notification in mergedNotifications">

            <div class="notification-container">

              <mini-profile
                  class="notify-profile"
                  :avatar_url="notification.headPath"
                  :name="notification.username"
                  :user_id="notification.accountNum"
                  style="grid-area: profile"
              />

              <div class="category-tag" style="grid-area: category">
                <el-tag v-if="notification.category === CR_Constant.WARNING"
                        type="warning">Warning</el-tag>
                <el-tag v-if="notification.category === CR_Constant.REPORT"
                type="danger">Report</el-tag>
              </div>

              <el-text class="notification-remark" style="grid-area: remark" multiple>
                REMARK: {{ notification.content }}
              </el-text><!--advice.content-->

              <div class="edit-area" style="grid-area: edit">
                <div v-if="notification.result === CR_Constant.PENDING"
                     class="pending">
                  <el-col :span="4" style="align-self: center">
                    <el-button type="primary" @click="handleAcceptClick(notification)">Accept</el-button>
                  </el-col>
                  <el-col :span="4" style="align-self: center">
                    <el-button type="danger" @click="handleRefuseClick(notification)">Refuse</el-button>
                  </el-col>
                </div>
                <div v-else class="state-tag">
                  <el-tag v-if="notification.result === CR_Constant.ACCEPT"
                          type="success"
                  >
                    Accepted
                  </el-tag>
                  <el-tag v-if="notification.result === CR_Constant.REFUSE"
                          type="danger"
                  >
                    Refused
                  </el-tag>
                </div>

              </div>
            </div>

          </el-dropdown-item>
        </el-dropdown-menu>
      </el-scrollbar>
    </template>
  </el-dropdown>
</template>

<script setup>
import {Bell} from "@element-plus/icons-vue";
import MiniProfile from "@/components/icons/MiniProfile.vue";
import {computed, onMounted, ref} from "vue";
import {CR_Constant, crStore} from "@/store/crStore.js";
import axios from "axios";

const tip_visible = ref(false)

const mergedNotifications = computed(() => {
  return crStore.getMergedNotifications()
})

const hasNewEntry = computed(() => {
  return mergedNotifications.value?.length > 0;
})

onMounted(async () => {
  await crStore.fetchNotifications()
})

const readAdvice = async (id) => {
  try {
    const res = await axios.post('my_chatroom/advice/read_advice',
        {id},
        {
          headers: {'Content-Type': 'multipart/form-data'}
        });
  } catch (err) {
    console.log(err)
  }
}
const acceptAdvice = async (id) => {
  try {
    const res = await axios.post('my_chatroom/advice/accept_advice',
        {id},
        {
          headers: {'Content-Type': 'multipart/form-data'}
        });
  } catch (err) {
    console.log(err)
  }
}

const refuseAdvice = async (id) => {
  try {
    const res = await axios.post('my_chatroom/advice/refuse_advice',
        {id},
        {
          headers: {'Content-Type': 'multipart/form-data'}
        });
  } catch (err) {
    console.log(err)
  }
}


const handleAcceptClick = (notification) => {
  acceptAdvice(notification.id)
}
const handleRefuseClick = (notification) => {
  refuseAdvice(notification.id)
}

</script>


<style scoped>



.notification-title {
  font-size: 1rem;
  font-weight: bolder;
  width: 100%;
  text-align: center;
}

.notification-container {
  display: grid;
  grid-template-columns: 2fr 3fr 2fr;
  grid-template-rows: 1fr 1fr;
  grid-template-areas:
  'profile category edit'
  'profile remark edit'
;
}

.unread-message{
  color: orangered;
}

</style>
