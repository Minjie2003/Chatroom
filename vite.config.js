import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    open: true,//启动项目自动弹出浏览器
    port: 5173,//启动端口
    proxy: {
      '/my_chatroom': {
        target: 'http://localhost:8080',	//实际请求地址
        ws: true,
        changeOrigin: true,
      },
    }
  }
})
