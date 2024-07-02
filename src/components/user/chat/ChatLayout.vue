<template>
  <el-container>
    <el-aside width="300px">
      <session-list></session-list>
    </el-aside>
    <el-divider direction="vertical" border-style="solid" style="margin: 0; height: 100vh;"></el-divider>
    <el-main>
      <ChatPanel></ChatPanel>
    </el-main>
  </el-container>
</template>

<script setup>

import SessionList from "@/components/user/chat/SessionList.vue";
import ChatPanel from "@/components/user/chat/ChatPanel.vue";
</script>



<style scoped>

</style>
