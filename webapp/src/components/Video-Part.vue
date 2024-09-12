<template>
<el-row class="video-part">
	<el-col :span="1">
		<SensorBanner title="摄像头数据"/>
	</el-col>
	<el-col :span="23">
		<VideoCard videoName="你丢过外卖吗？｜微电影《摇摆》-反网络暴力：不要以为你们没有拿刀就不是凶手 (1bU4y157za).mp4" title="belt-1"/>
		<VideoCard videoName="学姐带你逛中南.mp4" title="belt-2"/>
		<VideoCard videoName="张哲瀚-龚俊《天涯客》.mp4" title="belt-3"/>
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