<template>
  <div id="app">
    <h1>APP组件</h1> <!-- 页面标题 -->
    <!-- 路由声明链接，点击后会跳转到对应的路由 -->
    <router-link to="/discover">发现音乐</router-link> <!-- 跳转到发现音乐页面 -->
    <router-link to="/my">我的音乐</router-link> <!-- 跳转到我的音乐页面 -->
    <router-link to="/friends">关注音乐</router-link> <!-- 跳转到关注音乐页面 -->
    <!-- 声明路由占位标签，用来显示匹配到的路由组件 -->
    <router-view></router-view> <!-- 当前路由对应的组件会显示在这里 -->
    <test></test>
    <i class="fa fa-camera-retro fa-5x"></i> fa-5x
  </div>
</template>


<script>
import axios from 'axios';
import test from './components/elements/test.vue'
export default{
  name: 'App',
  components:{
    test
  },
  mounted:function(){
      axios.get('/product/search')
      .then(response => {          //箭头函数的作用域更广，继承父类
        console.log(response.data);
      })
    }
}
</script>
<style>
#app {
  font-family:  Arial, Helvetica, sans-serif;
  -webkit-font-smoothing: antialiased;
  background-color: #f0f0f0; /* 设置背景颜色 */
  color: #333; /* 设置文字颜色 */
  padding: 20px; /* 设置内边距 */
  border-radius: 10px; /* 设置边框圆角 */
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* 设置阴影效果 */
  transition: all 0.3s ease; /* 添加过渡效果 */
}
</style>