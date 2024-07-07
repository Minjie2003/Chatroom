<template>
    <div class="card-wrapper">
      <el-card>
        <template #header>
          <div class="card-header">
            <span>{{ $t('manager.user_management')}}</span>
          </div>
        </template>
          <div v-for="user in searchinfos" :key="user.id">
              <el-card class="user-card" body-style="padding:0px">
                  <el-row  justify="center">
                      <el-col :span="4">
                          <img class="avatar" :src="'/my_chatroom/'+user.photo" alt="Avatar">
                      </el-col>
                      <el-col :span="10">
                          <div class="username">{{ user.username }}</div>
                          <div class="account">{{ user.accountNum }}</div>
                      </el-col>
                      <el-col :span="10">
                          <el-button type="primary" @click="resetPassword(user.id)">{{ $t('manager.reset_password') }}</el-button>
                          <el-button type="danger" @click="banUser(user.id,user.state)">
                            <div v-if="user.state === 3">{{ $t('manager.ban_user') }}</div>
                            <div v-else>{{ $t('manager.unban_user') }}</div>
                          </el-button>
                      </el-col>
                  </el-row>
              </el-card>
          </div>
        <template #footer>
          <div class="footer-content">
            <el-pagination
              v-model:current-page="currentPage"
              :page-size="8"
              :size="size"
              :disabled="disabled"
              :background="background"
              layout="prev, pager, next, jumper, total"
              :total="total"
              @current-change="handleCurrentChange"
            />
          </div>
        </template>
      </el-card>
      </div>
    </template>
    
    <script lang="ts" setup>
    import { ref, onMounted,computed } from 'vue';
    import { ElMessage, type ComponentSize } from 'element-plus';
    import axios from 'axios';
    import { useStore } from 'vuex';
    const store = useStore()
    const searchinfos = computed(()=>store.state.searchinfos.searchinfos)

    const currentPage = ref(1);
    const total = ref(0);   // 总条数
    const maxIndex = ref(0);
    const size = ref<ComponentSize>('default');
    const background = ref(false);
    const disabled = ref(false); 
       //展示的用户信息
    const queryUser = () => {
      axios.get(`/my_chatroom/manager/get_page_user?index=${currentPage.value}`)
          .then(res => {
            store.dispatch('searchinfos/updateSearchInfos',res.data.data)
          })
          .catch(err => {
              console.error('Error fetching user data:', err);
          });
      };
    const getMaxIndex = async () => {
      try {
        const response = await axios.get("/my_chatroom/manager/get_user_max_index");
        maxIndex.value = response.data.data;
        await getTotal(); // 等待获取总条数
      } catch (error) {
        console.log(error);
      }
    };
    
    const getTotal = async () => {
      try {
        const response = await axios.get(`/my_chatroom/manager/get_page_user?index=${maxIndex.value}`);
        total.value = response.data.data.length + maxIndex.value * 8 - 8;
      } catch (error) {
        console.log(error);
      }
    };
    
    onMounted(() => {
      getMaxIndex();
      queryUser();
    });

    //重置密码
    const resetPassword=(id)=>{
        axios.post("/my_chatroom/manager/reset_password",{id:id},{
          headers:{
            'Content-Type': 'multipart/form-data'
          }
        }).then(res=>{
            if(res.data.code === 200){
                ElMessage.success("重置成功！")
                queryUser()
            }else if(res.data.code === 0){
              ElMessage.error("请输入正确的参数")
            }
        })
    }
    //封禁用户
    const banUser=(id,state)=>{
      if(state === 3){
        axios.post("/my_chatroom/manager/recover_user",{id:id},{
          headers:{
            'Content-Type': 'multipart/form-data'
          }
        }).then(res=>{
            if(res.data.code === 200){
                ElMessage.success("解封成功！")
                queryUser()
            }else if(res.data.code === 0){
              ElMessage.error("请输入正确的参数")
            }
        })
      }else{
        axios.post("/my_chatroom/manager/ban_user",{id:id},{
          headers:{
            'Content-Type': 'multipart/form-data'
          }
        }).then(res=>{
            if(res.data.code === 200){
                ElMessage.success("封禁成功！")
                queryUser()
            }else if(res.data.code === 0){
              ElMessage.error("请输入正确的参数")
            }
        })
      }
    }
    const handleCurrentChange = (val: number) => {
      queryUser()
    };
    </script>
    
    <style scoped>
    .card-wrapper {
      width: 60%;
      margin: 0 auto; /* 居中 */
    }
    .card-header {
      background-color: #f0f2f5;
      padding: 0px;
      border-bottom: 1px solid #dcdfe6;
    }
    
    .footer-content {
      display: flex;
      justify-content: flex-end;
      margin-top: 10px;
    }
    .user-card {
    margin-bottom: 20px;
    padding: 20px;
    border: 1px solid #ebeef5;
    border-radius: 4px;
    background-color: #fff;
  }
  
  .avatar {
    width: 80px;
    height: 80px;
    border-radius: 50%;
    object-fit: cover;
  }
  
  .username {
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 10px;
  }
  
  .account {
    color: #666;
    margin-bottom: 10px;
  }
  
  .el-button {
    margin-right: 10px;
  }
    </style>
    

   