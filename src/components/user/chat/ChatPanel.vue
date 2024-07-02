<template>
  <el-card class="chat-panel" ref="chatPanel">
    <el-row justify="center">
      <span>
          <el-text class="session-title" size="large">
            {{ session.name }}
          </el-text>
      </span>
    </el-row>
    <el-row>
      <el-card class="chat-box">
        <el-scrollbar style="overflow: visible" height="400px">
          <div v-for="msg in messages">
            <el-row
                v-if="msg.sender==='other'"
                justify="center">
              <el-col
                  :span="2">
                <el-avatar
                    class="message-avatar"
                    size="small"
                    :src="msg.avatar">
                </el-avatar>
              </el-col>

              <el-col :span="18">
                <el-row>
                  <el-text>{{ msg.sender }}</el-text>
                </el-row>
                <el-row>
                  <el-text class="other-message">
                    {{ msg.text }}
                  </el-text>
                </el-row>
              </el-col>
            </el-row>

            <el-row v-else justify="center">
              <el-col :span="18">
                <el-row justify="end">
                  <el-text>{{ msg.sender }}</el-text>
                </el-row>
                <el-row justify="end">
                  <el-text class="my-message">
                    {{ msg.text }}
                  </el-text>
                </el-row>
              </el-col>

              <el-col
                  :span="2">
                <el-avatar
                    class="message-avatar"
                    size="small"
                    :src="msg.avatar">
                </el-avatar>
              </el-col>
            </el-row>

          </div>
        </el-scrollbar>
      </el-card>
    </el-row>
  </el-card>
</template>

<script>
export default {
  data() {
    return {
      session: {
        name: 'Session Name'
      },
      messages: [
        {sender: 'me', text: 'Hello! ', avatar: 'src/assets/images/avatar-purple.png'},
        {sender: 'other', text: 'Hi there! ', avatar: 'src/assets/images/avatar-yellow.png'},
        {sender: 'other', text: 'Hi there! ', avatar: 'src/assets/images/avatar-yellow.png'},
        {sender: 'other', text: 'Hi there! ', avatar: 'src/assets/images/avatar-yellow.png'},
        {sender: 'other', text: 'Hi there! ', avatar: 'src/assets/images/avatar-yellow.png'},
        {sender: 'other', text: 'Hi there! ', avatar: 'src/assets/images/avatar-yellow.png'},
        {sender: 'other', text: 'Hi there! ', avatar: 'src/assets/images/avatar-yellow.png'},
        {sender: 'other', text: 'Hi there! ', avatar: 'src/assets/images/avatar-yellow.png'},
        {sender: 'other', text: 'Hi there! ', avatar: 'src/assets/images/avatar-yellow.png'},
        {sender: 'other', text: 'Hi there! ', avatar: 'src/assets/images/avatar-yellow.png'},
        {sender: 'other', text: 'Hi there! ', avatar: 'src/assets/images/avatar-yellow.png'},
        {sender: 'other', text: 'Hi there! ', avatar: 'src/assets/images/avatar-yellow.png'},
        {sender: 'other', text: 'Hi there! ', avatar: 'src/assets/images/avatar-yellow.png'},
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
.chat-panel {
  display: flex;
  flex-direction: column;
  height: 400px;
  width: 50vw;
  position: absolute;
  background-color: transparent;
}

.chat-panel::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url('../../../assets/images/ignored/miku-pink.jpg');
  background-size: cover;
  background-position: center;
  z-index: -1;
}

.session-title {
  font-size: 16px;
  font-weight: bold;
}

.chat-box {
  width: 90vw;
  height: 480px;
  background-color: #eee2;
  border-color: transparent;
}

.message-avatar {
  border: 1px solid #09f9;
}

.message-avatar:hover {
  box-shadow: 0 0 5px #09f8;
}

.other-message {
  background-color: rgba(133, 198, 243, 0.77);
  border-radius: 1em;
  padding-left: 1em;
  padding-right: 1em;
  margin-bottom: 0.5em;
  border: 1px solid #fff6;
}

.my-message {
  background-color: rgba(133, 243, 164, 0.77);
  border-radius: 1em;
  padding-left: 1em;
  padding-right: 1em;
  margin-bottom: 0.5em;
  border: 1px solid #fff6;
}
</style>
