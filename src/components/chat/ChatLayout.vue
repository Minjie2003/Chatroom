<template>
  <el-container>
    <el-aside width="15vw">
      <session-list></session-list>
    </el-aside>
    <el-main style="padding: 0;">
      <ChatPanel></ChatPanel>
    </el-main>
  </el-container>
</template>

<script setup>

import SessionList from "@/components/chat/ConversationList.vue";
import ChatPanel from "@/components/chat/ChatPanel.vue";
</script>

<style scoped>

</style>
