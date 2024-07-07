//这是用来存储某些组件的显示的
export default {
  namespaced: true,
  state: {
    isEdit: false,
    adminFlag: 1 // 显示Admin编辑的状态
  },
  mutations: {
    setIsEdit(state, isEdit) {
      state.isEdit = isEdit;
    },
    setAdminFlag(state, adminFlag) {
      state.adminFlag = adminFlag;
    }
  },
  actions: {
    updateIsEdit({ commit }, isEdit) {
      commit('setIsEdit', isEdit);
    },
    updateAdminFlag({ commit }, adminFlag) {
      commit('setAdminFlag', adminFlag);
    }
  },
  getters: {
    getIsEdit(state) {
      return state.isEdit;
    },
    getAdminFlag(state) {
      return state.adminFlag;
    }
  }
};

  