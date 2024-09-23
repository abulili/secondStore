import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate";
Vue.use(Vuex)

export default {
    state: {
        SerachList: [],
        storeDetail: {},
        Store0: {},
        saleView: {},
        isLogin: 0,
        userInfo: {},
        active: 1
    },
    getters: {

    },
    mutations: {
        setStore0(state, payload) {
            state.Store0 = payload;
        },
        setActive(state, payload) {
            state.active = payload;
        },
        setSerachList(state, newList) {
            state.SerachList = newList;
        },
        setStoreDetail(state, payload) {
            state.storeDetail = payload;
        },
        setSaleView(state, payload) {
            state.saleView = payload;
        },
        setIsLogin(state, payload) {
            state.isLogin = payload;
        },
        setUserInfo(state, payload) {
            state.userInfo = payload;
        },
    },
    actions: {
    },
    modules: {
    },
    // plugins: [createPersistedState({
    //     storage: window.localStorage,
    //     reducer(options) {
    //         return {
    //             state, mutations
    //         }
    //     },
    //     onError: (error) => {
    //         console.error('Vuex persisted state error:', error);
    //     },
    // }
    // )],
}
