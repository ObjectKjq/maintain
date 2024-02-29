<template>
  <view class="appoint">
    <uni-title type="h1" title="预约搜索" align="center"></uni-title>
    <uni-title type="h4" title="以下内容可以不全输入" color="#e0620d"></uni-title>
    <uni-forms-item label="用户名" required>
        <uni-easyinput v-model="search.name" placeholder="请输入用户名" />
    </uni-forms-item>
    <uni-forms-item label="城市" required>
        <uni-easyinput v-model="search.city" placeholder="请输入城市" />
    </uni-forms-item>
    <uni-forms-item label="电器" required>
        <uni-easyinput v-model="search.skill" placeholder="请输入家电名" />
    </uni-forms-item>
    <view class="example-body">
        <uni-datetime-picker type="date" :clear-icon="false" v-model="search.time"/>
    </view>
    <view class="search-button">
        <button type="primary" @click="searchAppoint">搜索</button>
        <button type="default" @click="clear">清空</button>
    </view>
</view>
</template>

<script>
export default {
    data() {
        return {
            search:{
                // 名称搜索
                name: "",
                // 城市搜索
                city: "",
                // 技能搜索
                skill: "",
                // 日期搜索
                time: ""
            }
        } 
    },
    methods: {
        searchAppoint(){
            // 跳转到搜索列表页面
            uni.navigateTo({
                url:'/subpkg/appoint_list/appoint_list?search='+encodeURIComponent(JSON.stringify(this.search))
            });
        },
        clear(){
            // 清空搜索内容
            this.search = {name: "",city: "",skill: "",time: ""}
        }
    },
}
</script>

<style scoped>
    .appoint{
        padding: 40rpx;
    }
    .search-button{
        width: calc(100% - 80rpx);
        position: fixed;
        bottom: 0px;
    }
    button{
        margin: 0 0 10px;
    }
</style>