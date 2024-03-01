<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.title" placeholder="描述" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        搜索
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
import {getAppoints} from '@/api/maintain'
export default {
  data() {
    return {
      // 搜索发送的数据
      listQuery: {
        // 预约状态
        status: 1,
        // 第几页
        page: 1,
        // 每页多少条数据
        limit: 20,
        // 搜索题目名称
        title: undefined,
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
    handleFilter(){

    },
    handleSizeChange(){

    },
    handleCurrentChange(){
      
    }
  },
  created(){
    // 初始化数据
    this.listLoading = true;
    getAppoints(this.listQuery).then((res)=>{
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