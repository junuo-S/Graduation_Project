export default {
	namespaced: true,
	mutations: {
		SET_LOGIN(state, value) {
			state.isLogin = value.isLogin;
			state.isAdmin = value.isAdmin;
			state.userName = value.userName;
			state.user = value.user;
		}
	},
	state: {
		isLogin: false,
		isAdmin: false,
		userName: '',
		user: null
	}
}