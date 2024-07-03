<template>
  <user-layout>
    <el-container style="height: 100%;">
      <el-aside width="300px">

        <el-text id="list-title">List Management</el-text>
        <div class="search-input-container">
          <el-input
              class="search-box category-search"
              v-model="categorySearchInput"
              placeholder="Search Category..."
              maxlength="20"
          >
            <template #append>
              <el-button class="custom-button" :icon="Search" style="color:  #50b5ff;"
              @click="SubmitSearchCategory"
              />
            </template>
          </el-input>
        </div>

        <el-divider border-style="solid" style="margin: 0 10px 1em 10px;"/>
        <div class="scroll-content">
          <el-scrollbar>
            <el-card
                class="category-card selectable"
                v-for="category in categories"
                :key="category.id"
            >
              {{ category.name }}
            </el-card>
          </el-scrollbar>
        </div>

      </el-aside>
      <el-main class="list-display">
        <el-card class="item-card selectable"
                 v-for="item in items">
          <el-row justify="space-around">
            <el-col span="8">
              <mini-profile :id="item.id" :name="item.name" :avatar_url="item.avatar_url"></mini-profile>
            </el-col>

            <el-col span="8" class="edit-buttons">
              <el-button type="primary">Send Message</el-button>
              <el-button type="danger">Delete</el-button>
            </el-col>
          </el-row>
        </el-card>
      </el-main>
    </el-container>
  </user-layout>

</template>


<script setup>
import MiniProfile from "@/components/common/navigation/header/MiniProfile.vue";
import store from "@/store/store.js"
import {ref} from "vue";
import UserLayout from "@/components/user/UserLayout.vue";
import {Search} from "@element-plus/icons-vue";
import {ElMessage} from "element-plus";

const items = ref(store.users)
const categories = ref(store.categories)
const categorySearchInput = ref('')

const SubmitSearchCategory = () => {
  ElMessage.info('Searching... ')
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

.search-input-container {
  margin: 30px 5px 30px 5px;
}

#list-title {
  margin: 20px 0 0 25%;
  font-weight: bold;
  font-size: 16px;
}

</style>
