<template>
	<el-menu
		default-active="1"
		class="el-menu-vertical-demo"
		background-color="#545c64"
		text-color="#fff"
		active-text-color="#ffd04b">
		
		<el-menu-item index="1" @click="routeTo('userManage')">
			<i class="el-icon-user-solid"></i>
			<span slot="title">用户管理</span>
		</el-menu-item>
		<el-menu-item index="2" @click="routeTo('deviceManage')">
			<i class="el-icon-cpu"></i>
			<span slot="title">设备概览</span>
		</el-menu-item>
		<el-menu-item index="3" @click="routeTo('inspectionTask')">
			<i class="el-icon-document"></i>
			<span slot="title">巡检计划</span>
		</el-menu-item>
		<el-menu-item index="4" @click="routeTo('realTimeInfo')">
			<i class="el-icon-s-grid"></i>
			<span slot="title">实时信息</span>
		</el-menu-item>
		<el-menu-item index="5" @click="routeTo('sensorData')">
			<i class="el-icon-s-data"></i>
			<span slot="title">传感器数据</span>
		</el-menu-item>
	</el-menu>
</template>

<script>
export default {
	name: "Nav-Menu",
	methods: {
		routeTo(routeName) {
			this.$bus.$emit('routeTo', routeName);
		}
	}
}
</script>

<style scoped>
.el-menu {
	height: 100%;
}
</style>