<template>
  <div class="conversation-list">
    <el-text class="list-title cr-title" size="large" truncated>Conversations</el-text>
    <el-divider border-style="solid"></el-divider>

    <div class="scrollbar-container">
      <el-scrollbar class="conversation-scrollbar">
        <VueDraggable ref="el" v-model="list">
          <div
              class="conversations"
              v-for="item in list" :key="item.id">

            <div class="conversation-entry">
              <el-avatar size="30" src="src/assets/images/avatar-purple.png"></el-avatar>
              <div style="display: flex; flex-flow: row nowrap;">
                <el-text size="large" style="color:#333; " truncated>{{ item.name }}</el-text>
                <el-text size="small" style="color:#888; " truncated>@{{ item.id }}</el-text>
              </div>
              <el-text size="small" style="color:#666; " truncated>
                Latest Message.
                This is some test words.
                This is some test words.
              </el-text>
            </div>

          </div>
        </VueDraggable>
      </el-scrollbar>
    </div>
  </div>
</template>

<script setup lang="ts">
import {ref} from 'vue'
import {VueDraggable} from 'vue-draggable-plus'
import {ElMessage, useCursor} from "element-plus";

const conversations = ref([
  {
    name: 'Joao',
    id: 1
  },{
    name: 'Joao',
    id: 1
  },{
    name: 'Joao',
    id: 1
  },{
    name: 'Joao',
    id: 1
  },{
    name: 'Joao',
    id: 1
  },{
    name: 'Joao',
    id: 1
  },{
    name: 'Joao',
    id: 1
  },{
    name: 'Joao',
    id: 1
  },{
    name: 'Joao',
    id: 1
  },{
    name: 'Joao',
    id: 1
  },{
    name: 'Joao',
    id: 1
  },{
    name: 'Joao',
    id: 1
  },{
    name: 'Joao',
    id: 1
  },
  {
    name: 'Jean',
    id: 2
  },
  {
    name: 'Johanna',
    id: 3
  },
  {
    name: 'Juan',
    id: 4
  },
  {
    name: 'Tommy',
    id: 5
  }
])
</script>

<style scoped>

.list-title {
  --el-text-color: var(--cr-text-color);
  font-size: 1.3rem;
}

@media (max-width: 800px) {
  .list-title{
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
  grid-template-columns: 1fr 4fr;
  grid-template-rows: 1fr 1fr;
  grid-template-areas: 'sidebar header'   'sidebar header';
}

.conversation-entry > .el-avatar {
  grid-area: sidebar;
  align-self: center;
  justify-self: center;
}

.conversation-entry > .el-text {
  align-self: center;
  justify-self: end;
}

.conversation-entry:hover {
  background-color: var(--cr-light-hover-color);
  box-shadow: 0 0 10px var(--cr-light-hover-color);
}

.conversation-entry:checked {
  background-color: rgba(0, 123, 255, 0.2);
}

</style>
