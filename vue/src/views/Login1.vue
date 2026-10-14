<template>
  <div class="wrapper">
    <div style="margin: 200px auto; background-color: #fff; width: 350px; height: 300px; padding: 20px; border-radius: 10px">
      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>管理员登录</b></div>
      <el-form :model="login" :rules="rules" ref="userForm">
        <el-form-item prop="username">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="login.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="login.password"></el-input>
        </el-form-item>
        <div style="margin: 10px 0; text-align: right">
          <el-button type="primary" size="small" autocapitalize="off" @click="login1">登录</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",

  data() {
    return {
      login: {},
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 1, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        ],
      },
    }
  },
  methods: {
    // 登陆
    login1() {
      this.$refs['userForm'].validate((valid) => {
        if (valid) {
          this.request.post("http://localhost:9090/login/login", this.login).then(res => {
            if(res.code === "200") {
              localStorage.setItem("user",JSON.stringify(res.data))
              this.$router.push("/manage/information")
              this.$message.success("登陆成功")
            }else {
              this.$message.error(res.msg)
            }
          })
        }
      });
    }
  }
}
</script>

<style>
  .wrapper {
    height: 100vh;
    background-image: linear-gradient(to bottom right, #fc466b, #3f5efb);
    overflow: hidden;
  }

</style>
