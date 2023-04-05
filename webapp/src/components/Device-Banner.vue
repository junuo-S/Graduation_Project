<template>
<h2 class="title">{{name}}数据</h2>
</template>

<script>
export default {
	name: "Device-Banner",
	props: ['name']
}
</script>

<style scoped>
.title {
	padding: 0;
	height: 50px;
	line-height: 50px;
	font-family: "楷体";
	font-size: 30px;
	background-color: #ccc;
	text-align: center;
	border-radius: 10px;
}
</style>