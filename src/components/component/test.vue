<template>
  <el-button :plain="true" @click="openerror">Error</el-button>
</template>

<script setup>
import { ElMessage } from 'element-plus'
import {defineProps} from 'vue'
import {ref} from 'vue'

const props = defineProps({
 id:Number 
})

const err = ref("")
const openerror = () => {
  ElMessage.error('Oops, this is a error message.')
}
</script>