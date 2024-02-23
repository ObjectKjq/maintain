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
        <view class="article-list">
            <uni-card @click="toArticle" title="文章标题" extra="创建时间">
                <text class="uni-body">这是一个基础卡片示例，此示例展示了一个标题加标题额外信息的标准卡片。</text>
            </uni-card>
        </view>
    </view>
</template>

<script>
export default {
     data() {
      return {
        value: 0,
        range: [
            { value: 0, text: "首页" },
            { value: 1, text: "足球" },
            { value: 2, text: "游泳" },
        ],
      };
    },
    methods: {
        change(e) {
            console.log("e:", e);
        },
        toArticle(){
            uni.navigateTo({
                url:'/subpkg/article_details/article_details'
            });
        }
    },
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