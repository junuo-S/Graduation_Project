<template>
<div class="video-card">
	<video controls autoplay muted>
		<source :src="baseUrl + videoName">
	</video>
</div>
</template>

<script>
export default {
	name: "Video-Card",
	data() {
		return {
			baseUrl: 'http://www.junuo.club/video/',
		}
	},
	props: ['videoName']
}
</script>

<style scoped>
.video-card {
	margin-right: 30px;
	display: inline-block;
	width: 28%;
}
video {
	width: 100%;
}
</style>