export default {
  application: 'Chatroom',
  home_page: 'Home Page',
  welcome: 'Welcome to @:application !',
  language: {
    title: 'Language',
    current: 'English',
    select: 'Select the @:language.title'
  },
  auth: {
    account: 'Account',
    password: 'Password',
    verification: 'Verification Code',
    login: 'Login',
    logout: 'Logout',
    sign_up: 'Sign Up',
    sign_in: 'Sign In',
    sign_off: 'Sign Off'
  },
  profile: {
    name: 'Name',
    avatar: 'Avatar',
    gender: 'Gender',
    address: 'Address',
    email: 'Email',
    telephone: 'Telephone',
    signature: 'Signature'
  },

  sidebar: {
    home: 'Home',
    profile: 'Profile',
    chat: 'Chat',
    list: 'List',
    groups: 'Groups',
    friends: 'Friends',
    about: 'About',
    setting: 'Setting'
  },

  chat: {
    title: 'Chat',
    new_category: 'New Category',
    list_management: 'List Management',

    delete_tip: 'Are you confirmed to delete {}? ',
    new_category_tip: 'A new category @({}) is added.',
    delete_warning_title: 'Warning'
  },

  buttons: {
    send: 'Send',
    delete: 'Delete',
    edit: 'Edit',
  },

  setting: {
    title: 'Setting',

    my_account: 'My Account',
    edit: 'Edit',

    theme_and_background: 'Theme&Background',
    theme_color: 'Theme Color',
    background_image: 'Background Image',

    language: 'Language',
    English: 'English',
    Chinese: 'Chinese',
    Japanese: 'Japanese',

    logout: 'Logout'

  },

  about: {
    title: 'About This Project',
    p1: 'This a project for learning. ',
    h_group_info: 'Group Information.',
    members: {
      zhl: 'Zheng Hailin',
      zmj: 'Zhong Minjie',

      zxj: 'Zhong Xiaojun',
      zpy: 'Zhou Pengyu',
    }
    ,
    p_group_info: 'Members: ' +
        '@:about.members.zhl ' +
        '@:about.members.zmj ' +
        '@:about.members.zxj ' +
        '@:about.members.zpy ',
    b_github: 'Github Repo: ',
    github_link: 'https://github.com/Minjie2003/Chatroom',
    b_email: 'Email: ',
  }
}
