// Import createRouter and createWebHashHistory methods
import {createRouter, createWebHashHistory} from 'vue-router';

const constantRoutes = [
    {
        path: '/',
        name: 'home',
        component: import('@/views/home/home.vue'),
    },
    {
        path: '/register',
        name: 'register',
        component: () => import('@/views/register/register.vue'),
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('@/views/login/login.vue'),
    },
    {
        path: '/update_password',
        name: 'update_password',
        component: () => import('@/views/update_password/update_password.vue'),
    }
];

const router = createRouter({
    history: createWebHashHistory(),
    routes: constantRoutes
});

export default router;
