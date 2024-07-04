<template>

  <div class="chat-outer-panel" ref="chatPanel">
    <el-row class="title-container" justify="center">
      <el-text class="conversation-title" size="large">
        {{ session.name }}
      </el-text>
    </el-row>

    <el-row>
      <div class="chat-inner-panel">
        <div class="scrollbar-container">
          <el-scrollbar>
            <div
                style="display: grid; align-content: center; align-items: center;"
                v-for="msg in messages">
              <!-- Other Message -->
              <div class="message-bubble"
                   v-if="msg.sender==='other'">
                <el-avatar
                    class="message-avatar"
                    :src="msg.avatar">
                </el-avatar>
                <div style="display: flex; flex-flow: column nowrap; align-content: start;">
                  <el-text style="align-self: start;">{{ msg.sender }}</el-text>
                  <el-text style="align-self: start;" class="other-message">
                    {{ msg.text }}
                  </el-text>
                </div>
                <span></span>
              </div>
              <!-- My Message-->
              <div class="message-bubble"
                   v-else>
                <span></span>
                <div>
                  <div style="display: flex; flex-flow: column nowrap; align-content: end;">
                    <el-text style="align-self: end;">{{ msg.sender }}</el-text>
                    <el-text style="align-self: end;" class="my-message">
                      {{ msg.text }}
                    </el-text>
                  </div>
                </div>
                <el-avatar
                    class="message-avatar"
                    :src="msg.avatar">
                </el-avatar>
              </div>

            </div>
          </el-scrollbar>
        </div>
      </div>
    </el-row>
    <!--Edit Area-->
    <div class="edit-area">
      <div class="toolbar">
          <el-icon class="effected-icon" :size="25" color="#0BFD" ><Share/></el-icon>
          <el-icon class="effected-icon" :size="25" color="#0BFD" ><Files/></el-icon>
          <el-icon class="effected-icon" :size="25" color="#0BFD" ><Picture/></el-icon>
          <el-icon class="effected-icon" :size="25" color="#0BFD" ><Camera/></el-icon>
          <el-icon class="effected-icon" :size="25" color="#0BFD" ><Location/></el-icon>
          <el-icon class="effected-icon" :size="25" color="#0BFD" ><Setting/></el-icon>
          <el-icon class="effected-icon" :size="25" color="#0BFD" ><Plus/></el-icon>
      </div>

      <el-button class="send-button">Send</el-button>

      <el-input class="message-input" v-model="message_input"
                maxlength="1024" show-word-limit
                placeholder="Input Message to Send..."
                clearable :autosize="{minRows: 1, maxRows: 3}"
                type="textarea" rows=2 />
    </div>

  </div>
</template>

<script>
import {Camera, Files, Location, Picture, Plus, Setting, Share} from "@element-plus/icons-vue";

export default {
  components: {Share, Location, Camera, Picture, Files, Plus, Setting},
  data() {
    return {
      message_input: '',

      session: {
        name: 'Session Name'
      },
      messages: [
        {sender: 'me', text: 'Hello! ', avatar: 'src/assets/images/avatar-purple.png'},
        {sender: 'other', text: 'Hi there! ', avatar: 'src/assets/images/avatar-yellow.png'},
        {sender: 'other', text: 'Hi there! ', avatar: 'src/assets/images/avatar-yellow.png'},
        {sender: 'other', text: 'Hi there! ', avatar: 'src/assets/images/avatar-yellow.png'},
        {sender: 'me', text: 'Hello! ', avatar: 'src/assets/images/avatar-purple.png'},
        {sender: 'other', text: 'Hi there! ', avatar: 'src/assets/images/avatar-yellow.png'},
        {sender: 'me', text: 'Hello! ', avatar: 'src/assets/images/avatar-purple.png'},
        {sender: 'other', text: 'Hi there! ', avatar: 'src/assets/images/avatar-yellow.png'},
        {sender: 'other', text: 'Hi there! ', avatar: 'src/assets/images/avatar-yellow.png'},
        {
          sender: 'other',
          text: 'test test test test test testtest test testtest test testtest test ' +
              'testtest test testtest test testtest test test' +
              'test test testtest test testtest test testtest test test' +
              'test test testtest test testtest test testtest test test' +
              'test test testtest test testtest test test! ',
          avatar: 'src/assets/images/avatar-yellow.png'
        },
        {sender: 'me', text: 'Hello! ', avatar: 'src/assets/images/avatar-purple.png'},
        {sender: 'other', text: 'Hi there! ', avatar: 'src/assets/images/avatar-yellow.png'},
        {sender: 'me', text: 'Hello! ', avatar: 'src/assets/images/avatar-purple.png'},
        {sender: 'me', text: 'Hello! ', avatar: 'src/assets/images/avatar-purple.png'},
        {sender: 'me', text: 'Hello! ', avatar: 'src/assets/images/avatar-purple.png'},
        {sender: 'other', text: 'Hi there! ', avatar: 'src/assets/images/avatar-yellow.png'},

      ],
    };
  },
  methods: {
    sendMessage() {
    }
  }
};
</script>

<style scoped>

.chat-outer-panel {
  display: flex;
  flex-direction: column;
  height: 100vh;
  width: 50%;
  position: absolute;
  background-color: transparent;
}

.chat-outer-panel::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url('../../assets/images/miku-pink2.jpg');
  background-size: cover;
  background-position: center;
  z-index: -1;
}

.title-container {
  background-color: rgba(72, 178, 224, 0.41);
  box-shadow: 0 0 10px rgba(32, 181, 236, 0.5);
}

.conversation-title {
  font-size: 1rem;
  font-weight: bold;
  height: 1em;
  margin: 0.5em 0 0.5em 0;
}

.chat-inner-panel {
  width: 100%;
  height: 100%;
  background-color: #eee2;
  border-color: transparent;
  align-self: center;
}

.scrollbar-container {
  align-self: center;
  width: 100%;
  height: 60vh;
}

.message-bubble {
  display: grid;
  padding: 0.5em;
  grid-template-columns: 1fr 14fr 1fr;
}

.message-bubble:hover {
  background-color: #fff4;
  border-radius: 5px;
  box-shadow: 0 0 5px #fff4;
}

.message-avatar {
  border: 2px solid #09fa;
}

.message-avatar:hover {
  box-shadow: 0 0 20px #09fa;
  transition: all 0.3s ease-in-out;
}

.message-bubble:hover .el-text {
  --el-text-color: #222d;
  border-color: #fffd;
  transition: all 0.3s ease-in-out;
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

.send-button{
  margin: 10px;
  flex-shrink: 2;
  flex-basis: 2rem;
}

.message-input {
  font-size: 1rem;
  align-self: start;
  justify-self: start;
  opacity: 0.8;
  margin-left: 1rem;
}

.toolbar {
  display: grid;
  grid-template-columns: repeat(10, 1fr);
  place-content: center;
}

</style>
