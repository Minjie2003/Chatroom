<template>
  <div class="card-wrapper">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>{{ $t('manager.report_management')}}</span>
        </div>
      </template>
        <div v-for="report in searchinfos" :key="report.id">
            <el-card class="user-card" body-style="padding:0px">
                <el-row  justify="center">
                    <el-col :span="2">
                        {{ report.sendName }}
                    </el-col>
                    <el-col :span="2">
                        ({{ report.sendId }})
                    </el-col>
                    <el-col :span="2">
                      {{ $t('manager.because') }}
                    </el-col>
                    <el-col :span="3">
                        {{ report.content }} {{ $t('manager.report') }}
                    </el-col>
                    <el-col :span="3">
                        {{ report.receiveName }}
                    </el-col>
                    <el-col :span="2">
                        ({{ report.receiveId }})
                    </el-col>
                    <el-col :span="7">
                        {{ report.sendTime }}
                    </el-col>
                    <el-col :span="3">
                      {{ $t('manager.warned') }}
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

  const queryReport = () => {
    axios.get(`/my_chatroom/manager/get_page_report?index=${currentPage.value}`)
        .then(res => {
          store.dispatch('searchinfos/updateSearchInfos',res.data.data)
        })
        .catch(err => {
            console.error('Error fetching report data:', err);
        });
    };
  const getMaxIndex = async () => {
    try {
      const response = await axios.get("/my_chatroom/manager/get_report_max_index");
      maxIndex.value = response.data.data;
      await getTotal(); // 等待获取总条数
    } catch (error) {
      console.log(error);
    }
  };
  
  const getTotal = async () => {
    try {
      const response = await axios.get(`/my_chatroom/manager/get_page_report?index=${maxIndex.value}`);
      total.value = response.data.data.length + maxIndex.value * 8 - 8;
    } catch (error) {
      console.log(error);
    }
  };
  
  onMounted(() => {
    getMaxIndex();
    queryReport();
  });

  const handleCurrentChange = (val: number) => {
    queryReport()
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
  

 