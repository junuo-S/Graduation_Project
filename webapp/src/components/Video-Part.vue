<template>
<el-row class="video-part">
	<el-col :span="1">
		<SensorBanner title="摄像头数据"/>
	</el-col>
	<el-col :span="23">
		<VideoCard videoName="belt-1.mp4" title="belt-1"/>
		<VideoCard videoName="belt-2.mp4" title="belt-2"/>
		<VideoCard videoName="belt-3.mp4" title="belt-3"/>
	</el-col>
</el-row>
</template>

<script>
import VideoCard from "@/components/Video-Card.vue";
import SensorBanner from "@/components/Sensor-Banner.vue";

export default {
	name: "Video-Part",
	components: {VideoCard, SensorBanner},
}
</script>

<style scoped>
.video-part {
	display: flex;
	align-items: center;
	height: 30%!important;
	overflow: auto;
	border-radius: 8px;
	border: 1px #ccc solid;
}
</style>