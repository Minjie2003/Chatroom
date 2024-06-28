<template>
  <RouterLink to="/">Home</RouterLink>
  <SideBar></SideBar>
  <RouterView />
</template>

<script>
import sideBar from "@/components/Reusabl/Sidebar.vue";
import {defineComponent} from "vue";
import SideBar from "@/components/Reusabl/Sidebar.vue";

export default defineComponent({
  components: {SidebarToggle, SideBar}
})

</script>

