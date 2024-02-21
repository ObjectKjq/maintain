import Vue from 'vue'
import App from './App'
import './uni.promisify.adaptor'
import {$http} from '@escook/request-miniprogram'

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
}
// 响应拦截器
$http.afterRequest = function(){
  uni.hideLoading()
}

const app = new Vue({
  ...App
})
app.$mount()
