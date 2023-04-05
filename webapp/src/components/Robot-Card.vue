<template>
<div class="card">
	<p class="title">{{robot.robotId}}</p>
	
	<el-popover
		placement="top-start"
		title="设备电量"
		width="200"
		trigger="hover"
		:content="'当前设备剩余电量:' + robot.battery + '%'"
	>
		<el-progress
			type="circle"
			:percentage="robot.battery"
			slot="reference"
		></el-progress>
	</el-popover>
	
	<div>
		<span class="footer">状态：{{statusStr}}</span>
		<button @click="showDetail(robot)">详情</button>
	</div>
</div>
</template>

<script>
export default {
	name: "Robot-Card",
	props: {robot: Object, showDetail: Function},
	computed: {
		statusStr() {
			if(this.robot.status === 0) {
				return "停止"
			}
			else {
				return "运行中"
			}
		}
	},
}
</script>

<style scoped>
.card {
	margin-top: 30px;
	margin-left: 20px;
	width: 150px;
	height: 200px;
	background-color: lightgreen;
	display: inline-block;
	border: #ccc 2px solid;
	border-radius: 6px;
}
.title, .footer {
	margin: 5px 0;
}
.title {
	font-family: Consolas;
	font-size: 16px;
}
.footer {
	font-family: 楷体;
	font-size: 16px;
}
button {
	width: 40px;
	height: 26px;
	margin-left: 5px;
	font-family: "宋体";
	font-size: 12px;
	background-color: lightgoldenrodyellow;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}
button:hover {
	font-size: 14px;
}
.el-progress {
	cursor: pointer;
}
</style>