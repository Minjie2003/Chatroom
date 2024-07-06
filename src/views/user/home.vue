<template>
  <user-space/>
</template>
<script setup>
import UserLayout from "@/components/CrLayout.vue";
import UserSpace from "@/components/UserSpace.vue";
import {onMounted} from "vue";
import {CR_DEBUG_ON, getUserInfo} from "@/store/cr_config.js";
import {ElMessage} from "element-plus";

onMounted(() => {
  getUserInfo()
  if(CR_DEBUG_ON) ElMessage.info('fetch userinfo')
})

</script>
