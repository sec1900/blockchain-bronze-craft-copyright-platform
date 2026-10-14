<template>
<div style="border-bottom: 1px solid black">
  <div class="swiper">
    <div class="swiper-content">
      <div class="swiper-item" v-for="(item, index) in imageurl" :key="index">
        <img :src="item" width="100%" />
      </div>
    </div>
    <div class="page">
      <span></span>
      <span></span>
      <span></span>
      <span></span>
    </div>
  </div>
</div>
</template>

<script>
import { Swiper } from "../unit/swiper";
export default {
  name: "Swiper",
  data() {
    return {
      imageurl: [
        require("../assets/image/1.jpg"),
        require("../assets/image/2.jpg"),
        require("../assets/image/3.jpg"),
        require("../assets/image/5.jpg"),
        require("../assets/image/1.jpg"),
      ],
    };
  },
  methods: {
    swiper() {
      new Swiper({
        content: ".swiper-content",
        swiperItem: ".swiper-item",
        page: ".page",
      });
    },
  },

  mounted() {
    this.swiper();
  },
};
</script>

<style>
.swiper {
  height: 400px;
  width: 800px;
  position: relative;
  overflow: hidden;
  margin: 100px auto;
  border: 10px solid royalblue;
  border-radius: 20px;
  box-shadow: 15px 15px 20px 4px rgba(5, 131, 148, 0.5);
}
.swiper-content {
  position: absolute;
  left: 0;
  display: flex;
}

.swiper-item {
  height: 400px;
  width: 800px;
}

img {
  height: 100%;
  width: 100%;
}

.page {
  position: absolute;
  left: 50%;
  transform: translate(-50%, -50%);
  bottom: 5px;
}

span {
  display: inline-block;
  height: 12px;
  width: 12px;
  background-color: orange;
  margin-left: 50px;
  border-radius: 200px;
  cursor: pointer;
}

</style>
