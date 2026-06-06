import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue'),
    meta: { title: '登录' }
  },
  {
    path: '/',
    component: () => import('../views/Layout.vue'),
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('../views/Dashboard.vue'),
        meta: { title: '首页' }
      },
      {
        path: 'departments',
        name: 'Departments',
        component: () => import('../views/DepartmentList.vue'),
        meta: { title: '科室管理' }
      },
      {
        path: 'doctors',
        name: 'Doctors',
        component: () => import('../views/DoctorList.vue'),
        meta: { title: '医生管理' }
      },
      {
        path: 'nurses',
        name: 'Nurses',
        component: () => import('../views/NurseList.vue'),
        meta: { title: '护士管理' }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 路由守卫：检查登录状态
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  if (to.path === '/login') {
    next()
  } else if (!token) {
    next('/login')
  } else {
    next()
  }
})

export default router
