<template>
  <div class="chat-container">

<!--    <session-list class="session-list" ref='sessionList'
                  @selected-change="$refs.chatPanel.fetchMsgList();"
    />
-->
    <ChatPanel class="chat-panel" ref="chatPanel"
               @update-list="$refs.sessionList.get_session_list();"
    />

<!--
    <profile class="other-profile" :profile-data="selectedOtherInfo"/>
-->

  </div>
</template>

<script setup>
import SessionList from "@/components/chat/ConversationList.vue";
import ChatPanel from "@/components/chat/ChatPanel.vue";
import Profile from "@/components/Profile.vue";
import {DefaultProfileData, DefaultUserData, selectedOtherInfo} from "@/store/cr_config.js";
import {ref} from "vue";
import {selected_session} from "@/store/modules/conv.js";

const otherInfo = ref({
  ...DefaultProfileData(),
  ...DefaultUserData()
})


</script>

<style scoped>

.chat-container {
  display: grid;
  grid-template-rows: 1fr;
  place-content: center;
}

.session-list {
  grid-row: 1;
  grid-column: 1;
}

.chat-panel {
  grid-row: 1;
  grid-column: 2;
}

.other-profile {
  grid-row: 1;
  grid-column: 3;
}

</style>
