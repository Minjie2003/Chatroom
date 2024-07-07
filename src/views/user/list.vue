<template>
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
          <VueDraggable ref="el" v-model="contact_group_list"
                        :on-change="()=>ElMessage.info('You switched the position.')">
            <el-card
                class="group-card selectable"
                v-for="group in contact_group_list"
                :key="group.id"
                @click="handleContactGroupSelect(group)"
            >
              <el-text class="list-name"> {{ group.name }}</el-text>
              <el-text class="list-id"> @{{ group.id }}</el-text>
              <div class="tag-container">
                <el-tag v-show="group.isDefault" type="danger">Default</el-tag>
                <el-tag v-show="group.category === CR_CHATROOM" type="primary">Chatroom</el-tag>
                <el-tag v-show="group.category === CR_FRIEND" type="success">Friend</el-tag>
                <el-tag v-if="group.convoCount === 0" type="warning">Empty</el-tag>
                <el-tag v-else type="success"> Count: {{ group.convoCount }}</el-tag>
              </div>
            </el-card>
          </VueDraggable>
        </el-scrollbar>
      </div>

    </el-aside>
    <el-main class="list-display">

      <el-card class="item-card cr-card selectable"
               v-for="conv in selected_contact_group_items" :key="conv.id">
        <el-row justify="space-around">

          <el-col span="8">
            <mini-profile :user_id="conv.id" :name="conv.nickName" :avatar_url="conv.avatar_url"></mini-profile>
          </el-col>
          <span></span>
          <el-col span="8" class="edit-buttons">
            <el-button type="primary" @click="SendMessageToOther(conv)">Send Message</el-button>
            <el-button type="danger" @click="DeleteDialogVisible = true">Delete</el-button>
          </el-col>
        </el-row>
      </el-card>

      <!--Dialogs -->
      <el-dialog
          v-model="DeleteDialogVisible"
          title="Warning"
          width="500"
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
            <el-input v-model="newGroup.name"
                      placeholder="Input List Name"
            />
          </el-form-item>
          <el-form-item>
            <el-radio-group v-model="newGroup.category">
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

</template>


<script setup>
import MiniProfile from "@/components/icons/MiniProfile.vue";
import {computed, onMounted, ref} from "vue";
import {Search} from "@element-plus/icons-vue";
import {ElMessage} from "element-plus";
import {VueDraggable} from "vue-draggable-plus";
import axios from "axios";
import {CR_Constant, crStore} from "@/store/crStore.js";
import {useRouter} from "vue-router";

const contact_group_list = computed(() => {
  return crStore.contactGroupList.map(group => {
    const convoCount = crStore.conversationList.filter(convo => convo.groupId === group.id).length;
    return {
      ...group,
      convoCount
    };
  });
});

const selected_group = computed(() => crStore.selectedContactGroup)
const selected_contact_group_items = computed(() => {
  return crStore.conversationList.filter((conv) => conv.groupId === selected_group.value?.id)
})

const categorySearchInput = ref('')
const DeleteDialogVisible = ref(false)

const NewListDialogVisible = ref(false)

const CR_CHATROOM = CR_Constant.CHATROOM
const CR_FRIEND = CR_Constant.FRIEND

const newGroup = ref({
  name: 'Default',
  category: CR_CHATROOM
})

const router = useRouter()

onMounted(() => {
  ElMessage.info('TRY: fetchContactGroupList')
  fetchContactGroupList()
})

const fetchContactGroupList = async () => {
  try {
    const res = await axios.post('/my_chatroom/contact_group/get_group');
    let code = res.data.code
    if (code === 200) {
      crStore.setContactGroupList(res.data.data)
      ElMessage.success(code.message || 'fetchContactGroupList Success')
    } else {
      ElMessage.warning(code.message || 'fetchContactGroupList Failed')
    }
  } catch (err) {
    console.error(err);
    ElMessage.error('Failed to fetch contact list');
  }
};

const createNewContactGroup = async () => {
  try {
    const res = await axios.post('/my_chatroom/contact_group/create_group', newGroup.value, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });
    if (res.data.code === 0) {
      ElMessage.error('Failed to create new contact group');
    } else {
      ElMessage.success('New contact group created successfully');
      await fetchContactGroupList();
      ElMessage.info('Contact Group List Refreshed')
    }
  } catch (err) {
    console.error(err);
    ElMessage.error('Failed to create new contact group');
  }
};

defineExpose({
  fetchContactGroupList
})

const handleContactGroupSelect = (group) => {
  crStore.setSelectedContactGroup(group)
}

const SearchOthers = () => {
  ElMessage.info('Searching... ')
}

const OpenNewListDialog = () => {
  ElMessage.info('New List... ')
  NewListDialogVisible.value = true
}

const SendMessageToOther = (conv) => {
  ElMessage.info('Sending...')
  crStore.setSelectedConversation(conv)
  router.push('chat')
}

const HandleNewListConfirm = () => {
  createNewContactGroup()
  NewListDialogVisible.value = false
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

.scrollbar-container {
  height: 60vh;
}

.cr-card {
  --el-card-padding: 5px;
  background-color: var(--cr-bg-color);
}

.group-card {
  display: flex;
  flex-flow: row wrap;
  --el-card-padding: 5px;
  background-color: var(--cr-bg-color);
}



</style>
