<template>
  <div class="app-container">
    <el-row>
      <el-button type="primary" @click="applyAK">Apply AccessKey</el-button>
    </el-row>
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

      <el-table-column prop="id" label="AccessKey ID" width="200" />
      <el-table-column prop="accesskey" label="AccessKey" />
      <el-table-column prop="createdTime" label="Created Time" width="160"/>
      <el-table-column label="Operation" width="200" align="center">
        <template slot-scope="scope">
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
<style>
  .el-row {
    margin-bottom: 20px;
  }
</style>
<script>
import akAPI from "@/api/filemgt/ak"


export default {
    data() {
        return {
            list: null,
            page: 1,
            limit: 10,
            total: 0
        }

    },
    created () {
        this.getList();
    },
    methods: {
        getList(page=1){
            this.page = page
            akAPI.getAKs(this.page, this.limit)
            .then(response =>{
                    this.list = response.data.records
                    this.total = response.data.total
            })
            .catch(error => {
                console.log(error)
            })
        },
       applyAK(){
          this.$confirm('Accesskey will allow anyone to access file system, make sure you can keep accesskey secretly.', 'Warning', {
              confirmButtonText: 'Apply',
              cancelButtonText: 'Cancel',
              type: 'warning'
            }).then(() => {
              akAPI.applyAK().then(res =>{
                this.$message({
                  type: 'success',
                  message: 'Apply Sucess!'
                });
                this.getList()
              })

            }).catch(() => {
              this.$message({
                type: 'info',
                message: 'Cancel Apply'
              });          
            });
       },
        removeDataById(id){
          this.$confirm('You will delete this AK Permanently , are you sure?', 'Warning', {
              confirmButtonText: 'OK',
              cancelButtonText: 'Cancel',
              type: 'warning'
          }).then(() => {  //点击确定，执行then方法
              //调用删除的方法
              akAPI.deteleAKById(id).then(res =>{
              this.$message({
                  type: 'success',
                  message: 'delete success!'
              });
              this.getList()
            })
        })
      }
    }
   
}
</script>