<template>
	<keep-alive>
		<router-view></router-view>
	</keep-alive>
</template>

<script>

export default {
	name: "App",
	methods: {
		readyLogin() {
			this.$router.push({name: 'realTimeInfo'});
		}
	},
	mounted() {
		this.$bus.$on('readyLogin', this.readyLogin);
		this.$router.push({name: 'login'});
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