// 该文件专门用于创建整个应用的路由器
import VueRouter from "vue-router";
import Login from "@/pages/Login.vue";
import Home from "@/components/Home.vue";
import UserManage from "@/pages/User-Manage.vue";
import DeviceManage from "@/pages/Device-Manage.vue";
import InspectionTask from "@/pages/Inspection-Task.vue";
import RealTimeInfo from "@/pages/Real-Time-Info.vue";

const router = new VueRouter({
	base: 'graduation_app',
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
			children: [
				{
					name: 'userManage',
					path: 'userManage',
					component: UserManage,
					meta: {title: '用户管理', needAuth: true},
				},
				{
					name: 'deviceManage',
					path: 'deviceManage',
					component: DeviceManage,
					meta: {title: '设备概览', needAuth: true},
				},
				{
					name: 'inspectionTask',
					path: 'inspectionTask',
					component: InspectionTask,
					meta: {title: '巡检计划', needAuth: true},
				},
				{
					name: 'realTimeInfo',
					path: 'realTimeInfo',
					component: RealTimeInfo,
					meta: {title: '实时信息', needAuth: true}
				}
			]
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