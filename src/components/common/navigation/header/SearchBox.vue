<template>
  <el-input
      class="search-box"
      v-model="searchInput"
      placeholder="Searching..."
      maxlength="20"
  >
    <template #append>
      <el-button class="custom-button" :icon="Search" style="color:  #50b5ff;"/>
    </template>
  </el-input>
</template>

<script>
import {Search} from "@element-plus/icons-vue";

export default {
  name: 'SearchBox',
  computed: {
    Search() {
      return Search
    }
  },
  data() {
    return {
      searchInput: '',
    };
  },
};
</script>

<style>

.el-input:hover {
  border-radius: 3px;
  box-shadow: 0 0 5px #50b5ff;
}

</style>

