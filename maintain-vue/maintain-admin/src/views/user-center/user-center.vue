<template>
  <div class="user-conter">
    <el-form label-position="right" label-width="100px" :model="formData">
      <el-form-item label="头像选择：">
        <el-upload
          class="avatar-uploader"
          action="/api/upload/avatar"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :headers="headers">
          <img v-if="imageUrl" :src="imageUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon el-upload"></i>
        </el-upload>
      </el-form-item>
      <el-form-item label="用户名：">
        <el-input v-model="formData.username"></el-input>
      </el-form-item>
      <el-form-item label="原密码：">
        <el-input v-model="formData.password"></el-input>
      </el-form-item>
      <el-form-item label="新密码：">
        <el-input v-model="formData.nPassword"></el-input>
      </el-form-item>
      <el-form-item label="确认密码：">
        <el-input v-model="formData.vPassword"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="updateUser">修改</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {mapMutations} from 'vuex'
import {updateUser} from '@/api/maintain'
export default {
  data() {
    return {
      formData:{},
      imageUrl: '',
      headers: {}
    }
  },
  methods: {
    updateUser(){
      updateUser(this.formData).then((res)=>{
        if(res.code){
          this.setName(this.formData.username);
          this.formData = {};
          this.$message({
            type: 'success',
            message: '修改成功!'
          })
        }else{
          this.$message({
            type: 'error',
            message: '修改失败!'
          })
        }
      })
    },
    ...mapMutations('user', {setAvatar:'SET_AVATAR', setName: 'SET_NAME'}),
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
      if(res.code === 20000){
        this.setAvatar(res.data)
        this.$message({
          type: 'success',
          message: '上传成功!'
        })
      }else{
        this.$message({
          type: 'error',
          message: '上传失败!'
        })
      }
    },
  },
  created() {
    this.headers = {
      "Authorization": this.$store.state.user.token
    }
  },
}
</script>

<style scoped>
  .user-conter{
    width: 600px;
    height: 100%;
    padding: 50px 0;
    margin: 0 auto;
  }
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 10px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 100px;
    height: 100px;
    line-height: 100px;
    text-align: center;
  }
  .avatar {
    width: 100px;
    height: 100px;
    display: block;
    border-radius: 10px;
    object-fit: cover;
  }
  
</style>