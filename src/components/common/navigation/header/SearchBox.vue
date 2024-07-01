<template>
  <el-input
      class="search-box"
      v-model="searchInput"
      placeholder="Please input"
      maxlength="20"
  >
    <template #append>
      <el-button class="custom-button" :icon="searchIcon" style="color:  #50b5ff;"/>
    </template>
  </el-input>
</template>

<script>
import {Search} from "@element-plus/icons-vue";

export default {
  name: 'SearchBox',
  data() {
    return {
      searchInput: '',
      searchIcon: Search
    };
  },
};
</script>

<style>

.search-box {
  width: 300px;
  min-width: 100px;
  align-content: center;
}
</style>

