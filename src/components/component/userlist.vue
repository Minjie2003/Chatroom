<template>
  <ul v-infinite-scroll="load" class="infinite-list" style="overflow: auto">
    <li>
      <el-card :body-style="{padding:'10px'}" style="width:450px;">
        <el-row>
          <el-col :span="24">
            <div>time</div>
          </el-col>
        </el-row>
        <el-row justify="sapce-around">
          <el-col :span="4">
            <el-image style="align-items: center;width: 50px ; height: 50px" :src="url" fit="fits" />
          </el-col>
          <el-col :span="4">
            <div>
              Name
            </div>
          </el-col>
          <el-col :span="6">
            <el-tooltip
                    class="box-item"
                    effect="light"
                    content="具体内容"
                    placement="bottom"
                    :visible="visible"
                >
                备注信息
            </el-tooltip>
          </el-col>
          <el-col :span="5">
      
              <el-button type="primary">同意</el-button>
          </el-col>
          <el-col :span="5">
              <el-button type="info">拒绝</el-button>
          </el-col>
        </el-row>
      </el-card>
    </li>
  </ul>
</template>

<script>
import url from "@/assets/images/Chatroom.jpg"
import axios from "axios";
export default{
  name:"userlist",
  data(){
    id:1
    return{
      url,

    }
  },
  method:{
    // axios.get('/user', {
    //   params: {
    //     id:this.id
    //   }
    // })
    // .then(function (response) {
    //   console.log(response);
    // })
    // .catch(function (error) {
    //   console.log(error);
    // })
    // .finally(function () {
    //   // 总是会执行
    // }),

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
  align-items:center;
  text-align: center;
}
.infinite-list {
  height: 300px;
  padding: 0;
  margin: 0;
  list-style: none;
}
.infinite-list .infinite-list-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  background: var(--el-color-primary-light-9);
  margin: 10px;
  color: var(--el-color-primary);
}
.infinite-list .infinite-list-item + .list-item {
  margin-top: 10px;
}
</style>
