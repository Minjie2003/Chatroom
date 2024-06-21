<template>
    <div>
      {{ count }}
      <button @click="add">+1</button>
    </div>
  </template>
  
  <script>
  import {mapState} from 'vuex'
  export default {
    name: "Friends",
    // 创建一个 Counter 组件
    computed: {
        count () {
        return this.$store.state.count
        }
    },
    methods: {
      add() {
        this.$store.commit('increment');
      }
    }
  };
  </script>