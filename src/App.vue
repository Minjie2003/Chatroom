<template>
  <debug></debug>
  <cr-layout v-if="!crStore.getIsAdmin()">
    <router-view></router-view>
  </cr-layout>
  <router-view v-else/>
</template>

<script setup>
import Debug from "@/components/debug/Debug.vue";
import CrLayout from "@/components/CrLayout.vue";
import {crStore} from "@/store/crStore.js";
import {useRouter} from "vue-router";
import {ElMessage} from "element-plus";

const router = useRouter();

router.beforeEach(async (to, from, next) => {

  if(crStore.getIsAdmin()) {
    if(to.path ==='/'){
      next('/admin')
    }
  }
/*  if(from.path === '/admin/login'){
    crStore.setIsAdmin(true)
  }*/

  const loggedIn = await crStore.getHasLoggedIn()

  if (!loggedIn) {
    if (to.path !== '/auth/login') {
      ElMessage.warning('Please Login.');
      next('/auth/login');
    } else {
      next();
    }
  } else {
    if (to.path === '/') {
      next('/home');
    } else {
      next();
    }
  }
});

</script>

<style>

:root {
  --cr-text-color: #444F;
  --cr-basic-hover-color: rgba(0, 123, 255, 0.5);
  --cr-hot-hover-color: rgba(236, 125, 145, 0.78);
  --cr-light-hover-color: rgba(84, 164, 255, 0.5);
  --cr-box-shadow-color: rgba(0, 123, 255, 0.5);
  --cr-text-hover-color: #007BFF;
  --cr-static-light-color: rgba(117, 225, 243, 0.46);

  --cr-default-opacity: 0.8;

  --cr-icon-size: 50px;
}
</style>
