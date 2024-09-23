import Vue from 'vue';

Vue.config.productionTip = false;
Vue.config.devtools = false;

const eventBus = new Vue();
export default eventBus;