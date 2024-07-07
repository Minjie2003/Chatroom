<!-- <template>
  <div class="test-container">
    <h1>Tests</h1>
    <h2>Get Group</h2>
    <el-button @click="get_group_list">Get Group List</el-button>
    <div v-for="group in groups"
         :key="group.id"
    >
      <p> {{ group.name }}@{{ group.id }}C:{{ group.category === 1? 'Friend':'Group'}}</p>
    </div>
    <h2>Create Group</h2>
    <div class="create-group" style="display: grid;">
      <el-input v-model="input_data.name"></el-input>
      <el-radio-group v-model="input_data.category">
        <el-radio-button :value="0" label="Group">Group</el-radio-button>
        <el-radio-button :value="1" label="Frined">Friend</el-radio-button>
        <el-text>{{ input_data.name}} | {{ input_data.category}}</el-text>
      </el-radio-group>
      <el-button @click="create_group">Create</el-button>
    </div>

    <h2>Get Session List</h2>
    <div class="get-session-list">
      <ul>
        <li v-for="item in session_list">
          ID:{{ item.username }}
          C:{{ item.id }}
          Time:{{item.createTime}}
          Unread:{{ item.unreadNum}} <br>
          LastMsg: {{item.lastContent}}
        </li>
      </ul>
    <el-button @click="get_session_list">
      Get Session List
    </el-button>
    </div>
  </div>
</template>


<script setup>
import axios from "axios";
import {ref} from "vue";
import {ElMessage} from "element-plus";
import {current_conversation_list} from "@/store/modules/conv.js";

const groups = ref([])
const get_group_list = () => {
  axios.post('/my_chatroom/contact_group/get_group')
      .then(res => {
        groups.value = res.data.data
        console.log(groups.value)
      })
      .catch(err => {
        console.error(err);
      })
}

const input_data = ref({
  name: 'New-zhl',
  category: 0
})

const create_group = () => {
  axios.post('/my_chatroom/contact_group/create_group', input_data.value, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then(res => {
        if(res.data.code === 0) {
          ElMessage.error('fail to create')
        }
        get_group_list()
      })
      .catch(err => {
        console.error(err);
      })
}

const session_list = ref(current_conversation_list)

const get_session_list = () => {
  axios.post('/my_chatroom/contact_session/get_contact_session_list', {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
      .then(res => {
        if(res.data.code === 0) {
          ElMessage.error('fail to get session list')
        }
        session_list.value = res.data.data
      })
      .catch(err => {
        console.error(err);
      })
}

</script>


<style scoped>
.test-container {
  display: grid;
  padding: 2em;
}
</style> -->
