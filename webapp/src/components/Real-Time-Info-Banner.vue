<template>
<div class="real-time-info-banner">
	<span>传送带:</span>
	<el-select
		v-model="belt.beltId"
		placeholder="请选择"
		size="small"
		@change="onBeltChanged">
		<el-option
			v-for="(belt, index) in belts"
			:key="index"
			:label="belt.beltId"
			:value="belt.beltId">
		</el-option>
	</el-select>
	<span v-show="moreInfoVisiable">{{ moreInfo }}</span>
</div>
</template>

<script>
import axios from "axios";

export default {
	name: "Real-Time-Info-Banner",
	data() {
		return {
			moreInfoVisiable: false,
			belt: {},
			task: null,
			robot: {}
		}
	},
	methods: {
		onBeltChanged(beltId) {
			axios.get('/task/selectByBeltId', {params: {beltId}}).then(
				resp => {
					this.task = resp.data;
				},
				error => {
					this.$message.error(error.message);
				}
			);
			this.moreInfoVisiable = true;
		},
		updateRobot() {
			axios.get('robot/selectByRobotId', {params: {robotId: this.task.robotId}}).then(
				resp => {
					this.robot = resp.data;
				},
				error => {
					this.$message.error(error.message);
				}
			);
		}
	},
	mounted() {
		this.$store.dispatch('DeviceOptions/updateBelt');
		this.$store.dispatch('DeviceOptions/updateRobot');
	},
	computed: {
		belts() {
			return this.$store.state.DeviceOptions.belts;
		},
		robots() {
			return this.$store.state.DeviceOptions.robots;
		},
		moreInfo() {
			let info = '';
			if(this.task) {
				info = this.task.robotId + ' 巡检中; 剩余电量: ';
				this.updateRobot();
				info += this.robot.battery + '%';
			}
			else {
				info = '未运行';
			}
			return info;
		}
	}
}
</script>

<style scoped>
.real-time-info-banner {
	margin: 15px 10px 0 10px;
	/*background-color: #eee;*/
	border-bottom: #ccc 1px solid;
}
span {
	margin-left: 10px;
	font-family: 宋体;
	font-size: 16px;
}
</style>