<template>
  <view>
    <uni-card v-for="appoint in searchList" :key="appoint.id" :title="appoint.username" 
    :extra="appoint.createTime.substring(0, 10)" 
    :thumbnail="appoint.avatar"
     @click="toAppointDetails(appoint)">
        <uni-list>
            <uni-list-item :title="'业务范围：' + appoint.scopes.toString()"></uni-list-item>
            <uni-list-item :title="'预约时间：' + appoint.appointTime.substring(0, 10)"></uni-list-item>
            <uni-list-item :title="'维修电器：' + appoint.skills.toString()"></uni-list-item>
        </uni-list>
    </uni-card>
  </view>
</template>

<script>
export default {
    data() {
        return {
            search: {
                // 第几页数据
                page: 1,
                // 每页展示多少条数据
                num: 6,
            },
            searchList:[],
        }
    },
    methods: {
        toAppointDetails(appoint){
            // 跳转到预约详情页面
            uni.navigateTo({
                url:'/subpkg/appoint_details/appoint_details?appoint='+encodeURIComponent(JSON.stringify(appoint))
            });
        },
        async getListAppoint(){
            const {data: res} = await uni.$http.get('/getAppoints', this.search)
            if(res.code == 20000)
            this.searchList.push(...res.data)
            // console.log(res.data[0].skills.toString())
        }
    },
    onLoad(option){
        const search = JSON.parse(decodeURIComponent(option.search));
        this.search = {...search, ...this.search};
        // this.getListAppoint();
    },
    onShow(){
        this.searchList = [];
        this.getListAppoint();
    }
}
</script>

<style scoped>

</style>