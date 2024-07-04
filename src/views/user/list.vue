<template>
  <user-layout>
    <el-container >
      <el-aside width="20vw" style="min-width: 150px;">

        <el-text id="list-title">List Management</el-text>

        <div class="edit-container">
          <el-input
              class="search-box category-search"
              v-model="categorySearchInput"
              placeholder="Search Category/Friend/Group..."
              maxlength="20"
          >
            <template #append>
              <el-button class="custom-button" :icon="Search" style="color:  #50b5ff;"
                         @click="SearchOthers"
              />
            </template>
          </el-input>
          <el-button type="primary" @click="NewGroup">New Group</el-button>
        </div>

        <div class="scroll-content">
          <el-scrollbar>
            <VueDraggable ref="el" v-model="categories" :on-change="()=>ElMessage.info('You switched the position.')">
              <el-card
                  class="category-card selectable"
                  v-for="category in categories"
                  :key="category.id"
              >
                {{ category.name }}
              </el-card>
            </VueDraggable>
          </el-scrollbar>
        </div>

      </el-aside>
      <el-main class="list-display">

        <el-card class="item-card selectable"
                 v-for="item in items" :key="item.id">
          <el-row justify="space-around">
            <el-col span="8">
              <mini-profile :user_id="item.id" :name="item.name" :avatar_url="item.avatar_url"></mini-profile>
            </el-col>

            <el-col span="8" class="edit-buttons">
              <el-button type="primary" @click="SendMessageToOther">Send Message</el-button>
              <el-button type="danger" @click="DeleteDialogVisible = true">Delete</el-button>
            </el-col>
          </el-row>
        </el-card>

        <el-dialog
            v-model="DeleteDialogVisible"
            title="Warning"
            width="500"
            :before-close="handleDeleteDialogClose"
        >
          <span>Delete Friend/Group</span>
          <template #footer>
            <div class="dialog-footer">
              <el-button @click="DeleteDialogVisible = false">
                Cancel
              </el-button>
              <el-button type="primary" @click="DeleteDialogVisible = false">
                Confirm
              </el-button>
            </div>
          </template>
        </el-dialog>

      </el-main>
    </el-container>
  </user-layout>

</template>


<script setup>
import MiniProfile from "@/components/icons/MiniProfile.vue";
import store from "@/store/store.js"
import {ref} from "vue";
import UserLayout from "@/components/UserLayout.vue";
import {Search} from "@element-plus/icons-vue";
import {ElMessage} from "element-plus";
import {VueDraggable} from "vue-draggable-plus";

const items = ref(store.users)
const categories = ref(store.categories)
const categorySearchInput = ref('')
const DeleteDialogVisible = ref(false)
const NewGroupDialogVisible = ref(false)

const SearchOthers = () => {
  ElMessage.info('Searching... ')
}

const NewGroup = () => {
  ElMessage.info('New Group... ')
}

const SendMessageToOther = () => {
  ElMessage.info('Sending...')
}

const HandleDeleteDialogClose = () => {
  ElMessage.info('Delete Other... ')
}

</script>

<style scoped>

.selectable:hover {
  background-color: #09f5;
}

.edit-buttons {
  display: flex;
  flex-flow: row nowrap;
  align-items: center;
}

.list-display {
  margin: 0;
}

.edit-container {
  margin: 30px 5px 30px 5px;
  display: grid;
  gap: 1em;
}

#list-title {
  margin: 20px 0 0 25%;
  font-weight: bold;
  font-size: 1rem;
}

</style>
