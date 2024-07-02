<!--这是一个走马灯的设计-->
<template>
  <div class="block" style="max-width: 400px;">
    <el-carousel height="394px" trigger="hover" interval=2000 :autoplay="true">
      <el-carousel-item v-for="item in images" :key="item.url">
        <img :src="item.url" alt="Lantern Image" class="carousel-image" />
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
import lantern1 from '@/assets/images/lantern1.jpg';
import lantern2 from '@/assets/images/lantern2.jpg';
import lantern3 from '@/assets/images/lantern3.jpg';

export default {
  name: "Lantern",
  data() {
    return {
      images: [
        { url: lantern1,value:"开始你的聊天" },
        { url: lantern2,value:"Create by Shangcheng"},
        { url: lantern3,value:"I like chating" },
      ]
    };
  }
};
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.carousel-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.rounded-carousel {
  overflow: hidden; /* 防止子元素溢出 */
}

.carousel-caption {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  padding: 10px;
  background-color: rgba(0, 0, 0, 0.5);
  color: white;
  text-align: center;
}
</style>
