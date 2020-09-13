<template>
  <div class="app-container">
   <el-form label-width="120px">
      <el-form-item label="File Path" :hidden="pathHidden">
        <el-input v-model="file.path" placeholder="aaa/bbb/ccc if not specify will use category default."/>
      </el-form-item>

      <el-form-item label="File Category">
          <el-select v-model="file.cateId" filterable placeholder="Please choose">
            <el-option
              v-for="item in categories"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
      </el-form-item>

      <el-form-item label="File Describe">
        <el-input v-model="file.fileDesc" :rows="10" type="textarea"/>
      </el-form-item>
      <el-form-item >
          <el-upload
            class="upload-demo"
            drag
            list-type="picture"
            :action="BASE_API + '/filemgt/files/file'"
            :limit=1
            :hidden="uploadHiddenAll"
            :on-remove="handleRemove"
            :on-success="handleSuccess"
            multiple>
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">Drag file here or click to upload<em>Click to Upload</em></div>
          </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">Save</el-button>
      </el-form-item>

    </el-form>

  </div>
</template>

<script>
import filesAPI from '@/api/filemgt/files'
import cateAPI from '@/api/filemgt/category'
export default {
  data() {
    return {
      file:{
        id: '',
        path: '',
        cateId: 'default',
        fileDesc: '',
        name: ''
      },
      categories: [],
      saveBtnDisabled: false,// 保存按钮是否禁用,
      pathHidden: false,
      uploadHiddenAll: false,
      BASE_API: process.env.BASE_API
    }
  },
  created() { //页面渲染之前执行
    this.init()
    this.getAllCates()
  },
  watch: {  //监听
    $route(to, from) { //路由变化方式，路由发生变化，方法就会执行
      this.init()
    }
  },
  methods:{
    init() {
      this.pathHidden=false
      this.uploadHidden=false
      //判断路径有id值,做修改
      if(this.$route.params && this.$route.params.id) {
          //从路径获取id值
          const id = this.$route.params.id
          //调用根据id查询的方法
          this.getInfo(id)
          this.pathHidden=true
          this.uploadHiddenAll=true

      } else { //路径没有id值，做添加
        //清空表单
        this.file = {
                      id: '',
                      path: '',
                      cateId: 'default',
                      fileDesc: '',
                      name: ''
                    }
      }
    },
    getAllCates(){
       cateAPI.getCatesAll().then(res =>{
           this.categories = res.data.categories
       }).catch(error => {
           console.log(error.message)
           this.categories =[{
                              id: 'default',
                              name: 'Uncategory'
                            }]
       })
    },
    handleSuccess(response, file, fileList){
        this.file.name = file.name
        // this.uploadHiddenAll = fileList.length >= 1;
        // this.uploadHidden=true
    },
    handleRemove(file, fileList){
        // this.uploadHiddenAll = fileList.length >= 1;
    },
    saveOrUpdate(){
      if(!this.file.id) {
        //添加
        this.saveFile()
      } else {
        //修改
        this.updateFile()
      }
    },
    saveFile(){
        console.log(this.file)
        filesAPI.addFiles(this.file).then(res => {
             this.$message({
              type: 'success',
              message: 'Add Success!'
          });
          this.$router.push({path:'/files/list'})
        }).catch(error => {
            
        })
    },
    getInfo(id){
      filesAPI.getFileById(id).then(res => {
          this.file = res.data.files 
      }).catch(error => {
          console.log(error.message)
      })
    },
    updateFile(){
      filesAPI.updateFileById(this.file).then(res => {
              this.$message({
              type: 'success',
              message: 'Update Success!'
          });
          this.$router.push({path:'/files/list'})
      }).catch(error => {
          console.log(error.message)
      })
        
    }
  }
}
</script>
