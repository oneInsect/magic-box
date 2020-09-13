<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="fileQuery.name" placeholder="File Name"/>
      </el-form-item>
      <el-form-item label="Created Time">
        <el-date-picker
          v-model="fileQuery.begin"
          type="datetime"
          placeholder="Select Start Time"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="fileQuery.end"
          type="datetime"
          placeholder="Select End Time"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>

      <el-button type="primary" icon="el-icon-search" @click="getList()">Search</el-button>
      <el-button type="default" @click="resetData()">Clear</el-button>
    </el-form>

    <!-- 表格 -->
    <el-table
      :data="list"
      border
      fit
      highlight-current-row>

      <el-table-column
        label="No."
        width="70"
        align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="name" label="Name" width="100" />
      <el-table-column prop="cateName" label="Category" width="160"/>
      <el-table-column prop="fileDesc" label="Describe" />
      <el-table-column prop="createdTime" label="Created Time" width="160"/>
      <el-table-column prop="modifiedTime" label="Modified Time" width="160"/>
      <el-table-column label="Operation" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/files/edit/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">Modify</el-button>
          </router-link>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>

  <!-- 分页 -->
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="getList"
    />
   
  </div>
</template>
<script>
import filesAPI from "@/api/filemgt/files"


export default {
    data() {
        return {
            list: null,
            page: 1,
            limit: 10,
            fileQuery: {},
            total: 0
        }

    },
    created () {
        this.getList();
    },
    methods: {
        getList(page=1){
            this.page = page
            filesAPI.getFiles(this.page, this.limit, this.fileQuery)
            .then(response =>{
                    console.log('this.list')  
                    this.list = response.data.records
                    this.total = response.data.total
                    console.log(this.list)   
                    console.log(this.total)
            })
            .catch(error => {
                console.log(error)
            })
        },
        resetData(){
            this.fileQuery = {}
            this.getList()
        },
        removeDataById(id){
           this.$confirm('You will delete this file Permanently , are you sure?', 'Warning', {
                confirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {  //点击确定，执行then方法
                //调用删除的方法
                filesAPI.deleteFileById(id)
                    .then(response =>{//删除成功
                    //提示信息
                    this.$message({
                        type: 'success',
                        message: 'delete success!'
                    });
                    //回到列表页面
                    this.getList()
                })
            }) //点击取消，执行catch方法
        }

    }
}
</script>