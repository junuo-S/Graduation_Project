// 该文件专门用于创建整个应用的路由器
import VueRouter from "vue-router";
import Login from "@/pages/Login.vue";

const router = new VueRouter({
	routes: [
		{
			name: 'login',
			path: '/login',
			component: Login,
			meta: {title: '登录', needAuth: false},
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