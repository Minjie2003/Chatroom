import {reactive, ref} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";

export const CR_DEBUG_ON = ref(false);

export const Chatroom = reactive({
  NAME: 'Chatroom',
  SHORT_NAME: 'CR',
  LOGO: 'src/assets/images/chatroom_logo.png'
})

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



export const getUserInfo = () => {
  axios.post('my_chatroom/user/get_userinfo')
      .then(res => {
        thisUser.value = res.data.data
        if(CR_DEBUG_ON) {ElMessage.warning('Updated User Info.')}
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


/*  contact_session
*
*
*
* */




