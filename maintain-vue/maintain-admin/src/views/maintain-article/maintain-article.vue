<template>
  <div class="components-container">
    <el-button type="primary" @click="submitArticle">提交</el-button>
    <el-select style="margin-left: 10px" v-model="article.sort" placeholder="请选择">
      <el-option
        v-for="item in options"
        :key="item.id"
        :label="item.label"
        :value="item.id">
      </el-option>
    </el-select>
    <div class="demo-input-suffix">
      <div class="demo-title">文章标题：</div><el-input size="medium" v-model="article.title" maxlength="50" show-word-limit placeholder="文章标题"></el-input>
    </div>
    <div>
      <tinymce ref="content" v-model="article.content" :height="400" />
    </div>
  </div>
</template>

<script>
import Tinymce from '@/components/Tinymce'
import {getSort, addArticle, getArticle, updateArticle} from '@/api/maintain'

export default {
  name: 'MaintainArticle',
  components: { Tinymce },
  data() {
    return {
      article:{
        // 用户填写文章内容
        content:``,
        // 文章标题
        title: '',
        // 文章分类
        sort:'',
      },
      options: [],
    }
  },
  methods: {
    submitArticle(){
      this.$confirm('是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if(this.article.id == undefined){
          // 直接添加文章
          addArticle(this.article).then((res)=>{
            if(res.code === 20000){
              this.article = {};
              this.$refs.content.setContent("");
              this.$message({
                type: 'success',
                message: '发布成功!'
              });
            }else{
              this.$message({
                type: 'error',
                message: '发布失败!'
              });
            }
          })
        }else{
          // 修改文章
          updateArticle(this.article).then((res)=>{
            if(res.code === 20000){
              this.article = {};
              this.$refs.content.setContent("");
              this.$message({
                type: 'success',
                message: '修改成功!'
              });
            }else{
              this.$message({
                type: 'error',
                message: '修改失败!'
              });
            }
          })
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });          
      });
    }
  },
  created() {
    if(this.$route.query.id){
      // 获取文章数据
      getArticle(this.$route.query.id).then((res)=>{
        if(res.code === 20000){
          this.article = res.data
        }
      })
    }
    // 获取文章分类列表数据
    getSort().then((res) => {
      if(res.code === 20000){
        res.data.forEach(element => {
          this.options.push({id: element.id, label: element.content})
        });
      }
    })
  },
}
</script>

<style scoped>
  .demo-input-suffix{
    margin: 20px 0;
    display: flex;
    justify-content: space-between;
  }
  .demo-title{
    width: 120px;
    line-height: 35px;
    font-size: 20px;
  }
</style>