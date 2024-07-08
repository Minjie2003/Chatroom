<template>
    <div class="admin-layout">
      <el-container>
        <el-header class="cr-header-container" style="padding: 0;">
          <AdminHeader/>
        </el-header>

        <el-container>

          <el-aside width="9vw">
            <AdminSidebar/>
          </el-aside>

          <el-main style="padding: 0;">
            <div v-if="adminFlag === 1">
                <user-management></user-management>
            </div>
            <div v-if="adminFlag === 2">
                <chatroom-managerment></chatroom-managerment>
            </div>
            <div v-if="adminFlag === 3">
                <report-managerment></report-managerment>
            </div>
          </el-main>

        </el-container>
      </el-container>
    </div>
  </template>

  <script>
  import {useStore} from 'vuex'
  import {computed} from 'vue'
  import AdminSidebar from "@/components/admin/AdminSideMenu.vue";
  import AdminHeader from "@/components/admin/AdminHeader.vue";
  import UserManagement from '@/components/admin/adminops/UserManagement.vue';
  import ChatroomManagerment from '@/components/admin/adminops/ChatroomManagerment.vue'
  import ReportManagerment from '@/components/admin/adminops/ReportManagement.vue'
  export default {
    name: 'UserLayout',
    data() {
      return {
        sidebar_width: "200px",
      }
    },
    components: {AdminHeader, AdminSidebar,UserManagement,ChatroomManagerment,ReportManagerment},
    setup(){
        const store = useStore()
        const adminFlag = computed(() => store.state.flag.adminFlag)
        return{
            store,adminFlag
        }
    }
  }
  </script>

  <style>

  :root {
    --cr-header-height: 60px;
    --cr-sidebar-width: 300px;
  }

  * {
    padding: 0;
    margin: 0;
  }

  </style>

