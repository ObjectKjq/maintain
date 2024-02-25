import { asyncRoutes, constantRoutes } from '@/router'

/**
 * Use meta.role to determine if the current user has permission
 * @param roles 角色
 * @param route 某一条路由规则
 */

function hasPermission(roles, route) {
  if (route.meta && route.meta.roles) {
    return roles.some(role => route.meta.roles.includes(role))
  } else {
    return true
  }
}

/**
 * Filter asynchronous routing tables by recursion
 * @param routes 所有动态路由
 * @param roles 所有角色
 */
export function filterAsyncRoutes(routes, roles) {
  // 计算出来的可以访问的路由规则
  const res = []
  // 遍历所有动态路由路径
  routes.forEach(route => {
    // 把规则copy给tmp
    const tmp = { ...route }
    // hasPermission函数，判断角色是否可以访问此路由
    if (hasPermission(roles, tmp)) {
      // 可以访问
      if (tmp.children) {
        // 递归计算
        tmp.children = filterAsyncRoutes(tmp.children, roles)
      }
      res.push(tmp)
    }
  })

  return res
}

const state = {
  // 角色可以访问的所有路由规则，静态+计算
  routes: [],
  // 计算出来的路由规则
  addRoutes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    // 静态路由拼接上我们计算出来的路由规则
    state.routes = constantRoutes.concat(routes)
  }
}

const actions = {
  // roles传递过来的角色，封装路由规则
  generateRoutes({ commit }, roles) {
    return new Promise(resolve => {
      let accessedRoutes
      // 判断角色中是否包含admin角色
      // if (roles.includes('admin')) {
        // 把所有动态路由复制给路由规则
        // 说白了你的角色中有admin，那么asyncRoutes中的所有规则你都能访问
        // accessedRoutes = asyncRoutes || []
      // } else {
        // 如果角色没有admin，就计算路由规则
        accessedRoutes = filterAsyncRoutes(asyncRoutes, roles)
      // }
      // 存储计算好的路由规则
      commit('SET_ROUTES', accessedRoutes)
      resolve(accessedRoutes)
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
