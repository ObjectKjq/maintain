<template>
  <view>
    <uni-title type="h1" title="预约详情" align="center"></uni-title>
    <view>
        <uni-list>
            <uni-list-item title="维修师名：小明" />
            <uni-list-item title="维修师评分：9.1" />
            <uni-list-item title="维修师证书：软件设计师" />
            <uni-list-item title="业务范围：北京，天津，郑州" />
            <uni-list-item title="预约时间：2024-1-20"/>
            <uni-list-item title="维修电器：冰箱，空调，手机"/>
            <uni-list-item title="业务介绍：第三方接口龙卷风法律的开始经理反馈的萨芬就扣税的垃圾" />
        </uni-list>
    </view>
    <view class="appointBotton">
        <button type="primary" @click="appoint">预约</button>
        <button type="primary" @click="consult">咨询维修师</button>
    </view>
    <!-- 输入框示例 -->
    <uni-popup ref="inputDialog" type="dialog" background-color="#fff">
        <view class="fromContent">
            <uni-forms-item label="真实姓名" required>
                <uni-easyinput v-model="baseFormData.name" placeholder="请输入姓名" />
            </uni-forms-item>
            <uni-forms-item label="电话" required>
                <uni-easyinput v-model="baseFormData.name" placeholder="请输入电话" />
            </uni-forms-item>
            <uni-forms-item label="详细地址" required>
                <uni-easyinput v-model="baseFormData.name" placeholder="请输入地址" />
            </uni-forms-item>
            <uni-forms-item label="描述信息" required>
                <uni-easyinput v-model="baseFormData.name" placeholder="请输入描述" />
            </uni-forms-item>
            <view class="example-body">
                <uni-file-picker limit="3" title="最多选择9张图片"></uni-file-picker>
            </view>
        </view>
        
        <view class="button">
            <button type="primary" size="mini" @click="appoint">预约</button>
            <button type="default" size="mini" @click="close">取消</button>
        </view>
    </uni-popup>
  </view>
</template>

<script>
export default {
    methods: {
        // 咨询维修师
        consult(){

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