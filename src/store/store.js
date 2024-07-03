export default {
  users: [{
    name: 'Tom',
    id: 'tomlikeschatting',
    signature: 'Tom likes chatting. ',
    avatar_url: 'src/assets/images/avatar-yellow.png',
  }, {
    name: 'Alice',
    id: 'alicelikessleeping',
    signature: 'Alice likes sleeping. ',
    avatar_url: 'src/assets/images/avatar-purple.png',
  }],
  groups: [{
    name: 'VueLean', id: '999888', avatar_url: 'src/assets/images/chatroom_logo.png'
  }, {
    name: 'SpringBoot', id: '12345', avatar_url: 'src/assets/images/chatroom_logo.png'
  }, {
    name: 'Maven', id: '987789', avatar_url: 'src/assets/images/chatroom_logo.png'
  },],

  reports: [{
    sender: 'Tom',
    other: 'Alice',
    description: 'Illegal Speech'
  }],

  categories: [
    {
      name: 'friends', id: '1'
    },{
      name: 'groups', id: '2'
    },

  ]
}

