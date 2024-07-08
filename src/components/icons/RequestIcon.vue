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
            :calss="{unreadMessage: hasNewEntry}"
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
          <el-dropdown-item>
            <el-text class="request-title">Requests</el-text>
          </el-dropdown-item>

          <el-dropdown-item
              v-for="request in crStore.getMergedRequests()" :key="request.id">

            <div class="request-container">

              <mini-profile
                  class="notify-profile"
                  :avatar_url="request.headPath"
                  :name="request.username || 'NoName'"
                  :user_id="request.accountNum"
                  style="grid-area: profile"
              />

              <!--                <mini-profile
                  class="notify-profile"
                  :avatar_url="getSenderAvatar(request.send_id)"
                  :name="getSenderName(request.send_id)"
                  :user_id="getSenderAccountNum(request.send_id)"
              ></mini-profile>-->
              <div class="category-tag" style="grid-area: category">
                <el-tag v-if="request.category === CR_Constant.FRIEND">Friend Request</el-tag>
                <el-tag v-if="request.category === CR_Constant.CHATROOM">Chatroom Request</el-tag>
              </div>

              <el-text class="request-remark" style="grid-area: remark" multiple>
                REMARK: {{ request.content || 'No remark'}}
              </el-text><!--advice.content-->

              <div class="edit-area" style="grid-area: edit">
                <div v-if="request.result === CR_Constant.PENDING"
                     class="pending">
                  <el-col :span="4" style="align-self: center">
                    <el-button type="primary" @click="handleAcceptClick(request)">Accept</el-button>
                  </el-col>
                  <el-col :span="4" style="align-self: center">
                    <el-button type="danger" @click="handleRefuseClick(request)">Refuse</el-button>
                  </el-col>
                </div>
                <div v-else class="state-tag">
                  <el-tag v-if="request.result === CR_Constant.ACCEPT"
                          type="success"
                  >
                    Accepted
                  </el-tag>
                  <el-tag v-if="request.result === CR_Constant.REFUSE"
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
import {Bell, Message} from "@element-plus/icons-vue";
import MiniProfile from "@/components/icons/MiniProfile.vue";
import {computed, onMounted, ref} from "vue";
import {CR_Constant, crStore, DefaultProfileData} from "@/store/crStore.js";
import axios from "axios";

const tip_visible = ref(false)

const mergedRequests = computed(() => {
  console.log(mergedRequests)
  return crStore.getMergedRequests()
})

const hasNewEntry = computed(() => {
  return crStore.getMergedRequests().length > 0;
})

const sendersData = ref({}); // Object to store sender data

onMounted(async () => {
  await crStore.fetchAndStoreRequests()
  console.log('requests',crStore.getMergedRequests())
  await fetchSendersData()
})

const fetchSendersData = async () => {
  for (const request of mergedRequests.value) {
    try {
      const response = await axios.post(`/my_chatroom/user/get_contact_info`,
          {id: request.send_id}, {
            headers: {'Content-Type': 'multipart/form-data'}
          }
      );
      sendersData.value[request.send_id] = response.data.data;
    } catch (error) {
      console.error(`Error fetching data for sender ${request.send_id}:`, error);
    }
  }
};

const readRequest = async (id) => {
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


const handleAcceptClick = (request) => {
  acceptAdvice(request.id)
}
const handleRefuseClick = (request) => {
  refuseAdvice(request.id)
}

const getSenderAvatar = (send_id) => {
  return sendersData.value[send_id]?.avatar_url || '';
};

const getSenderName = (send_id) => {
  return sendersData.value[send_id]?.username || 'Unknown';
};

const getSenderAccountNum = (send_id) => {
  return sendersData.value[send_id]?.accountNum || '';
};

</script>

<style>

.notify-profile .user-info {
  display: grid;
  align-self: start;
  margin-top: 2rem;
}

.request-title {
  font-size: 1rem;
  font-weight: bolder;
  width: 100%;
  text-align: center;
}

.request-container {
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
