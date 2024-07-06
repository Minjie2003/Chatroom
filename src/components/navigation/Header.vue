<template>
  <div class="header">

    <span id="pad1"></span>

    <div class="logo-container">
      <logo :app_logo="Chatroom.LOGO" :app_name="Chatroom.NAME"/>
    </div>

    <span id="pad2"></span>
    <div class="search-box-container">
      <div class="search-box">
        <el-input
            class="search-box-input"
            v-model="searchInput"
            placeholder="Searching..."
            maxlength="20"
        >
          <template #append>
            <el-button class="custom-button" :icon="Search" style="color:  #50b5ff;"/>
          </template>
        </el-input>
      </div>
    </div>

    <div class="mini-profile-container">
      <el-dropdown>
        <mini-profile
            :avatar_url="userInfo.avatar_url"
            :name="userInfo.username"
            :user_id="userInfo.accountNum"
        ></mini-profile>
        <template #dropdown>
          <div class="profile-scrollbar-container">
            <el-scrollbar max-height="60vh">
              <profile class="dropdown-profile" :profile-data="toProfileData(userInfo)"></profile>
            </el-scrollbar>
          </div>

        </template>
      </el-dropdown>
    </div>

    <div class="header-icons-container">
      <invitation-message/>
      <Notification/>
      <language-switch/>
      <account-switch/>
    </div>

    <span id="pad3"></span>

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
import {computed} from "vue";
import {Chatroom, thisUser, toProfileData} from "@/store/cr_config.js";

export default {
  name: "Header",
  computed: {
    Search() {
      return Search
    },
    Chatroom() {
      return Chatroom
    }
  },
  methods: {toProfileData},

  setup() {
    const userInfo = computed(() => {
      return {
        ...toProfileData(thisUser.value)
      }
    })

    return {userInfo}
  },

  data() {
    return {
      search_input: '',
      search_icon: Search,
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
.header {
  width: 100%;
  height: var(--cr-header-height);
  opacity: var(--cr-default-opacity);
  display: grid;
  grid-template-columns: 1fr 3fr 3fr 6fr 3fr 3fr 1fr;
  grid-template-areas: 'left logo span search avatar tool right';
  align-content: start;
  justify-content: center;
  position: sticky;
  top: 0;
  background-color: #DDDA;
  z-index: 99;
}

.header:hover {
  background-color: #EEED;
}

.header::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url("src/assets/images/transparent-bg.png");
  background-size: cover;
}

.header * {
  z-index: 99;
}

#pad1 {
  grid-area: left
}

#pad2 {
  grid-area: span
}

#pad3 {
  grid-area: right
}

.logo-container {
  grid-area: logo;
}

.search-box-container {
  grid-area: search;
  place-content: center;
}

.search-box {
  min-width: 120px;
  width: 70%;
  height: 70%;

}

.mini-profile-container {
  grid-area: avatar;
}

.dropdown-profile {
  width: 30vw;
  height: 100%;
  .el-avatar {
    width: 120px;
    height: 120px;
  }
}

.header-icons-container {
  grid-area: tool;
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

