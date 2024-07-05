<template>
  <user-layout>
    <el-container>
      <el-aside width="20vw" style="min-width: 150px;">

        <el-text id="list-title">List Management</el-text>

        <div class="edit-container">
          <el-input
              class="search-box category-search"
              v-model="categorySearchInput"
              placeholder="Search..."
              maxlength="20"
          >
            <template #append>
              <el-button class="custom-button" :icon="Search" style="color:  #50b5ff;"
                         @click="SearchOthers"
              />
            </template>
          </el-input>
          <el-button type="primary" @click="OpenNewListDialog">New List</el-button>
        </div>

        <div class="scrollbar-container">
          <el-scrollbar>
            <VueDraggable ref="el" v-model="user_cl" :on-change="()=>ElMessage.info('You switched the position.')">
              <el-card
                  class="list-card selectable"
                  v-for="list in user_cl"
                  :key="list.id"
              >
                <el-text class="list-name"> {{ list.name }}</el-text>
                <el-text class="list-id"> @{{ list.id }}</el-text>
              </el-card>
            </VueDraggable>
          </el-scrollbar>
        </div>

      </el-aside>
      <el-main class="list-display">

        <el-card class="item-card selectable"
                 v-for="item in user_cl" :key="item.id">
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

<!--Dialogs -->
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

        <el-dialog
            v-model="NewListDialogVisible"
            title="New List"
            width="500"
            align-center
        >
          <el-form>
            <el-form-item>
              <el-input v-model="input_list_data.name"
                        placeholder="Input List Name"
              />
            </el-form-item>
            <el-form-item>
              <el-radio-group v-model="input_list_data.category">
                <el-radio-button :value="0" label="0">Group</el-radio-button>
                <el-radio-button :value="1" label="0">Friend</el-radio-button>
              </el-radio-group>
            </el-form-item>
          </el-form>
          <template #footer>
            <div class="dialog-footer">
              <el-button @click="NewListDialogVisible = false">
                Cancel
              </el-button>
              <el-button type="primary" @click="HandleNewListConfirm">
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
import {onMounted, ref} from "vue";
import UserLayout from "@/components/UserLayout.vue";
import {Search} from "@element-plus/icons-vue";
import {ElMessage} from "element-plus";
import {VueDraggable} from "vue-draggable-plus";
import axios from "axios";
import user_contact_lists from "@/store/modules/contacts.js";

const user_cl = ref(user_contact_lists)
const categorySearchInput = ref('')
const DeleteDialogVisible = ref(false)
const NewListDialogVisible = ref(false)

const SearchOthers = () => {
  ElMessage.info('Searching... ')
}

const OpenNewListDialog = () => {
  ElMessage.info('New List... ')
  NewListDialogVisible.value = true
}

const SendMessageToOther = () => {
  ElMessage.info('Sending...')
}

const handleDeleteDialogClose = () => {
  ElMessage.info('Delete Other... ')
}

const fetch_contact_list = () => {
  axios.post('/my_chatroom/contact_group/get_group')
      .then(res => {
        user_cl.value = res.data.data
      })
      .catch(err => {
        console.error(err);
      })
}

const input_list_data = ref({
  name: 'NewList',
  category: 0
})

const HandleNewListConfirm = () => {
  create_new_list()
  NewListDialogVisible.value = false
}

const create_new_list = () => {
  axios.post('/my_chatroom/contact_group/create_group', input_list_data.value, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
      .then(res => {
        if (res.data.code === 0) {
          ElMessage.error('fail to create')
        }
        fetch_contact_list()
      })
      .catch(err => {
        console.error(err);
      })
}

onMounted(() => {
  ElMessage.info('Fetching contact list.')
  fetch_contact_list()
})

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

.scrollbar-container {
  height: 80vh;
}


</style>
