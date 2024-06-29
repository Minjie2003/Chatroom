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
        component: () => import('@/views/user/auth/register.vue'),
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('@/views/user/auth/login.vue'),
    },
    {
        path: '/reset',
        name: 'reset',
        component: () => import('@/views/user/auth/reset.vue'),
    }
];

const router = createRouter({
    history: createWebHashHistory(),
    routes: constantRoutes
});

export default router;
