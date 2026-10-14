<template>
  <div class="wrapper">
    <div style="margin: 200px auto; background-color: #fff; width: 350px; height: 560px; padding: 20px; border-radius: 10px">
      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>注册</b></div>
      <el-form :model="login" :rules="rules" ref="userForm">
        <el-form-item prop="username">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" placeholder="请输入用户名" v-model="login.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input  size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock"  placeholder="请输入密码" show-password  v-model="login.password"></el-input>
        </el-form-item>
        <el-form-item prop="confirmPassword">
          <el-input  size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" placeholder="请确认密码" show-password v-model="login.confirmPassword"></el-input>
        </el-form-item>
        <el-form-item prop="phone">
          <el-input  size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" placeholder="请输入手机号" v-model="phone"></el-input>
          <el-button type="primary" size="small" autocapitalize="off" @click="getrandom">获取验证码</el-button>
        </el-form-item>
        <el-form-item prop="random">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" placeholder="请输入验证码" v-model="random"></el-input>
        </el-form-item>
        <div style="margin: 10px 0; text-align: right">
          <el-button type="primary" size="small" autocapitalize="off" @click="yanzheng">确定</el-button>
          <el-button type="primary" size="small" autocapitalize="off" @click="$router.push('/')">返回登录</el-button>
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
      form:{},
      phone:"",
      random: null,
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 1, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        ],
        confirmPassword: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 1, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    login1() {
      this.$refs['userForm'].validate((valid) => {
        if (valid) {
          if(this.login.password !== this.login.confirmPassword) {
            this.$message.error("两次输入不一致")
            return false
          }
          this.request.post("http://localhost:9090/user1/register", this.login).then(res => {
            if(res.code === "200") {
              this.$message.success("注册成功")
              this.getone()
            }else {
              this.$message.error(res.msg)
            }
          })
        }
      });
    },
    getone() {
      this.request.get("http://localhost:9090/user1/username/" + this.login.username).then(res => {
        this.form = res
        this.form.phone = this.phone
        this.setphone()
      })
    },
    setphone() {
      this.request.post("http://localhost:9090/user1", this.form).then(res => {})
    },

    getrandom() {
      this.request.get("http://localhost:9090/user1/getphone?phone="+this.phone).then(res => {
        if(res){
          this.$message.success("验证码已发送")
        }
      })
    },
    yanzheng() {
      console.log(this.random)
      this.request.get("http://localhost:9090/user1/phone?phone="+this.phone+"&random="+this.random).then(res => {
        console.log(res)
        if(res){
          this.login1()
        }else {
          this.$message.error("验证码输入错误")
        }
      })
    },
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
