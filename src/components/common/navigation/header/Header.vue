<template>
  <div class="header-card">
    <el-row class="header-row">

      <el-col :span="6">
        <logo app_logo="src/assets/images/chatroom_logo.png" app_name="Chatroom"></logo>
      </el-col>

      <el-col :span="10">
        <search-box class="header-search-box"></search-box>
      </el-col>

      <el-col class="header-mini-profile" :span="4">
        <mini-profile></mini-profile>
      </el-col>

      <el-col class="header-tooltips" :span="4">
        <el-tooltip
            class="box-item"
            effect="light"
            content="好友添加 "
            placement="bottom"
            :visible="tooltips_visible"
        >
          <el-icon color="#50b5ff" size="20" @click="showUserApply = !showUserApply">
            <Bell/>
          </el-icon>
        </el-tooltip>

        <el-tooltip
            class="box-item"
            effect="light"
            content="查看举报/警告"
            placement="bottom"
            :visible="tooltips_visible"
        >
          <el-icon :size="20" color="#50b5ff">
            <Warning/>
          </el-icon>
        </el-tooltip>

        <el-tooltip
            class="box-item"
            effect="light"
            content="切换语言"
            placement="bottom"
            :visible="tooltips_visible"
        >
          <el-icon :size="20" color="#50b5ff">
            <Guide/>
          </el-icon>
        </el-tooltip>

        <el-tooltip
            class="box-item"
            effect="light"
            content="退出登录"
            placement="bottom"
            :visible="tooltips_visible"
        >
          <el-icon :size="20" color="#50b5ff">
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

export default {
  name: "Header",
  data() {
    return {
      header_app_logo: 'src/assets/images/chatroom_logo.png',

      search_input: '',
      search_icon: Search,

      avatar_url: 'src/assets/images/avatar.png',
      userinfo: {
        name: 'Tommy',
        id: 'tommy345'
      },

      tooltips_visible: false,
    }
  },
  components: {
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
}

.header-row {
  display: flex;
  flex-flow: row nowrap;
  align-items: center;
  justify-content: space-between;
  margin: 0;
  padding-left: 3vw;
  padding-right: 3vw;
}

.logo-display {
  display: flex;
  flex-flow: row wrap;
  justify-content: flex-start;
  align-items: center;
  gap: 1vw;
}

.header-search-box {
  align-self: center;
}

.header-mini-profile {
  display: flex;
  flex-flow: row wrap;
  justify-content: center;
  align-items: center;
  gap: 1vw;
}

.header-tooltips {
  display: flex;
  flex-flow: row nowrap;
  justify-content: flex-end;
  padding-right: 2px;
  gap: 2vw;
}

</style>

