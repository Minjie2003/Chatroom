<template>
  <div class="list-manage">
    <el-row>
      <el-col :span="12">
        <h2>Friend List</h2>
        <el-card v-for="(friend, index) in friends" :key="index" class="friend-card">
          <span>{{ friend }}</span>
          <el-button type="danger" @click="removeFriend(index)">Remove</el-button>
        </el-card>
        <el-input v-model="newFriend" placeholder="Add a new friend" @keyup.enter="addFriend"></el-input>
        <el-button type="primary" @click="addFriend">Add Friend</el-button>
      </el-col>
      <el-col :span="12">
        <h2>Group List</h2>
        <el-card v-for="(group, index) in groups" :key="index" class="group-card">
          <span>{{ group }}</span>
          <el-button type="danger" @click="removeGroup(index)">Remove</el-button>
        </el-card>
        <el-input v-model="newGroup" placeholder="Add a new group" @keyup.enter="addGroup"></el-input>
        <el-button type="primary" @click="addGroup">Add Group</el-button>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <h2>Custom Lists</h2>
        <el-input v-model="newListName" placeholder="Create a new list" @keyup.enter="addList"></el-input>
        <el-button type="primary" @click="addList">Create List</el-button>
        <div v-for="(list, index) in customLists" :key="index" class="custom-list">
          <h3>{{ list.name }}</h3>
          <el-card v-for="(item, itemIndex) in list.items" :key="itemIndex" class="custom-item">
            <span>{{ item }}</span>
            <el-button type="danger" @click="removeCustomItem(index, itemIndex)">Remove</el-button>
          </el-card>
          <el-input v-model="newCustomItem[index]" placeholder="Add a new item" @keyup.enter="addCustomItem(index)"></el-input>
          <el-button type="primary" @click="addCustomItem(index)">Add Item</el-button>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { ref } from 'vue';
import { ElRow, ElCol, ElCard, ElInput, ElButton } from 'element-plus';

export default {
  name: 'ListManage',
  components: {
    ElRow,
    ElCol,
    ElCard,
    ElInput,
    ElButton
  },
  setup() {
    const friends = ref(['Alice', 'Bob', 'Charlie']);
    const groups = ref(['Group 1', 'Group 2', 'Group 3']);
    const newFriend = ref('');
    const newGroup = ref('');
    const newListName = ref('');
    const customLists = ref([]);
    const newCustomItem = ref([]);

    const addFriend = () => {
      if (newFriend.value) {
        friends.value.push(newFriend.value);
        newFriend.value = '';
      }
    };

    const removeFriend = (index) => {
      friends.value.splice(index, 1);
    };

    const addGroup = () => {
      if (newGroup.value) {
        groups.value.push(newGroup.value);
        newGroup.value = '';
      }
    };

    const removeGroup = (index) => {
      groups.value.splice(index, 1);
    };

    const addList = () => {
      if (newListName.value) {
        customLists.value.push({ name: newListName.value, items: [] });
        newCustomItem.value.push('');
        newListName.value = '';
      }
    };

    const addCustomItem = (index) => {
      if (newCustomItem.value[index]) {
        customLists.value[index].items.push(newCustomItem.value[index]);
        newCustomItem.value[index] = '';
      }
    };

    const removeCustomItem = (listIndex, itemIndex) => {
      customLists.value[listIndex].items.splice(itemIndex, 1);
    };

    return {
      friends,
      groups,
      newFriend,
      newGroup,
      newListName,
      customLists,
      newCustomItem,
      addFriend,
      removeFriend,
      addGroup,
      removeGroup,
      addList,
      addCustomItem,
      removeCustomItem
    };
  }
};
</script>

<style scoped>
.list-manage {
  padding: 20px;
}

.friend-card, .group-card, .custom-item {
  margin-bottom: 10px;
}

.custom-list {
  margin-top: 20px;
}
</style>
