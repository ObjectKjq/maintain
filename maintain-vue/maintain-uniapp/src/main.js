import Vue from 'vue'
import App from './App'
import './uni.promisify.adaptor'
import {$http} from '@escook/request-miniprogram'
import store from './store/index'

Vue.config.productionTip = false

App.mpType = 'app'
uni.$http = $http
// 设置全局根路径
$http.baseUrl = 'http://localhost:8081'
// 请求拦截器
$http.beforeRequest = function(options){
  // 当请求开始时，屏幕出现数据加载中
  uni.showLoading({
    title: '数据加载中...'
  });
  // 获取token放到header中
  if(store.state.user.token != undefined){
    options.header = {
      "Authorization": store.state.user.token
    }
  }else if(options.url.indexOf('/login') !== -1){
    options.header = {
      "content-type": "application/x-www-form-urlencoded",
    }
  }
  
}
// 响应拦截器
$http.afterRequest = function(){
  uni.hideLoading()
}

// 数据请求失败封装全局方法，当数据请求失败就调用此方法
uni.$showMsg = function(title = '数据请求失败', duration = 1500) {
  // 提示数据请求失败
  uni.showToast({
    title,
    duration,//1.5s显示
    icon: 'none'
  })
}

const app = new Vue({
  ...App,
  store,
})
app.$mount()
