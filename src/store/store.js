import { createStore } from 'vuex';
import createPersistedState from "vuex-persistedstate";
import myinfos from './modules/myinfos';
import flag from './modules/flag';

const store = createStore({
  modules: {
    myinfos,
    flag
  },
  plugins: [
    createPersistedState({
      storage: window.sessionStorage,
      reducer(val) {
        return {
          myinfos: val.myinfos // 保留 myinfos 模块的状态
        };
      },
      logErrors: true // 输出错误日志以便调试
    })
  ]
});

export default store;
