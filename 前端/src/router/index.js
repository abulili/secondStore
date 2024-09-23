import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = new VueRouter({
  routes: [
    {
      path: '/',
      name: 'login',
      component: () => import('../views/Login/login.vue'),
    },
    {
      path: '/index',
      name: 'index',
      component: () => import('../views/Index/index.vue'),
      // options: {
      //   allowDuplicate: true,
      // },
    },
    // {
    //   path: '/about',
    //   name: 'about',
    //   meta: { requiresAuth: true },
    //   // route level code-splitting
    //   // this generates a separate chunk (about.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
    // },
    // {
    //   path: '/index',
    //   name: 'index',
    //   component: () => import('../views/Index/index.vue'),
    // },
    {
      path: '/chat',
      name: 'chat',
      meta: { requiresAuth: true },
      component: () => import('../views/Chat/chat.vue'),
    },
    {
      path: '/car',
      name: 'car',
      meta: { requiresAuth: true },
      component: () => import('../views/Car/car.vue'),
    },
    {
      path: '/manage',
      name: 'manage',
      meta: { requiresAuth: true },
      component: () => import('../views/Manage/manage.vue'),
    },
    {
      path: '/release',
      name: 'release',
      meta: { requiresAuth: true },
      component: () => import('../views/Release/release.vue'),
    },
    {
      path: '/selfChange',
      name: 'selfChange',
      meta: { requiresAuth: true },
      component: () => import('../views/SelfChange/self.vue'),
    },
    {
      path: '/star',
      name: 'star',
      meta: { requiresAuth: true },
      component: () => import('../views/Star/star.vue'),
    },
    {
      path: '/store',
      name: 'store',
      // meta: { requiresAuth: true },
      component: () => import('../views/Store/store.vue'),
    },
    {
      path: '/userview',
      name: 'userview',
      // meta: { requiresAuth: true },
      component: () => import('../views/UserView/userview.vue'),
    },
    {
      path: '/test',
      name: 'test',
      meta: { requiresAuth: true },
      component: () => import('../views/Test/test.vue'),
    },
  ]
})


// const router = new VueRouter({
//   routes
// })

export default routes
