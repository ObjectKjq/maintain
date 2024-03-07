<template>
  <view class="example-body">
    <view class="uni-box-head">
        <uni-title type="h1" align="center" :title="article.title"></uni-title>
    </view>
    <view class="uni-box" v-if="article.createTime">
        <uni-title class="h5" type="h5" :title="article.createTime.substring(0, 10)"></uni-title>
    </view>
    <view>
        <span class="uni-text" v-html="article.content"></span>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      article:{}
    }
  },
  methods: {
    async getArticle(id){
      let res = await uni.$http.get(`/getArticle/${id}`)
      this.article = res.data.data;
      return res;
    }
  },
  onLoad(option){
    this.getArticle(option.id)
  }
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