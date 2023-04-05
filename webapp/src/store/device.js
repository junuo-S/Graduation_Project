import axios from "axios";

export default {
	namespaced: true,
	actions: {
		updateBelt(context, value) {
			// 发送请求获取传送带数据
		},
		updateRobot(context, value) {
			// 发送请求获取机器人数据
			axios.get('/robot/selectAll').then(
				resp => {
					context.commit('UPDATE_ROBOT', resp.data);
				},
				error => {
					console.log(error.message);
				}
			)
		}
	},
	mutations: {
		UPDATE_BELT(state, value) {
			// 将传送带数据存储到state
		},
		UPDATE_ROBOT(state, value) {
			// 将机器人数据存储到state
			state.robots = value.forEach((robot) => {
				robot.battery = Number.parseInt(robot.battery);
				robot.status = Number.parseInt(robot.status);
			});
			state.robots = value;
		}
	},
	state: {
		robots: [],
		belts: [{beltId: 'belt-1', length: 10}]
	}
}