import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

import bak from './bak.js'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    noCache: true                if set true, the page will no be cached(default is false)
    affix: true                  if set true, the tag will affix in the tags-view
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
// 静态路由
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/auth-redirect'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/error-page/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error-page/401'),
    hidden: true
  },
]

/**
 * the routes that need to be dynamically loaded based on user roles
 */
// 动态路由，本文件没有使用。其他文件导入使用
// 这里的规则是根据用户的角色，去计算出当前角色能访问哪些路由
export const asyncRoutes = [
  // 以下配置的是维修师路由
  {
    path: '/',
    component: Layout,
    redirect: 'publish',
    alwaysShow: true,
    name: 'Appoint',
    meta: {
      title: '预约管理',
      icon: 'documentation',
      roles: ['maintain']
    },
    children: [
      {
        path: 'publish',
        component: () => import('@/views/maintain-appoint/publish-appoint'),
        name: 'Publish',
        meta: {
          // 管理员标题名
          title: '发布预约',
          roles: ['maintain']
        }
      },
      {
        path: 'appraise',
        component: () => import('@/views/maintain-appoint/appraise'),
        name: 'Appraise',
        meta: {
          // 管理员标题名
          title: '估价管理',
          roles: ['maintain']
        }
      },
      {
        path: 'verify',
        component: () => import('@/views/maintain-appoint/verify'),
        name: 'Verify',
        meta: {
          // 管理员标题名
          title: '维修完成管理',
          roles: ['maintain']
        }
      }
    ]
  },
  {
    path: '/article',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('@/views/maintain-article/maintain-article'),
        name: 'Article',
        meta: { title: '发布文章', icon: 'edit', roles: ['maintain']}
      }
    ]
  },
  {
    path: '/skill',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('@/views/maintain-skill/maintain-skill'),
        name: 'Skill',
        meta: { title: '技能管理', icon: 'skill', roles: ['maintain'] }
      }
    ]
  },
  {
    path: '/certificate',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('@/views/maintain-certificate/maintain-certificate'),
        name: 'Certificate',
        meta: { title: '证书管理', icon: 'education', roles: ['maintain'] }
      }
    ]
  },
  {
    path: '/scope',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('@/views/maintain-scope/maintain-scope'),
        name: 'Scope',
        meta: { title: '业务范围管理', icon: 'international', roles: ['maintain'] }
      }
    ]
  },
  {
    path: '/consult',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('@/views/maintain-consult/maintain-consult'),
        name: 'Consult',
        meta: { title: '消息管理', icon: 'message', roles: ['maintain'] }
      }
    ]
  },
  // 以下配置的是管理员路由
  {
    path: '/',
    component: Layout,
    redirect: '/user',
    children: [
      {
        path: 'user',
        component: () => import('@/views/admin-user/admin-user'),
        name: 'User',
        meta: {
          title: '用户管理',
          icon: 'peoples', 
          // affix: true, 
          roles: ['admin'] 
        }
      }
    ]
  },
  {
    path: '/article',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('@/views/admin-article/admin-article'),
        name: 'Article',
        meta: {
          title: '文章管理',
          icon: 'documentation', 
          roles: ['admin'] 
        }
      }
    ]
  },
  {
    path: '/feedback',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('@/views/admin-feedback/admin-feedback'),
        name: 'Feedback',
        meta: {
          title: '反馈管理',
          icon: 'bug', 
          roles: ['admin'] 
        }
      }
    ]
  },
  {
    path: '/report',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('@/views/admin-report/admin-report'),
        name: 'Report',
        meta: {
          title: '举报管理',
          icon: 'el-icon-warning', 
          roles: ['admin'] 
        }
      }
    ]
  },
  {
    path: '/sort',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('@/views/admin-sort/admin-sort'),
        name: 'Sort',
        meta: {
          title: '分类管理',
          icon: 'component', 
          roles: ['admin'] 
        }
      }
    ]
  },
  {
    path: '/audit',
    component: Layout,
    redirect: '/audit/article',
    meta: {
      title: '审核',
      icon: 'el-icon-s-claim', 
      roles: ['admin'] 
    },
    children: [
      {
        path: 'article',
        component: () => import('@/views/admin-audit/article'),
        name: 'Article',
        meta: {
          title: '文章审核', 
          roles: ['admin'] 
        }
      },
      {
        path:'certificate',
        component: () => import('@/views/admin-audit/certificate'),
        name: 'Certificate',
        meta: {
          title: '证书审核',
          roles: ['admin'] 
        }
      }
    ]
  },
  {
    path: '/consult',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('@/views/admin-consult/admin-consult'),
        name: 'Consult',
        meta: {
          title: '咨询管理',
          icon: 'message', 
          roles: ['admin'] 
        }
      }
    ]
  },
  {
    path: '/score',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('@/views/admin-score/admin-score'),
        name: 'Score',
        meta: {
          title: '评分管理',
          icon: 'form', 
          roles: ['admin'] 
        }
      }
    ]
  },
  // 原本效果路由
  // ...bak,

]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  // 配置路由规则
  routes: constantRoutes
})

// 调用函数返回router
const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}
// 导出路由对象
export default router
