// Import createRouter and createWebHashHistory methods
import {createRouter, createWebHashHistory} from 'vue-router';

const constantRoutes = [
    {
        path: '/',
        name: 'home',
        component: import('@/views/user/home.vue'),
    },
    {
        path: '/register',
        name: 'register',
        component: () => import('@/views/user/account/register.vue'),
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('@/views/user/account/login.vue'),
    },
    {
        path: '/update_password',
        name: 'update_password',
        component: () => import('@/views/user/account/reset_pwd.vue'),
    }
];

const router = createRouter({
    history: createWebHashHistory(),
    routes: constantRoutes
});

export default router;
