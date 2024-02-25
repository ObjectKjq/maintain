import Vue from 'vue'

// 操作cookie，后端返回token存储到cookie中
import Cookies from 'js-cookie'

// 重置网页默认样式
import 'normalize.css/normalize.css'

// 引入element-ui
import Element from 'element-ui'
import './styles/element-variables.scss'
import enLang from 'element-ui/lib/locale/lang/en'// 如果使用中文语言包请默认支持，无需额外引入，请删除该依赖

// 映入自己写的全局样式
import '@/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'

// 映入icons图标
import './icons' 
// 映入权限控制，计算当前角色可以访问的路由规则
import './permission'
// 错误日志
import './utils/error-log' // error log
// 引入过滤器
import * as filters from './filters' // global filters

/**
 * 如果你不想使用mock-server
 * 你想使用MockJs模拟api
 * 你可以执行:mockXHR()
 *
 * 目前MockJs将在生产环境中使用，
 * 请在上网前将其删除!！ ！
 */

// process是node中的全局变量
// 当通过npm run dev时，node_dnv就是development
// 当通过npm run build时，node_dnv就是production
// process.env.NODE_ENV === 'production'表示生成环境
// if (process.env.NODE_ENV === 'production') {
//   // 导入mock模块，模拟接口中的，本项目接口，都是模拟的
//   const { mockXHR } = require('../mock')
//   mockXHR()
// }

// element时插件
Vue.use(Element, {
  // 在cookie中存入按钮大小，size表示小按钮
  size: Cookies.get('size') || 'medium', // set element-ui default size
  // locale: enLang // 如果使用中文，无需设置，请删除
})

// 遍历并且注册全局过滤器
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
