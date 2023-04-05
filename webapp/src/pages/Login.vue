<template>
<div class="login">
	<div class="dialog">
		<h2 class="title">皮带巡检机器人云平台</h2>
		
		<el-form label-position="left" label-width="80px">
			<el-form-item label="用户名">
				<el-input placeholder="请输入用户名" v-model="userName" clearable></el-input>
			</el-form-item>
			<el-form-item label="密码">
				<el-input placeholder="请输入密码" v-model="password" show-password></el-input>
			</el-form-item>
			<el-form-item label="验证码">
				<el-col :span="18">
					<el-input placeholder="请输入验证码" v-model="checkCode" clearable></el-input>
				</el-col>
				<el-col :span="6"><img src="../asset/a.jpg" ref="checkCode" @click="changeCheckCode"></el-col>
			</el-form-item>
		</el-form>
		
		<el-row class="buttons">
			<el-button type="primary" round @click="login">登录</el-button>
			<el-button type="warning" round @click="reset">重置</el-button>
		</el-row>
	</div>
</div>
</template>

<script>
import axios from "axios";
axios.defaults.withCredentials = true;
export default {
	// eslint-disable-next-line vue/multi-word-component-names
	name: "Login",
	data() {
		return {
			userName: '',
			password: '',
			checkCode: '',
		};
	},
	methods: {
		reset() {
			this.userName = '';
			this.password = '';
			this.checkCode = '';
		},
		login() {
			if(this.userName === '' || this.password === '' || this.checkCode === '') {
				this.$message.warning("填写不完整");
				return;
			}
			axios({
				method: 'post',
				url: '/user/login',
				headers: {'content-type': 'application/x-www-form-urlencoded'},
				data: {userName:this.userName, password:this.password, checkCode:this.checkCode},
			}).then(resp => {
				// console.log(resp.data);
				if(resp.data.statusCode === 0 && resp.data.msg === 'failed') {
					this.$message.error(resp.data.bzText);
				}
				else if(resp.data.statusCode === 1 && resp.data.msg === 'success') {
					this.$message.success(resp.data.bzText);
					this.$store.commit('LoginOptions/SET_LOGIN', {
						isLogin: true,
						userName: resp.data.user.userName,
						isAdmin: resp.data.user.isAdmin,
						user: resp.data.user
					});
					this.$bus.$emit('readyLogin');
				}
			}, error => {
				this.$message.error(error.message);
			})
		},
		changeCheckCode() {
			this.checkCode = '';
			axios.get(
				'/user/checkCode?' + Date.now(),
				{responseType: "blob"}
			).then(resp => {
				let imageType = resp.headers["content-type"]; //获取图片类型
				const blob = new Blob([resp.data], { type: imageType });
				const imageUrl = (window.URL || window.webkitURL).createObjectURL(blob);
				this.$refs.checkCode.src = imageUrl;
			},
			error => {
				this.$message.warning(error.message);
			})
			//this.$refs.checkCode.src="/user/checkCode?" + Date.now();
		}
	},
	mounted() {
		this.changeCheckCode();
	}
}
</script>

<style scoped>
.login {
	width: 100%;
	height: 100%;
	position: absolute;
	background: url("../asset/bg_login.jpg") no-repeat;
	background-size: 100% 100%;
}
.dialog {
	position: absolute;
	width: 600px;
	height: 400px;
	top: 55%;
	left: 50%;
	transform: translate(-50%, -50%);
	background-color: rgba(0, 0 ,0, 0.2);
	padding: 30px 90px 0 90px;
	border-radius: 10px;
}
.title {
	text-align: center;
	font-size: 30px;
	font-family: "楷体";
	margin-bottom: 40px;
}
.buttons {
	text-align: center;
}
img {
	margin-left: 5px;
	width: 80px;
	height: 35px;
	cursor: pointer;
}
</style>