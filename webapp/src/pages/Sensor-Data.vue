<template>
<div class="sensor-data">
	<VideoPart/>
	<AudioPart/>
	<TemperaturePart/>
</div>
</template>

<script>
import VideoPart from "@/components/Video-Part.vue";
import AudioPart from "@/components/Audio-Part.vue";
import TemperaturePart from "@/components/Temperature-Part.vue";

export default {
	name: "Sensor-Data",
	components: {TemperaturePart, VideoPart, AudioPart},
}
</script>

<style scoped>
.sensor-data {
	position: fixed;
	height: 100%;
	margin: 15px;
}
</style>