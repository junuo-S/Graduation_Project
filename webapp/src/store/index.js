import Vuex from 'vuex'
import LoginOptions from './login'
import DeviceOptions from './device'
import Vue from "vue";

Vue.use(Vuex);

const store =  new Vuex.Store({
	modules: {
		LoginOptions,
		DeviceOptions
	}
})

export default store;