<template>
<div class="sensor-data">
	<VideoPart/>
</div>
</template>

<script>
import VideoPart from "@/components/Video-Part.vue";

export default {
	name: "Sensor-Data",
	components: {VideoPart},
}
</script>

<style scoped>
.sensor-data {
	position: fixed;
	height: 100%;
	margin: 20px;
}
</style>