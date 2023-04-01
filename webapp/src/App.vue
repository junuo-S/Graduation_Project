<template>
	<router-view></router-view>
</template>

<script>

export default {
	name: "App",
	mounted() {
		if(this.$store.state.LoginOptions.isLogin) {
			console.log(111)
		}
		else {
			this.$router.replace({name: 'login'});
		}
	}
}
</script>

<style>
* {
	box-sizing: border-box;
}
</style>