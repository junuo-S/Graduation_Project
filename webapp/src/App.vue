<template>
	<router-view></router-view>
</template>

<script>

export default {
	name: "App",
	methods: {
		readyLogin() {
			console.log('readyLogin');
			this.$router.replace({name: 'home'});
		}
	},
	mounted() {
		this.$bus.$on('readyLogin', this.readyLogin);
		this.$router.replace({name: 'home'});
	},
	beforeDestroy() {
		this.$bus.$off('readyLogin');
	}
}
</script>

<style>
* {
	box-sizing: border-box;
}
</style>