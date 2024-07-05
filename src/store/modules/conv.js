import {reactive, ref} from "vue";

/*
category
contactId
createTime
groupId
headPath
id
isDeleted
isHide
lastContent
messageType
nickName
sessionId
unreadNum
updateTime
userId
username
*/
/* annotation
*  Current Conversation List
*  Conversation Attributes
*
* */
export const crConv = {
  category: null,
  contactId: null,
  createTime: null,
  groupId: null,
  headPath: null,
  id: null,
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

export const selected_session = ref(null)

export const current_conversation_list = reactive([])
