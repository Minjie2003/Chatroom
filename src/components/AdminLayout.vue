<template>
  <el-tabs
      v-model="activeName"
      class="admin-layout-tabs"
      tab-position="top">
    <el-tab-pane label="User Manage" name="first">
      <el-row
          v-for="user in users"
          class="user-edit"
          justify="space-evenly" :key="user">
        <el-col span="4">
          <mini-profile :avatar_url="user.avatar_url"
                        :name="user.name"
                        :id="user.id"
          ></mini-profile>
        </el-col>

        <el-col span="2" class="align-center">
          <el-button class="reset-password"
                     type="warning">
            Reset Password
          </el-button>
        </el-col>

        <el-col span="2" class="align-center">
          <el-button class="delete-user"
                     type="danger">
            Delete User
          </el-button>
        </el-col>

        <el-divider border-style="solid"></el-divider>

      </el-row>


      <el-row justify="center">
        <el-col span="10">
          <el-pagination
              v-model="currentPage.OfUser"
              background
              layout="prev, pager, next" :total="50"/>
        </el-col>
      </el-row>
    </el-tab-pane>

    <el-tab-pane label="Group Manage" name="second">
      <el-row
          v-for="group in groups"
          class="group-edit"
          justify="space-evenly" :key="group">
        <el-col span="4">
          <mini-profile :avatar_url="group.avatar_url"
                        :name="group.name"
                        :id="group.id"
          ></mini-profile>
        </el-col>

        <el-col span="2" class="align-center">
          <el-button class="delete-group"
                     type="danger">
            Delete Group
          </el-button>
        </el-col>

        <el-divider border-style="solid"></el-divider>

      </el-row>

      <el-row justify="center">
        <el-col span="10">
          <el-pagination
              v-model="currentPage.OfGroup"
              background
              layout="prev, pager, next" :total="50"/>
        </el-col>
      </el-row>
    </el-tab-pane>
    <!--  Report Manage -->
    <el-tab-pane label="Report Manage" name="third">
      <el-row
          v-for="report in reports"
          class="report-edit"
          justify="space-evenly" :key="report">
        <el-col span="4">
          <mini-profile :avatar_url="report.avatar_url"
                        :name="report.name"
                        :id="report.id"
          ></mini-profile>
        </el-col>

        <el-col span="2" class="align-center">
          <el-text>
            {{ report.description}}
          </el-text>
        </el-col>

        <el-col span="2" class="align-center">
          <el-button class="confirm-report"
                     type="danger">
            Confirm
          </el-button>
        </el-col>
        <el-divider border-style="solid"></el-divider>
      </el-row>

      <el-row justify="center">
        <el-col span="10">
          <el-pagination
              v-model="currentPage.OfGroup"
              background
              layout="prev, pager, next" :total="50"/>
        </el-col>
      </el-row>
    </el-tab-pane>
  </el-tabs>
</template>
<script setup>
import {ref} from 'vue'
import store from '@/store/store.js'
import MiniProfile from "@/components/icons/MiniProfile.vue";

/* Tabs*/
const activeName = ref('first')
const currentPage = ref(
    {
      OfUser: 1,
      OfGroup: 1,
      OfWarning: 1
    }
)
const users = ref(store.users)
const groups = ref(store.groups)
const reports = ref(store.reports)
</script>

<style>
.demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}

</style>
