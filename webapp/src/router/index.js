// 该文件专门用于创建整个应用的路由器
import VueRouter from "vue-router";
import Login from "@/pages/Login.vue";
import axios from "axios";
import Vue from "vue";
import Home from "@/components/Home.vue";

const router = new VueRouter({
	routes: [
		{
			name: 'login',
			path: '/login',
			component: Login,
			meta: {title: '登录', needAuth: false},
		},
		{
			name: 'home',
			path: '/home',
			component: Home,
			meta: {title: '首页', needAuth: true},
		}
	],
});

// 全局前置路由守卫
router.beforeEach((to, from, next) => {
	next();
});

// 全局后置路由守卫
router.afterEach((to, from) => {
	document.title = to.meta.title;
})
export default router;