<template>
  <div style="height: 100%;">
    <el-container style="height: 500px; border: 1px solid #eee;height: 100%">
      <el-aside :width="sideWidth+'px'" style="background-color: rgb(238, 241, 246); height: 100%;" >
        <Asideuser :isCollapse="isCollapse" :logoTextshow="logoTextshow" />
      </el-aside>

      <el-container>
        <el-header style="border-bottom: 1px solid #ccc">
          <Header :collapseBtnClass="collapseBtnClass" :collapse="collapse" />
        </el-header>

        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>

import Asideuser from "@/components/Asideuser";
import Header from "@/components/Header";

export default {
  name: 'HomeView',
    data() {
      return {
        collapseBtnClass: 'el-icon-s-fold',
        isCollapse:false,
        sideWidth: 200,
        logoTextshow: true,
      }
    },
  components: {
    Asideuser,
    Header
  },
  methods: {
    collapse() {
      this.isCollapse = !this.isCollapse;
      if(this.isCollapse) {
        this.sideWidth = 64
        this.collapseBtnClass = "el-icon-s-unfold"
        this.logoTextshow = false
      }else {
        this.sideWidth = 200
        this.collapseBtnClass = "el-icon-s-fold"
        this.logoTextshow = true
      }
    }
  }
}
</script>

