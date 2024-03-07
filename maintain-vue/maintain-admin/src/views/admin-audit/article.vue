<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button class="filter-item" type="warning" icon="el-icon-s-open" @click="toggleSelection">
        清空选择
      </el-button>
    </div>

    <el-table
    ref="multipleTable"
    v-loading="listLoading"
    :data="list"
    border
    fit
    highlight-current-row
    stripe
    style="width: 100%;"
    @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column prop="id" label="ID" width="55">
      </el-table-column>
      <el-table-column prop="createTime" label="创建日期" width="180">
      </el-table-column>
      <el-table-column prop="sort" label="分类">
      </el-table-column>
      <el-table-column prop="name" label="用户名">
      </el-table-column>
      <el-table-column prop="title" label="标题">
      </el-table-column>
      <el-table-column label="内容">
        <template slot-scope="scope">
          {{scope.row.content.replace(/<[^<>]+>/g,"").replace(/\s/g,"").replace(/[\r\n]/g).substring(0, 40)}}
        </template>
      </el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button size="mini" type="success"  @click="handleEdit(scope.$index, scope.row)">
            通过
          </el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">
            拒绝
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :page-sizes="[3, 20, 30, 40]"
      :current-page="1"
      background
      layout="total, sizes, prev, pager, next, jumper"
      :total="total" 
      class="page">
    </el-pagination>
  </div>
</template>

<script>
import {getArticles, passArticle, downArticle} from '@/api/admin'
export default {
  data() {
    return {
      // 搜索发送的数据
      params: {
        // 第几页
        page: 1,
        // 每页多少条数据
        limit: 3,

        status: 0,
      },
      // 总共有多少条数据
      total: 0,
      // 列表是否在加载中
      listLoading: false,
      // 数据绑定到这里
      list:[],
      // 这里是被选中的列表
      multipleSelection: []
    }
  },
  methods: {
    // 通过文章
    handleEdit(index, article){
      this.$confirm('通过文章', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        passArticle(article.id).then((res)=>{
          if(res.code === 20000){
            this.list.splice(index, 1);
            this.$message({
              type: 'success',
              message: '成功!'
            });
          }else{
            this.$message({
              type: 'error',
              message: '失败!'
            });
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消通过'
        });          
      });
    },
    // 拒绝文章
    handleDelete(index, article){
      this.$confirm('通过文章', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        downArticle(article).then((res)=>{
          if(res.code === 20000){
            this.list.splice(index, 1);
            this.$message({
              type: 'success',
              message: '成功!'
            });
          }else{
            this.$message({
              type: 'error',
              message: '失败!'
            });
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消拒绝'
        });          
      });
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    toggleSelection() {
      this.$refs.multipleTable.clearSelection();
    },
    handleSizeChange(limit){
      this.params.limit = limit;
      this.params.page = 1;
      this.listLoading = true;
      getArticles(this.params).then((res)=>{
        if(res.code == 20000){
          this.list = res.data;
          this.total = this.list.pop().id;
          this.listLoading = false;
        }
      })
    },
    handleCurrentChange(page){
      this.params.page = page;
      this.listLoading = true;
      getArticles(this.params).then((res)=>{
        if(res.code == 20000){
          this.list = res.data;
          this.total = this.list.pop().id;
          this.listLoading = false;
        }
      })
    }
  },
  created(){
    // 初始化数据
    this.listLoading = true;
    getArticles(this.params).then((res)=>{
      if(res.code == 20000){
        this.list = res.data;
        this.total = this.list.pop().id;
        this.listLoading = false;
      }
    })
  }
}
</script>

<style scoped>
  .page{
    margin-top: 18px;
  }
</style>