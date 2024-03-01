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
      :page-sizes="[10, 20, 30, 40]"
      :current-page="1"
      background
      layout="total, sizes, prev, pager, next, jumper"
      :total="total" 
      class="page">
    </el-pagination>
  </div>
</template>

<script>
import {getConsults} from '@/api/maintain'
export default {
  data() {
    return {
      // 搜索发送的数据
      params: {
        // 第几页
        page: 1,
        // 每页多少条数据
        limit: 20,
      },
      // 总共有多少条数据
      total: 100,
      // 列表是否在加载中
      listLoading: false,
      // 数据绑定到这里
      list:[],
    }
  },
  methods: {
    handleSizeChange(){

    },
    handleCurrentChange(){
      
    }
  },
  created(){
    // 初始化数据
    this.listLoading = true;
    getConsults(this.params).then((res)=>{
      if(res.code == 20000){
        this.list = res.data;
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