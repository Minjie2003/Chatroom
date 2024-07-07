<template>
  <div class="conversation-list" ref="sessionList">
    <el-text class="list-title cr-title" size="large" truncated>Conversations</el-text>
    <el-divider border-style="solid"></el-divider>

    <div class="scrollbar-container">
      <el-scrollbar class="conversation-scrollbar">

        <VueDraggable ref="el" v-model="conversation_list">
          <div
              class="conversations"
              v-for="conv in conversation_list" :key="conv.id"
              @click="handleConversationSelect(conv)"
          >

            <div class="conversation-entry" :class="{selected: crStore.selectedConversation?.id === conv?.id}">

              <el-avatar :src=" 'src/assets/images/avatar-purple.png' "/>

              <div class="name-id-container" style="display: flex; flex-flow: row nowrap;">
                <el-text class="conv-name" size="large" style="color:#333; " truncated>{{
                    conv.nickName
                  }}
                </el-text>
                <el-text class="conv-id" size="small" style="color:#888; " truncated>@{{ conv.id }}</el-text>
              </div>

              <div class="conv-tags">
                <el-tag v-if="conv.category === CR_Constant.CHATROOM">Chatroom</el-tag>
                <el-tag v-if="conv.category === CR_Constant.FRIEND" type="success">Friend</el-tag>
                <el-tag v-if="conv.isDeleted" type="danger">HasQuit</el-tag>
              </div>

              <div class="unread-icon-container">
                <el-text v-show="conv.unreadNum"
                         class="unread-number" size="small"
                >
                  {{ conv.unreadNum }}
                </el-text>
              </div>

              <el-text class="last-content" size="small" style="color:#666; " truncated>
                {{ conv.lastContent ?? 'No messages. ' }}
              </el-text>

            </div>

          </div>

          <div v-if="conversation_list && conversation_list.length === 0">
            <p>No Conversation.</p>
          </div>

        </VueDraggable>
      </el-scrollbar>
    </div>
  </div>
</template>

<script setup>

import {VueDraggable} from "vue-draggable-plus";
import {computed, onMounted, ref} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";
import {CR_Constant, crStore} from "@/store/crStore.js";

const conversation_list = computed(() => crStore.conversationList)
const other_info = computed(() => crStore.otherInfo)
const selectedConversation = computed(() => crStore.selectedConversation)

onMounted(async () => {
  crStore.setSelectedConversation(null)
  console.log('try to fetch conversation list');
  await fetchConversationList();

  if (conversation_list.value && conversation_list.value.length > 0) {
    crStore.setSelectedConversation(conversation_list.value[0])
    ElMessage.info('Selected Default Conv @' + selectedConversation.value?.nickName);
  } else {
    ElMessage.warning('No Conversation Selected');
  }
});

const fetchConversationList = async () => {
  try {
    const res = await axios.post('/my_chatroom/contact_session/get_contact_session_list', {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });

    const code = res.data.code;
    if (code === 200) {
      crStore.setConversationList(res.data.data);
    } else {
      ElMessage.info('ERROR:' + res.data.message);
    }
  } catch (err) {
    console.error(err);
  }
};

const fetchOtherInfo = (otherId) => {
  if (!otherId) console.log('fetchOtherInfo fail: No otherId')
  axios.post('my_chatroom/user/get_contact_info', {id: otherId}, {headers: {'Content-Type': 'multipart/form-data'}})
      .then(res => {
        let code = res.data.code
        if (code === 200) {
          crStore.setOtherInfo(res.data.data)
        }
      })
      .catch(err => console.log(err))
}

defineExpose({
  fetchConversationList,
  fetchOtherInfo,
})

const handleConversationSelect = (conv) => {
  crStore.setSelectedConversation(conv)
  console.log(`conv@handleConversationSelect: ${conv.contactId}`)
  fetchOtherInfo(conv?.contactId)
}

</script>

<style scoped>

.list-title {
  --el-text-color: var(--cr-text-color);
  font-size: 1.3rem;
}

@media (max-width: 800px) {
  .list-title {
    font-size: 1rem;
    transition: all 0.3s ease;
  }
}

.conversation-list {
  flex-basis: 15vw;
  min-width: 150px;
  flex-shrink: 1;
  height: 60vh;
}

.conversation-entry {
  display: grid;
  grid-template-columns: 1fr 4fr 1fr;
  grid-template-rows: 1fr 1fr;
  grid-template-areas: 'sidebar header'   'sidebar header';
  gap: 4px;
  padding: 4px;
  background-color: var(--cr-shadow-color);
}

.selected {
  background-color: var(--cr-hot-hover-color);
  box-shadow: 0 0 2rem var(--cr-hot-hover-color);
}

.unread-number {
  display: flex;
  width: 1em;
  height: 1em;
  color: white;
  padding: 1px;
  font-size: 1em;
  place-content: center;
  align-self: center;
  border-radius: 50%;
  background-color: red;
}

.last-content {
  font-size: 0.8em;
}

.conversation-entry {
  display: grid;
  grid-template-columns: 1fr 2fr 1fr 1fr;
  grid-template-rows: 1fr 1fr;
  grid-template-areas:
      'avatar name tag unread'
      'avatar last last last'
  ;
}

.conversation-entry > .el-avatar {
  grid-area: avatar;
  align-self: center;
  justify-self: center;
}

.conversation-entry > .name-id-container {
  grid-area: name;
  align-self: center;
  justify-self: start;
}

.conversation-entry > .conv-tags {
  grid-area: tag;
  display: flex;
  flex-flow: row nowrap;
}

.conversation-entry > .unread-icon-container {
  grid-area: unread;
  align-self: center;
  justify-self: start;
}

.conversation-entry > .last-content {
  grid-area: last;
  align-self: center;
  justify-self: start;
}




.conversation-entry:hover {
  background-color: var(--cr-light-hover-color);
  box-shadow: 0 0 10px var(--cr-light-hover-color);
}

.conversation-entry:checked {
  background-color: rgba(0, 123, 255, 0.2);
}

</style>
