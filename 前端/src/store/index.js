import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate";
import users from "./moudule/store"
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  getters: {
  },
  mutations: {

  },
  actions: {
  },
  modules: {
    users
  },
  plugins: [
    createPersistedState({
      key: 'key', // 添加此行，将 'your_key_name' 替换为您希望使用的键名
      storage: window.localStorage,
      reducer: (state) => ({
        users: state.users
      }),
      onError: (error) => {
        console.error('Vuex persisted state error:', error);
      },
    })
  ]
})
