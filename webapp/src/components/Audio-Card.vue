<template>
	<div id="peak-container">
		<p v-if="!peaks" class="loading"><i class="el-icon-loading"></i>Loading...</p>
		<div id="zoomview-container" ref="zoomview"></div>
		<div id="overview-container" ref="overview"></div>
		<audio ref="audio" id="audio" muted>
			<source src="../asset/huatong-xiaona.mp3" type="audio/mpeg" />
		</audio>
		<el-button
			type="info"
			:icon="icon"
			circle
			id="btn-play"
			ref="btn-play"
			@click="play"
			v-show="peaks"
		>
		</el-button>
	</div>
</template>

<script>
import Peaks from "peaks.js";
export default {
	name: 'Audio-Card',
	data() {
		return {
			icons: {play: 'el-icon-video-play', pause: 'el-icon-video-pause'},
			playStatus: false,
			peaks: null,
		}
	},
	computed: {
		icon() {
			return this.playStatus ? this.icons.pause : this.icons.play;
		},
	},
	created() {
		this.$nextTick(this.init);
	},
	methods: {
		init() {
			const options = {
				zoomview: {
					container: this.$refs.zoomview, //缩略图
				},
				overview: {
					container: this.$refs.overview,
				},
				mediaElement: this.$refs.audio,
				webAudio: {
					audioContext: new AudioContext(),
				},
				emitCueEvents: true,
			};
			Peaks.init(options, (err, peaks) => {
				if (err) return;
				this.peaks = peaks;
			});
		},
		play() {
			this.playStatus ? this.peaks.player.pause() : this.peaks.player.play();
			this.playStatus = !this.playStatus;
		}
	}
};
</script>

<style>
.loading {
	position: absolute;
	top: 50%;
}
#peak-container {
	/*width: 30%;*/
	/*border: lightskyblue 1px solid;*/
	/*border-radius: 8px;*/
	position: relative;
}
#btn-play {
	position: absolute;
	left: 3px;
	bottom: 3px;
}
#audio {
	/*display: none;*/
}
#zoomview-container {
	height: 100px;
}
#overview-container {
	height: 100px;
}
</style>