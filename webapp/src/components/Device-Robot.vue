<template>
	<el-row class="device-robot">
		<DeviceBanner name="机器人"/>
		<div class="robot-display">
			<RobotCard
				v-for="(robot, index) in robots"
				:robot="robot"
				:showDetail="showDetail"
				:key="index"
			/>
		</div>
		<el-drawer
			:title="drawer.robotId + '详细信息'"
			append-to-body
			:visible.sync="detailVisiable"
			:before-close="handleClose"
			custom-class="drawer"
		>
			<el-row>
				<span>设备名称:{{drawer.robotId}}</span>
			</el-row>
			<el-row>
				<el-col :span="6">
					<span>剩余电量:</span>
				</el-col>
				<el-col :span="18">
					<el-progress :text-inside="true" :stroke-width="26" :percentage="drawer.battery"></el-progress>
				</el-col>
			</el-row>
			<el-row>
				<span>状态:{{drawer.status | statusStr}}</span>
			</el-row>
			<el-row>
				<button class="btn-task" @click="onCheck">查看巡检任务</button>
			</el-row>
		</el-drawer>
	</el-row>
</template>

<script>
import DeviceBanner from "@/components/Device-Banner.vue";
import RobotCard from "@/components/Robot-Card.vue";
export default {
	name: "Device-Robot",
	components: {RobotCard, DeviceBanner},
	data() {
		return {
			detailVisiable: false,
			drawer: {}
		}
	},
	computed: {
		robots() {
			return this.$store.state.DeviceOptions.robots;
		}
	},
	methods: {
		handleClose(done) {
			done();
		},
		showDetail(robot) {
			this.drawer = robot;
			this.detailVisiable = true;
		},
		onCheck() {
			this.$router.push({name: 'realTimeInfo'});
		}
	},
	filters: {
		statusStr(status) {
			if(status === 0) {
				return "停止";
			}
			else {
				return "运行中";
			}
		}
	},
	mounted() {
		this.$store.dispatch('DeviceOptions/updateRobot');
	}
}
</script>

<style scoped>
.robot-display {
	text-align: center;
}
.drawer .el-row {
	display: block;
	margin: 50px 25px;
	font-family: 楷体;
	font-size: 20px;
}
.btn-task {
	width: 100%;
	height: 35px;
	font-family: 宋体;
	font-size: 18px;
	background-color: lightskyblue;
	border: none;
	border-radius: 8px;
	cursor: pointer;
}
.btn-task:hover {
	font-size: 20px;
}
</style>
