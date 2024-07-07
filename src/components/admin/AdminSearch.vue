<template>
  <div class="search-box">
    <el-input
      class="search-box-input"
      v-model="searchInput"
      :placeholder="$t('manager.placeholder')"
      maxlength="20"
      @keyup.enter="searchWithInput"
    >
    <template #append>
      <el-button @click="searchWithInput" class="custom-button" :icon="Search" style="color:  #50b5ff;"/>
    </template>
    </el-input>
  </div>
</template>

<script setup>
import { ref,computed } from 'vue';
import { Search } from '@element-plus/icons-vue';
import axios from 'axios';
import { useStore } from 'vuex';
import { ElMessage } from 'element-plus';

const store = useStore()
const searchinfos = computed(()=>store.state.searchinfos.searchinfos)
const adminFlag = computed(() => store.state.flag.adminFlag)
const searchInput = ref('');
const searchUser=(key)=>{
    axios.post("/my_chatroom/manager/search_user",{key:key},{headers:{'Content-Type': 'multipart/form-data'}})
    .then(res=>{store.dispatch('searchinfos/updateSearchInfos',res.data.data)})
    .catch(err => {ElMessage.error(err)});
}
const searchChatroom=(key)=>{
    axios.post("/my_chatroom/manager/search_chatroom",{key:key},{headers:{'Content-Type': 'multipart/form-data'}})
    .then(res=>{store.dispatch('searchinfos/updateSearchInfos',res.data.data)})
    .catch(err => {ElMessage.error(err)});
}
const searchReport=(key)=>{
    axios.post("/my_chatroom/manager/search_report",{key:key},{headers:{'Content-Type': 'multipart/form-data'}})
    .then(res=>{store.dispatch('searchinfos/updateSearchInfos',res.data.data)})
    .catch(err => {ElMessage.error(err)});
}
const searchWithInput = () => {
  if(adminFlag.value === 1){searchUser(searchInput.value);console.log(searchInput.value)}
  else if(adminFlag.value === 2){searchChatroom(searchInput.value)}
  else if(adminFlag.value === 3){searchReport(searchInput.value)}
};

</script>

<style>


.el-input:hover {
  border-radius: 3px;
  box-shadow: 0 0 5px #50b5ff;
}

</style>

