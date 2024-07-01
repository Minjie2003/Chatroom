<template>
  <el-card class="chat-panel" ref="chatPanel">
    <div class="chat-header" @mousedown="startDrag">
      <h2>Chat</h2>
    </div>
    <div class="chat-messages" ref="chatMessages">
      <div v-for="(message, index) in messages" :key="index" class="message">
        <div :class="['message-content', message.sender === 'me' ? 'my-message' : 'other-message']">
          <el-avatar :src="message.avatar" />
          <div class="text">{{ message.text }}</div>
        </div>
      </div>
    </div>
    <el-input
        v-model="newMessage"
        placeholder="Type a message..."
        @keyup.enter="sendMessage"
        class="chat-input"
    >
      <template #append>
        <el-button @click="sendMessage">Send</el-button>
      </template>
    </el-input>
  </el-card>
</template>

<script>
export default {
  data() {
    return {
      messages: [
        { sender: 'me', text: 'Hello!', avatar: 'path/to/my/avatar.jpg' },
        { sender: 'other', text: 'Hi there!', avatar: 'path/to/other/avatar.jpg' }
      ],
      newMessage: '',
      isDragging: false,
      dragStartX: 0,
      dragStartY: 0,
      panelStartX: 0,
      panelStartY: 0
    };
  },
  methods: {
    sendMessage() {
      if (this.newMessage.trim() !== '') {
        this.messages.push({ sender: 'me', text: this.newMessage, avatar: 'path/to/my/avatar.jpg' });
        this.newMessage = '';
        this.scrollToBottom();
      }
    },
    scrollToBottom() {
      this.$nextTick(() => {
        const chatMessages = this.$refs.chatMessages;
        chatMessages.scrollTop = chatMessages.scrollHeight;
      });
    },
    startDrag(event) {
      this.isDragging = true;
      this.dragStartX = event.clientX;
      this.dragStartY = event.clientY;
      const panelRect = this.$refs.chatPanel.getBoundingClientRect();
      this.panelStartX = panelRect.left;
      this.panelStartY = panelRect.top;
      document.addEventListener('mousemove', this.onDrag);
      document.addEventListener('mouseup', this.stopDrag);
    },
    onDrag(event) {
      if (this.isDragging) {
        const deltaX = event.clientX - this.dragStartX;
        const deltaY = event.clientY - this.dragStartY;
        this.$refs.chatPanel.style.transform = `translate(${this.panelStartX + deltaX}px, ${this.panelStartY + deltaY}px)`;
      }
    },
    stopDrag() {
      this.isDragging = false;
      document.removeEventListener('mousemove', this.onDrag);
      document.removeEventListener('mouseup', this.stopDrag);
    }
  },
  mounted() {
    this.scrollToBottom();
  }
};
</script>

<style scoped>
.chat-panel {
  display: flex;
  flex-direction: column;
  height: 500px;
  width: 400px;
  position: absolute;
  background-color: transparent;
  cursor: move; /* Indicate draggable area */
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

.chat-header {
  background-color: rgba(255, 255, 255, 0.8);
  padding: 10px;
  text-align: center;
  z-index: 1;
  cursor: move; /* Indicate draggable area */
}

.chat-messages {
  flex: 1;
  overflow-y: auto;
  padding: 10px;
  background-color: rgba(255, 255, 255, 0.8);
  border-bottom: 1px solid #eaeaea;
  z-index: 1;
}

.message {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.message-content {
  display: flex;
  align-items: center;
}

.my-message .text {
  background-color: #dcf8c6;
  margin-left: 10px;
}

.other-message .text {
  background-color: #fff;
  margin-right: 10px;
}

.text {
  padding: 10px;
  border-radius: 5px;
  max-width: 70%;
}

.chat-input {
  padding: 10px;
  border-top: 1px solid #eaeaea;
  background-color: rgba(255, 255, 255, 0.8);
  z-index: 1;
}
</style>
