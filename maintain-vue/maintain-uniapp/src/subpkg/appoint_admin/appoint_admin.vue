<template>
  <view>
        <view class="uni-container" v-if="status == 1">
            <uni-table ref="table" :loading="loading" border stripe emptyText="暂无更多数据">
                <uni-tr>
                    <uni-th width="150" align="center">描述</uni-th>
                    <uni-th width="150" align="center">维修日期</uni-th>
                    <uni-th width="150" align="center">填写信息</uni-th>
                    <uni-th width="204" align="center">设置</uni-th>
                </uni-tr>
                <uni-tr v-for="(item, index) in tableData" :key="index">
                    <uni-td>
                        <view class="content">{{ item.content }}</view>
                    </uni-td>
                    <uni-td>
                        <view class="time">{{ item.appointTime.substring(0, 10) }}</view>
                    </uni-td>
                    <uni-td>
                        <view class="message">{{ item.message }}</view>
                    </uni-td>
                    <uni-td>
                        <view class="uni-group">
                            <!-- <button class="uni-button" size="mini" type="primary">修改</button> -->
                            <button class="uni-button" size="mini" type="warn" @click="cancelAppoint(item.id, index)">取消预约</button>
                        </view>
                    </uni-td>
                </uni-tr>
            </uni-table>
            <view class="uni-pagination-box"><uni-pagination show-icon :page-size="pageSize" :current="pageCurrent" :total="total" @change="change" /></view>
        </view>
        <view class="uni-container" v-if="status == 2">
            <uni-table ref="table" :loading="loading" border stripe emptyText="暂无更多数据">
                <uni-tr>
                    <uni-th width="150" align="center">描述</uni-th>
                    <uni-th width="150" align="center">维修日期</uni-th>
                    <uni-th width="150" align="center">填写信息</uni-th>
                    <uni-th width="150" align="center">价格</uni-th>
                    <uni-th width="204" align="center">设置</uni-th>
                </uni-tr>
                <uni-tr v-for="(item, index) in tableData" :key="index">
                    <uni-td>
                        <view class="content">{{ item.content }}</view>
                    </uni-td>
                    <uni-td>
                        <view class="time">{{ item.appointTime.substring(0, 10) }}</view>
                    </uni-td>
                    <uni-td>
                        <view class="message">{{ item.message }}</view>
                    </uni-td>
                    <uni-td>
                        <view class="message">{{ item.price }}</view>
                    </uni-td>
                    <uni-td>
                        <view class="uni-group">
                            <button class="uni-button" size="mini" type="primary" @click="tPrice(item.id, index)">确认</button>
                            <button class="uni-button" size="mini" type="warn" @click="fPrice(item.id, index)">取消</button>
                        </view>
                    </uni-td>
                </uni-tr>
            </uni-table>
            <view class="uni-pagination-box"><uni-pagination show-icon :page-size="pageSize" :current="pageCurrent" :total="total" @change="change" /></view>
        </view>
        <view class="uni-container" v-if="status == 4">
            <uni-table ref="table" :loading="loading" border stripe emptyText="暂无更多数据">
                <uni-tr>
                    <uni-th width="150" align="center">描述</uni-th>
                    <uni-th width="150" align="center">维修日期</uni-th>
                    <uni-th width="150" align="center">填写信息</uni-th>
                    <uni-th width="150" align="center">价格</uni-th>
                    <uni-th width="204" align="center">设置</uni-th>
                </uni-tr>
                <uni-tr v-for="(item, index) in tableData" :key="index">
                    <uni-td>
                        <view class="content">{{ item.content }}</view>
                    </uni-td>
                    <uni-td>
                        <view class="time">{{ item.appointTime.substring(0, 10) }}</view>
                    </uni-td>
                    <uni-td>
                        <view class="message">{{ item.message }}</view>
                    </uni-td>
                    <uni-td>
                        <view class="message">{{ item.price }}</view>
                    </uni-td>
                    <uni-td>
                        <uni-rate v-model="rateValue" @change="onChange(item.id, index)" />
                    </uni-td>
                </uni-tr>
            </uni-table>
            <view class="uni-pagination-box"><uni-pagination show-icon :page-size="pageSize" :current="pageCurrent" :total="total" @change="change" /></view>
        </view>
  </view>
</template>

<script>
export default {
    data() {
		return {
            // 请求到的列表数据
			tableData: [],
			// 每页数据量
			pageSize: 10,
			// 当前页
			pageCurrent: 1,
			// 数据总量
			total: 0,
            // 是否显示加载中
			loading: false,
            // 什么状态的数据
            status: 0,
            // 评分记录
            rateValue: 0,
		}
	},
	onLoad(option) {
        this.status = option.status;
		this.getData(1, option.status)
	},
	methods: {
		// 分页触发
		change(e) {
			this.getData(e.current, this.status)
		},
		// 获取数据
		async getData(pageCurrent, status) {
			this.loading = true
            // 获取总页数
            const {data: pageNum} = await uni.$http.get('/getTotal', {
                status: status
            })
            if(pageNum.code == 20000){
                this.total = pageNum.data;
            }
            // 获取分页数据
			this.pageCurrent = pageCurrent
            const {data: res} = await uni.$http.get('/getStatusAppoint', {
                page: pageCurrent,
                num: this.pageSize,
                status: status,
            })
            if(res.code == 20000){
                this.tableData = res.data;
            }
            this.loading = false
		},
        // 取消预约
        async cancelAppoint(id, index){
            const {data: res} = await uni.$http.put('/cancelAppoint', {
                id,
            })
            if(res.code == 20000){
                this.tableData.splice(index, 1);
                return uni.$showMsg("取消成功")
            }else{
                return uni.$showMsg("取消失败")
            }
        },
        // 确认价格
        async tPrice(id, index){
            const {data: res} = await uni.$http.put('/tPrice', {
                id
            })
            if(res.code == 20000){
                this.tableData.splice(index, 1);
                return uni.$showMsg("价格已确认")
            }else{
                return uni.$showMsg("确认失败")
            }
        },
        // 取消预约
        async fPrice(id, index){
            const {data: res} = await uni.$http.put('/cancelAppoint', {
                id,
            })
            if(res.code == 20000){
                this.tableData.splice(index, 1);
                return uni.$showMsg("取消成功")
            }else{
                return uni.$showMsg("取消失败")
            }
        },
        // 打分后的处理函数
        async onChange(id, index){
            const {data: res} = await uni.$http.post('/mark',{
                id: id,
                rate: this.rateValue
            })
            if(res.code == 20000){
                this.tableData.splice(index, 1);
                return uni.$showMsg("评分成功")
            }else{
                return uni.$showMsg("评分失败")
            }
        }
	}
}
</script>

<style scoped>
    .uni-group {
        display: flex;
        align-items: center;
    }
</style>