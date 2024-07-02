<template>
  <el-scrollbar
      class="current-session-scrollbar">
    <el-affix offset="0">
      <el-text size="large">Session List</el-text>
      <el-divider direction="horizontal" border-style="solid" style="border-width: 2px; margin: 0 2vw;"></el-divider>
    </el-affix>
    <VueDraggable ref="el" v-model="list">
      <div
          class="current-session-list"
          v-for="item in list" :key="item.id">
        <div class="session-item">
          <el-row justify="space-evenly">
            <el-col :span="4">
              <el-avatar size="30" src="src/assets/images/avatar-purple.png"></el-avatar>
            </el-col>
            <el-col :span="16">
              <el-row>
                <el-text size="large" style="color:#333; " truncated>{{item.name}}</el-text>
                <el-text size="small" style="color:#888; " truncated>@{{item.id }}</el-text>
              </el-row>
              <el-row>
                <el-text size="small" style="color:#666; " truncated>
                  Latest Message.
                  This is some test words.
                  This is some test words.
                </el-text>
              </el-row>
            </el-col>
            <span></span>
          </el-row>
          <el-divider direction="horizontal" border-style="solid" style="margin: 0 10px 0 10px;"></el-divider>
        </div>
      </div>
    </VueDraggable>
  </el-scrollbar>
</template>

<script setup lang="ts">
import {ref} from 'vue'
import {VueDraggable} from 'vue-draggable-plus'

const list = ref([
  {
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
.current-session-list {
  width: 300px;
}

.session-item > .el-row {
  align-items: center;
}

.session-item:hover {
  background-color: rgba(164, 205, 248, 0.2);
  box-shadow: 0 0 3px rgba(0, 123, 255, 0.5);
}

.session-item:checked {
  background-color: rgba(0, 123, 255, 0.2);
}

</style>
