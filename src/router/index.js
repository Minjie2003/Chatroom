// Import createRouter and createWebHashHistory methods
import {createRouter, createWebHashHistory} from 'vue-router';

const constantRoutes = [
    {
        path: '/',
        name: 'application',
        component: import('@/views/common/layout.vue')
    },
    {
        path: '/welcome',
        name: 'welcome',
        component: import('@/views/common/welcome.vue')
    },
    {
        path: '/home',
        name: 'home',
        component: import('@/views/user/home.vue'),
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
    },
    {
        path: '/list/friends',
        name: 'friends',
        component: () => import('@/views/user/list/friends.vue')
    },
    {
        path: '/list/groups',
        name: 'groups',
        component: () => import('@/views/user/list/groups.vue')
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
    }
];

const router = createRouter({
    history: createWebHashHistory(),
    routes: constantRoutes
});

export default router;
