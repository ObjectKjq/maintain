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
      <el-table-column prop="appointTime" label="预约日期" width="180">
      </el-table-column>
      <el-table-column label="操作" width="110">
        <template slot-scope="scope">
          <el-button size="mini" type="success"  @click="handleEdit(scope.$index, scope.row)">
            添加价格
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

    <!-- 弹窗 -->
    <el-dialog title="添加价格" :visible.sync="dialogFormVisible">
      <el-form :model="appoint">
        <el-form-item label="价格:" :label-width="formLabelWidth">
          <el-input v-model="appoint.price" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addPrice">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {getAppoints, addPrice} from '@/api/maintain'
export default {
  data() {
    return {
      formLabelWidth: '80px',
      dialogFormVisible:false,
      // 弹窗数据
      appoint:{},
      index: -1,
      // 搜索发送的数据
      listQuery: {
        // 预约状态
        status: 1,
        // 第几页
        page: 1,
        // 每页多少条数据
        limit: 3,
      },
      // 总共有多少条数据
      total: 0,
      // 列表是否在加载中
      listLoading: false,
      // 数据绑定到这里
      list:[],
    }
  },
  methods: {
    addPrice(){
      addPrice(this.appoint).then((res)=>{
        if(res.code === 20000){
          this.list[this.index] = this.appoint;
          this.$message({
            type: 'success',
            message: '添加成功'
          })
        }else{
          this.$message({
            type: 'error',
            message: '添加失败'
          })
        }
        this.dialogFormVisible = false;
        this.appoint = {};
        this.index = -1;
      })
    },
    handleEdit(index, appoint){
      this.appoint = appoint
      this.dialogFormVisible = true;
      this.index = index;
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