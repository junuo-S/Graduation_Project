<template>
<div class="video-card">
	<video autoplay muted loop>
		<source :src="baseUrl + videoName">
	</video>
	<p>{{title}}</p>
</div>
</template>

<script>
export default {
	name: "Video-Card",
	data() {
		return {
			baseUrl: 'http://www.junuo.club/media/',
		}
	},
	props: ['videoName', 'title']
}
</script>

<style scoped>
.video-card {
	margin-right: 30px;
	display: inline-block;
	width: 28%;
}
video {
	width: 90%;
}
p {
	width: 100%;
	font-family: 宋体;
	font-size: 18px;
	text-align: center;
}
</style>