import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import VueBMap from 'vue-bmap-gl';
import 'vue-bmap-gl/dist/style.css'
import VueMapvgl from 'vue-mapvgl';
import Cookies from 'js-cookie'
// import compressFile from '@/utils/compressFile';

router.beforeEach((to, from, next) => {
  if (to.matched.some((record) => record.meta.requiresAuth)) {
    let uid = Cookies.get('uid')
    // 再设置一个，然后看路径，决定让不让跳
    if (!uid) {
      next({
        path: '/',
        query: { redirect: to.fullPath },
      })
    } else {
      next()
    }
  } else {
    next()
  }
})

// Vue.prototype.$compressFile = compressFile;
Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.use(VueBMap);
Vue.use(VueMapvgl);
VueBMap.initBMapApiLoader({
  ak: 'mjYb4195uq4M9MG1TORK5a65ZLFeFeDC',
  v: '1.0'
});
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
