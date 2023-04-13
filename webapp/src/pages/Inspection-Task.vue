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
	<el-button type="primary" class="add-task">新增巡检任务</el-button>
</div>
</template>

<script>
import moment from "moment";
import axios from "axios";
export default {
	name: "Inspection-Task",
	computed: {
		tasks() {
			return this.$store.state.TaskOptions.tasks;
		}
	},
	methods: {
		handleDone(row) {
			if(row.status === 0) {
				this.$message.info('已结束');
				return;
			}
			axios.post('task/setDone',
				{id: row.id},
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
		handleDelete(row) {
			this.$confirm('确认删除？', '提示', {type: "warning"}).then(
				() => {
					axios.post('/task/deleteById',
						{id: row.id},
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
				}
			).catch(
				() => {this.$message.info("已取消删除");}
			)
		}
	},
	mounted() {
		this.$store.dispatch('TaskOptions/updateTask');
	},
	filters: {
		formatTime(time) {
			return moment(time * 1000).format("YYYY-MM-DD HH:mm:ss");
		},
		getDoneStr(status) {
			return status === 0 ? "已完成" : "进行中";
		}
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