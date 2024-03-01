<template>
  <view>
    <view class="example-body custom-image-box">
      <text class="text">头像</text>
      <uni-file-picker limit="1" disable-preview :imageStyles="imageStyles"
        file-mediatype="image" @select="upload">选择</uni-file-picker>
    </view>
    <uni-forms-item label="姓名" required>
      <uni-easyinput v-model="baseFormData.name" placeholder="请输入姓名" />
    </uni-forms-item>
    <uni-forms-item label="性别" required>
      <uni-data-checkbox v-model="baseFormData.sex" :localdata="sexs" />
    </uni-forms-item>
	<button type="primary" @click="updateUser">提交</button>
  </view>
</template>

<script>
export default {
  data() {
    return {
    	baseFormData: {
			name: '',
			sex: 2,
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
		imageStyles: {
			width: 64,
			height: 64,
			border: {
				radius: '50%'
			}
		},
    }
  },
  methods: {
	upload(e){
		const tempFilePaths = e.tempFilePaths;//e是获取的图片源
		uni.uploadFile({
			url: 'http://localhost:8081/files', //上传图片的后端接口
			filePath: tempFilePaths[0],
			name: 'file',
			header:{
				"Authorization": this.$store.state.user.token
			},
			success: res => {
				this.$store.state.user.avatar = JSON.parse(res.data).data
			}
		})
	},
	async updateUser(){
		const {data: res} = await uni.$http.put('/updateUser', this.baseFormData)
		if(res.code == 20000){
			this.$store.state.user.name = this.baseFormData.name;
			this.baseFormData.name = "";
			this.baseFormData.sex = 2;
			return uni.$showMsg('修改成功')
		}else{
			return uni.$showMsg('修改失败')
		}
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