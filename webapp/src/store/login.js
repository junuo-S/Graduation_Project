export default {
	namespaced: true,
	mutations: {
		SET_LOGIN(state, value) {
			state = {...state, ...value};
		}
	},
	state: {
		isLogin: false,
		isAdmin: false,
		userName: ''
	}
}