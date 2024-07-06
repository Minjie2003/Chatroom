<template>
  <div class="chat-outer-panel" ref="chatPanel">
    <el-row class="title-container" justify="center" @click="OtherInfoDialogVisible = true">
      <el-text class="conversation-title" size="large">
        {{ selected_session.nickName }}
      </el-text>
    </el-row>

    <el-row>
      <div class="chat-inner-panel">
        <div class="scrollbar-container">
          <el-scrollbar class="msg-scrollbar" ref="msg_scrollbar">
            <div>
              <el-alert type="warning"
                        class="no-message" style="font-size: 2em; margin-top: 2em;"
                        v-show="msg_list.length === 0"> No Message.
              </el-alert>
            </div>

            <div
                style="display: grid; align-content: center; align-items: center;"
                v-for="(msg, index) in msg_list"
                :key="index"
                ref="msg_bubble"
            >

              <!-- Other Message -->
              <div class="message-row"
                   v-if="msg.username !== thisUser.value.username ">
                <span></span>

                <el-avatar
                    class="message-avatar other-avatar"
                    :src="msg.headPath">
                </el-avatar>

                <div style="display: flex; flex-flow: column nowrap; align-content: start;">
                  <el-text style="align-self: start;">{{ msg.username }}</el-text>

                  <div class="message-bubble other-bubble">
                    <el-text v-if="msg.category === 0" class="other-message message-text">{{ msg.content }}</el-text>
                    <el-image v-if="msg.category === 1" class="other-message message-image"
                              style="padding: 1em; width: 10em; height: 10em;
                               min-width: 5em; min-height: 5em;"
                              :src="msg.content" :alt=" 'A picture at' + msg.content "/>
                  </div>
                </div>

                <span></span>
                <span></span>
              </div>

              <!-- My Message-->
              <div class="message-row"
                   v-else>
                <span></span>
                <span></span>
                <div>
                  <div style="display: flex; flex-flow: column nowrap; align-content: end;">
                    <el-text style="align-self: end;">{{ msg.username }}</el-text>
                    <div class="message-container me-bubble" style="align-self: end;">
                      <el-text v-if="msg.category === 0" class="my-message message-text">{{ msg.content }}</el-text>
                      <el-image v-if="msg.category === 1" class="my-message message-image"
                                style="padding: 1em; width: 10em; height: 10em;
                               min-width: 5em; min-height: 5em;"
                                :src="msg.content" :alt=" 'A picture at' + msg.content "/>
                    </div>
                  </div>
                </div>
                <el-avatar
                    class="message-avatar my-avatar"
                    :src="msg.headPath">
                </el-avatar>
                <span></span>
              </div>

            </div>
          </el-scrollbar>
        </div>
      </div>
    </el-row>

    <!--Edit Area-->
    <div class="edit-area">
      <div class="toolbar">
        <el-icon class="effected-icon" :size="25" color="#0BFD">
          <Share/>
        </el-icon>
        <el-icon class="effected-icon" :size="25" color="#0BFD">
          <Files/>
        </el-icon>
        <el-icon class="effected-icon" :size="25" color="#0BFD" @click="handlePictureClick">
          <Picture/>
        </el-icon>
        <el-icon class="effected-icon" :size="25" color="#0BFD">
          <Camera/>
        </el-icon>
        <el-icon class="effected-icon" :size="25" color="#0BFD">
          <Location/>
        </el-icon>
        <el-icon class="effected-icon" :size="25" color="#0BFD">
          <Setting/>
        </el-icon>
        <el-icon class="effected-icon" :size="25" color="#0BFD">
          <Plus/>
        </el-icon>
      </div>

      <div>
        <!--        <el-button class="send-button" @click="fetchMsgList">Refresh</el-button>-->
        <el-button class="send-button" @click="handleSendClick">Send</el-button>
      </div>

      <el-input class="message-input" v-model="message_input" ref="message_input"
                maxlength="1024" show-word-limit
                placeholder="Input Message to Send..."
                clearable :autosize="{minRows: 1, maxRows: 3}"
                type="textarea"
      />
    </div>

    <el-dialog class="otherInfoDialog" v-model="OtherInfoDialogVisible">
      <div class="other-info-container">
        <el-text class="other-info-title">Other Info</el-text>
        <el-text class="other-name">{{ otherInfo.usrname }}</el-text>
        <div class="nickname-edit">
          <el-text>备注</el-text>
          <el-input v-model="inputUserNickname"
                    :placeholder="otherInfo.nickName"
          />
          <el-button @click="handleNicknameModify">Modify</el-button>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {Camera, Files, Location, Picture, Plus, Setting, Share} from "@element-plus/icons-vue";
import {ElMessage} from "element-plus";
import axios from "axios";
import {
  current_conv_msg_list,
  selected_session,
  selectedOtherInfo,
  thisUser
} from "@/store/cr_config.js";

export default {

  emits: ['updateList'],
  computed: {
    selected_session() {
      return selected_session.value
    },
    thisUser() {
      return thisUser.value
    },
    otherInfo() {
      return selectedOtherInfo.value
    },

  },

  components: {Share, Location, Camera, Picture, Files, Plus, Setting},
  data() {
    return {
      message_input: '',
      OtherInfoDialogVisible: false,
      inputUserNickname: '',
      msg_list: current_conv_msg_list,

    };
  },

  methods: {
    fetchMsgList() {
      axios.post('my_chatroom/message/get_message_list',
          {sessionId: selected_session.value.sessionId},
          {
            headers: {
              "Content-Type": "multipart/form-data",
            }
          })
          .then(res => {
            this.msg_list = res.data.data
          })

          .catch(err => {
            console.log(err)
          })
    },

    handleSendClick() {
      if (this.message_input === '') {
        ElMessage.warning('Empty Message')
      }
      axios.post('my_chatroom/message/send_message',
          {
            content: this.message_input,
            contactSessionId: selected_session.value.id,
            sessionId: selected_session.value.sessionId
          }, {
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          })
          .then(res => {
            if (res.data.code === 200) {
              ElMessage.success('Send Success')
              this.$refs.message_input.clear()
            }
            this.fetchMsgList()
          }).catch(err => console.log(err))
    },

    handleNicknameModify() {
      if (this.inputUserNickname === '') {
        ElMessage.error('Empty Name')
        return
      }
      axios.post('my_chatroom/contact_session/update_nick_name', {
            id: selected_session.value.id,
            nickName: this.inputUserNickname,
          },
          {headers: {"Content-Type": "multipart/form-data"}}
      ).then(res => {
        if (res.data.message !== '') ElMessage.info(res.data.message)
        else ElMessage.info('Modification Success')
      }).catch(err => console.log(err))
      this.OtherInfoDialogVisible = false
      this.$emit('updateList')
    },


    handlePictureClick() {
      ElMessage.info('Insert Picture')
    },

    scrollToLatestMessage() {
      ElMessage.info('Scrolling to latest message (to be impl). ')
    },
  },
};
</script>

<style scoped>

.chat-outer-panel {
  display: grid;
  height: calc(100vh - var(--cr-header-height));
  width: 100%;
  position: relative;
  background-color: transparent;
  border-radius: 1rem;
}

.chat-outer-panel::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url('../../assets/images/miku-pink.jpg');
  opacity: 0.6;
  box-shadow: 0 0 2em var(--cr-hot-hover-color);
  background-size: cover;
  background-position: center;
  border-radius: 1rem;
  z-index: -1;
}

.title-container {

}

.conversation-title {
  font-size: 1rem;
  font-weight: bold;
  height: 1em;
  margin: 0.5em 0 0.5em 0;
  background-color: rgba(238, 238, 238, 0.6);
  padding: 2px 3em 2px 3em;
  border-radius: 3px;
}

.chat-inner-panel {
  width: 100%;
  height: 100%;
  border-color: transparent;
  align-self: center;
}

.scrollbar-container {
  align-self: center;
  width: 100%;
  height: 60vh;
}

.message-row {
  display: grid;
  padding: 1rem;
  grid-template-columns: 1fr 1fr 14fr 1fr 1fr;
  grid-template-areas: 'left other bubble meava right';
}

.message-row:hover {
  background-color: #fff4;
  border-radius: 5px;
  box-shadow: 0 0 5px #fff4;
}

.message-avatar {
  border: 2px solid rgba(76, 181, 255, 0.91);
  --el-avatar-size: 3.5rem;
}

.message-avatar:hover {
  box-shadow: 0 0 20px rgba(40, 166, 250, 0.77);
  border-color: rgba(150, 209, 246, 0.47);
  transition: all 0.3s ease-in-out;
}

.message-row:hover .el-text {
  --el-text-color: #222d;
  border-color: #fffd;
  transition: all 0.3s ease-in-out;
}

.message-bubble {
  grid-area: bubble;
}

.other-message {
  --el-text-font-size: 18px;
  background-color: rgba(133, 198, 243, 0.77);
  border-radius: 1em;
  padding-left: 1em;
  padding-right: 1em;
  margin-bottom: 0.5em;
  border: 1px solid #fff6;
}

.my-message {
  --el-text-font-size: 18px;
  background-color: rgba(133, 243, 164, 0.77);
  border-radius: 1em;
  padding-left: 1em;
  padding-right: 1em;
  margin-bottom: 0.5em;
  border: 1px solid #fff6;
}

.other-avatar {
  grid-area: other;
}

.my-avatar {
  grid-area: meava;
}

.edit-area {
  height: 30vh;
  background-color: #fff9;
  border-radius: 1em;
  box-shadow: 0 0 1em #fff9;

  display: grid;
  grid-template-columns: 4fr 1fr;
  grid-template-rows: 1fr 3fr;
  grid-column-gap: 1em;

}

.send-button {
  margin: 10px;
  flex-shrink: 2;
  flex-basis: 2rem;
}

.message-input {
  font-size: 1rem;
  align-self: start;
  justify-self: start;
  opacity: 0.4;
  margin-left: 1rem;
}

.toolbar {
  display: grid;
  grid-template-columns: repeat(10, 1fr);
  place-content: center;
}

.other-info-container {
  display: grid;
  grid-template-rows: 1fr 1fr 1fr;
}

.other-info-title {
  font-weight: bolder;
  align-self: center;
  justify-self: center;
}

.other-name {
  font-size: 1rem;
  justify-self: center;
}

.nickname-edit {
  display: grid;
  grid-template-columns: 1fr 3fr 1fr;
}
</style>
