<template>
  <el-card :body-style="{padding:'4px'}">
    <el-row justify="sapce-around">
      <el-col :span="2">
        <el-image style="align-items: center;width: 50px ; height: 50px" :src="url" fit="fits"/>
      </el-col>
      <el-col :span="2">
        <h4>Chatroom</h4>
      </el-col>
      <el-col :span="13">
        <div>
          <el-input
              v-model="input3"
              style="width: 300px;border-radius: 15px;"
              placeholder="Please input"
              class="input-with-select"
              maxlength="20"
          >
            <template #append>
              <el-button :icon="Search" style="color:  #50b5ff;"/>
            </template>
          </el-input>
        </div>
      </el-col>
      <!--引入头像-->
      <el-col :span="1">
        <div class="demo-basic--circle">
          <div class="block">
            <el-avatar :size="50" :src="circleUrl"/>
          </div>
        </div>
      </el-col>
      <el-col :span="2">
        <h5 style="color:black">{{ username }}</h5>
      </el-col>
      <el-col :span="1">
        <el-tooltip
            class="box-item"
            effect="light"
            content="好友添加 "
            placement="bottom"
            :visible="visible"
        >
          <el-icon color="#50b5ff" size="20" @click="showUserApply = !showUserApply">
            <Bell/>
          </el-icon>
        </el-tooltip>
      </el-col>
      <el-col :span="1">
        <el-tooltip
            class="box-item"
            effect="light"
            content="查看举报/警告"
            placement="bottom"
            :visible="visible"
        >
          <el-icon :size="20" color="#50b5ff">
            <Warning/>
          </el-icon>
        </el-tooltip>

      </el-col>
      <el-col :span="1">
        <el-tooltip
            class="box-item"
            effect="light"
            content="切换语言"
            placement="bottom"
            :visible="visible"
        >
          <el-icon :size="20" color="#50b5ff">
            <Guide/>
          </el-icon>
        </el-tooltip>
      </el-col>
      <el-col :span="1">
        <el-tooltip
            class="box-item"
            effect="light"
            content="退出登录"
            placement="bottom"
            :visible="visible"
        >
          <el-icon :size="20" color="#50b5ff">
            <Switch/>
          </el-icon>
        </el-tooltip>
      </el-col>
    </el-row>
  </el-card>
  <userapply></userapply>
</template>

<script>
import url from "@/assets/images/Chatroom.jpg"
import {ref} from 'vue'
import {Guide, Search} from '@element-plus/icons-vue'
import background from '@/assets/images/background.jpg'
import language from "@/assets/images/language.svg"

export default {
  name: "Header",
  data() {
    return {
      fits: [
        'fill',
        'contain',
        'cover',
        'none',
        'scale-down',
      ],
      url: url,
      Search: Search,
      circleUrl: background,
      username: "Minjie",
      language: language,
      showUserApply: false
    }
  },
  setup() {
    const input1 = ref('')
    const input2 = ref('')
    const input3 = ref('')
    return {
      input1,
      input2,
      input3
    }
  },
  components: {
    userapply
  }
}
</script>

<style>
.el-row {
  margin-bottom: 2px;
  margin: 0;
}

.el-row:last-child {
  margin-bottom: 0;
}

.el-col {
  display: grid;
  border-radius: 10px;
  place-items: center;
  align-content: center;
  align-items: center;
  text-align: center;
}

.tooltip-base-box {
  width: 600px;
}

.tooltip-base-box .row {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.tooltip-base-box .center {
  justify-content: center;
}

.tooltip-base-box .box-item {
  width: 110px;
  margin-top: 10px;
}
</style>

