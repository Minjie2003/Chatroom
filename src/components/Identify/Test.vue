<template>
    <h1>这是一个测试{{ store.myinfos.accountNum }}</h1>

    <!-- <img :scr="avatar" alt="这是一个测试" style="width: 110px; height: 40px;></img> -->
</template>
<script setup>
import { ref } from 'vue'
const avatar = ref('')
import store from '@/store/store.js'

</script>
