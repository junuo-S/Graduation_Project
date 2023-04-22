<template>
	<div id="main" ref="main"></div>
</template>
<script>
import moment from "moment";

const echarts = require('echarts');

export default {
	name: 'Temperature-Card',
	data() {
		return {
			charts: null,
			xAxis: [],
			opinionData: [],
		}
	},
	methods: {
		drawLine() {
			this.charts = echarts.init(this.$refs.main)
			let option = {
				xAxis: {
					type: 'category',
					data: this.xAxis
				},
				yAxis: {
					type: 'value',
					min: 22,
					max: 28,
				},
				grid: {
					top: '10%',
					left: '10%',
					right: '10%',
					bottom: '20%'
				},
				series: [
					{
						data: this.opinionData,
						type: 'line'
					}
				]
			};
			this.charts.setOption(option);
		},
		getData() {
			let temp = Math.random() * 6 + 22;
			let time = moment().format("HH:mm:ss");
			this.xAxis.length === 5 && this.xAxis.shift();
			this.xAxis.push(time);
			this.opinionData.length === 5 && this.opinionData.shift();
			this.opinionData.push(temp);
		}
	},
	//调用
	mounted() {
		this.drawLine();
		this.timer = setInterval(this.getData, 2000);
	},
	watch: {
		opinionData() {
			this.drawLine();
		},
		xAxis() {
			this.drawLine();
		}
	},
	beforeDestroy() {
		clearInterval(this.timer);
	}
}
</script>
<style scoped>
#main {
	position: relative;
	display: inline-block;
	margin-right: 30px;
	height: 100%;
	width: 28%;
	border: lightskyblue 1px solid;
	overflow: auto;
}
</style>