// getUserMyInfos.js
// getUserInfos.js
import axios from 'axios';

const getAdminInfos = (store) => {
  if (!store) {
    console.error('Store is undefined');
    return;
  }
  
  axios.get("/my_chatroom/user/get_userinfo")
    .then(res => {
      store.dispatch('myinfos/updateMyInfos', res.data.data);
    }).catch(err => {
      console.error(err); // 打印错误信息
    });
};

export default getAdminInfos;

