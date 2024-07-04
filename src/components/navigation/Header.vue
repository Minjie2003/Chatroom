<template>
  <div class="cr-header">

    <logo app_logo="src/assets/images/chatroom_logo.png" app_name="Chatroom"/>

    <search-box/>

    <div class="header-mini-profile-container">
      <el-dropdown>
        <mini-profile
            :avatar_url="store.myinfos.photo || user.avatar_url"
            :name="store.myinfos.username || 'unnamed'"
            :id="store.myinfos.id || 'null'"
        ></mini-profile>
        <template #dropdown>
          <profile class="dropdown-profile"></profile>
        </template>
      </el-dropdown>
    </div>

    <div class="header-icons-container">
      <invitation-message/>
      <Notification/>
      <language-switch/>
      <account-switch/>
    </div>

  </div>
</template>

<script>
import MiniProfile from "@/components/icons/MiniProfile.vue";
import SearchBox from "@/components/navigation/SearchBox.vue";
import Logo from "@/components/icons/Logo.vue";
import InvitationMessage from "@/components/icons/RequestIcon.vue";
import Notification from "@/components/icons/NotificationIcon.vue";
import LanguageSwitch from "@/components/icons/LanguageSwitch.vue";
import AccountSwitch from "@/components/icons/AccountSwitch.vue";

import {Search} from "@element-plus/icons-vue";
import Profile from "@/components/Profile.vue";
import store from "@/store/store.js"

export default {
  name: "Header",
  computed: {
    store() {
      return store
    }
  },
  data() {
    return {
      header_app_logo: 'src/assets/images/chatroom_logo.png',

      search_input: '',
      search_icon: Search,
      icon_size: 25,


      user: {
        name: 'Tommy',
        id: 'tommy345',
        avatar_url: 'src/assets/images/avatar-yellow.png',
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
.cr-header {
  width: 100%;
  height: var(--cr-header-height);
  opacity: var(--cr-default-opacity);
  display: grid;
  grid-template-columns: 1fr 3fr 1fr 1fr;
  align-content: center;
  position: fixed;
}

.cr-header logo {
  justify-self: center;
  align-self: center;
}

.cr-header  > .search-box {
  height: 50%;
  width: 40%;
  align-self: center;
  justify-self: center;
  min-width: 160px;
}

.header-mini-profile-container {
  align-self: center;
  justify-self: center;
}

.dropdown-profile {
  width: calc(60vh * 3/4);
  height: 65vh !important;
  .background {
    height: 25vh;
  }
  .avatar {
    width: 100px;
    height: 100px;
  }
}

.header-icons-container {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  justify-content: center;
  align-content: center;
}

/* add interactive effect for icons*/
.effected-icon {
  transition: transform 0.2s ease, color 0.2s ease;
  cursor: pointer;
}

.effected-icon:hover {
  transform: scale(1.3);
  color: #007bff;
  border-radius: 50%;
  box-shadow: 0 0 10px rgba(0, 123, 255, 0.5);
}

.effected-icon:active {
  transform: scale(1.1);
  color: #ff4500;
}
</style>

