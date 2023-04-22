<template>
<el-row class="audio-part">
	<el-col :span="1">
		<SensorBanner title="音频数据"/>
	</el-col>
	<el-col :span="23">
		<keep-alive>
			<AudioCard/>
		</keep-alive>
	</el-col>
</el-row>
</template>

<script>
import SensorBanner from "@/components/Sensor-Banner.vue";
import AudioCard from "@/components/Audio-Card.vue";
export default {
	name: "Audio-Part",
	components: {SensorBanner, AudioCard}
}
</script>

<style scoped>
.audio-part {
	margin-top: 15px;
	display: flex;
	align-items: center;
	height: 30%;
	overflow: auto;
	border: #ccc 1px solid;
	border-radius: 8px;
}
</style>