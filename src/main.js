import {createApp} from 'vue'
import ElementPlus from 'element-plus'
import {createI18n} from "vue-i18n";
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

import App from './App.vue'
import store from './store'
import router from "./router"
import axios from 'axios'

import {messages} from "@/i18n/config.js";


const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
export let i18n = createI18n({
    locale: 'ch',
    fallbackLocale: 'en',
    messages: messages
})


app.config.globalProperties.$http = axios
app.use(store)     //此时任意的组件里面就可以去取vuex的数据了
app.use(ElementPlus)
app.use(router)
app.use(i18n)

app.mount("#app")

