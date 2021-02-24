import Vue from 'vue'
import VueRouter from 'vue-router'

import HomePage from '@/views/HomePage.vue'
import CreatePostPage from '@/views/CreatePostPage.vue'
import MuServersPage from '@/views/MuServersPage.vue'
import MuServerPostsPage from '@/views/MuServerPostsPage.vue'

import UserPostsPage from '@/views/user/UserPostsPage.vue'
import ProfilePage from '@/views/user/ProfilePage.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'HomePage',
    component: HomePage,
  },
  {
    path: '/mu-servers',
    name: 'MuServersPage',
    component: MuServersPage,
  },
  {
    path: '/mu-server',
    redirect: { name: '/' },
  },
  {
    path: '/mu-server/:id',
    redirect: { name: '/' },
  },
  {
    path: '/mu-server/:id/posts',
    name: 'MuServerPostsPage',
    component: MuServerPostsPage,
  },
  {
    path: '/user',
    redirect: { name: '/' },
  },
  {
    path: '/user/posts',
    name: 'UserPostsPage',
    component: UserPostsPage,
  },
  {
    path: '/user/profile',
    name: 'ProfilePage',
    component: ProfilePage,
  },
  {
    path: '/post',
    redirect: { name: '/' },
  },
  {
    path: '/post/new',
    name: 'CreatePostPage',
    component: CreatePostPage,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
