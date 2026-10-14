<template>
  <div>
    <header id="header" class="skel-layers-fixed">
      <h1><a href="#">铜工艺品版权管理系统</a></h1>
      <nav id="nav">
        <ul>
          <li><a href="http://localhost:8080/">首页</a></li>
          <li><a href="http://localhost:8080/login">版权保护</a></li>
          <li><a href="http://localhost:8080/show">3D展示</a></li>
          <li><a href="http://localhost:8080/login1">管理员登录</a></li>
        </ul>
      </nav>
    </header>

    <div style="padding-top: 60px">
      <h1>
        这是3D展示页面
      </h1>
    </div>
  </div>
</template>

<script>
export default {
  name: "Show"
}
</script>

<style scoped>
@import '../../public/static/css/skel.css' ;
@import '../../public/static/css/style.css' ;
@import '../../public/static/css/style-xlarge.css' ;

</style>
