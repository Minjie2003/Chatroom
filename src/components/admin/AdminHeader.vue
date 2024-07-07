<template>
  <el-row>
      <div class="cr-header">

      <logo app_logo="src/assets/images/chatroom_logo.png" app_name="Chatroom"/>

          <admin-search/>
      
          <div class="header-mini-profile-container">
              <mini-profile
                  :avatar_url="user_demo.avatar_url"
                  :name="`管理员`"
                  :user_id="`admin`"
              ></mini-profile>
          </div>
      
          <div class="header-icons-container">
              <language-switch/>
              <account-switch/>    
          </div>
      
      </div>
  </el-row>
</template>

<script>
import MiniProfile from "@/components/icons/MiniProfile.vue";
import Logo from "@/components/icons/Logo.vue";
import LanguageSwitch from "@/components/icons/LanguageSwitch.vue";
import AccountSwitch from "@/components/icons/AccountSwitch.vue";
import AdminSearch from '@/components/admin/AdminSearch.vue'
import {Search} from "@element-plus/icons-vue";
import {useStore} from "vuex";
import {useRouter} from "vue-router";
import {computed,onMounted} from "vue";

export default {
  name: "test",

  //... 操作符用于将 mapState 返回的对象展开到当前组件的 computed 计算属性中,myinfos：模块名
  setup(){
    const store = useStore()
  //   onMounted(() => {
  //     getuserinfos(store)
  //   });
    const myinfos = computed(() => store.state.myinfos.myinfos);
    const photo_url = '/my_chatroom/'+ myinfos.value.photo
    return {
      store,myinfos, photo_url
    }
  },
  data() {
    return {
      header_app_logo: 'src/assets/images/chatroom_logo.png',
      search_input: '',
      search_icon: Search,
      icon_size: 25,
      user_demo: {
        name: 'Default',
        id: 'NULL',
        avatar_url: 'src/assets/images/manager.jpg',
      }
    }
  },
  components: {
    AdminSearch,
    AccountSwitch,
    LanguageSwitch,
    Logo,
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
}
  .background {
    height: 25vh;
  }
  .avatar {
    width: 100px;
    height: 100px;
  }


.header-icons-container {
  display: grid;
  grid-template-columns: 1fr 1fr;
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

