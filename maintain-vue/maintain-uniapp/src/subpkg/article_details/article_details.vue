<template>
  <view class="example-body">
    <view class="uni-box-head">
        <uni-title type="h1" align="center" title="uni-app介绍"></uni-title>
    </view>
    <view class="uni-box">
        <uni-title class="h3" type="h3" title="1 框架介绍"></uni-title>
    </view>
    <view class="uni-box">
        <uni-title class="h4" type="h4" title="1.1 什么是uni-app"></uni-title>
    </view>
    <view>
        <text class="uni-text">uni-app是一个使用Vue.js开发所有前端应用的框架，开发者编写一套代码，可发布到iOS、Android、H5、以及各种小程序（微信/支付宝/百度/头条/QQ/钉钉）等多个平台。即使不跨端，uni-app同时也是更好的小程序开发框架。DCloud公司拥有370万开发者用户，旗下uni-app有5万+案例、900款插件、50+微信/qq群，并且被阿里小程序工具内置，开发者可以放心选择。</text>
    </view>
    <view>打赏用的二维码</view>
  </view>
</template>

<script>
export default {

}
</script>

<style lang="scss" scoped>
    .example-body {
		/* #ifndef APP-NVUE */
		display: block;
		/* #endif */
		padding: 10px;
	}
	.uni-text {
		font-size: 14px;
		line-height: 22px;
		color: #333;
	}
</style>