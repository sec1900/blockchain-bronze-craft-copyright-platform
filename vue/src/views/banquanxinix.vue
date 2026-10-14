<template>
  <div>

    <div style="padding: 10px 0">
      <el-input style="width: 200px;" placeholder="请输入名称" v-model="name" :disabled="true"></el-input>
      <el-button style="margin-left: 10px" type="primary" @click="load">查看</el-button>
    </div>

    <el-table :data="tableData" border stripe header-cell-class-name="headerBg">
      <el-table-column prop="username" label="作者" width="140">
      </el-table-column>
      <el-table-column prop="name" label="作品" width="120">
      </el-table-column>
      <el-table-column prop="type" label="作品类型">
      </el-table-column>
      <el-table-column prop="time" label="上传时间">
      </el-table-column>
      <el-table-column prop="url" label="证书号">
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

    <el-dialog title="信息" :visible.sync="dialogFormVisible1">
      <el-form label-width="120px" size="small">
        <el-form-item label="作者">
          <el-input v-model="form.username" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="作品名称">
          <el-input v-model="form.name" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="类型">
          <el-input v-model="form.type" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-input v-model="url" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">返 回</el-button>
        <el-button @click="shensu" :disabled="disabled">申 诉</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "Guanlibanquan",
  data() {
    return{
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 2,
      name: "",
      form: {},
      dialogFormVisible: false,
      dialogFormVisible1: false,
      headerBg: 'headerBg',
      temp: "",
      username:"",
      disabled: false,
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) :{},
      url: "",
      id:"",
      form1:"",
      temp1:""
    }
  },
  created() {
    this.request.get("http://localhost:9090/user1/username/" + this.user.username).then(res => {
      this.name = res.name
    })
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
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit1(row) {
      // console.log(row.id)
      this.form = row
      console.log(this.temp1)
      if(this.form.url === null) {
        this.disabled = true
        this.url = "审核中"
      }else {
        this.url = "审核通过"
      }
      this.dialogFormVisible1 = true
    },
    shensu() {
      this.form.url = "2"
      this.request.post("http://localhost:9090/shensu", this.form).then(res => {
        if(res) {
          this.$message.success("申诉提交成功")
          this.dialogFormVisible1 = false
          this.load()
        }else {
          this.$message.error("申诉提交失败")
        }
      })
    },
  }
}
</script>

<style>
.headerBg {
  background: #eee!important;
}
</style>

