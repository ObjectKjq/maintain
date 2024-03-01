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
      <el-button class="filter-item" type="danger" icon="el-icon-delete-solid" @click="deleteSelection">
        删除选择
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="success" icon="el-icon-edit" @click="handleCreate">
        添加
      </el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">
        下载
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
        status: 0,
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
      // 这里是被选中的列表
      multipleSelection: []
    }
  },
  methods: {
    handleFilter(){

    },
    handleCreate(){

    },
    handleDownload(){

    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    toggleSelection() {
      this.$refs.multipleTable.clearSelection();
    },
    // 发送网络请求删除所选择的数据
    deleteSelection(){

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