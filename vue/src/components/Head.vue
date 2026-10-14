<template>
  <div>
    <el-menu
        class="el-menu-demo"
        mode="horizontal"
        style="background-color: bisque;display: flex"
        text-color="black"
        router
    >
      <el-menu-item index="/home1">首页</el-menu-item>
      <el-menu-item index="/login">版权保护</el-menu-item>
      <el-menu-item index="/show">3D展示</el-menu-item>
      <el-menu-item index="/login1">管理员登录</el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Head"
}
</script>

<style scoped>

</style>
