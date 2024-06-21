// 导入 createRouter 和 createWebHashHistory 方法
import { createRouter, createWebHashHistory } from 'vue-router'

// 导入需要使用的组件
import Discover from '@/components/Discover.vue'
import Friends from '@/components/Friends.vue'
import My from '@/components/My.vue'

// 定义路由规则
const routes = [
    { path: '/discover', component: Discover }, // 匹配 '/discover' 路径时显示 Discover 组件
    { path: '/friends', component: Friends }, // 匹配 '/friends' 路径时显示 Friends 组件
    { path: '/my', component: My }, // 匹配 '/my' 路径时显示 My 组件
]

// 创建路由器实例，传入 history 和 routes
const router = createRouter({
    history: createWebHashHistory(), // 使用 hash 模式的路由
    routes // 路由规则
})

// 导出路由器实例
export default router

