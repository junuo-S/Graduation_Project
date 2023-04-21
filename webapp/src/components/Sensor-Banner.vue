<template>
<div class="video-banner">
	<p>{{ title }}</p>
</div>
</template>

<script>
export default {
	name: "Sensor-Banner",
	props: ['title']
}
</script>

<style scoped>
.video-banner {
	margin: 0 5px;
}
p {
	width: 30px;
	font-family: 楷体;
	font-size: 26px;
	background-color: lightskyblue;
	color: white;
	border-radius: 8px;
	text-align: center;
}
</style>