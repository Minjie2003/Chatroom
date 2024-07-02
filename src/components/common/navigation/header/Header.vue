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
        <mini-profile></mini-profile>
      </el-col>

      <el-col class="header-icons" :span="4">

        <Notification></Notification>

        <el-tooltip
            class="box-item"
            effect="light"
            content="查看举报/警告"
            placement="bottom"
        >
          <el-icon class="interactive-icon" :size="icon_size" color="#50b5ff">
            <Warning/>
          </el-icon>
        </el-tooltip>

        <language-switch></language-switch>

        <el-tooltip
            class="box-item"
            effect="light"
            content="退出登录"
            placement="bottom"
        >
          <el-icon
              class="interactive-icon"
              :size="icon_size" color="#50b5ff">
            <Switch/>
          </el-icon>
        </el-tooltip>
      </el-col>

    </el-row>
  </div>
</template>

<script>
import {Bell, Guide, Search, Switch} from '@element-plus/icons-vue'
import MiniProfile from "@/components/common/navigation/header/MiniProfile.vue";
import SearchBox from "@/components/common/navigation/header/SearchBox.vue";
import Logo from "@/components/common/navigation/header/Logo.vue";
import LanguageSwitch from "@/components/common/icons/LanguageSwitch.vue";
import Notification from "@/components/common/icons/Notification.vue";

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
    Notification,
    LanguageSwitch,
    Logo,
    SearchBox,
    MiniProfile,
    Bell,
    Guide,
    Switch,
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

