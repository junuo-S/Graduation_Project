<template>
<div>
	<RealTimeInfoBannerNew/>
	<RealTimeRecord robotId="robot-1" beltId="belt-1"/>
	<RealTimeRecord robotId="robot-2" beltId="belt-2"/>
	<RealTimeRecord robotId="robot-3" beltId="belt-3"/>
</div>
</template>

<script>
import RealTimeRecord from "@/components/Real-Time-Record.vue";
import RealTimeInfoBannerNew from "@/components/Real-Time-Info-Banner-New.vue";

export default {
	name: "Real-Time-Info",
	components: {RealTimeRecord, RealTimeInfoBannerNew},
}
</script>

<style scoped>

</style>