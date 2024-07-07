<template>
    <div>
      <el-upload
        class="upload-demo"
        :http-request="uploadImage"
        list-type="picture-card"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :limit="1"
        :before-upload="beforeUpload"
      >
        <i class="el-icon-plus"></i>
      </el-upload>
      <el-dialog
        v-model="dialogVisible"
        width="50%"
        :before-close="handleClose"
      >
        <img width="100%" :src="dialogImageUrl" alt="" />
      </el-dialog>
    </div>
  </template>
  
  <script>
  import { ref } from 'vue';
  import axios from 'axios';
  
  export default {
    setup() {
      const dialogImageUrl = ref('');
      const dialogVisible = ref(false);
  
      const handlePreview = (file) => {
        dialogImageUrl.value = file.url;
        dialogVisible.value = true;
      };
  
      const handleRemove = (file, fileList) => {
        console.log(file, fileList);
      };
  
      const beforeUpload = (file) => {
        const isJPG = file.type === 'image/jpeg';
        const isPNG = file.type === 'image/png';
        const isLt2M = file.size / 1024 / 1024 < 2;
  
        if (!isJPG && !isPNG) {
          this.$message.error('上传头像图片只能是 JPG 或 PNG 格式!');
          return false;
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
          return false;
        }
        return true;
      };
  
      const uploadImage = async (options) => {
        const { file, onSuccess, onError } = options;
        const formData = new FormData();
        formData.append('file', file);
  
        try {
          const response = await axios.post('/api/upload', formData, {
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          });
          onSuccess(response.data);
          dialogImageUrl.value = response.data.url; // 更新对话框中的图片URL
          console.log('上传成功:', response.data);
        } catch (error) {
          console.error('上传失败:', error);
          onError(error);
        }
      };
  
      const handleClose = (done) => {
        dialogVisible.value = false;
        done();
      };
  
      return {
        dialogImageUrl,
        dialogVisible,
        handlePreview,
        handleRemove,
        beforeUpload,
        uploadImage,
        handleClose
      };
    }
  };
  </script>
  
  <style scoped>
  .upload-demo {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 200px;
    height: 200px;
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    position: relative;
    cursor: pointer;
    overflow: hidden;
  }
  .upload-demo .el-icon-plus {
    font-size: 28px;
    color: #8c939d;
  }
  </style>
  