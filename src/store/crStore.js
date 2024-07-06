import {reactive, ref} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";

export const CR_DEBUG_ON = ref(false);

export const crConv = {
  category: null,
  contactId: null,
  createTime: null,
  groupId: null,
  headPath: null,
  id: 0,
  isDeleted: null,
  isHide: null,
  lastContent: null,
  messageType: null,
  nickName: null,
  sessionId: null,
  unreadNum: null,
  updateTime: null,
  userId: null,
  username: null
};

export const DefaultUserData = () => ({
  accountNum: 'user0000',
  birthday: '2099-00-00',
  createTime: '2099-00-03T18:03:39',
  id: 16,
  location: '中国',
  mail: 'example@example.com',
  password: '123',
  photo: 'src/assets/images/avatar-default.jpg',
  sex: '未知',
  state: 2,
  updateTime: null,
  username: '新用户'
});

export const DefaultProfileData = () => ({
  avatar_url: 'src/assets/images/avatar-yellow.png',
  username: '新用户',
  accountNum: 'user0000',
  sex: '未知',
  mail: 'example@example.com',
  location: '未知',
  birthday: '未知',
  createTime: null,
  signature: 'This is a default signature.',
  backgroundImage: 'src/assets/images/lake.png'
});


export const Chatroom = reactive({
  NAME: 'Chatroom',
  SHORT_NAME: 'CR',
  LOGO: 'src/assets/images/chatroom_logo.png'
})

export const thisUser = ref({
  ...DefaultProfileData(),
  ...DefaultUserData(),
})

export const selectedOtherInfo = ref({
  ...DefaultProfileData(),
  ...DefaultUserData()
})


export const conversation_list = ref([])

export const current_conv_msg_list = ref([])
export const user_contact_lists = ref([])

export const selected_list_items = ref([])

export const selected_list = ref(null)


/* UserController.java:
*  user/
*       /logout
*       /get_permit
*       /update_userinfo
*       /update_head
*       /select_user
*      /get_contact_info
*      /get_user_info
*     /....
*
* */

export const getUserInfo = () => {
  axios.post('my_chatroom/user/get_userinfo')
      .then(res => {
        let code = res.data.code
        if (code === 200) {
          thisUser.value = res.data.data
          ElMessage.success('fetchUserInfo')
        } else {
          ElMessage.warning(res.data.message)
        }
      })
      .catch(err => console.log(err))
}

export const toProfileData = (userInfo) => {
  return {
    ...DefaultProfileData(),
    ...userInfo
  }
}


export const modifyProfileDialogVisible = ref(false)


export const crStore = reactive({

  hasLoggedIn: false,
  setHasLoggedIn(val) {
    this.hasLoggedIn = val
  },
  getHasLoggedIn() {
    return this.hasLoggedIn
  },

  userInfo: {
    ...DefaultUserData()
  },
  getUserInfo() {
    return this.userInfo
  },
  getUserProfile() {
    return {
      ...DefaultProfileData(),
      ...this.userInfo
    }
  },

  setUserInfo(info) {
    this.userInfo = {
      ...this.userInfo,
      ...info
    }
  },

  conversationList: [],
  setConversationList(list) {
    this.conversationList = list
  },

  selectedConversation: null,
  setSelectedConversation(conversation) {
    this.selectedConversation = conversation
  },
  /*Current Conversation or Selected OtherInfo*/
  otherInfo: null,
  setOtherInfo(info) {
    this.otherInfo = info
  },

  messageList: [],
  setMessageList(list) {
    this.messageList = list
  },
  appendMessages(messages) {
    this.messageList.push(messages)
  },

})





