<template>
	<router-view></router-view>
</template>

<script>

export default {
	name: "App",
	methods: {
		readyLogin() {
			this.$router.push({name: 'deviceManage'});
		}
	},
	mounted() {
		this.$bus.$on('readyLogin', this.readyLogin);
		this.$router.push({name: 'realTimeInfo'});
	},
	beforeDestroy() {
		this.$bus.$off('readyLogin');
	}
}
</script>

<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}
</style>