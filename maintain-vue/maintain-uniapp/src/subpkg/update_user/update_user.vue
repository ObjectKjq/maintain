<template>
  <view>
    <view class="example-body custom-image-box">
      <text class="text">头像</text>
      <uni-file-picker limit="1" :del-icon="false" disable-preview :imageStyles="imageStyles"
        file-mediatype="image">选择头像</uni-file-picker>
    </view>
    <uni-forms-item label="姓名" required>
      <uni-easyinput v-model="baseFormData.name" placeholder="请输入姓名" />
    </uni-forms-item>
    <uni-forms-item label="性别" required>
      <uni-data-checkbox v-model="baseFormData.sex" :localdata="sexs" />
    </uni-forms-item>
  </view>
</template>

<script>
export default {
  data() {
    return {
      baseFormData: {
					name: '',
					age: '',
					introduction: '',
					sex: 2,
					hobby: [5],
					datetimesingle: 1627529992399
				},
        sexs: [{
					text: '男',
					value: 0
				}, {
					text: '女',
					value: 1
				}, {
					text: '保密',
					value: 2
				}],
    }
  },
}
</script>

<style scoped>
  .example-body {
    padding: 0 0 20px;
		padding-top: 0;
	}

	.custom-image-box {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
	}

	.text {
		font-size: 14px;
		color: #333;
	}
</style>