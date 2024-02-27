<template>
  <view>
    <uni-search-bar 
    class="uni-mt-10" 
    radius="5" 
    placeholder="请输入..." 
    clearButton="auto" 
    cancelButton="none" 
    @input="input" />
    <view class="article-list" v-if="articles.length">

        <uni-card v-for="item in articles" :key="item.id" @click="toArticle(item.id)" :title="item.title" :extra="item.createTime.substring(0, 10)">
            <text class="uni-body">{{item.content.substring(0, 40)}}</text>
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
            // 默认是第一页
            page: 1,
            // 每页六条数据
            num: 6,
            // 文章列表数据
            articles: [],
            // articleList节流阀，防止发送过多数据请求
            isloading: false
        }
    },
    methods: {
        toArticle(id){
            uni.navigateTo({
                url:`/subpkg/article_details/article_details?id=${id}`
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
                this.page = 1;
                this.articles = [];
                this.getSearchList();
            }, 500);
        },
        getSearchList(){
            if(this.kw == null || this.kw == "" || this.kw == undefined) return;
            uni.$http.get('/getArticles', {
                // 第几页
                page: this.page,
                // 每页多少条数据
                num: this.num,
                // 搜索查询的内容
                searchText: this.kw,
            }).then((res) => {
                this.articles.push(...res.data.data);
            })
        }
    },
    // 接收上拉触顶行为
    onReachBottom(){
        // 节流阀打开就不发送请求
        if(this.isloading) return;
        this.page += 1;
        this.getSearchList();
    },
}
</script>

<style scoped>

</style>