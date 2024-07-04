export default  store = reactive({

    demo_user: {
      name: 'DemoUser',
      id: 'demo_user@cr.chat',
      signature: 'Demo User for Test. ',
      avatar_url: 'src/assets/images/avatar-yellow.png',
    },

    users: [{
      name: 'Tom',
      id: 'tomlikeschatting',
      signature: 'Tom likes chatting. ',
      avatar_url: 'src/assets/images/avatar-yellow.png',
    }, {
      name: 'Alice',
      id: 'alicelikessleeping',
      signature: 'alice.likes.sleeping. ',
      avatar_url: 'src/assets/images/avatar-purple.png',
    },{
      name: 'Notify',
      id: 'notify_sample_user',
      signature: 'notify-sample-user ',
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
      },{
        name: 'custom', id: '3'
      },{
        name: 'classmates', id: '2fa'
      },{
        name: 'workmates', id: '1fa'
      },{
        name: 'teachers', id: 'fhh'
      },

    ]
  })
