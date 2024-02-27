<template>
    <view>
        <view class="menu">
            <uni-data-select
                v-model="value"
                :localdata="range"
                @change="change"
                :clear="false"
            ></uni-data-select>
        </view>
        <view class="article-list" >
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
        value: 0,
        range: [],
        // 默认是第一页
        page: 1,
        // 每页六条数据
        num: 6,
        // 文章列表
        articles: [],
        // articleList节流阀，防止发送过多数据请求
        isloading: false
      };
    },
    methods: {
        change(e) {
            this.page = 1;
            this.articles = [];
            this.value = e;
            this.getListArticle();
        },
        toArticle(id){
            uni.navigateTo({
                url:`/subpkg/article_details/article_details?id=${id}`
            });
        },
        getListArticle(){
            this.isloading = true;
            uni.$http.get('/getArticles', {
                // 第几页
                page: this.page,
                // 每页多少条数据
                num: this.num,
                // 分类的id
                sortId: this.value,
                // 搜索查询的内容
            }).then((res) => {
                this.articles.push(...res.data.data);
            })
            this.isloading = false;
        }
    },
    onLoad(){
        // 获取分类数据
        uni.$http.get('/getSorts').then((res)=>{
            let sorts = [];
            sorts.push({value: 0,text: '首页'})
            res.data.data.forEach(item => {
                sorts.push({
                    value: item.id,
                    text: item.content
                })
            });
            this.range = sorts;
        })
        // 获取文章列表
        this.getListArticle();
    },
    // 接收上拉触顶行为
    onReachBottom(){
        // 节流阀打开就不发送请求
        if(this.isloading) return;
        this.page += 1;
        this.getListArticle();
    },
    // 下拉刷新事件
    onPullDownRefresh(){
        this.value = 0;
        this.page = 1;
        this.articles = [];
        this.getListArticle();
    }
}
</script>

<style scoped>
    .menu{
        width: 100%;
        background: white;
        top: 0;
        position: fixed;
        z-index: 1;
        border-bottom: 1px solid rgb(227, 222, 212);
    }
    .article-list{
        padding: 30px 0 0;
    }
</style>