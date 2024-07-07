// searchinfos.js
export default {
    namespaced: true,
    state: {
      searchinfos: [] // 初始化为空数组
    },
    mutations: {
      setSearchInfos(state, searchinfos) {
        state.searchinfos = searchinfos;
      }
    },
    actions: {
      updateSearchInfos({ commit }, newInfos) {
        commit('setSearchInfos', newInfos);
      }
    },
    getters: {
      getSearchInfos(state) {
        return state.searchinfos;
      }
    }
  };
  