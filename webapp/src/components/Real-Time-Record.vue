<template>
<div class="record">
	<div class="controls">
		<span ref="light"></span>
		<p>{{beltStatusStr}}</p>
		<button ref="btn-start-stop" @click="onBtnStartStop">停止</button>
	</div>
	<div class="rot-photo">
		<ul ref="rot-ul" class="rot-ul">
			<li><img src="../asset/belt-show.png" alt=""></li>
			<li><img src="../asset/belt-show.png" alt=""></li>
		</ul>
	</div>
	<div class="more-info">
		<span>机器人: {{robotId}}</span>
		<span>传送带: {{beltId}}</span>
		<button @click="onShowLog">查看检测日志</button>
	</div>
	<div class="footer">
		<ul class="log-ul" ref="log-ul">
			<el-tooltip
				v-for="(record, index) in checkRet"
				class="item"
				effect="light"
				:content="record.bzText"
				placement="top"
				:key="index"
			>
				<li>
					<img src="../asset/tuogun.png" alt="">
					<i class="el-icon-loading"></i>
					<img id="img-ret" :src="statusImgMap[record.status]" alt="">
					<span>{{ record.bzText }}</span>
				</li>
			</el-tooltip>
		</ul>
	</div>
	<div id="robot-box">
		<img id="robot" src="../asset/robot.png" alt="" :style="{left: robotImgLeft+'%'}">
	</div>
	<el-drawer
		title="检测日志"
		:visible.sync="drawer"
		direction="rtl"
		append-to-body
	>
		<div class="drawer">
			<h3 class="drawer-title">{{beltId}}检测日志</h3>
			<div v-for="log in checkLog" class="content">
				<p>机器人：{{log.robotId}}</p>
				<p>检测时间：{{log.time}}</p>
				<p>托辊编号：{{log.roller}}</p>
				<p>检测结果：{{log.ret}}</p>
				<p>剩余电量：{{log.battery}}</p>
			</div>
		</div>
	</el-drawer>
</div>
</template>

<script>
import axios from "axios";
import moment from "moment";

export default {
	name: "Real-Time-Record",
	props: ['robotId', 'beltId'],
	data() {
		return {
			drawer: false,
			rotUlLeft: -893,
			beltSpeed: 0.8,
			beltStatus: 1,
			robotImgLeft: 0,
			isRobotMoving: false,
			isChecking: false,
			currentIndex: 0,
			checkRet: [
				{id: 1, time: 0, robotId: 'robot-1', beltId: 'belt-1', roller: 0, status: 2, bzText: '待检测'},
				{id: 2, time: 0, robotId: 'robot-1', beltId: 'belt-1', roller: 1, status: 2, bzText: '待检测'},
				{id: 3, time: 0, robotId: 'robot-1', beltId: 'belt-1', roller: 2, status: 2, bzText: '待检测'},
				{id: 4, time: 0, robotId: 'robot-1', beltId: 'belt-1', roller: 3, status: 2, bzText: '待检测'},
				{id: 5, time: 0, robotId: 'robot-1', beltId: 'belt-1', roller: 4, status: 2, bzText: '待检测'},
			],
			statusImgMap: {
				'-1': require('@/asset/status-error.png'),
				'0': require('@/asset/status-warning.png'),
				'1': require('@/asset/status-ok.png'),
				'2': require('@/asset/status-not-check.png'),
			},
			watching: false,
			task: {},
			checkLog: [],
		}
	},
	computed: {
		beltStatusStr() {
			return this.beltStatus === 1 ? '运行中' : '停止';
		}
	},
	methods: {
		startBelt() {
			let rotUl = this.$refs['rot-ul'];
			this.beltTimer = setInterval(() => {
				this.rotUlLeft+=this.beltSpeed;
				if(this.rotUlLeft>0)
					this.rotUlLeft=-893;
				rotUl.style.left=this.rotUlLeft+'px';
			}, 10);
		},
		stopBelt() {
			clearInterval(this.beltTimer);
		},
		btnStart() {
			this.beltStatus = 1;
			this.$refs['light'].style.backgroundColor = 'lightgreen';
			this.$refs['btn-start-stop'].innerText='停止';
			this.startBelt();
			this.watching = true;
			this.checkNext();
		},
		btnStop() {
			this.beltStatus = 0;
			this.$refs['light'].style.backgroundColor = 'deeppink';
			this.$refs['btn-start-stop'].innerText='启动';
			this.stopBelt();
			this.stopCheck();
			this.checkLog = [];
		},
		onBtnStartStop() {
			this.beltStatus === 1 ? this.doneTaskAndStart() : this.addTaskAndStart();
		},
		robotImgTo(index) {
			let destination = 20*index;
			let step = destination - this.robotImgLeft;
			this.isRobotMoving = true;
			this.robotTimer = setInterval(() => {
				this.robotImgLeft+=step/160;
				if(Math.abs(this.robotImgLeft - destination) < 0.2) {
					clearInterval(this.robotTimer);
					this.isRobotMoving = false;
				}
			}, 10);
		},
		checkNext() {
			this.isChecking = true;
			this.robotImgTo(this.currentIndex);
		},
		stopAllTimer() {
			clearInterval(this.beltTimer);
			clearInterval(this.robotTimer);
			clearTimeout(this.delayTimer);
		},
		stopCheck() {
			this.stopAllTimer();
			this.watching = false;
			this.$refs['log-ul'].children[this.currentIndex].className = '';
			this.robotImgTo(0);
			this.isChecking = false;
			this.currentIndex = 0;
		},
		getTaskAndStart() {
			axios.get(
				'task/selectByRobotIdAndBeltId',
				{params: {robotId: this.robotId, beltId: this.beltId}}
			).then(
				resp => {
					this.task = resp.data;
				},
				error => {
					this.$message.error(error.message);
				}
			).then(() => {
				if(this.task)
					this.btnStart();
				else
					this.btnStop();
			});
		},
		addTaskAndStart() {
			let time = Math.floor(Date.now() / 1000);
			axios.post('task/addTask', {robotId: this.robotId, beltId: this.beltId, time}).then(
				resp => {
					if(resp.data.statusCode === 1) {
						this.$message.success('已创建任务');
						this.$store.dispatch('TaskOptions/updateTask');
					}
					else {
						this.$message.warning(resp.data.bzText);
					}
				},
				error => {
					this.$message.error(error.message);
				}
			).then(() => {this.getTaskAndStart()});
		},
		doneTaskAndStart() {
			axios.post('task/setDone',
				{id: this.task.id},
				{headers: {'content-type': 'application/x-www-form-urlencoded'}}).then(
				resp => {
					if(resp.data.statusCode === 1) {
						this.$message.success('已结束任务');
						this.$store.dispatch('TaskOptions/updateTask');
					}
					else {
						this.$message.warning(resp.data.bzText);
					}
				},
				error => {
					this.$message.error(error.message);
				}
			).then(() => {this.getTaskAndStart()});
		},
		writeLog(record)  {
			axios.get(
				'robot/getBattery',
				{params: {robotId: this.robotId}}
			).then(
				resp => {
					let time = moment(record.time*1000).format("YYYY-MM-DD hh:mm:ss");
					this.checkLog.push({
						robotId: this.robotId,
						time,
						roller: record.roller,
						ret: record.bzText,
						battery: resp.data
					});
				},
				error => {
					this.$message.error(error.message);
				}
			);
			
		},
		checkOne() {
			axios.get(
				'/record/selectLastByRobotIdAndBeltIdAndRoller',
				{params: {robotId: this.robotId, beltId: this.beltId, roller: this.currentIndex}}
			).then(
				resp => {
					resp.data.id = parseInt(resp.data.id);
					resp.data.time = parseInt(resp.data.time);
					resp.data.roller = parseInt(resp.data.roller);
					this.writeLog(resp.data);
					this.checkRet[this.currentIndex] = resp.data;
				},
				error => {
					this.$message.error(error.message);
					this.checkRet[this.currentIndex].status = 2;
					this.checkRet[this.currentIndex].bzText = '待检测';
				}
			).then(() => {
				this.$refs['log-ul'].children[this.currentIndex].className = '';
				this.isChecking = false;
			});
		},
		onShowLog() {
			if(this.beltStatus === 1)
				this.drawer = true;
			else
				this.$message.info(this.beltId + '尚未开始工作');
		}
	},
	watch: {
		isChecking(newVal) {
			if(!this.watching) return;
			// 停止检查后开始检查下一个
			if(newVal === false) {
				this.currentIndex = ++this.currentIndex%5;
				this.checkNext();
			}
		},
		isRobotMoving(newVal) {
			if(!this.watching) return;
			// 机器人停下2秒后向服务器请求数据
			if(newVal === false) {
				this.checkRet[this.currentIndex].status = 2;
				this.checkRet[this.currentIndex].bzText = '检测中';
				this.$refs['log-ul'].children[this.currentIndex].className = 'show';
				this.delayTimer = setTimeout(() => {this.checkOne()}, 2000);
			}
		},
	},
	mounted() {
		this.getTaskAndStart();
	},
	beforeDestroy() {
		this.stopAllTimer();
	}
}
</script>

<style scoped>
.record {
	margin-left: 10px;
}
.controls {
	position: relative;
	display: inline-block;
	width: 100px;
	bottom: 20px;
	margin: 5px 30px 0 30px;
	text-align: center;
}
.controls span {
	position: relative;
	top: -6px;
	left: 50%;
	transform: translate(-50%);
	display: block;
	width: 30px;
	height: 30px;
	border-radius: 50%;
	background-color: lightgreen;
}
.controls p {
	font-family: 宋体;
	font-size: 16px;
}
.controls button, .more-info button {
	margin-top: 10px;
	width: 40px;
	height: 25px;
	font-family: 宋体;
	font-size: 16px;
	background-color: skyblue;
	border: #ccc 1px solid;
	border-radius: 5px;
	cursor: pointer;
}
.rot-photo {
	position: relative;
	display: inline-block;
	width: 893px;
	height: 107px;
	overflow: hidden;
	/*border: #ccc 1px solid;*/
}
.rot-ul li {
	float: left!important;
}
.rot-ul {
	position: relative;
	list-style-type: none;
	width: 1788px;
	left: -893px;
}
.more-info {
	display: inline-block;
	position: relative;
	top: -26px;
	text-align: center;
	margin-left: 30px;
}
.more-info span {
	display: block;
	font-family: 宋体;
	font-size: 16px;
}
.more-info button {
	width: 100px;
}
.footer {
	margin-left: 160px;
}
.log-ul {
	width: 893px;
	list-style-type: none;
}
.log-ul li {
	float: left;
	width: 20%;
	height: 30px;
	border: #ccc 1px solid;
	overflow: hidden;
	cursor: pointer;
}
.log-ul i {
	display: none;
}
.log-ul .show i {
	display: inline-block;
}
.log-ul .show #img-ret {
	display: none;
}
.log-ul i, .log-ul span {
	position: relative;
	top: -5px;
}
.log-ul #img-ret {
	position: relative;
	top: -3px;
	width: 20px;
}
.log-ul span {
	font-family: 宋体;
	font-size: 16px;
}
#robot-box {
	margin-left: 160px;
	width: 893px;
}
#robot {
	position: relative;
	width: 50px;
}
.drawer {
	position: static;
	margin: 20px;
}
.drawer .title {
	font-family: 宋体;
	font-size: 18px;
	text-align: center;
}
.drawer .content {
	margin-top: 20px;
	padding: 8px;
	border: #cccccc 1px solid;
	border-radius: 5px;
}
</style>