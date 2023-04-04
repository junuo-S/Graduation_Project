<template>
<div class="home">
	<el-row>
		<!--导航区-->
		<el-col :span="4" class="nav">
			<NavMenu/>
		</el-col>
		
		<!--展示区-->
		<el-col :span="20" class="main">
			<router-view></router-view>
		</el-col>
	</el-row>
</div>
</template>

<script>
import NavMenu from "@/components/Nav-Menu.vue";
export default {
	// eslint-disable-next-line vue/multi-word-component-names
	name: "Home",
	components: {NavMenu},
	methods: {
		routeTo(routeName) {
			this.$router.push({name: routeName});
		}
	},
	mounted() {
		this.$bus.$on('routeTo', this.routeTo);
	},
	beforeDestroy() {
		this.$bus.$off('routeTo');
	}
}
</script>

<style scoped>
.home {
	position: fixed;
	width: 100%;
	height: 100%;
	/*background-color: pink;*/
}
.el-row {
	height: 100%;
}
.nav {
	height: 100%;
	padding-top: 100px;
	background-color: #545c64;
}
</style>