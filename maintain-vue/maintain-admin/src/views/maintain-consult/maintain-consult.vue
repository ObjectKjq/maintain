<template>
  <div class="app-container">
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
      <el-table-column prop="sendName" label="发送人">
      </el-table-column>
      <el-table-column prop="acceptName" label="接收人">
      </el-table-column>
      <el-table-column prop="content" label="消息">
      </el-table-column>
      <el-table-column label="操作" width="80">
        <template slot-scope="scope">
          <el-button size="mini" type="primary"  @click="handleEdit(scope.$index, scope.row)">
            回复
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
      <el-form :model="form">
        <el-form-item label="消息内容:" :label-width="formLabelWidth">
          <el-input v-model="form.content" autocomplete="off"></el-input>
        </el-form-item>
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
import {getConsults, reply} from '@/api/maintain'
export default {
  data() {
    return {
      formLabelWidth: '80px',
      dialogFormVisible: false,
      title:'',
      index: -1,
      form:{},
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
    }
  },
  methods: {
    updateForm(){
      reply(this.form).then((res)=>{
        if(res.code === 20000){
          this.list.splice(this.index, 1);
          this.$message({
            type: 'success',
            message: '回复成功!'
          })
        }else{
          this.$message({
            type: 'error',
            message: '回复失败!'
          })
        }
        this.dialogFormVisible = false;
        this.title = '';
        this.form = {};
        this.index = -1;
      })
    },
    handleEdit(index, consult){
      this.dialogFormVisible = true;
      this.title = '回复消息';
      this.form = consult;
      // 调整内容
      this.form.content = '';
      this.form.userAcceptId = this.form.userSendId;

      this.index = index;
    },
    handleSizeChange(limit){
      this.params.limit = limit;
      this.params.page = 1;
      this.listLoading = true;
      getConsults(this.params).then((res)=>{
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
      getConsults(this.params).then((res)=>{
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
    getConsults(this.params).then((res)=>{
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