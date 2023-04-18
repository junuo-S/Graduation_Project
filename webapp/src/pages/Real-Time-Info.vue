<template>
<div>
	<RealTimeInfoBanner/>
	<RealTimeRecord robotId="robot-1" beltId="belt-1"/>
</div>
</template>

<script>
import RealTimeInfoBanner from "@/components/Real-Time-Info-Banner.vue";
import RealTimeRecord from "@/components/Real-Time-Record.vue";

export default {
	name: "Real-Time-Info",
	components: {RealTimeInfoBanner, RealTimeRecord},
}
</script>

<style scoped>

</style>