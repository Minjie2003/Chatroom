import { createStore } from 'vuex';
import createPersistedState from "vuex-persistedstate";
import flag from './modules/flag';
import myinfos from './modules/myinfos'
import searchinfos from './modules/searchinfos';

const store = createStore({
  modules: {
    flag,myinfos,searchinfos
  },
  plugins: [
    createPersistedState({
      storage: window.sessionStorage,
      reducer(val) {
        return {
          myinfos:val.myinfos// 保留 myinfos 模块的状态
        };
      },
      logErrors: true // 输出错误日志以便调试
    })
  ]
});

export default store;
