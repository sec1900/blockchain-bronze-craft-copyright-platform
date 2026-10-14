<template>
  <div>

    <div style="padding: 10px 0">
      <el-input style="width: 200px;" placeholder="请输入名称" v-model="name" ></el-input>
      <el-button style="margin-left: 10px" type="primary" @click="load">查看</el-button>
    </div>

    <div style="padding: 10px 0">
      <el-button style="margin-left: 10px" type="primary" @click="handleAdd">新增</el-button>
    </div>

    <el-table :data="tableData" border stripe header-cell-class-name="headerBg">
      <el-table-column prop="username" label="管理员账号" width="140">
      </el-table-column>
      <el-table-column prop="name" label="管理员姓名" width="120">
      </el-table-column>
      <el-table-column prop="sex" label="管理员性别">
      </el-table-column>
      <el-table-column prop="phone" label="管理员电话">
      </el-table-column>
      <el-table-column prop="place" label="管理员地址">
      </el-table-column>
      <el-table-column prop="address" label="操作">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">查看详情 <i class="el-icon-edit"></i></el-button>
          <el-button type="success" @click="handleEdit1(scope.row)">修改权限 <i class="el-icon-edit"></i></el-button>

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
        <el-form-item label="管理员账号">
          <el-input v-model="form.username" autocomplete="off" :disabled="disabled"></el-input>
        </el-form-item>
        <el-form-item label="管理员密码">
          <el-input v-model="form.password" autocomplete="off" :disabled="disabled"></el-input>
        </el-form-item>
        <el-form-item label="管理员姓名">
          <el-input v-model="form.name" autocomplete="off" :disabled="disabled"></el-input>
        </el-form-item>
        <el-form-item label="管理员性别">
          <el-input v-model="form.sex" autocomplete="off" :disabled="disabled"></el-input>
        </el-form-item>
        <el-form-item label="管理员电话">
          <el-input v-model="form.phone" autocomplete="off" :disabled="disabled"></el-input>
        </el-form-item>
        <el-form-item label="管理员地址">
          <el-input v-model="form.place" autocomplete="off" :disabled="disabled"></el-input>
        </el-form-item>
        <el-form-item label="管理员权限">
          <el-input v-model="quanxian" autocomplete="off" :disabled="disabled"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="save">确 定</el-button>
        <el-button @click="dialogFormVisible = false;disabled = true">返 回</el-button>
      </div>
    </el-dialog>

    <el-dialog title="权限" :visible.sync="dialogFormVisible1">
      <el-select v-model="value" placeholder="请选择">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="save">确 定</el-button>
        <el-button @click="dialogFormVisible1 = false;disabled = true">返 回</el-button>
      </div>

    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Guanli",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 2,
      name: "",
      form: {},
      dialogFormVisible: false,
      dialogFormVisible1: false,
      headerBg: 'headerBg',
      disabled: true,
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) :{},
      options: [{
        value: '1',
        label: '查看，修改权限'
      }, {
        value: '2',
        label: '查看'
      }],
      value: '',
      quanxian: ""
    }
  },
  created() {
    this.request.get("http://localhost:9090/login/username/" + this.user.username).then(res => {
      // console.log(res.quanxian)
      if(res.quanxian === "1") {
        this.value = '1'
        this.load()
      }else{
        this.$message.error("权限不足")
      }
    })
  },
  methods: {
    load() {
      this.request.get("http://localhost:9090/login/page?pageNum=" + this.pageNum + "&pageSize=" + this.pageSize + "&name=" + this.name).then(res => {
        this.tableData = res.records
        this.total = res.total
      })
    },
    rowclick(row) {
      if (row.quanxian === "1") {
        this.value = '1'
        this.quanxian = "查看，新增管理员"
      }else {
        this.value = '2'
        this.quanxian = "查看"
      }
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
      this.disabled = false
      this.form = {}
    },
    handleEdit(row) {
      // console.log(row.id)
      this.form = row
      this.rowclick(row)
      this.dialogFormVisible = true
    },
    handleEdit1(row) {
      // console.log(row.id)
      this.form = row
      this.rowclick(row)
      this.dialogFormVisible1 = true
    },
    save() {
      this.form.quanxian = this.value
      this.request.post("http://localhost:9090/login", this.form).then(res => {
        if (res) {
          this.$message.success("上传成功")
          this.dialogFormVisible = false
          this.dialogFormVisible1 = false
          this.disabled = true
          this.load()
        } else {
          this.$message.error("上传失败")
        }
      })
    },
  }
}
</script>

<style scoped>

</style>
