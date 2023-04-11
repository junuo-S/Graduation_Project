<template>
<div class="card">
	<p class="title">{{belt.beltId}}</p>
	<img src="../asset/belt.png" alt="">
	<p class="footer">长度: {{belt.length}}</p>
</div>
</template>

<script>
export default {
	name: "Belt-Card",
	props: {belt: Object},
}
</script>

<style scoped>
.card {
	margin-top: 30px;
	margin-left: 20px;
	width: 150px;
	height: 200px;
	background-color: lightgreen;
	display: inline-block;
	border: #ccc 2px solid;
	border-radius: 6px;
}
img {
	width: 100px;
	height: 130px;
}
.title, .footer {
	margin: 5px 0;
}
.title {
	font-family: Consolas;
	font-size: 16px;
}
.footer {
	font-family: 楷体;
	font-size: 16px;
}
</style>