<template>
  <div class="header">

    <span id="pad1"></span>

    <div class="logo-container">
      <logo :app_logo="Chatroom.LOGO" :app_name="Chatroom.NAME"/>
    </div>

    <span id="pad2"></span>
    <div class="search-box-container">
      <div class="search-box">
        <el-input
            class="search-box-input"
            v-model="searchInput"
            placeholder="Searching..."
            maxlength="20"
        >
          <template #append>
            <el-button class="custom-button"
                       :icon="Search" style="color:  #50b5ff;"
                       @click="handleHeaderSearch"
            />
          </template>
        </el-input>
      </div>
    </div>

    <div class="mini-profile-container">
      <el-dropdown>
        <mini-profile
            :avatar_url="userInfo.avatar_url"
            :name="userInfo.username"
            :user_id="userInfo.accountNum"
        ></mini-profile>
        <template #dropdown>
          <div class="profile-scrollbar-container">
            <el-scrollbar max-height="60vh">
              <profile class="dropdown-profile" :profile-data="toProfileData(userInfo)"></profile>
            </el-scrollbar>
          </div>

        </template>
      </el-dropdown>
    </div>

    <div class="header-icons-container">
      <invitation-message/>
      <Notification/>
      <language-switch/>
      <account-switch/>
    </div>

    <span id="pad3"></span>

  </div>

  <el-dialog
      class="search-result-dialog"
      v-model="SearchResultDialogVisible"
      width="300px"
      title="Search Results"
  >
    <el-tabs v-model="activeTab">
      <el-tab-pane label="Users" name="users">
        <el-scrollbar class="result-scrollbar">

          <el-card v-for="user in searchResult.users" :key="user.id" class="result-entry">
            <div class="text">
              {{ user.username }} @({{ user.accountNum }})
            </div>
            <el-button @click="sendFriendRequest(user.id)">Send Friend Request</el-button>
          </el-card>

        </el-scrollbar>
      </el-tab-pane>

      <el-tab-pane label="Chatrooms" name="chatrooms">
        <el-scrollbar class="result-scrollbar">

          <el-card v-for="chatroom in searchResult.chatrooms" :key="chatroom.id" class="result-entry">
            <div class="text">
              {{ chatroom.name }} (Session ID: {{ chatroom.sessionId }})
            </div>
            <el-button @click="">Join Request</el-button>
          </el-card>

        </el-scrollbar>
      </el-tab-pane>
    </el-tabs>
  </el-dialog>

  <el-dialog class="remark-dialog" v-model="RemarkInputDialogVisible">
    <el-text>Remark</el-text>
    <el-input v-model="requestData.remark" label="remark"></el-input>
    <el-button @click="submitRequest">Confirm</el-button>
    <el-button @click="RemarkInputDialogVisible = false">Cancel</el-button>

  </el-dialog>

</template>

<script setup>
import MiniProfile from "@/components/icons/MiniProfile.vue";
import Logo from "@/components/icons/Logo.vue";
import InvitationMessage from "@/components/icons/RequestIcon.vue";
import Notification from "@/components/icons/NotificationIcon.vue";
import LanguageSwitch from "@/components/icons/LanguageSwitch.vue";
import AccountSwitch from "@/components/icons/AccountSwitch.vue";
import {Search} from "@element-plus/icons-vue";
import Profile from "@/components/Profile.vue";
import {computed, ref} from "vue";
import {Chatroom, CR_Constant, crStore, toProfileData} from "@/store/crStore.js";
import axios from "axios";
import {ElMessage} from "element-plus";


const searchInput = ref('')
const userInfo = computed(() => {
  return crStore.getUserInfo()
})

const searchResult = ref({
  users: [],
  chatrooms: [],
})

const SearchResultDialogVisible = ref(false)
const RemarkInputDialogVisible = ref(false)


const activeTab = ref('Users') /*Chatrooms*/

const requestData = ref({
  type: CR_Constant.FRIEND_REQUEST,
  id: null,
  remark: '',
})

const submitRequest = async () => {
  if (requestData.value.type === CR_Constant.FRIEND_REQUEST) {
    try {
      const res = await axios.post('my_chatroom/advice/friend_req',
          {receiveId: requestData.value.id, content: requestData.value.remark},
          {headers: {'Content-Type': 'multipart/form-data'}}
      );
      let code = res.data.code
      if(code === 200){
        ElMessage.success("好友请求发送成功");
        RemarkInputDialogVisible.value = false
      }else if(code ===0){
        ElMessage.warning("发送好友请求的receiveId未被后端接收到");
      }else if(code === 1) {
        ElMessage.warning("该用户已经是你的好友");
        //关闭小窗和大窗
        RemarkInputDialogVisible.value = false
      }
    } catch (err) {
      console.log(err)
    }
  }else if(requestData.value.type === CR_Constant.CHATROOM_REQUEST){

  }
}

const handleHeaderSearch = async () => {
  await ExactSearch(searchInput.value)
}

const ExactSearch = async (input) => {
  searchResult.value.users = await searchUserByKey(input)
  searchResult.value.chatrooms = await searchChatroomByKey(input)
  console.log(searchResult.value.users)
  console.log(searchResult.value.chatrooms)
  SearchResultDialogVisible.value = true
}

const sendFriendRequest = (user_id) => {
  requestData.value.type = CR_Constant.FRIEND_REQUEST
  requestData.value.id = user_id
  RemarkInputDialogVisible.value = true
}

const joinChatroom = (id) => {

}

const searchUserByKey = async (key) => {
  try {
    const response = await axios.post('my_chatroom/user/select_user', {key},
        {headers: {'Content-Type': 'multipart/form-data'}});
    return response.data.data; // Assuming the users list is in response.data.data
  } catch (error) {
    console.error('Error searching users:', error);
    return [];
  }
}

const searchChatroomByKey = async (key) => {
  try {
    const response = await axios.post('my_chatroom/chatroom/select_chatroom', {key},
        {headers: {'Content-Type': 'multipart/form-data'}});
    return response.data.data; // Assuming the users list is in response.data.data
  } catch (error) {
    console.error('Error searching users:', error);
    return [];
  }
}

</script>

<style>
.header {
  width: 100%;
  height: var(--cr-header-height);
  opacity: var(--cr-default-opacity);
  display: grid;
  grid-template-columns: 1fr 3fr 3fr 6fr 3fr 3fr 1fr;
  grid-template-areas: 'left logo span search avatar tool right';
  align-content: start;
  justify-content: center;
  position: sticky;
  top: 0;
  background-color: #DDDA;
  z-index: 99;
}

.header:hover {
  background-color: #EEED;
}

.header::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url("src/assets/images/transparent-bg.png");
  background-size: cover;
}

.header * {
  z-index: 99;
}

#pad1 {
  grid-area: left
}

#pad2 {
  grid-area: span
}

#pad3 {
  grid-area: right
}

.logo-container {
  grid-area: logo;
}

.search-box-container {
  grid-area: search;
  place-content: center;
}

.search-box {
  min-width: 120px;
  width: 70%;
  height: 70%;

}

.mini-profile-container {
  grid-area: avatar;
}

.dropdown-profile {
  width: 30vw;
  height: 100%;
}

.header-icons-container {
  grid-area: tool;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  justify-content: center;
  align-content: center;
}

/* add interactive effect for icons*/
.effected-icon {
  transition: transform 0.2s ease, color 0.2s ease;
  cursor: pointer;
}

.effected-icon:hover {
  transform: scale(1.3);
  color: #007bff;
  border-radius: 50%;
  box-shadow: 0 0 10px rgba(0, 123, 255, 0.5);
}

.effected-icon:active {
  transform: scale(1.1);
  color: #ff4500;
}

</style>

