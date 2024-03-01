<template>
  <div class="components-container">
    <el-button type="primary">提交</el-button>
    <el-button type="warning">清空</el-button>
    <div class="demo-input-suffix">
      <div class="demo-title">文章标题：</div><el-input size="medium" v-model="title" maxlength="50" show-word-limit placeholder="文章标题"></el-input>
    </div>
    <div>
      <tinymce v-model="content" :height="400" />
    </div>
  </div>
</template>

<script>
import Tinymce from '@/components/Tinymce'

export default {
  name: 'MaintainArticle',
  components: { Tinymce },
  data() {
    return {
      // 用户填写文章内容
      content:``,
      // 文章标题
      title: ''
    }
  }
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