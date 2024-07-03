<!-- App.vue or any other component -->
<template>
  <div>
    <div @contextmenu.prevent="showContextMenu"
         @contextlost="hideContextMenu"
         class="context-area"
    ></div>
    <el-button>else</el-button>
  </div>
  <ContextMenu ref="contextMenu"></ContextMenu>
</template>

<script>
import ContextMenu from '@/components/ContextMenu.vue';
import {ElMessage} from "element-plus";

export default {
  components: {
    ContextMenu
  },
  methods: {
    ElMessage,
    showContextMenu(event) {

      this.$refs.contextMenu.showMenu(event);
    },
    hideContextMenu(event){
      this.$refs.contextMenu.hideMenu()
    },
    handleMenuCommand(command) {
      console.log('Selected command:', command);
      // Handle the command here (e.g., perform some action based on the selected command)
    }
  }
};
</script>

<style scoped>
.context-area {
  width: 100%;
  height: 10vh;
  background-color: #d28585;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 16px;
  color: #333;
  border: 1px solid #ccc;
}
</style>
