<template>
	<div class="device-manage">
		<DeviceRobot/>
		<DeviceBelt/>
	</div>
</template>

<script>
import DeviceRobot from "@/components/Device-Robot.vue";
import DeviceBelt from "@/components/Device-Belt.vue";

export default {
	name: "Device-Manage",
	components: {DeviceBelt, DeviceRobot},
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