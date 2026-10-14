<template>
  <div>
    <el-card style="width: 750px; padding: 20px;">
      <el-form label-width="120px" size="small">
        <el-form-item label="上传正面图片">
          <div style="margin: 10px 0">
            <!--            <el-upload :action="'http://localhost:9090/file/upload'" -->
            <!--                       :on-success="handleFileUploadSuccess" style="display: inline-block">-->
            <!--              <el-button type="primary" class="ml-5" :disabled="disabled" style="margin-left: 100px;">上传文件 <i class="el-icon-top"></i></el-button>-->
            <!--            </el-upload>-->
            <el-upload
                class="upload-demo1"
                drag
                :disabled="disabled1"
                :show-file-list="false"
                action="http://localhost:9090/file1/upload"
                :on-success="handleFileUploadSuccess"
                style="display: inline-block">
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            </el-upload>
          </div>
        </el-form-item>
      </el-form>
    </el-card>

    <el-dialog title="信息" :visible.sync="dialogFormVisible">
      <el-form label-width="120px" size="small">
<!--        <h3 style="color: red;margin-left: 240px">您上传的作品可能已存在，请重新上传</h3>-->
        <h3 style="color: red;margin-left: 280px">数据库中已存在该作品</h3>
        <img :src=src style="width: 200px;height: 200px;margin-left: 300px">
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false;disabled1 = false">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Upbanquan",
  data() {
    return {
      username: "",
      form: {},
      form1: {},
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) :{},
      disabled: false,
      disabled1: false,
      disabled2: false,
      dialogFormVisible: false,
      src: " "
    }
  },
  methods: {
    handleFileUploadSuccess(res) {
      console.log(res)
      this.disabled1 = true
      this.request.get("http://localhost:9090/file1").then(res => {
        if(res){
          // this.$message.error("您上传的作品可能已存在，请重新上传")
          this.src = res.url
          this.dialogFormVisible = true
        }else {
          this.$message.success("上传成功")
        }
      })
    },
  }
}
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
