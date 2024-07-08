<template>
  <div class="chat-outer-panel" ref="chatPanel">
    <el-row class="title-container" justify="center" @click="OtherInfoDialogVisible = true">
      <el-text class="conversation-title" size="large">
        {{ selected_conversation?.nickName ?? 'No Conversation Selected' }}
      </el-text>
    </el-row>

    <el-row>
      <div class="chat-inner-panel">
        <div class="scrollbar-container">
          <el-scrollbar class="msg-scrollbar" ref="msg_scrollbar" v-infinite-scroll="handleMessageScroll">
            <div>
              <el-alert type="warning"
                        class="no-message" style="font-size: 2em; margin-top: 2em;"
                        v-show="message_list?.length === 0"> No Message.
              </el-alert>
              <el-alert type="error" v-if="selected_conversation?.isDeleted">
                You have quit the conversation. You can review the history messages but
                can't send new messages.
              </el-alert>
            </div>

            <div
                style="display: grid; align-content: center; align-items: center;"
                v-for="(msg, index) in message_list"
                :key="msg.id"
                ref="msg_bubble"
            >
              <div class="message-row-wrapper">

                <div class="message-time">
                  {{ formatMessageTime(new Date(msg.sendTime) , index > 0 ? new Date(message_list[index - 1].sendTime) : null) }}
                </div>
                <!-- Other Message -->
                <div class="message-row"
                     v-if="msg?.username !== user_info?.username ">
                  <span></span>

                  <el-avatar
                      class="message-avatar other-avatar"
                      :src="msg?.headPath">
                  </el-avatar>

                  <div style="display: flex; flex-flow: column nowrap; align-content: start;">
                    <el-text style="align-self: start;">{{ msg?.username }}</el-text>

                    <div class="message-bubble other-bubble">
                      <el-text v-if="msg.category === 0" class="other-message message-text">{{ msg.content }}</el-text>
                      <el-image v-if="msg.category === 1" class="other-message message-image"
                                style="padding: 1em; width: 10em; height: 10em;
                               min-width: 5em; min-height: 5em;"
                                :src="msg.content" :alt=" 'A picture at' + msg.content "/>
                    </div>
                  </div>

                  <span></span>
                  <span></span>
                </div>

                <!-- My Message-->
                <div class="message-row"
                     v-else>
                  <span></span>
                  <span></span>
                  <div>
                    <div style="display: flex; flex-flow: column nowrap; align-content: end;">
                      <el-text style="align-self: end;">{{ msg?.username }}</el-text>
                      <div class="message-container me-bubble" style="align-self: end;">
                        <el-text v-if="msg.category === 0" class="my-message message-text">{{ msg.content }}</el-text>
                        <el-image v-if="msg.category === 1" class="my-message message-image"
                                  style="padding: 1em; width: 10em; height: 10em;
                               min-width: 5em; min-height: 5em;"
                                  :src="msg.content" :alt=" 'A picture at' + msg.content "/>
                      </div>
                    </div>
                  </div>
                  <el-avatar
                      class="message-avatar my-avatar"
                      :src="msg.headPath">
                  </el-avatar>
                  <span></span>
                </div>
              </div>

            </div>
          </el-scrollbar>
        </div>
      </div>
    </el-row>

    <!--Edit Area-->
    <div class="edit-area">
      <div class="toolbar">
        <el-icon class="effected-icon" :size="25" color="#0BFD">
          <Share/>
        </el-icon>
        <el-icon class="effected-icon" :size="25" color="#0BFD">
          <Files/>
        </el-icon>
        <el-icon class="effected-icon" :size="25" color="#0BFD" @click="handlePictureClick">
          <Picture/>
        </el-icon>
        <el-icon class="effected-icon" :size="25" color="#0BFD">
          <Camera/>
        </el-icon>
        <el-icon class="effected-icon" :size="25" color="#0BFD">
          <Location/>
        </el-icon>
        <el-icon class="effected-icon" :size="25" color="#0BFD">
          <Setting/>
        </el-icon>
        <el-icon class="effected-icon" :size="25" color="#0BFD">
          <Plus/>
        </el-icon>
      </div>

      <div>
        <!--        <el-button class="send-button" @click="fetchMsgList">Refresh</el-button>-->
        <el-button class="send-button" @click="handleSendClick">Send</el-button>
      </div>

      <el-input class="message-input" v-model="messageInput" ref="messageInputRef"
                maxlength="1024" show-word-limit
                placeholder="Input Message to Send..."
                clearable :autosize="{minRows: 1, maxRows: 3}"
                type="textarea"
      />
    </div>

    <el-dialog class="otherInfoDialog" v-model="OtherInfoDialogVisible">
      <div class="other-info-container">
        <el-text class="other-info-title">Other Info</el-text>
        <el-text class="other-name">{{ other_info?.username }}</el-text>
        <div class="nickname-edit">
          <el-text>备注</el-text>
          <el-input v-model="nicknameInput"
                    :placeholder="other_info?.nickName"
                    :value="other_info?.nickName"
          />
          <el-button @click="handleNicknameModify">Modify</el-button>
        </div>
      </div>
    </el-dialog>

    <el-dialog
        class="permission-dialog"
        title="Enter Password"
        v-model="PermissionDialogVisible"
        width="30%"
        @close="resetPermissionForm"
    >
      <el-form :model="permissionForm" ref="permission_form">
        <el-form-item label="Password" :rules="[{ required: true, message: 'Please input password', trigger: 'blur' }]">
          <el-input
              v-model="permissionForm.password"
              type="password"
              autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="PermissionDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="submitPermissionForm">Submit</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script setup>
import {Camera, Files, Location, Picture, Plus, Setting, Share} from "@element-plus/icons-vue";
import {crStore} from "@/store/crStore.js";
import {computed, onMounted, ref, watch} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";

const message_list = computed(() => crStore.messageList)
const selected_conversation = computed(() => crStore.selectedConversation)
const user_info = computed(() => crStore.userInfo)
const other_info = computed(() => crStore.otherInfo)


const messageInput = ref('')
const messageInputRef = ref(null)
const nicknameInput = ref('')

const OtherInfoDialogVisible = ref(false)
const PermissionDialogVisible = ref(false)
const permissionForm = ref({
  password: ''
})
const hasGotPermission = ref(false)
const hasFetchedAllMessage = ref(false)

const resetPermissionForm= () => {
  permissionForm.value.password = '';
}

const submitPermissionForm= async () => {
  try {
    const response = await axios.post('my_chatroom/user/get_permit', { password: permissionForm.value.password },
        {
          headers:{
            'Content-Type': 'multipart/form-data'
          }
        });
    if (response.data.code === 200) {
      ElMessage.success(response.data.message);
      PermissionDialogVisible.value = false;
      hasGotPermission.value = true
    } else {
      ElMessage.error(response.data.message);
    }
  } catch (error) {
    console.error('Error submitting password:', error);
    ElMessage.error('An error occurred while verifying the password');
  }
}

onMounted(() => {
  if(selected_conversation.value) {
    fetchMessageList()
  }
})

watch(() => crStore.selectedConversation, async (newConv, oldConv) => {
  fetchMessageList()
})

const fetchMessageList = () => {
  hasFetchedAllMessage.value =false
  axios.post('my_chatroom/message/get_message_list',
      {sessionId: selected_conversation.value?.sessionId},
      {
        headers: {
          "Content-Type": "multipart/form-data",
        }
      })
      .then(res => {
        let code = res.data.code
        if (code === 200) {
          crStore.setMessageList(res.data.data)
        } else {
        }
      })
      .catch(err => {
        console.log(err)
      })
}


const fetchRestMessage = async (sessionId) => {
  if(!hasGotPermission.value){
    PermissionDialogVisible.value = true
    ElMessage.warning('Get Permission First To View History')
    return
  }

  if(hasFetchedAllMessage.value) {
    return
  }
  try {
    const response = await axios.get('my_chatroom/message/get_rest_message', {
      params: {
        sessionId: sessionId
      },
      withCredentials: true // Ensure cookies are sent with the request if session-based authentication is used
    });

    const data = response.data;

    if (data.code === 200) {
      // Handle success
      console.log('Success:', data);
      ElMessage.success('Fetched Rest Message')
      return data.data; // Assuming the messages are in data.data
    } else {
      // Handle failure
      console.error('Error:', data.message);
      ElMessage.error(data.message || 'Failed to fetch rest messages');
      return null;
    }
  } catch (error) {
    // Handle error
    console.error('Error:', error);
    ElMessage.error('Request failed');
    return null;
  }
};

import { format, isToday, isYesterday, differenceInMinutes, isSameWeek, isSameYear } from 'date-fns';

const formatMessageTime = (currentMessageTime, previousMessageTime) => {
  const currentDateTime = new Date(currentMessageTime);
  const previousDateTime = previousMessageTime ? new Date(previousMessageTime) : null;

  if (previousDateTime && differenceInMinutes(currentDateTime, previousDateTime) < 3) {
    return null; // Return null if the time gap is smaller than 3 minutes
  }

  if (isToday(currentDateTime)) {
    return `Today ${format(currentDateTime, 'HH:mm')}`;
  }

  if (isYesterday(currentDateTime)) {
    return `Yesterday ${format(currentDateTime, 'HH:mm')}`;
  }

  if (isSameWeek(currentDateTime, new Date())) {
    return format(currentDateTime, 'EEEE HH:mm');
  }

  if (isSameYear(currentDateTime, new Date())) {
    return format(currentDateTime, 'MMM d, HH:mm');
  }

  return format(currentDateTime, 'yyyy-MM-dd HH:mm');
};

const handleMessageScroll = async () => {
  let rest = await fetchRestMessage(selected_conversation.value.sessionId)
  crStore.appendMessages(rest)
  hasFetchedAllMessage.value = true
}

const handleSendClick = () => {
  if (messageInput.value === '') {
    ElMessage.warning('Empty Message')
    return
  }
  if (crStore.selectedConversation === null) {
    ElMessage.error('No Conversation Selected')
    return
  }

  axios.post('my_chatroom/message/send_message',
      {
        content: messageInput.value,
        contactSessionId: selected_conversation.value?.id,
        sessionId: selected_conversation.value?.sessionId
      }, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then(res => {
        if (res.data.code === 200) {
          ElMessage.success('Send Success')
          messageInput.value = ''
          messageInputRef.value.value = ''
        }
        fetchMessageList()
      }).catch(err => console.log(err))
}


const handleNicknameModify = () => {
  if (nicknameInput.value === '') {
    ElMessage.error('Empty Nickname Not Allowed. ')
    return
  }
  axios.post('my_chatroom/contact_session/update_nick_name', {
        id: selected_conversation.value?.id,
        nickName: nicknameInput.value,
      },
      {headers: {"Content-Type": "multipart/form-data"}}
  ).then(res => {
    let code = res.data.code
    if (code !== 200) ElMessage.error('Modify Failed')
    else ElMessage.success('Modification Success')
  }).catch(err => console.log(err))
  OtherInfoDialogVisible.value = false
}

const handlePictureClick = () => {
  ElMessage.info('Send Picture(TO BE IMPL)')
}

</script>

<style scoped>

.chat-outer-panel {
  display: grid;
  height: calc(100vh - var(--cr-header-height));
  width: 100%;
  position: relative;
  background-color: transparent;
  border-radius: 1rem;
}

.chat-outer-panel::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url('../../assets/images/miku-pink.jpg');
  opacity: 0.6;
  box-shadow: 0 0 2em var(--cr-hot-hover-color);
  background-size: cover;
  background-position: center;
  border-radius: 1rem;
  z-index: -1;
}

.title-container {

}

.conversation-title {
  font-size: 1rem;
  font-weight: bold;
  height: 1em;
  margin: 0.5em 0 0.5em 0;
  background-color: rgba(238, 238, 238, 0.6);
  padding: 2px 3em 2px 3em;
  border-radius: 3px;
}

.chat-inner-panel {
  width: 100%;
  height: 100%;
  border-color: transparent;
  align-self: center;
}

.scrollbar-container {
  align-self: center;
  width: 100%;
  height: 60vh;
}

.message-row {
  display: grid;
  padding: 1rem;
  grid-template-columns: 1fr 1fr 14fr 1fr 1fr;
  grid-template-areas: 'left other bubble meava right';
}

.message-row:hover {
  background-color: #fff4;
  border-radius: 5px;
  box-shadow: 0 0 5px #fff4;
}

.message-time {
  position: relative;
  height: 0;
  font-size: 1rem;
  color: #f5f0dc;
  margin-bottom: 2px;
  text-align: center;
  align-self: center;
  top: 0;
  text-shadow: 0 0 2px black;
}


.message-avatar {
  border: 2px solid rgba(76, 181, 255, 0.91);
  --el-avatar-size: 3.5rem;
}

.message-avatar:hover {
  box-shadow: 0 0 20px rgba(40, 166, 250, 0.77);
  border-color: rgba(150, 209, 246, 0.47);
  transition: all 0.3s ease-in-out;
}

.message-row:hover .el-text {
  --el-text-color: #222d;
  border-color: #fffd;
  transition: all 0.3s ease-in-out;
}

.message-bubble {
  grid-area: bubble;
}

.other-message {
  --el-text-font-size: 18px;
  background-color: rgba(133, 198, 243, 0.77);
  border-radius: 1em;
  padding-left: 1em;
  padding-right: 1em;
  margin-bottom: 0.5em;
  border: 1px solid #fff6;
}

.my-message {
  --el-text-font-size: 18px;
  background-color: rgba(133, 243, 164, 0.77);
  border-radius: 1em;
  padding-left: 1em;
  padding-right: 1em;
  margin-bottom: 0.5em;
  border: 1px solid #fff6;
}

.other-avatar {
  grid-area: other;
}

.my-avatar {
  grid-area: meava;
}

.edit-area {
  height: 30vh;
  background-color: #fff9;
  border-radius: 1em;
  box-shadow: 0 0 1em #fff9;

  display: grid;
  grid-template-columns: 4fr 1fr;
  grid-template-rows: 1fr 3fr;
  grid-column-gap: 1em;

}

.send-button {
  margin: 10px;
  flex-shrink: 2;
  flex-basis: 2rem;
}

.message-input {
  font-size: 1rem;
  align-self: start;
  justify-self: start;
  opacity: 0.4;
  margin-left: 1rem;
}

.toolbar {
  display: grid;
  grid-template-columns: repeat(10, 1fr);
  place-content: center;
}

.other-info-container {
  display: grid;
  grid-template-rows: 1fr 1fr 1fr;
}

.other-info-title {
  font-weight: bolder;
  align-self: center;
  justify-self: center;
}

.other-name {
  font-size: 1rem;
  justify-self: center;
}

.nickname-edit {
  display: grid;
  grid-template-columns: 1fr 3fr 1fr;
}
</style>
