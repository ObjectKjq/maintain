<template>
  <div class="app-container">
    <!-- 表格
    v-loading是否在加载中
    data数据源
    highlight-current-row选中变色
    stripe带斑马线
     -->
    <el-table
    ref="multipleTable"
    v-loading="listLoading"
    :data="list"
    border
    fit
    highlight-current-row
    stripe
    style="width: 100%;"
    >
      <el-table-column prop="id" label="ID" width="55">
      </el-table-column>
      <el-table-column prop="createTime" label="创建日期" width="180">
      </el-table-column>
      <el-table-column prop="content" label="描述">
      </el-table-column>
      <el-table-column prop="message" label="预约信息">
      </el-table-column>
      <el-table-column prop="price" label="价格" width="180">
      </el-table-column>
      <el-table-column prop="appointTime" label="预约日期" width="180">
      </el-table-column>
      <el-table-column label="操作" width="80">
        <template slot-scope="scope">
          <el-button size="mini" type="success"  @click="handleEdit(scope.$index, scope.row)">
            提交
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
import {getAppoints, subAppoint} from '@/api/maintain'
export default {
  data() {
    return {
      // 搜索发送的数据
      listQuery: {
        // 预约状态
        status: 3,
        // 第几页
        page: 1,
        // 每页多少条数据
        limit: 3,
        // 搜索题目名称
        title: undefined,
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
    handleEdit(index, appoint){
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
          subAppoint(appoint).then((res)=>{
            if(res.code === 20000){
              this.list.splice(index, 1);
              this.$message({
                type: 'success',
                message: '提交成功'
              }); 
            }else{
              this.$message({
                type: 'error',
                message: '提交失败'
              }); 
            }
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });          
      });
    },
    handleSizeChange(limit){
      this.listQuery.limit = limit;
      this.listQuery.page = 1;
      this.listLoading = true;
      getAppoints(this.listQuery).then((res)=>{
        if(res.code == 20000){
          this.list = res.data;
          this.total = this.list.pop().total;
          this.listLoading = false;
        }
      })
    },
    handleCurrentChange(page){
      this.listQuery.page = page;
      this.listLoading = true;
      getAppoints(this.listQuery).then((res)=>{
        if(res.code == 20000){
          this.list = res.data;
          this.total = this.list.pop().total;
          this.listLoading = false;
        }
      })
    }
  },
  created(){
    // 初始化数据
    this.listLoading = true;
    getAppoints(this.listQuery).then((res)=>{
      if(res.code == 20000){
        this.list = res.data;
        this.total = this.list.pop().total;
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