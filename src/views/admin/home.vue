<template>
  <div class="admin-wrapper">

    <admin-layout></admin-layout>

  </div>
</template>


<script setup>

import AdminLayout from "@/components/admin/AdminLayout.vue";


</script>

<style>
.admin-wrapper {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: white;
  background-size: cover;
  background-position: center;
  z-index: 9;
}
</style>
