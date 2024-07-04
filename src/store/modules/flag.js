//这是用来存储某些组件的显示的
export default {
  namespaced: true,
    state: {
      isEdit: false
    },
    mutations: {
      setIsEdit(state, isEdit) {
        state.isEdit = isEdit;
      }
    },
    actions: {
      updateIsEdit({ commit }, isEdit) {
        commit('setIsEdit', isEdit);
      }
    },
    getters: {
      getIsEdit(state) {
        return state.isEdit;
      }
    }
  };
  