import Vuex from 'vuex'
import LoginOptions from './login'
import Vue from "vue";

Vue.use(Vuex);

const store =  new Vuex.Store({
	modules: {
		LoginOptions,
	}
})

export default store;