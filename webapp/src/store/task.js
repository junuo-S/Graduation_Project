import axios from "axios";

export default {
	namespaced: true,
	actions: {
		updateTask(context, value) {
			axios.get('/task/selectAll').then(
				resp => {
					context.commit('UPDATE_TASK', resp.data);
				},
				error => {
					console.log(error.message);
				}
			)
		}
	},
	mutations: {
		UPDATE_TASK(state, value) {
			value.forEach( task =>  {
				task.id = Number.parseInt(task.id);
				task.time = Number.parseInt(task.time);
				task.status = Number.parseInt(task.status);
			})
			state.tasks = value;
		}
	},
	state: {
		tasks: []
	}
}