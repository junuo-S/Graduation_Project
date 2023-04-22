<template>
<el-row class="temperature-part">
	<el-col :span="1">
		<SensorBanner title="红外传感器"/>
	</el-col>
	<el-col :span="23" class="content">
		<TemperatureCard/>
		<TemperatureCard/>
		<TemperatureCard/>
	</el-col>
</el-row>
</template>

<script>
import SensorBanner from "@/components/Sensor-Banner.vue";
import TemperatureCard from "@/components/Temperature-Card.vue";

export default {
	name: "Temperature-Part",
	components: {TemperatureCard, SensorBanner}
}
</script>

<style scoped>
.temperature-part {
	position: relative;
	margin-top: 15px;
	height: 30%;
	border: #ccc 1px solid;
	border-radius: 8px;
	
	display: flex;
	align-items: center;
}
.content {
	height: 100%;
}
</style>