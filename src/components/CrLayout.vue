<template>
  <div class="cr-layout">

    <div class="cr-header">
      <Header/>
    </div>

    <div class="cr-left">
      <SideMenu/>
    </div>

    <div class="cr-main">
      <slot/>
    </div>

    <div class="cr-right">
      <ConversationList/>
    </div>

    <div class="cr-footer">
      <span>© 2024 Chatroom. All rights reserved.</span>
    </div>

  </div>
</template>

<script setup>

import SideMenu from "@/components/navigation/SideMenu.vue";
import Header from "@/components/navigation/Header.vue";
import ConversationList from "@/components/chat/ConversationList.vue";
import {onMounted} from "vue";
import {refreshUserInfo} from "@/store/crStore.js";

onMounted(async () => {
  await refreshUserInfo()

})

</script>

<style>

:root {

  --cr-header-height: 60px;

  /* Text Colors */
  --cr-text-color: #1d9cce;
  --cr-text-color-light: rgba(29, 156, 206, 0.8);
  --cr-text-color-dark: rgba(29, 156, 206, 1);

  /* Background Colors */
  --cr-bg-color: rgba(255, 255, 255, 0.9);
  --cr-bg-color-light: rgba(255, 255, 255, 0.7);
  --cr-bg-color-dark: rgba(240, 240, 240, 0.9);

  /* Border Colors */
  --cr-border-color: rgba(29, 156, 206, 0.5);
  --cr-border-color-light: rgba(29, 156, 206, 0.3);
  --cr-border-color-dark: rgba(29, 156, 206, 0.7);

  /* Shadow Colors */
  --cr-shadow-color: rgba(29, 156, 206, 0.3);
  --cr-shadow-color-light: rgba(29, 156, 206, 0.15);
  --cr-shadow-color-dark: rgba(29, 156, 206, 0.45);

  /* Gradient Colors */
  --cr-gradient-color: linear-gradient(135deg, rgba(29, 156, 206, 0.3), rgba(255, 255, 255, 0.3));
  --cr-gradient-color-light: linear-gradient(135deg, rgba(29, 156, 206, 0.15), rgba(255, 255, 255, 0.15));
  --cr-gradient-color-dark: linear-gradient(135deg, rgba(29, 156, 206, 0.45), rgba(255, 255, 255, 0.45));

  /* Button Colors */
  --cr-button-bg-color: rgba(29, 156, 206, 0.8);
  --cr-button-bg-color-hover: rgba(29, 156, 206, 1);
  --cr-button-text-color: rgba(255, 255, 255, 0.9);

  /* Miscellaneous */
  --cr-highlight-color: rgba(255, 255, 204, 0.7);
  --cr-overlay-color: rgba(29, 156, 206, 0.1);
}

* {
  padding: 0;
  margin: 0;
}

.cr-layout {
  position: relative;
  background-color: transparent;
  display: grid;
  /*  grid-template-columns: 3fr 3fr 12fr 3fr 3fr;*/
  grid-template-columns: 6fr 12fr 6fr;
  /*  grid-template-rows: 60px 1fr 100px;*/
  grid-template-areas: 'header header header'
  'left main right'
  'footer footer footer';
  width: 100%;
  height: 100vh;
}

.cr-layout::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: url('src/assets/images/display-image00.jpg') repeat;
  opacity: 0.6;
  background-size: cover;
  z-index: -1;
}

.cr-header {
  grid-area: header;
  position: sticky;
}

.cr-aside {
  grid-area: sidebar;
}

.cr-main {
  grid-area: main;
}

.cr-footer {
  grid-area: footer;

  background-image: var(--cr-gradient-color);
  text-align: center;
  padding: 100px 0 100px 0;
  position: relative;
  top: calc(0px - var(--cr-header-height))
}


</style>

