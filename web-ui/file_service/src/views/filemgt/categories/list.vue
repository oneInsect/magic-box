<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="cateQuery.name" placeholder="Category Name"/>
      </el-form-item>
      <el-form-item label="Created Time">
        <el-date-picker
          v-model="cateQuery.begin"
          type="datetime"
          placeholder="Select Start Time"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="cateQuery.end"
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

      <el-table-column prop="name" label="CategoryName" width="100" />
      <el-table-column prop="cateDesc" label="Describe" />
      <el-table-column prop="createdTime" label="Created Time" width="160"/>
      <el-table-column prop="modifiedTime" label="Modified Time" width="160"/>
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
import catesAPI from "@/api/filemgt/category"


export default {
    data() {
        return {
            list: null,
            page: 1,
            limit: 10,
            cateQuery: {},
            total: 0
        }

    },
    created () {
        this.getList();
    },
    methods: {
        getList(page=1){
            this.page = page
            catesAPI.getCates(this.page, this.limit, this.cateQuery)
            .then(response =>{
                    this.list = response.data.records
                    this.total = response.data.total
            })
            .catch(error => {
                console.log(error)
            })
        },
        resetData(){
            this.cateQuery = {}
            this.getList()
        }

    }
}
</script>