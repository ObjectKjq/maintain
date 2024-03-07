<template>
  <view>
    <uni-card :title="user.name" 
    :isFull="true"
     :thumbnail="user.avatar">
    </uni-card>
    <uni-list>
      <uni-list-item :to="`/subpkg/update_user/update_user`" showArrow title="修改信息"/>
      <!-- status=1，预约成功，可以删除预约 -->
      <uni-list-item :to="`/subpkg/appoint_admin/appoint_admin?status=1`" showArrow title="预约管理"/>
      <!-- status=2，价格估算成功，用户确认 -->
      <uni-list-item :to="`/subpkg/appoint_admin/appoint_admin?status=2`" showArrow title="价格估算"/>
      <!-- 可以举报 -->
      <!-- status=4，维修成功打分，也可以举报 -->
      <uni-list-item :to="`/subpkg/appoint_admin/appoint_admin?status=4`" showArrow title="等待打分"/>
      <!-- 消息列表 -->
      <uni-list-item :to="`/subpkg/consult_list/consult_list`" showArrow title="消息列表"/>
    </uni-list>
    <button class="button" @click="inputDialogToggle">反馈</button>
    <!-- 输入框示例 -->
    <uni-popup ref="inputDialog" type="dialog">
      <uni-popup-dialog ref="inputClose"  mode="input" title="反馈内容"
        placeholder="请输入内容" @confirm="dialogInputConfirm"></uni-popup-dialog>
    </uni-popup>
  </view>
</template>

<script>
export default {
  data() {
    return {
      user: {},
    }
  },
  methods: {
    inputDialogToggle() {
      this.$refs.inputDialog.open()
    },
    async dialogInputConfirm(val){
      // 添加返回信息
      const {data: res} = await uni.$http.post("/addFeedback", {
        content: val
      })
      if(res.code == 20000){
        return uni.$showMsg("反馈成功")
      }else{
        return uni.$showMsg("反馈失败")
      }
    }
  },
  onShow(){
    this.user = this.$store.state.user;
  }
}
</script>

<style scoped>
  .button{
    border: 0;
    outline: none;
    background: #ffffff;
    font-size: 14px;
    display: flex;
    color: #515151;
  }
</style>