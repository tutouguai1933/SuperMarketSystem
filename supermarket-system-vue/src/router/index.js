import {createRouter, createWebHistory} from 'vue-router'
import Layout from "@/layout/Layout";

const routes = [{
    path: '/', name: 'Layout', component: Layout, redirect: "/bill", children: [{
        path: '/bill', name: 'Bill', component: () => import("@/views/Bill"),
    },{
        path: '/provider', name: 'Provider', component: () => import("@/views/Provider"),
    },{
        path: '/user', name: 'User', component: () => import("@/views/User"),
    },{
        path: '/password', name: 'Password', component: () => import("@/views/Password"),
    },{
        path: '/message', name: 'Message', component: () => import("@/views/Message"),
    }]
}, {
    path: '/login', name: 'Login', component: () => import("@/views/Login")
}, {
    path: '/register', name: 'Register', component: () => import("@/views/Register")
},]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL), routes
})

router.beforeEach((to, from, next) => {
    if (to.path === '/login' || to.path === '/register') {
        next()
        return
    }
    let user = sessionStorage.getItem("user") ? JSON.parse(sessionStorage.getItem("user")) : {}
    if (!user) {
        next('/login')
    } else {
        next()
    }
})

export default router
