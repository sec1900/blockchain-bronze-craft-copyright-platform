import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import(/* webpackChunkName: "about" */ '../views/Homehtml.vue')
  },
  {
    path: '/home',
    name: 'home1',
    component: () => import(/* webpackChunkName: "about" */ '../views/Homehtml1.vue'),
  },
  {
    path: '/show',
    name: 'Show',
    component: () => import(/* webpackChunkName: "about" */ '../views/Show.vue')
  },
  {
    path: '/p1',
    name: 'Show',
    component: () => import(/* webpackChunkName: "about" */ '../views/P1.vue')
  },
  {
    path: '/p2',
    name: 'Show',
    component: () => import(/* webpackChunkName: "about" */ '../views/P2.vue')
  },
  {
    path: '/p3',
    name: 'Show',
    component: () => import(/* webpackChunkName: "about" */ '../views/P3.vue')
  },
  {
    path: '/p4',
    name: 'Show',
    component: () => import(/* webpackChunkName: "about" */ '../views/P4.vue')
  },
  {
    path: '/p5',
    name: 'Show',
    component: () => import(/* webpackChunkName: "about" */ '../views/P5.vue')
  },
  {
    path: '/z1',
    name: 'Show',
    component: () => import(/* webpackChunkName: "about" */ '../views/z1.vue')
  },
  {
    path: '/z2',
    name: 'Show',
    component: () => import(/* webpackChunkName: "about" */ '../views/z2.vue')
  },
  {
    path: '/z3',
    name: 'Show',
    component: () => import(/* webpackChunkName: "about" */ '../views/z3.vue')
  },
  {
    path: '/z4',
    name: 'Show',
    component: () => import(/* webpackChunkName: "about" */ '../views/z4.vue')
  },
  {
    path: '/z5',
    name: 'Show',
    component: () => import(/* webpackChunkName: "about" */ '../views/z5.vue')
  },
  {
    path: '/z6',
    name: 'Show',
    component: () => import(/* webpackChunkName: "about" */ '../views/z6.vue')
  },
  {
    path: '/z7',
    name: 'Show',
    component: () => import(/* webpackChunkName: "about" */ '../views/z7.vue')
  },
  {
    path: '/z8',
    name: 'Show',
    component: () => import(/* webpackChunkName: "about" */ '../views/z8.vue')
  },
  {
    path: '/z9',
    name: 'Show',
    component: () => import(/* webpackChunkName: "about" */ '../views/z9.vue')
  },
  {
    path: '/z10',
    name: 'Show',
    component: () => import(/* webpackChunkName: "about" */ '../views/z10.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')
  },
  {
    path: '/login1',
    name: 'Login1',
    component: () => import(/* webpackChunkName: "about" */ '../views/Login1.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import(/* webpackChunkName: "about" */ '../views/Register.vue')
  },
  {
    path: '/manageuser',
    component: () => import('../views/Manageuser.vue'),
    children: [
      {path: 'information', name: '个人信息', component: () => import(/* webpackChunkName: "about" */ '../views/User-information.vue')},
      {path: 'up', name: '版权登记', component: () => import(/* webpackChunkName: "about" */ '../views/Upbanquan.vue')},
      {path: 'guanli', name: '版权管理', component: () => import(/* webpackChunkName: "about" */ '../views/Guanlibanquan.vue')},
      {path: 'updata', name: '版权修改', component: () => import(/* webpackChunkName: "about" */ '../views/updata.vue')},
      {path: 'shensu', name: '版权申诉', component: () => import(/* webpackChunkName: "about" */ '../views/Shensu.vue')},
      {path: 'chaxun', name: '版权查询', component: () => import(/* webpackChunkName: "about" */ '../views/Find.vue')},
      {path: 'xinxi', name: '版权信息', component: () => import(/* webpackChunkName: "about" */ '../views/banquanxinix.vue')},
    ]
  },
  {
    path: '/manage',
    component: () => import('../views/Manage.vue'),
    children: [
      {path: 'information', name: '标签管理', component: () => import(/* webpackChunkName: "about" */ '../views/User.vue')},
      {path: 'banquan', name: '博客管理', component: () => import(/* webpackChunkName: "about" */ '../views/Banquan.vue')},
      // {path: 'guanli', name: '管理员信息', component: () => import(/* webpackChunkName: "about" */ '../views/Guanli.vue')},
      // {path: 'file', name: '文件管理', component: () => import(/* webpackChunkName: "about" */ '../views/File.vue'),}
      // {path: 'pic', name: '照片管理', component: () => import(/* webpackChunkName: "about" */ '../views/Pic.vue'),},
      // {path: 'pic1', name: '照片管理', component: () => import(/* webpackChunkName: "about" */ '../views/pic1.vue'),},
      // {path: 'shensu', name: '申诉管理', component: () => import(/* webpackChunkName: "about" */ '../views/Gshensu.vue')},
      {path: 'article', name: '博客发布', component: () => import(/* webpackChunkName: "about" */ '../views/article.vue')}


    ]
  },
  {
    path: '/about',
    name: 'about',
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next)=>{
  localStorage.setItem("currentPathName", to.name)
  store.commit("setPath")
  next()
})


export default router
