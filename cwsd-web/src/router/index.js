import Vue from 'vue'
import Router from 'vue-router'

import Layout from '@/layout'
import { getStore } from '@/utils/storage'

Vue.use(Router)
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/home',
    children: [
      {
        path: 'help',
        component: () => import('@/views/help/index'),
      },
      {
        path: 'home',
        component: () => import('@/views/home/index'),
      },
      {
        path: 'message',
        component: () => import('@/views/message/index'),
      },
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
      },
      {
        path: 'permission',
        component: () => import('@/views/permission/index'),
      },
      {
        path: 'userInfo',
        component: () => import('@/views/userInfo/index'),
      },
      {
        path: 'attendance',
        component: () => import('@/views/attendance/index'),
      },
      {
        path: 'attendanceApply',
        component: () => import('@/views/attendance/apply'),
      },
      {
        path: 'attendanceVet',
        component: () => import('@/views/attendance/vet'),
      },
      {
        path: 'myAttendance',
        component: () => import('@/views/attendance/myAttendance'),
      },
      {
        path: 'monitor',
        component: () => import('@/views/monitor/index'),
      },
      {
        path: 'dictype',
        component: () => import('@/views/dictype/index'),
      }
    ]
  },
  {
    path: '/404',
    component: () => import('@/views/error-page/404'),
    hidden: true
  },

]

const createRouter = () => new Router({
  routes: constantRoutes
})

const router = createRouter()

router.beforeEach((to, form, next) => {
  //let user = sessionStorage.getItem('user');
  let user = getStore('user');
  console.log(user)
  if (to.path == '/login' | to.path == '/register') {
    if (user) {
      next = ({ path: '/' })
    } else {
      next()
    }
  } else {
    if (!user) {
      next({ path: '/login' })
    } else {
      next()
    }
  }
})

export default router