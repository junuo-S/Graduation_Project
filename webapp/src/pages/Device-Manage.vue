<template>
	<div class="device-manage">
		<DeviceRobot/>
	</div>
</template>

<script>
import DeviceRobot from "@/components/Device-Robot.vue";

export default {
	name: "Device-Manage",
	components: {DeviceRobot},
}
</script>

<style scoped>
.device-manage {
	position: relative;
	height: 100%;
	width: 100%;
	padding: 30px 10px 0 10px;
	/*background-color: pink;*/
}
</style>