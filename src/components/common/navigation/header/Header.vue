<template>
  <div class="header-card">
    <el-row class="header-row" justify="space-between">

      <el-col class="logo-display" :span="6">
        <logo app_logo="src/assets/images/chatroom_logo.png" app_name="Chatroom"></logo>
      </el-col>

      <el-col :span="10" style="display:flex; justify-content: center">
        <search-box class="header-search-box"></search-box>
      </el-col>

      <el-col class="header-mini-profile" :span="4">
        <el-dropdown>
          <mini-profile></mini-profile>
          <template #dropdown>
            <profile class="dropdown-profile"></profile>
          </template>
        </el-dropdown>

      </el-col>

      <el-col class="header-icons" :span="4">
        <invitation-message></invitation-message>
        <Notification></Notification>
        <language-switch></language-switch>
        <account-switch></account-switch>
      </el-col>

    </el-row>
  </div>
</template>

<script>
import MiniProfile from "@/components/common/navigation/header/MiniProfile.vue";
import SearchBox from "@/components/common/navigation/header/SearchBox.vue";
import Logo from "@/components/common/navigation/header/Logo.vue";
import InvitationMessage from "@/components/common/icons/InvitationMessage.vue";
import Notification from "@/components/common/icons/Notification.vue";
import LanguageSwitch from "@/components/common/icons/LanguageSwitch.vue";
import AccountSwitch from "@/components/common/icons/AccountSwitch.vue";

import {Search} from "@element-plus/icons-vue";
import Profile from "@/components/user/Profile.vue";

export default {
  name: "Header",
  data() {
    return {
      header_app_logo: 'src/assets/images/chatroom_logo.png',

      search_input: '',
      search_icon: Search,
      icon_size: 25,

      avatar_url: 'src/assets/images/avatar.png',
      userinfo: {
        name: 'Tommy',
        id: 'tommy345'
      }
    }
  },
  components: {
    Profile,
    InvitationMessage,
    AccountSwitch,
    Notification,
    LanguageSwitch,
    Logo,
    SearchBox,
    MiniProfile,
    Search
  }
}
</script>

<style>
.header-card {
  width: 100vw;
  height: 80px;
  align-content: center;
  border-bottom: solid 1px lightgray;
  background-color: white;
  opacity: 0.8;
}

.header-row {
  align-items: center;
  padding-left: 4vw;
  padding-right: 4vw;
}

.logo-display {
  display: flex;
  flex-flow: row wrap;
  justify-content: flex-start;
  align-items: center;
  gap: 1vw;
}

.header-search-box {
  flex-basis: 300px;
  min-width: 100px;
  min-height: 40px;
  align-self: center;
}

.dropdown-profile {
  width: 600px;
  height: 600px;
}

.header-icons {
  display: flex;
  flex-flow: row nowrap;
  justify-content: flex-end;
  padding-right: 2px;
  gap: 2vw;
}

/* add interactive effect for icons*/
.interactive-icon {
  transition: transform 0.2s ease, color 0.2s ease;
  cursor: pointer;
}

.interactive-icon:hover {
  transform: scale(1.3);
  color: #007bff;
  border-radius: 50%;
  box-shadow: 0 0 10px rgba(0, 123, 255, 0.5);
}

.interactive-icon:active {
  transform: scale(1.1);
  color: #ff4500;
}
</style>

