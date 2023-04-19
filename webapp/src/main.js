import Vue from 'vue'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui';
import axios from "axios";
import 'element-ui/lib/theme-chalk/index.css';

import router from './router'
import store from "@/store";
import App from './App.vue'

Vue.config.productionTip = false;
// 应用插件之后就可以配置一个新的配置项，router
Vue.use(VueRouter);
Vue.use(ElementUI);

axios.defaults.baseURL = "http://localhost:9521/webservice_war";
// axios.defaults.baseURL = "http://www.junuo.club:8080/webservice";

new Vue({
	render: h => h(App),
	router,
	store,
	beforeCreate() {
		// 创建全局事件总线
		Vue.prototype.$bus = this;
	}
}).$mount('#app')