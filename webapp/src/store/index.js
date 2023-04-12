import Vuex from 'vuex'
import LoginOptions from './login'
import DeviceOptions from './device'
import TaskOptions from "./task";
import Vue from "vue";

Vue.use(Vuex);

const store =  new Vuex.Store({
	modules: {
		LoginOptions,
		DeviceOptions,
		TaskOptions
	}
})

export default store;