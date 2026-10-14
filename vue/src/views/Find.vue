<template>
<div>
  <div class="inner" style="margin-top: 20px;position: relative; z-index: 1;">
    <b style="font-size: large ; margin-left: 50px">输入作品证书号查询信息</b>
    <ul class="actions" style="margin-top: 10px">
      <el-input  size="medium" style="width: 500px;" placeholder="请输入名称" v-model="url" ></el-input>
      <el-button style="margin-left: 10px" type="primary" @click="getone" >查看</el-button>
    </ul>
  </div>

  <el-dialog title="信息" :visible.sync="dialogFormVisible1">
    <el-form label-width="120px" size="small">
      <el-form-item label="作者">
        <el-input v-model="form1.username" autocomplete="off" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="作品名称">
        <el-input v-model="form1.name" autocomplete="off" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="类型">
        <el-input v-model="form1.type" autocomplete="off" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="上传时间">
        <el-input v-model="form1.time" autocomplete="off" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="作品介绍">
        <!--          <el-input v-model="form.introduce" autocomplete="off"></el-input>-->
        <el-input
            type="textarea"
            :rows="5"
            placeholder="请输入内容"
            v-model="form1.introduce"
            :disabled="true">
        </el-input>
      </el-form-item>
      <el-form-item label="作品长度（cm）">
        <el-input v-model="form1.length" autocomplete="off" placeholder="cm" :disabled="true" ></el-input>
      </el-form-item>
      <el-form-item label="作品高度（cm）">
        <el-input v-model="form1.height" autocomplete="off" placeholder="cm" :disabled="true" ></el-input>
      </el-form-item>
      <el-form-item label="作品宽度（cm）">
        <el-input v-model="form1.width" autocomplete="off" placeholder="cm" :disabled="true" ></el-input>
      </el-form-item>
      <el-form-item label="作品重量（kg）">
        <el-input v-model="form1.weight" autocomplete="off" placeholder="cm" :disabled="true" ></el-input>
      </el-form-item>
      <el-form-item label="状态">
        <el-input v-model="zhuangtai" autocomplete="off" :disabled="true" :class="input1" ></el-input>
      </el-form-item>
    </el-form>
  </el-dialog>
</div>
</template>

<script>
export default {
  name: "Find",
  data() {
    return{
      url:"",
      form:"",
      form1: {},
      zhuangtai:"",
      dialogFormVisible1: false,
      dialogFormVisible2: false,
      input1: "rt-input",
      id:"",
      temp:"",
      i:0,
    }
  },
  methods:{
    getone() {
      this.dialogFormVisible1 = true
      this.request.get("http://localhost:9090/banquan/url/" + this.url).then(res => {
        this.id = res.id
        this.form1 = res
        this.data = "用户名："+this.form1.username+" 作品名："+this.form1.name+" 作品类型："+this.form1.type+"证书号："+this.form1.url+"上传时间："+this.form1.time+"高度："+this.form1.height+"长度："+this.form1.length+"宽度"+this.form1.width+"重量："+this.form1.weight+"介绍："+this.form1.introduce
        this.hecha(this.data,this.id)
        // console.log(this.id)
        // this.temp = "{\"name\":\""+this.form1.name+"\",\"type\":\""+this.form1.type+"\",\"url\":\""+this.form1.url+"\",\"username\":\""+this.form1.username+"\"}"
        // if(this.temp !== this.form){
        //   this.input1 = "rt-input1"
        //   this.form1.zhuangtai = "版权信息被修改"
        // }else{
        //   this.input1 = "rt-input"
        //   this.form1.zhuangtai = "正常"
        // }
      })
    },
    hecha(form1,id){
      this.request.get("http://localhost:8081/cat/" + id).then(res => {
        // this.form = res.payload
        this.request.get("http://localhost:9090/banquan/aes?data=" + form1 + "&data1=" + res.payload ).then(res => {
          console.log(res)
          if(!res){
            this.input1 = "rt-input1"
            this.zhuangtai = "版权信息被修改"
          }else{
            this.input1 = "rt-input"
            this.zhuangtai = "正常"
          }
        })
        // console.log(res.payload)
      })
    }
  }
}
</script>

<style scoped>
.rt-input /deep/ .el-input__inner {
  color: rgb(92, 112, 234) !important;
  cursor: pointer;
}

.rt-input1 /deep/ .el-input__inner {
  color: rgb(255,0,0) !important;
  cursor: pointer;
}
</style>
