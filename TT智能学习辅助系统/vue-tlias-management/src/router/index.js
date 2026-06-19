import { createRouter, createWebHistory } from 'vue-router';
import Index from '@/views/index/index.vue';
import Clazz from '@/views/clazz/index.vue';
import Stu from '@/views/stu/index.vue';
import Dept from '@/views/dept/index.vue';
import Emp from '@/views/emp/index.vue';
import EmpReport from '@/views/report/emp/index.vue';
import StuReport from '@/views/report/stu/index.vue';
import Log from '@/views/log/index.vue';
import Login from '@/views/login/index.vue';
import Layout from '@/views/layout/index.vue';

const routes = [
  { path: '/', 
    component: Layout,
    redirect: '/index',
    children: [
      { path: '/index', component: Index },
      { path: '/clazz', component: Clazz },
      { path: '/stu', component: Stu },
      { path: '/dept', component: Dept },
      { path: '/emp', component: Emp },
      { path: '/report/emp', component: EmpReport },
      { path: '/report/stu', component: StuReport },
      { path: '/log', component: Log }
    ]
  },
  { path: '/login', component: Login }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

// 全局路由导航守卫
router.beforeEach((to, from, next) => {
  // 如果是去登录页，直接放行
  if (to.path === '/login') {
    next();
    return;
  }
  
  // 检查本地存储中是否存在用户信息
  const loginUser = localStorage.getItem('loginUser');
  if (!loginUser) {
    // 未登录，重定向到登录页
    next('/login');
  } else {
    // 已登录，允许访问
    next();
  }
});

export default router;