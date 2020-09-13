<template>
  <div class="app-container">
   <el-form label-width="120px">
      <el-form-item label="Category Name" >
        <el-input v-model="category.name" />
      </el-form-item>

      <el-form-item label="Category Describe">
        <el-input v-model="category.cateDesc" :rows="10" type="textarea"/>
      </el-form-item>
    
      <el-form-item>
        <el-button type="primary" @click="saveCate">Save</el-button>
      </el-form-item>

    </el-form>

  </div>
</template>

<script>
import cateAPI from '@/api/filemgt/category'
export default {
  data() {
    return {
      category:{
        name: '',
        cateDesc: '',

      }
    }
  },
  created() { //页面渲染之前执行

  },
  methods:{
    saveCate(){
        cateAPI.addCate(this.category).then(res => {
             this.$message({
              type: 'success',
              message: 'Add Success!'
          });
          this.$router.push({path:'/categories/list'})
        }).catch(error => {
          
        })
    }
  }
}
</script>
