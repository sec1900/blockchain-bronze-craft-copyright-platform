<template>
  <div>
    <el-card style="width: 750px; padding: 20px;">
      <el-form label-width="120px" size="small">
        <el-form-item label="作者">
          <el-input v-model="form1.name" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="作品名称">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="作品长度（cm）">
          <el-input v-model="form.length" autocomplete="off" placeholder="cm"></el-input>
        </el-form-item>
        <el-form-item label="作品高度（cm）">
          <el-input v-model="form.height" autocomplete="off" placeholder="cm"></el-input>
        </el-form-item>
        <el-form-item label="作品宽度（cm）">
          <el-input v-model="form.width" autocomplete="off" placeholder="cm"></el-input>
        </el-form-item>
        <el-form-item label="作品重量（kg）">
          <el-input v-model="form.weight" autocomplete="off" placeholder="kg"></el-input>
        </el-form-item>
        <el-form-item label="作品介绍">
<!--          <el-input v-model="form.introduce" autocomplete="off"></el-input>-->
          <el-input
              type="textarea"
              :rows="5"
              placeholder="请输入内容"
              v-model="form.introduce">
          </el-input>
        </el-form-item>
        <el-form-item label="作品类型">
          <el-input v-model="form.type" autocomplete="off"></el-input>
        </el-form-item>
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
        <el-form-item label="上传文件">
          <p style="color: red">请将作品不同方位的图片六张，并附一作品说明文件，和建模信息打包成压缩包上传</p>
          <div style="margin: 10px 0">
<!--            <el-upload :action="'http://localhost:9090/file/upload'" -->
<!--                       :on-success="handleFileUploadSuccess" style="display: inline-block">-->
<!--              <el-button type="primary" class="ml-5" :disabled="disabled" style="margin-left: 100px;">上传文件 <i class="el-icon-top"></i></el-button>-->
<!--            </el-upload>-->
            <el-upload
                class="upload-demo"
                drag
                :disabled="disabled2"
                :show-file-list="false"
                action="http://localhost:9090/file/upload"
                :on-success="handleFileUploadSuccess1"
                style="display: inline-block">
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            </el-upload>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="save" :disabled="disabled" style="margin-left: 150px;width: 100px">上传</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-dialog title="信息" :visible.sync="dialogFormVisible">
      <el-form label-width="120px" size="small">
<!--        <h3 style="color: red;margin-left: 240px">您上传的作品可能已存在，请重新上传</h3>-->
        <h3 style="color: red;margin-left: 130px">您上传的作品与该作品雷同，请核实后再进行上传</h3>
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
  created() {
    this.request.get("http://localhost:9090/user1/username/" + this.user.username).then(res => {
      this.form1 = res
      if(this.form1.name === null) {
        this.$message.error("请上传个人信息")
        this.disabled = true
      }
    })
  },
  methods: {
    save() {
      this.form.username = this.form1.name
      this.form.url = 1
          if(this.form.name != null && this.form.type != null) {
            this.request.post("http://localhost:9090/banquan", this.form).then(res => {
              if(res) {
                this.$message.success("上传成功")
              }else {
                this.$message.error("上传失败")
              }
            })
          }else {
            this.$message.error("上传失败")
          }
        },
        // console.log(this.id)
        // this.temp = "{\"name\":\""+this.form1.name+"\",\"type\":\""+this.form1.type+"\",\"url\":\""+this.form1.url+"\",\"username\":\""+this.form1.username+"\"}"
        // if(this.temp !== this.form){
        //   this.input1 = "rt-input1"
        //   this.form1.zhuangtai = "版权信息被修改"
        // }else{
        //   this.input1 = "rt-input"
        //   this.form1.zhuangtai = "正常"
        // }
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
    handleFileUploadSuccess1(res) {
      console.log(res)
      this.disabled2 = true
      this.$message.success("上传成功")
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
