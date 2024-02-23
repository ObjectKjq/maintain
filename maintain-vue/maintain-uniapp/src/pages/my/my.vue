<template>
  <view>
    <uni-card title="用户名" 
    :isFull="true"
     sub-title="账号"
     extra="性别"
     :thumbnail="avatar">
    </uni-card>
    <uni-list>
      <uni-list-item :to="`/subpkg/update_user/update_user`" showArrow title="修改信息"/>
      <uni-list-item :to="`/subpkg/appoint_list/appoint_list`" showArrow title="预约信息"/>
      <uni-list-item :to="`/subpkg/appoint_list/appoint_list`" showArrow title="价格估算成功待确认"/>
      <!-- 可以举报 -->
      <uni-list-item :to="`/subpkg/appoint_list/appoint_list`" showArrow title="待打分"/>
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
      avatar: 'https://qiniu-web-assets.dcloud.net.cn/unidoc/zh/unicloudlogo.png',
    }
  },
  methods: {
    inputDialogToggle() {
      this.$refs.inputDialog.open()
    },
  },
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