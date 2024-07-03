// Import createRouter and createWebHashHistory methods
import {createRouter, createWebHashHistory} from 'vue-router';

const constantRoutes = [
    {
        path: '/',
        name: 'welcome',
        component: () => import('@/views/common/welcome.vue')
    },
    {
        path: '/home',
        name: 'home',
        component: () => import('@/views/user/home.vue'),
    },
    {
        path: '/auth/register',
        name: 'register',
        component: () => import('@/views/user/auth/register.vue'),
    },
    {
        path: '/auth/login',
        name: 'login',
        component: () => import('@/views/user/auth/login.vue'),
    },
    {
        path: '/admin/login',
        name: 'MLogin',
        component: () => import('@/views/admin/login.vue'),
    },
    {
        path: '/auth/reset',
        name: 'reset',
        component: () => import('@/views/user/auth/reset.vue'),
    },
    {
        path: '/auth/sign_off',
        name: 'sign_off',
        component: () => import('@/views/user/auth/sign_off.vue'),
    },
    {
        path: '/profile',
        name: 'profile',
        component: () => import('@/views/user/profile.vue')
    },
    {
        path: '/chat',
        name: 'chat',
        component: () => import('@/views/user/chat.vue')
    },{
        path: '/list',
        name: 'list',
        component: () => import('@/views/user/list.vue')
    },
    {
        path: '/setting',
        name: 'setting',
        component: () => import('@/views/user/setting.vue')
    },
    {
        path: '/about',
        name: 'about',
        component: () => import('@/views/common/about.vue')
    },
    {
        path: '/admin',
        name: 'admin',
        component: () => import('@/views/admin/home.vue')
    },
    {
        path: '/test2',
        name: 'test2',
        component: () => import('@/components/ContextMenuTest.vue')
    },
    {
        path: '/test',
        name: 'test',
        component:() => import('@/components/Identify/Test.vue')
    }
];

const router = createRouter({
    history: createWebHashHistory(),
    routes: constantRoutes
});

export default router;
