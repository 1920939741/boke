/**
 * 路由注册中心
 */

import Vue from 'vue'
import VueRouter from 'vue-router'


//注册页面
import Login from '../views/Login.vue'
import Blogs from '../views/Blogs.vue'
import BlogEdit from '../views/BlogEdit.vue'
import BlogDetail from '../views/BlogDetail.vue'


Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'index',
        redirect: {name: "Blogs"}   //页面重定向
    },
    {
        path: '/blogs',
        name: 'Blogs',
        component: Blogs
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/blog/add',
        name: 'BlogAdd',
        component: BlogEdit,
        //添加权限访问，表示只有登录之后才能进行该操作
        meta: {
            requireAuth: true
        }
    },
    {
        path: '/blog/:blogId/edit',
        name: 'BlogEdit',
        component: BlogEdit,
        //添加权限访问，表示只有登录之后才能进行该操作
        meta: {
            requireAuth: true
        }
    },
    {
        path: '/blog/:blogId',
        name: 'BlogDetail',
        component: BlogDetail
    },

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
