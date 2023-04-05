import axios from "axios";

export default {
	namespaced: true,
	actions: {
		updateBelt(context, value) {
			// 发送请求获取传送带数据
		},
		updateRobot(context, value) {
			// 发送请求获取机器人数据
			console.log('updateRobot');
		}
	},
	mutations: {
		UPDATE_BELT(state, value) {
			// 将传送带数据存储到state
		},
		UPDATE_ROBOT(state, value) {
			// 将机器人数据存储到state
		}
	},
	state: {
		robots: [{robotId: 'robot-1', battery: 80, status: 0}],
		belts: [{beltId: 'belt-1', length: 10}]
	}
}