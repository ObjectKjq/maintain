<template>
  <view>
    <view class="uni-container">
        <uni-table ref="table" :loading="loading" border stripe emptyText="暂无更多数据">
            <uni-tr>
                <uni-th width="150" align="center">发送者ID</uni-th>
                <uni-th width="150" align="center">接收者ID</uni-th>
                <uni-th width="150" align="center">消息内容</uni-th>
                <uni-th width="204" align="center">设置</uni-th>
            </uni-tr>
            <uni-tr v-for="(item, index) in tableData" :key="index">
                <uni-td>
                    <view class="send-id">{{ item.userSendId }}</view>
                </uni-td>
                <uni-td>
                    <view class="accept-id">{{ item.userAcceptId }}</view>
                </uni-td>
                <uni-td>
                    <view class="content">{{ item.content }}</view>
                </uni-td>
                <uni-td>
                    <view class="button-list">
                        <button  class="mini-btn" type="primary" size="mini" @click="reConsult(item.id, item.userSendId, index)">回复</button>
                        <button class="mini-btn" type="warn" size="mini" @click="deConsult(item.id, index)">删除</button>
                    </view>
                </uni-td>
            </uni-tr>
        </uni-table>
        <view class="uni-pagination-box"><uni-pagination show-icon :page-size="pageSize" :current="pageCurrent" :total="total" @change="change" /></view>
    </view>
    <!-- 输入框示例 -->
    <uni-popup ref="inputMessage" type="dialog">
      <uni-popup-dialog ref="inputClose"  mode="input" title="消息回复"
        placeholder="请输入输入" @confirm="dialogInputConfirm"></uni-popup-dialog>
    </uni-popup>
  </view>
</template>

<script>
export default {
    data() {
        return {
            // 是否显示加载中
			loading: false,
            // 消息
            tableData: [],
            // 发给谁消息
            appointId: 0,
            // 消息id
            index: 0,
            // item的id
            id: 0,
        }
    },
    methods: {
        async deConsult(id, index){
            const {data: res} = await uni.$http.delete("/deleteConsult", {
                id
            })
            if(res.code == 20000){
                this.tableData.splice(index, 1)
                return uni.$showMsg("删除成功")
            }else{
                return uni.$showMsg("删除失败")
            }
        },
        reConsult(id, appointId, index){
            this.appointId = appointId;
            this.index = index;
            this.id = id;
            this.$refs.inputMessage.open()
        },
        async dialogInputConfirm(val){
            const {data: res} = await uni.$http.post("/addConsult",{
                content:val,
                appointId: this.appointId,
                id: this.id,
            })
            if(res.code == 20000){
                this.tableData.splice(this.index, 1)
                return uni.$showMsg("回复成功")
            }else{
                return uni.$showMsg("回复失败")
            }
        }
    },
	async onLoad() {
        const {data: res} = await uni.$http.get("/getListConsult")
        this.tableData = res.data;
    }
}
</script>

<style scoped>
    .button-list{
        display: flex;
        justify-content: space-around;
        width: 100%;
    }
</style>