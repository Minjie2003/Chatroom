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
  avatar_url: 'src/assets/images/avatar-default.jpg',
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

export const refreshUserInfo = async () => {
  try {
    const res = await axios.post('my_chatroom/user/get_userinfo');
    const code = res.data.code;
    if (code === 200) {
      crStore.setUserInfo(res.data.data);
      ElMessage.success('fetchUserInfo Success');
      return true;
    } else {
      ElMessage.warning(res.data.message);
      return false;
    }
  } catch (err) {
    console.error(err);
    ElMessage.error('An error occurred while fetching user info');
    return false;
  }
};

export const toProfileData = (userInfo) => {
  return {
    ...DefaultProfileData(),
    ...userInfo
  }
}


export const modifyProfileDialogVisible = ref(false)

export const CR_Constant = {
  CHATROOM: 0,
  FRIEND: 1,

  FRIEND_REQUEST: 0,
  CHATROOM_REQUEST: 1,
  WARNING: 3,
  REPORT: 4,

  PENDING: 0,
  ACCEPT: 2,
  REFUSE: 1,

}

export const crStore = reactive({


  hasSynchronized: false,
  setHasSynchronized(val) {
    this.hasSynchronized = val;
  },
  getHasSynchronized() {
    return this.hasSynchronized;
  },

  async getHasLoggedIn() {
    return await refreshUserInfo();
  },

  isAdmin: false,
  setIsAdmin(val) {
    this.isAdmin = val
  },
  getIsAdmin() {
    return this.isAdmin
  },

  userInfo: {
    ...DefaultUserData()
  },
  getUserInfo() {
    return {
      ...DefaultProfileData(),
      ...this.userInfo
    };
  },

  setUserInfo(info) {
    this.userInfo = {
      ...this.userInfo,
      ...info
    };
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
    if (Array.isArray(messages)) {
      this.messageList = [...this.messageList, ...messages];
    }
  },

  /* List */
  contactGroupList: [],
  setContactGroupList(list) {
    this.contactGroupList = list
  },

  selectedContactGroup: null,
  setSelectedContactGroup(group) {
    this.selectedContactGroup = group
  },
  selectedContactGroupItems: [],
  setSelectedContactGroupItem(items) {
    this.selectedContactGroupItems = items
  },

  friendRequests: [],
  chatroomRequests: [],
  setRequests(friendRequests, chatroomRequests) {
    this.friendRequests = friendRequests;
    this.chatroomRequests = chatroomRequests;
  },
  getFriendRequests() {
    return this.friendRequests;
  },
  getChatroomRequests() {
    return this.chatroomRequests;
  },
  getRequests() {
    return {
      friendRequests: this.getFriendRequests(),
      chatroomRequests: this.getChatroomRequests()
    };
  },
  getMergedRequests() {
    return [
      ...this.getFriendRequests() ||[],
      ...this.getChatroomRequests() || []
    ];
  },

  async fetchAndStoreRequests() {
    try {
      const [friendAdvice, chatroomAdvice] = await Promise.all([
        axios.get('my_chatroom/advice/get_all_friend_advice'),
        axios.get('my_chatroom/advice/get_all_chatroom_advice')
      ]);
      this.setRequests(friendAdvice.data.data, chatroomAdvice.data.data);
      console.log(this.getMergedRequests())
    } catch (error) {
      console.error('Error fetching requests:', error);
    }
  },

  warningNotifications: [],
  reportNotifications: [],
  setNotifications(warnings, reports){
    this.warningNotifications = warnings;
    this.reportNotifications = reports;
  },
  getMergedNotifications() {
    return [
        ...this.warningNotifications,
        ...this.reportNotifications,
    ];
  },

  async fetchNotifications() {
    try {
      const [warningAdvice, reportAdvice] = await Promise.all([
        axios.get('my_chatroom/advice/get_all_warn_advice'),
        axios.get('my_chatroom/advice/get_all_report_advice')
      ]);
      this.setRequests(warningAdvice.data.data, reportAdvice.data.data);
    } catch (error) {
      console.error('Error fetching notifications:', error);
    }
  },

})





