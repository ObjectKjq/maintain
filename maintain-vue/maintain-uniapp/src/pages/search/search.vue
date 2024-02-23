<template>
  <view>
    <uni-search-bar 
    class="uni-mt-10" 
    radius="5" 
    placeholder="请输入..." 
    clearButton="auto" 
    cancelButton="none" 
    @input="input" />
    <view class="article-list">
        <uni-card @click="toArticle" title="基础卡片" extra="额外信息">
            <text class="uni-body">这是一个基础卡片示例，此示例展示了一个标题加标题额外信息的标准卡片。</text>
        </uni-card>
    </view>
  </view>
</template>

<script>
export default {
    data() {
        return {
            // 延时器，timerId
            timer: null,
            // 搜索关键字
            kw: '',
        }
    },
    methods: {
        toArticle(){
            uni.navigateTo({
                url:'/subpkg/article_details/article_details'
            });
        },
        input(e) {
            // 清除timer对应的延时器
            clearTimeout(this.timer)
            // 重新启动一个延时器，并把timerId赋值给this.timer
            this.timer = setTimeout(() => {
                // 五百毫秒没有触发输入事件，搜索关键字
                this.kw = e;
                // 根据关键词调用搜索建议，这里触发搜索事件
                // this.getSearchList();
                console.log('----input:', e)
            }, 500);
        },
    },
}
</script>

<style scoped>

</style>