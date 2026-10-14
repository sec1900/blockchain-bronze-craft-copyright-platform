<template>
  <div>

    <div style="padding: 10px 0">
      <el-input style="width: 200px;" placeholder="请输入名称" v-model="name" ></el-input>
      <el-button style="margin-left: 10px" type="primary" @click="load">查看</el-button>
    </div>

    <el-table :data="tableData" border stripe header-cell-class-name="headerBg">
      <el-table-column prop="username" label="作者" width="140">
      </el-table-column>
      <el-table-column prop="name" label="作品名" width="120">
      </el-table-column>
      <el-table-column prop="type" label="作品类型">
      </el-table-column>
      <el-table-column prop="time" label="上传时间">
      </el-table-column>
      <el-table-column prop="address" label="操作">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">查看详情 <i class="el-icon-edit"></i></el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 15]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

    <el-dialog title="信息" :visible.sync="dialogFormVisible">
      <el-form label-width="120px" size="small">
        <el-form-item label="作者">
          <el-input v-model="form.username" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="作品名称">
          <el-input v-model="form.name" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="类型">
          <el-input v-model="form.type" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="上传时间">
          <el-input v-model="form.time" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="作品介绍">
          <!--          <el-input v-model="form.introduce" autocomplete="off"></el-input>-->
          <el-input
              type="textarea"
              :rows="5"
              v-model="form.introduce">
          </el-input>
        </el-form-item>
        <el-form-item label="作品高度（cm）">
          <el-input v-model="form.height" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="作品宽度（cm）">
          <el-input v-model="form.width" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="作品长度（cm）">
          <el-input v-model="form.length" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="作品重量（kg）">
          <el-input v-model="form.weight" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-input v-model="key" autocomplete="off" ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save" :disabled="disabled">上 链</el-button>
      </div>
    </el-dialog>

    <el-dialog title="信息" :visible.sync="dialogFormVisible1">
      <el-form label-width="120px" size="small">
        <el-form-item label="加密后的密文为">
          <!--          <el-input v-model="form.introduce" autocomplete="off"></el-input>-->
          <el-input
              type="textarea"
              :rows="5"
              v-model="introduce">
          </el-input>
        </el-form-item>
        <h3 style="color: red;margin-left: 240px">铜工艺品版权信息加密后上链成功</h3>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Banquan",
  data() {
    return{
      id: "",
      tableData: [],
      form1: {},
      total: 0,
      pageNum: 1,
      pageSize: 2,
      name: "",
      introduce: "",
      form: {},
      dialogFormVisible: false,
      dialogFormVisible1: false,
      headerBg: 'headerBg',
      key:"",
      disabled: false,
      disabled1: false,
      form2:"",
      temp:""
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.request.get("http://localhost:9090/banquan/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize+"&name="+ this.name).then(res => {
        this.tableData = res.records
        this.total = res.total
      })
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },
    handleEdit(row) {
      this.form = row
      // if(this.form2 === this.temp) {
      //   this.key = "审核通过"
      //   this.disabled = true
      //   this.disabled1 = true
      // }
      // console.log(this.form.url)
      if(this.form.url === "1") {
        this.key = "审核中"
        this.disabled = false
      }else {
        this.key = "审核通过"
        this.disabled = true
      }
      this.dialogFormVisible = true
    },
    save() {
      this.form.url = 2
      this.request.post("http://localhost:9090/banquan", this.form).then(res => {
        if(res) {
          // this.$message.success("铜工艺品版权信息将加密后上链成功")
          this.shangchuan(this.form.name)
          this.dialogFormVisible = false
          // this.dialogFormVisible1 = true
          this.disabled = false
          this.load()
        }else {
          this.$message.error("保存失败")
        }
      })
    },
    shangchuan(name) {

      this.request.get("http://localhost:9090/banquan/name/" + name).then(res => {
        this.form = res
        this.id = this.form.id + ""
        this.form.id = this.id
        this.form1.key = this.id
        this.form1.username = this.form.username
        this.form1.name = this.form.name
        this.form1.type = this.form.type
        this.form1.url = this.form.url
        this.form1.time = this.form.time
        this.form1.height = this.form.height
        this.form1.width = this.form.width
        this.form1.weight = this.form.weight
        this.form1.length = this.form.length
        this.form1.introduce = this.form.introduce
        // console.log(this.form1)

        this.request.post("http://localhost:8081/cat/", this.form1).then(res => {
          if(res.status === "ok") {
            console.log(res.payload)
            this.introduce = res.payload
            this.dialogFormVisible1 = true
            // this.$message.success("铜工艺品版权信息加密后上链成功")
            this.dialogFormVisible = false
            this.disabled = false
            this.load()
          }else {
            this.$message.error("保存失败")
          }
        })
      })
    },
  }
}
</script>

<style scoped>

/*input:disabled,textarea:disabled{*/
/*  opacity: 1;*/
/*  -webkit-text-fill-color: red;*/
/*}*/

/*.el-input.is-disabled .el-input__inner,textarea:disabled{*/
/*  color: #000 !important;*/
/*  cursor: pointer;*/
/*}*/
</style>
