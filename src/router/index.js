import { createRouter, createWebHistory } from 'vue-router'
// 动态导入 components 文件夹下的所有 .vue 文件
import Login from '@/views/Identify/Login.vue'
import Register from '@/views/Identify/Register.vue';
import UPassword from '@/views/Identify/UPassword.vue';
import MLogin from '@/views/Identify/MLogin.vue';
import HomePage from '@/views/Identify/HomePage.vue';
const routes = [
  {
    path: '/',
    component: HomePage
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/register',
    component: Register
  },
  {
    path: '/forgot-password',
    component: UPassword
  },
  {
    path: '/mlogin',
    component: MLogin
  },
];

  const router = createRouter({
    history: createWebHistory(),
    routes
  });
export default router

