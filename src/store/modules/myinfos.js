export default {
  namespaced: true,
  state: {
    myinfos: {
      accountNum: "",
      birthday: "",
      createTime: "",
      id: null,
      location: "",
      mail: "",
      password: "",
      photo: "",
      sex: "",
      state: null,
      updateTime: null,
      username: ""
    }
  },
  mutations: {
    setMyInfos(state, myinfos) {
      state.myinfos = myinfos;
    }
  },
  actions: {
    updateMyInfos({ commit }, newInfos) {
      commit('setMyInfos', newInfos);
    }
  },
  getters: {
    getMyInfos(state) {
      return state.myinfos;
    }
  }
};
