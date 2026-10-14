<template>
  <div>

    <div style="padding: 10px 0">
      <el-input style="width: 200px;" placeholder="请输入名称" v-model="name" ></el-input>
      <el-button style="margin-left: 10px" type="primary" @click="load">查看</el-button>
    </div>

    <el-table :data="tableData" border stripe header-cell-class-name="headerBg">
      <el-table-column prop="username" label="用户名" width="140">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
      <el-table-column prop="sex" label="性别">
      </el-table-column>
      <el-table-column prop="phone" label="电话">
      </el-table-column>
      <el-table-column prop="place" label="地址">
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
  </div>
</template>

<script>
export default {
  name: "User",
  data() {
    return{
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 2,
      name: "",
      form: {},
      dialogFormVisible: false,
      headerBg: 'headerBg',
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.request.get("http://localhost:9090/user1/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize+"&name="+ this.name).then(res => {
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
      // console.log(row.id)
      this.form = row
      this.dialogFormVisible = true
    },
  }
}
</script>

<style>
.headerBg {
  background: #eee!important;
}
</style>

