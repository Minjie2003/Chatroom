import { createStore } from 'vuex';
import createPersistedState from "vuex-persistedstate";
import myinfos from './modules/myinfos';
import flag from './modules/flag';


const store = createStore({
  plugins: [
    createPersistedState({
      storage: window.sessionStorage, // 输出错误日志以便调试
      logErrors: true,
      reducer(val) {
        return {
          myinfos: val.myinfos // 保留 myinfos 模块的状态
        };
      }
    })
  ],
  modules: {
    myinfos,
    flag
  }
});

export default store;
