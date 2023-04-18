<template>
<div class="record">
	<div class="controls">
		<span ref="light"></span>
		<p>{{statusStr}}</p>
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
		<button>查看检测日志</button>
	</div>
	<div class="footer">
		<ul class="log-ul" ref="log-ul">
			<li>
				<img src="../asset/tuogun.png" alt="">
				<i class="el-icon-loading"></i>
				<img id="img-ret" src="../asset/status-ok.png" alt="" style="width: 20px;height: 20px">
				<span>检查中</span>
			</li>
			
			<li>
				<img src="../asset/tuogun.png" alt="">
				<i class="el-icon-loading"></i>
				<img id="img-ret" src="../asset/status-ok.png" alt="" style="width: 20px;height: 20px">
				<span>检查中</span>
			</li>
			
			<li>
				<img src="../asset/tuogun.png" alt="">
				<i class="el-icon-loading"></i>
				<img id="img-ret" src="../asset/status-ok.png" alt="" style="width: 20px;height: 20px">
				<span>检查中</span>
			</li>
			
			<li>
				<img src="../asset/tuogun.png" alt="">
				<i class="el-icon-loading"></i>
				<img id="img-ret" src="../asset/status-ok.png" alt="" style="width: 20px;height: 20px">
				<span>检查中</span>
			</li>
			
			<li>
				<img src="../asset/tuogun.png" alt="">
				<i class="el-icon-loading"></i>
				<img id="img-ret" src="../asset/status-ok.png" alt="" style="width: 20px;height: 20px">
				<span>检查中</span>
			</li>
		</ul>
	</div>
	<div id="robot-box">
		<img id="robot" src="../asset/robot.png" alt="" style="width: 50px">
	</div>
</div>
</template>

<script>
export default {
	name: "Real-Time-Record",
	props: ['robotId', 'beltId'],
	data() {
		return {
			rotUlLeft: -893,
			speed: 0.8,
			status: 1,
		}
	},
	computed: {
		statusStr() {
			return this.status === 1 ? '运行中' : '停止';
		}
	},
	methods: {
		startBelt() {
			let rotUl = this.$refs['rot-ul'];
			this.beltTimer = setInterval(() => {
				this.rotUlLeft+=this.speed;
				if(this.rotUlLeft>0)
					this.rotUlLeft=-893;
				rotUl.style.left=this.rotUlLeft+'px';
			}, 10);
		},
		stopBelt() {
			clearInterval(this.beltTimer);
		},
		onBtnStartStop() {
			if(this.status === 1) {
				this.status = 0;
				this.$refs['light'].style.backgroundColor = 'deeppink';
				this.$refs['btn-start-stop'].innerText='启动';
				this.stopBelt();
			}
			else {
				this.status = 1;
				this.$refs['light'].style.backgroundColor = 'lightgreen';
				this.$refs['btn-start-stop'].innerText='停止';
				this.startBelt();
			}
		}
	},
	mounted() {
		this.startBelt();
	},
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
	border: #ccc 1px solid;
}
.log-ul i, .log-ul span {
	position: relative;
	top: -5px;
}
.log-ul #img-ret {
	position: relative;
	top: -3px;
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
}
</style>