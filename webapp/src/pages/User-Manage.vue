<template>
<div>
	<el-table
		:data="userData"
		style="width: 100%"
		:header-cell-style="{'text-align': 'center'}"
		:cell-style="{'text-align': 'center'}">
		<el-table-column
			prop="id"
			label="id"
			width="180">
		</el-table-column>
		<el-table-column
			prop="userName"
			label="用户名"
			width="180">
		</el-table-column>
		<el-table-column
			label="密码"
			width="180">
			<template slot-scope="scope">
				<span>{{scope.row.hidden}}</span>
				<img
					src="../asset/password-off.png"
					id="password-view"
					@dragstart.prevent
					@mousedown="showPassword(scope.$index, true, $event)"
					@mouseup="showPassword(scope.$index, false, $event)"
					@mouseleave="showPassword(scope.$index, false, $event)">
			</template>
		</el-table-column>
		<el-table-column
			label="是否为管理员">
			<template slot-scope="scope">
				<input type="checkbox" :checked="scope.row.isAdmin" @click.prevent>
			</template>
		</el-table-column>
		<el-table-column
			label="状态">
			<template slot-scope="scope">
				<el-switch
					v-model="scope.row.status"
					:active-value="1"
					:inactive-value="0"
					active-color="#13ce66"
					inactive-color="#ccc"
					disabled>
				</el-switch>
			</template>
		</el-table-column>
		<el-table-column
			label="操作">
			<template slot-scope="scope">
				<button id="del-btn" @click="handleDelete(scope.row)">删除</button>
			</template>
		</el-table-column>
	</el-table>
	<el-button type="primary" id="table-append" @click="addDialogVisiable = true">新增用户</el-button>
	
	<el-dialog
		title="收货地址"
		:visible.sync="addDialogVisiable"
		width="30%"
		center
		append-to-body
		@open="handleOpen"
	>
		
		<el-form :model="newUser">
			<el-form-item label="用户名" :label-width="formLabelWidth">
				<el-input v-model="newUser.name" clearable></el-input>
			</el-form-item>
			<el-form-item label="密码" :label-width="formLabelWidth">
				<el-input v-model="newUser.password" show-password></el-input>
			</el-form-item>
			<el-form-item label="管理员权限" :label-width="formLabelWidth">
				<el-switch
					v-model="newUser.isAdmin"
					:active-value="1"
					:inactive-value="0"
					active-color="#13ce66"
					inactive-color="#ccc"
				>
				</el-switch>
			</el-form-item>
			<el-form-item label="状态" :label-width="formLabelWidth">
				<el-switch
					v-model="newUser.status"
					:active-value="1"
					:inactive-value="0"
					active-color="#13ce66"
					inactive-color="#ccc"
				>
				</el-switch>
			</el-form-item>
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button @click="addDialogVisiable = false">取 消</el-button>
			<el-button type="primary" @click="handleAddUser">确 定</el-button>
		</div>
	</el-dialog>
</div>
</template>

<script>

export default {
	name: "User-Manage",
	data() {
		return {
			userData:
				[
					{id: 1, userName: 'junuo', password: '123', isAdmin: true, status: 1, hidden: '******'},
					{id: 2, userName: 'junuo2', password: '1232', isAdmin: false, status: 1, hidden: '******'}
				],
			addDialogVisiable: false,
			newUser: {},
			formLabelWidth: 'auto'
		}
	},
	methods: {
		showPassword(index, condition, e) {
			if(condition) {
				this.userData[index].hidden = this.userData[index].password;
				e.target.src = require('../asset/password-on.png');
			}
			else {
				this.userData[index].hidden = "******";
				e.target.src = require('../asset/password-off.png');
			}
		},
		handleDelete(row) {
			// selectById
			console.log(row)
		},
		updateUserData() {
			// selectAll
		},
		handleOpen() {
			this.newUser = {};
		},
		handleAddUser() {
			// addUser
		}
	}
}
</script>

<style scoped>
#password-view {
	width: 25px;
	height: 25px;
	cursor: pointer;
	position: relative;
	top: 6px;
}
#del-btn {
	background-color: rgb(245, 108, 108);
	color: #ffffff;
	border: none;
	width: 50px;
	height: 30px;
	border-radius: 5px;
	cursor: pointer;
}
#del-btn:hover {
	background-color: rgb(245, 140, 140);
}
#del-btn:active {
	background-color: rgb(245, 108, 108);
}
#table-append {
	position: relative;
	left: 50%;
	transform: translate(-50%);
	margin-top: 20px;
}
</style>