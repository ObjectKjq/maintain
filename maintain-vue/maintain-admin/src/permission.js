// 引入路由
import router from './router'
// 引入仓库
import store from './store'
// 引入一个提示组件
import { Message } from 'element-ui'
// 引入进度条组件
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
// 登陆成功后，后端响应token存储到cookie中，获取token
import { getToken } from '@/utils/auth' // get token from cookie
// 获取文章标题
import getPageTitle from '@/utils/get-page-title'

// 开启进度条
NProgress.configure({ showSpinner: false }) // NProgress Configuration

// 定义白名单
const whiteList = ['/login', '/auth-redirect'] // no redirect whitelist

// 路由前置守卫，
router.beforeEach(async(to, from, next) => {
  // 开启进度条
  NProgress.start()

  // 设置标题，每个路由中都有一个meta，meta有title
  document.title = getPageTitle(to.meta.title)

  // 从cookie获取token
  const hasToken = getToken()

  // 如果有值表示登陆了
  if (hasToken) {
    // 判断访问的页面是不是登陆页面
    if (to.path === '/login') {
      // 跳转到首页
      next({ path: '/' })
      // 关闭进度条
      NProgress.done() 
    } else {
      // 已经登陆
      // 从vuex中获取角色
      const hasRoles = store.getters.roles && store.getters.roles.length > 0
      if (hasRoles) {
        // 拿到角色后放行
        
        next()
      } else {
        // 没有角色
        try {
          // 调用方法重新获取角色数据，保存到vuex中
          // 并且拿到roles等用户数据
          const { roles } = await store.dispatch('user/getInfo')

          // 根据角色，生成当前角色可以访问的路由
          const accessRoutes = await store.dispatch('permission/generateRoutes', roles)

          // 把角色生的路由规则添加到路由系统中
          router.addRoutes(accessRoutes)

          // 放行
          next({ ...to, replace: true })
        } catch (error) {
          // 清除token
          await store.dispatch('user/resetToken')
          Message.error(error || 'Has Error')
          // 添砖到登录页
          next(`/login?redirect=${to.path}`)
          NProgress.done()
        }
      }
    }
  } else {
    // 没有token
    // 看看访问路径在白名单没有
    if (whiteList.indexOf(to.path) !== -1) {
      // 放行
      next()
    } else {
      // 跳转到登录页面
      next(`/login?redirect=${to.path}`)
      // 关闭进度条
      NProgress.done()
    }
  }
})

// 路由后置守卫
router.afterEach(() => {
  // 关闭进度条
  NProgress.done()
})
