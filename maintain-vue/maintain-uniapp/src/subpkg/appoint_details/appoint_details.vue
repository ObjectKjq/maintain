<template>
  <view>
    <uni-title type="h1" title="预约详情" align="center"></uni-title>
    <view>
        <uni-list>
            <uni-list-item :title="'维修师名：' + appointData.username" />
            <uni-list-item :title="'维修师评分：' + appointData.score" />
            <uni-list-item :title="'维修师证书：' + appointData.certificates" />
            <uni-list-item :title="'业务范围：' + appointData.scopes" />
            <uni-list-item :title="'预约时间：' + appointData.appointTime.substring(0, 10)"/>
            <uni-list-item :title="'维修电器：' + appointData.skills"/>
            <uni-list-item :title="'描述信息：' + appointData.content" />
        </uni-list>
    </view>
    <view class="appointBotton">
        <button type="primary" @click="appoint">预约</button>
        <button type="primary" @click="consult">咨询维修师</button>
    </view>
    <!-- 输入框示例 -->
    <uni-popup ref="inputDialog" type="dialog" background-color="#fff">
        <view class="fromContent">
            <uni-title type="h1" title="填写预约信息" align="center"></uni-title>
            <uni-forms-item label="真实姓名" required>
                <uni-easyinput v-model="baseFormData.name" placeholder="请输入姓名" />
            </uni-forms-item>
            <uni-forms-item label="电话" required>
                <uni-easyinput v-model="baseFormData.phone" placeholder="请输入电话" />
            </uni-forms-item>
            <uni-forms-item label="详细地址" required>
                <uni-easyinput v-model="baseFormData.address" placeholder="请输入地址" />
            </uni-forms-item>
            <uni-forms-item label="描述信息" required>
                <uni-easyinput v-model="baseFormData.message" placeholder="请输入描述" />
            </uni-forms-item>
        </view>
        
        <view class="button">
            <button type="primary" size="mini" @click="byAppoint">预约</button>
            <button type="default" size="mini" @click="close">取消</button>
        </view>
    </uni-popup>

    <uni-popup ref="inputConsult" type="dialog">
      <uni-popup-dialog ref="inputClose"  mode="input" title="咨询维修师"
        placeholder="请输入消息" @confirm="dialogInputConfirm"></uni-popup-dialog>
    </uni-popup>
  </view>
</template>

<script>
export default {
    data() {
        return {
            appointData:{},
            // 用户填写的预约信息
            baseFormData:{}
        }
    },
    methods: {
        async dialogInputConfirm(val){
            const {data: res} = await uni.$http.post("/addConsult", {
                content: val,
                appointId: this.appointData.appointId
            })
            
            if(res.code == 20000){
                return uni.$showMsg('咨询成功')
            }else{
                return uni.$showMsg('预约失败')
            }
        },
        // 咨询维修师
        consult(){
            this.$refs.inputConsult.open()
        },
        // 调用预约接口预约
        async byAppoint(){
            const {data: res} = await uni.$http.put("/updateStatus", this.baseFormData)
            if(res.code == 20000){
                this.$refs.inputDialog.close()
                return uni.$showMsg('预约成功')
            }else{
                return uni.$showMsg('预约失败')
            }
        },
        // 打开弹窗
        appoint() {
            this.$refs.inputDialog.open()
        },
        // 关闭弹窗
        close() {
			this.$refs.inputDialog.close()
		}
    },
    onLoad(option){
        const appoint = JSON.parse(decodeURIComponent(option.appoint));
        this.appointData = appoint;
        this.baseFormData.id = appoint.id;
    }
}
</script>

<style scoped>
    button{
        margin: 10px;
    }
    .fromContent{
        padding: 20px;
        
    }
    .button{
        display: flex;
        justify-content: space-between;
        padding: 10px;
    }
</style>