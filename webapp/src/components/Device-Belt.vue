<template>
	<el-row class="device-belt">
		<DeviceBanner name="传送带"/>
		<div class="belt-display">
			<BeltCard
				v-for="(belt, index) in belts"
				:belt="belt"
				:key="index"
			/>
		</div>
	</el-row>
</template>

<script>
import DeviceBanner from "@/components/Device-Banner.vue";
import BeltCard from "@/components/Belt-Card.vue";

export default {
	name: "Device-Belt",
	components: {BeltCard, DeviceBanner},
	computed: {
		belts() {
			return this.$store.state.DeviceOptions.belts;
		}
	}
}
</script>

<style scoped>
.device-belt {
	margin-top: 30px;
	text-align: center;
}
</style>