<template>
  <div class="cr-debug" v-show="CR_DEBUG_ON">
    <h1> DEBUG ON </h1>
    <p><strong>Toggle</strong> Press <strong>Ctrl</strong></p>
    <h2>Profile</h2>
    <el-button @click="getUserInfo(); ElMessage.info('Update User Info.')">Refresh</el-button>
  </div>
</template>

<script setup>

import {onMounted, ref} from "vue";
import {CR_DEBUG_ON, getUserInfo} from "@/store/crStore.js";
import {ElMessage} from "element-plus";
// Define the reactive debug state

// Function to toggle the debug state
const toggleDebug = (event) => {
  if (event.ctrlKey && event.key === '0') {
    CR_DEBUG_ON.value = !CR_DEBUG_ON.value;
    console.log(`DEBUG_ON is now ${CR_DEBUG_ON.value}`);
  }

};
// Add event listener on component mount
onMounted(() => {
  window.addEventListener('keydown', toggleDebug);
});

/*onbeforeunload(() => {
  window.removeEventListener('keydown', toggleDebug);
})*/
</script>


<style scoped>

.cr-debug {
  position: absolute;
  display: grid;
  opacity: 0.6;
  z-index: 9999;
}

.cr-debug * {
  z-index: 9999;
  color: orangered;
}

</style>
