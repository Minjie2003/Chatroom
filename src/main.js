import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

import App from './App.vue'
import store from './store'
import router from "./router"
import axios from 'axios'
//import { createRouter } from 'vue-router'
axios.defaults.baseURL = "https://localhost:8080/my_chatroom"

// 引入全局 CSS 文件
import './assets/css/bootstrap.min.css'
import './assets/css/typography.css'
import './assets/css/style.css'
import './assets/css/responsive.css'

// 动态加载 JS 文件
// import { loadScripts } from './loadScripts.js'
// loadScripts()

const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.config.globalProperties.$http = axios
app.use(store)     //此时任意的组件里面就可以去取vuex的数据了
app.use(ElementPlus)
app.use(router)
app.mount("#app")

