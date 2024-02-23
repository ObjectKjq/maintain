<template>
  <view>
    <uni-card title="用户名" 
    extra="创建时间" 
    :thumbnail="avatar"
     @click="toAppointDetails">
        <uni-list>
            <uni-list-item title="业务范围：北京，天津，郑州"></uni-list-item>
            <uni-list-item title="预约时间：2024-1-30"></uni-list-item>
            <uni-list-item title="维修电器：冰箱，空调，手机"></uni-list-item>
        </uni-list>
    </uni-card>
  </view>
</template>

<script>
export default {
    data() {
        return {
            avatar: 'https://qiniu-web-assets.dcloud.net.cn/unidoc/zh/unicloudlogo.png',
        }
    },
    methods: {
        toAppointDetails(e){
            // 跳转到预约详情页面
            uni.navigateTo({
                url:'/subpkg/appoint_details/appoint_details'
            });
        },
    },
}
</script>

<style scoped>

</style>