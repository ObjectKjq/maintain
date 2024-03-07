<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button class="filter-item" type="warning" icon="el-icon-s-open" @click="toggleSelection">
        清空选择
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="success" icon="el-icon-edit" @click="handleCreate">
        添加
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
      <el-table-column prop="username" label="用户名">
      </el-table-column>
      <el-table-column prop="userAccount" label="账号">
      </el-table-column>
      <el-table-column prop="userPassword" label="密码">
      </el-table-column>
      <el-table-column prop="avatarUrl" label="用户头像">
      </el-table-column>
      <el-table-column prop="gender" label="性别">
      </el-table-column>
      <el-table-column prop="userRole" label="角色">
      </el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button size="mini" type="primary"  @click="handleEdit(scope.$index, scope.row)">
            编辑
          </el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">
            禁用
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

    <el-dialog :title="title" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="用户名:" :label-width="formLabelWidth">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="账号:" :label-width="formLabelWidth">
          <el-input v-model="form.userAccount" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码:" :label-width="formLabelWidth">
          <el-input v-model="form.userPassword" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别:" :label-width="formLabelWidth">
          <el-radio-group v-model="form.gender">
            <el-radio :label="0">男</el-radio>
            <el-radio :label="1">女</el-radio>
            <el-radio :label="2">保密</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="角色:" :label-width="formLabelWidth">
          <el-radio-group v-model="form.userRole">
            <el-radio :label="0">用户</el-radio>
            <el-radio :label="1">维修师</el-radio>
            <el-radio :label="2">管理员</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateForm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {getUsers, addUser, updateUser, deleteUser} from '@/api/admin'
export default {
  data() {
    return {
      formLabelWidth: '80px',
      dialogFormVisible: false,
      title:'',
      form:{},
      index: -1,
      // 搜索发送的数据
      params: {
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
      // 这里是被选中的列表
      multipleSelection: []
    }
  },
  methods: {
    handleDelete(index, user){
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteUser(user.id).then((res)=>{
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
    updateForm(){
      if(this.index == -1){
        // 添加用户
        addUser(this.form).then((res)=>{
          if(res.code === 20000){
            this.list.unshift(res.data)
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
          this.title = '';
          this.form = {};
        })
      }else{
        // 修改用户
        updateUser(this.form).then((res)=>{
          if(res.code === 20000){
            this.list[this.index] = this.form;
            this.$message({
              type: 'success',
              message: '修改成功'
            })
          }else{
            this.$message({
              type: 'error',
              message: '修改失败'
            })
          }
          this.dialogFormVisible = false;
          this.title = '';
          this.form = {};
          this.index = -1;
        })
      }
    },
    handleEdit(index, user){
      this.dialogFormVisible = true;
      this.title = '编辑用户';
      this.form = user;
      this.index = index;
    },
    handleCreate(){
      this.form = {};
      this.index = -1;
      this.dialogFormVisible = true;
      this.title = '添加用户';
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
      getUsers(this.params).then((res)=>{
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
      getUsers(this.params).then((res)=>{
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
    getUsers(this.params).then((res)=>{
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