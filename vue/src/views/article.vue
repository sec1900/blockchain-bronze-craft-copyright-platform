<template>
  <div id="main">
    <el-input v-model="form.user" autocomplete="off" placeholder="请输入作者" style="width: 500px;margin-bottom: 20px"></el-input>
    <mavon-editor v-model="form.content"/>
    <el-button @click="save" type="primary" style="margin-top: 20px">发 布</el-button>
  </div>
</template>

<script>

import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
export default {
  name: "article",
  data() {
    return {
      form:{}
    }
  },

  methods:{
    save() {

    }
  }
}
</script>

<style scoped>

</style>
