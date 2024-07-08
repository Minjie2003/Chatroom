<template>
  <user-space/>
</template>
<script setup>
import UserLayout from "@/components/CrLayout.vue";
import UserSpace from "@/components/UserSpace.vue";
import {onMounted} from "vue";
import {CR_DEBUG_ON, refreshUserInfo} from "@/store/crStore.js";
import {ElMessage} from "element-plus";

onMounted(() => {
  refreshUserInfo()
})

</script>
