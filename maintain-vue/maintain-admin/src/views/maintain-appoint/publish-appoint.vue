<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.title" placeholder="描述" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        搜索
      </el-button>
      <el-button class="filter-item" type="warning" icon="el-icon-s-open" @click="toggleSelection">
        清空选择
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="success" icon="el-icon-edit" @click="handleCreate">
        添加
      </el-button>
    </div>
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
    @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column prop="id" label="ID" width="55">
      </el-table-column>
      <el-table-column prop="createTime" label="创建日期" width="180">
      </el-table-column>
      <el-table-column prop="content" label="描述">
      </el-table-column>
      <el-table-column prop="appointTime" label="预约日期" width="180">
      </el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button size="mini" type="primary"  @click="handleEdit(scope.$index, scope.row)">
            编辑
          </el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">
            删除
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
    <el-dialog :title="title" :visible.sync="dialogFormVisible">
      <el-form :model="appoint">
        <el-form-item label="描述:" :label-width="formLabelWidth">
          <el-input v-model="appoint.content" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="预约日期:" :label-width="formLabelWidth">
          <el-date-picker
            v-model="appoint.appointTime"
            type="date"
            placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateAppoint">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {getAppoints, deleteAppoint, updateAppoint, addAppoint, formatDate} from '@/api/maintain'
export default {
  data() {
    return {
      title:'',
      formLabelWidth: '80px',
      // 搜索发送的数据
      listQuery: {
        // 预约状态
        status: 0,
        // 第几页
        page: 1,
        // 每页多少条数据
        limit: 3,
        // 搜索题目名称
        title: undefined,
      },
      // 总共有多少条数据
      total: 100,
      // 列表是否在加载中
      listLoading: false,
      // 数据绑定到这里
      list:[],
      // 这里是被选中的列表
      multipleSelection: [],
      // 弹窗弹出有否
      dialogFormVisible:false,
      // 弹窗数据
      appoint:{},
      // 数据在列表中的id
      index:-1
    }
  },
  methods: {
    // 修改预约信息
    updateAppoint(){
      // 把时间转换成字符串
      this.appoint.appointTime = formatDate(this.appoint.appointTime)
      if(this.index != -1){
        updateAppoint(this.appoint).then((res)=>{
          if(res.code === 20000){
            // 修改列表信息
            this.list[this.index] = this.appoint;
            this.$message({
              type: 'success',
              message: '修改成功'
            })
          }else{
            this.$message({
              type:'error',
              message:'修改失败'
            })
          }
          this.dialogFormVisible = false
          this.appoint = {};
          this.index = -1;
        })
      }else{
        addAppoint(this.appoint).then((res)=>{
          if(res.code === 20000){
            this.list.unshift(res.data)
            this.$message({
              type: 'success',
              message: '添加成功'
            })
          }else{
            this.$message({
              type:'error',
              message:'添加失败'
            })
          }
          this.dialogFormVisible = false
          this.appoint = {};
        })
      }
    },
    // 编辑信息
    handleEdit(index, appoint){
      this.appoint = appoint;
      this.index = index;
      this.dialogFormVisible = true;
      this.title = '修改预约'
    },
    // 删除数据
    handleDelete(index, appoint){
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteAppoint(appoint).then((res)=>{
          if(res.code === 20000){
            this.list.splice(index, 1);
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
          }else{
            this.$message({
              type: 'error',
              message: '删除失败!'
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
    handleFilter(){
      // 初始化数据
      this.listLoading = true;
      getAppoints(this.listQuery).then((res)=>{
        if(res.code == 20000){
          this.list = res.data;
          this.total = this.list.pop().total;
          this.listLoading = false;
        }
      })
    },
    handleCreate(){
      this.dialogFormVisible = true;
      this.title = '添加预约'
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    toggleSelection() {
      this.$refs.multipleTable.clearSelection();
    },
    // 选择每页展示多少条数据调用
    handleSizeChange(limit){
      this.listQuery.limit = limit;
      this.listQuery.page = 1;
      this.listQuery.title = undefined;
      // 初始化数据
      this.listLoading = true;
      getAppoints(this.listQuery).then((res)=>{
        if(res.code == 20000){
          this.list = res.data;
          this.total = this.list.pop().total;
          this.listLoading = false;
        }
      })
    },
    // 选择页面调用
    handleCurrentChange(page){
      this.listQuery.page = page;
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