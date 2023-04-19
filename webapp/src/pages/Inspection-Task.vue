<template>
<div>
	<el-table
		:data="tasks"
		stripe
		style="width: 100%"
		:header-cell-style="{'text-align': 'center'}"
		:cell-style="{'text-align': 'center'}">
		<el-table-column
			prop="id"
			label="id"
			width="180">
		</el-table-column>
		<el-table-column
			label="创建时间"
			width="200"
		>
			<template slot-scope="scope">
				<i class="el-icon-time"></i>
				<span style="margin-left: 10px">{{ scope.row.time | formatTime }}</span>
			</template>
		</el-table-column>
		<el-table-column
			prop="robotId"
			label="机器人">
		</el-table-column>
		<el-table-column
			prop="beltId"
			label="皮带">
		</el-table-column>
		<el-table-column
			label="状态"
		>
			<template slot-scope="scope">
				<i class="el-icon-loading" v-if="scope.row.status === 1"></i>
				<i class="el-icon-circle-check" v-else-if="scope.row.status === 0"></i>
				<span style="margin-left: 10px">{{ scope.row.status | getDoneStr }}</span>
			</template>
		</el-table-column>
		<el-table-column
			label="操作">
			<template slot-scope="scope">
				<el-button type="info" size="mini" v-if="scope.row.status===1" @click="handleDone(scope.row)">结束</el-button>
				<el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
			</template>
		</el-table-column>
	</el-table>
	<el-button type="primary" class="add-task" @click="handleOpenDialog">新增巡检任务</el-button>
	
	<el-dialog
		title="新增巡检任务"
		:visible.sync="dialogFormVisible"
		width="30%"
		append-to-body
		center>
		
		<el-form :model="form">
			<el-form-item label="空闲机器人" :label-width="formLabelWidth">
				<el-select v-model="form.robotId" placeholder="请选择空闲机器人">
					<el-option
						v-for="(robot, index) in freeRobots"
						:label="robot.robotId + ' [剩余电量:' + robot.battery + '%]'"
						:value="robot.robotId"
						:key="index"
					>
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="空闲传送带" :label-width="formLabelWidth">
				<el-select v-model="form.beltId" placeholder="请选择空闲传送带">
					<el-option
						v-for="(belt, index) in freeBelts"
						:label="belt.beltId + ' [长度:' + belt.length + 'm]'"
						:value="belt.beltId"
						:key="index"
					>
					</el-option>
				</el-select>
			</el-form-item>
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button @click="dialogFormVisible = false">取 消</el-button>
			<el-button type="primary" @click="handleAddTask">确 定</el-button>
		</div>
	</el-dialog>
</div>
</template>

<script>
import moment from "moment";
import axios from "axios";
export default {
	name: "Inspection-Task",
	data() {
		return {
			dialogFormVisible: false,
			formLabelWidth: '120px',
			form: {
				robotId: '',
				beltId: '',
			},
			freeRobots: [],
			freeBelts: [],
		}
	},
	computed: {
		tasks() {
			return this.$store.state.TaskOptions.tasks;
		}
	},
	methods: {
		doneTask(id) {
			axios.post('task/setDone',
				{id},
				{headers: {'content-type': 'application/x-www-form-urlencoded'}}).then(
				resp => {
					if(resp.data.statusCode === 1) {
						this.$message.success(resp.data.bzText);
						this.$store.dispatch('TaskOptions/updateTask');
					}
					else {
						this.$message.warning(resp.data.bzText);
					}
				},
				error => {
					this.$message.error(error.message);
				}
			)
		},
		handleDone(row) {
			if(row.status === 0) {
				this.$message.info('已结束');
				return;
			}
			this.$confirm("确认结束任务？", '提示', {type: "warning"}).then(
				() => {	this.doneTask(row.id); }
			).catch(
				() => {this.$message.info('已取消结束任务');}
			)
		},
		deleteTask(id) {
			axios.post('/task/deleteById',
				{id},
				{headers: {'content-type': 'application/x-www-form-urlencoded'}}).then(
				resp =>  {
					if(resp.data.statusCode === 1) {
						this.$store.dispatch('TaskOptions/updateTask');
						this.$message.success(resp.data.bzText);
					}
					else {
						this.$message.warning(resp.data.bzText);
					}
				},
				error => {
					this.$message.error(error.message);
				}
			)
		},
		handleDelete(row) {
			this.$confirm('确认删除？', '提示', {type: "warning"}).then(
				() => {	this.deleteTask(row.id);}
			).catch(
				() => {this.$message.info("已取消删除");}
			)
		},
		handleOpenDialog() {
			this.updateFreeRobots();
			this.updateFreeBelts();
			this.form.robotId = '';
			this.form.beltId = '';
			this.dialogFormVisible = true;
		},
		addTask(robotId, beltId) {
			let time = Math.floor(Date.now() / 1000);
			axios.post('task/addTask', {robotId, beltId, time}).then(
				resp => {
					if(resp.data.statusCode === 1) {
						this.$message.success(resp.data.bzText);
						this.$store.dispatch('TaskOptions/updateTask');
					}
					else {
						this.$message.warning(resp.data.bzText);
					}
				},
				error => {
					this.$message.error(error.message);
				}
			);
		},
		handleAddTask() {
			if(this.form.beltId === '' || this.form.robotId === '') {
				this.$message.warning("请选择机器人和传送带");
				return;
			}
			this.addTask(this.form.robotId, this.form.beltId);
			this.dialogFormVisible = false;
		},
		updateFreeRobots() {
			axios.get('/robot/getFreeRobots').then(
				resp => {
					this.freeRobots = resp.data;
				},
				error => {
					this.$message.error(error.message);
				}
			)
		},
		updateFreeBelts() {
			axios.get('/belt/getFreeBelts').then(
				resp => {
					this.freeBelts = resp.data;
				},
				error => {
					this.$message.error(error.message);
				}
			)
		}
	},
	mounted() {
		this.$store.dispatch('TaskOptions/updateTask');
		this.$bus.$on('addTask', this.addTask);
		this.$bus.$on('doneTask', this.doneTask);
	},
	filters: {
		formatTime(time) {
			return moment(time * 1000).format("YYYY-MM-DD HH:mm:ss");
		},
		getDoneStr(status) {
			return status === 0 ? "已完成" : "进行中";
		}
	},
	beforeDestroy() {
		this.$bus.$off('addTask');
		this.$bus.$off('doneTask');
	}
}
</script>

<style scoped>
.add-task {
	margin-top: 20px;
	position: relative;
	left: 50%;
	transform: translate(-50%, 0);
}
</style>