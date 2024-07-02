<template>
    <el-card class="translucent-card">
      <div class="typing-effect">
        <span class="scrollable-text">{{ displayedText }}</span>
      </div>
    </el-card>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import { ElCard } from 'element-plus';
  
  // 固定打字内容
  const text = '这是我们设计的一个网页聊天室，欢迎注册';
  const displayedText = ref('');
  const speed = 100; // 打字速度，单位为毫秒
  
  const typeText = () => {
    let index = 0;
    const interval = setInterval(() => {
      if (index < text.length) {
        displayedText.value += text[index];
        index++;
      } else {
        clearInterval(interval);
      }
    }, speed);
  };
  
  onMounted(() => {
    typeText();
  });
  </script>
  
  <style scoped>
  .translucent-card {
    background-color: rgba(255, 255, 255, 0.7); /* 半透明背景 */
    border: 1px solid #ddd;
    padding: 0px;
    width: 400px;
    margin: 0px auto;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }
  
  .typing-effect {
    font-family: monospace;
    white-space: nowrap;
    overflow: hidden;
    border-right: .15em solid orange;
    animation: caret 0.75s steps(1) infinite;
  }
  
  .scrollable-text {
    display: block;
    max-height: 100px; /* 设置一个合理的最大高度 */
    overflow-y: auto;
    white-space: normal;
  }
  
  @keyframes caret {
    50% {
      border-color: transparent;
    }
  }
  </style>
  