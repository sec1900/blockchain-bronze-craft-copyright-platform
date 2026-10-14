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
          <el-input v-model="form.username" autocomplete="off" :disabled="disabled1"></el-input>
        </el-form-item>
        <el-form-item label="作品名称">
          <el-input v-model="form.name" autocomplete="off" :disabled="disabled1"></el-input>
        </el-form-item>
        <el-form-item label="类型">
          <el-input v-model="form.type" autocomplete="off" :disabled="disabled1"></el-input>
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
        <el-form-item label="上传时间">
          <el-input v-model="form.time" autocomplete="off" :disabled="disabled1"></el-input>
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
          <el-input v-model="form.weight" autocomplete="off" placeholder="cm"></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-input v-model="key" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="qukuaixinxi" :disabled="disabled1">修 复</el-button>
        <el-button type="primary" @click="save1" :disabled="disabled1">修复上传</el-button>
      </div>
    </el-dialog>

    <el-dialog title="信息" :visible.sync="dialogFormVisible1">
      <el-form label-width="120px" size="small">
        <el-form-item label="区块链密文信息">
<!--          <el-input v-model="form2" autocomplete="off" :disabled="true"></el-input>-->
          <el-input
              type="textarea"
              :rows="5"
              v-model="form2"
              :disabled="true">
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="jiemi1">解 密</el-button>
        <el-button @click="dialogFormVisible1 = false">关 闭</el-button>
      </div>
    </el-dialog>

    <el-dialog title="信息" :visible.sync="dialogFormVisible2">
      <el-form label-width="120px" size="small">
        <el-form-item label="区块链明文信息">
          <!--          <el-input v-model="form2" autocomplete="off" :disabled="true"></el-input>-->
          <el-input
              type="textarea"
              :rows="5"
              v-model="form3"
              :disabled="true">
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">关 闭</el-button>
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
      form: {},
      dialogFormVisible: false,
      dialogFormVisible1: false,
      dialogFormVisible2: false,
      headerBg: 'headerBg',
      key:"",
      disabled: false,
      disabled1: false,
      form2:"",
      form3:"",
      temp:""
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.request.get("http://localhost:9090/shensu/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize+"&name="+ this.name).then(res => {
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
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit(row) {
      this.key = "版权信息已被修改"
      this.id = row.id + ""
      this.form = row
      console.log(this.id)
      this.request.get("http://localhost:8081/cat/"+ this.id).then(res => {
        if(res.status === "ok") {
          this.form2 = res.payload
          // this.jiemi(res.payload)
        }
      })
      this.dialogFormVisible = true
    },
    qukuaixinxi() {
      if(this.key === "版权信息已被修改") {
        this.dialogFormVisible1 = true
        this.disabled1 = false
      }
    },
    jiemi(data) {
      this.request.get("http://localhost:9090/banquan/aes/" + data).then(res => {
        console.log(res.mingwen)
        if(res) {
          this.$message.success("区块链密文信息解密成功")
          this.form3 = res.mingwen
        }else {
          this.$message.error("区块链密文信息解密失败")
        }
      })
    },
    jiemi1() {
      // console.log(this.form2)
      this.dialogFormVisible1 = false
      this.dialogFormVisible2 = true
      this.jiemi(this.form2)
    },
    save1() {
      this.form.url = "2"
      this.request.post("http://localhost:9090/banquan/", this.form).then(res => {
        if(res) {
          this.$message.success("铜工艺品版权信息修复完成")
          this.delete()
          this.load()
          this.dialogFormVisible = false
          this.disabled = false
        }
      })
    },
    delete() {
      this.request.delete("http://localhost:9090/shensu/" + this.form.id).then(res => {
      })
    }
  }
}
</script>

<style scoped>

</style>
