<style lang="scss" scoped>
	.q-fixed {
		position: sticky;
		top: 0;
		/* 当滚动到距离顶部 0 像素时粘住 */
		z-index: 1;
		/* 确保粘性元素在其他元素上方 */
	}

	.q-nav-bar {
		display: flex;
		width: 100vw;
		justify-content: space-between;

		.q-nav-bar-left {
			display: grid;
			place-content: end start;
			align-items: end;

			.icon {
				width: 32px;
				height: 32px;
			}
		}

		.q-nav-bar-middle {
			display: grid;
			place-content: end center;

			font-family: 'Inter';
			font-style: normal;
			font-weight: 800;
			font-size: 32px;
			line-height: 39px;
		}

		.q-nav-bar-right {}
	}
</style>

<template>
	<view class="q-nav-bar" :class="{'q-fixed': props.fixed}" :style="style">
		<view class="q-nav-bar-left" :style="`width: ${menuButtonInfo.width}px`">
			<cover-image v-if="props.leftIcon" @click="handlerReverseBack" class="icon"
				:src="`/static/svg/${props.leftIcon}.svg`" size="32px" />
		</view>
		<view class="q-nav-bar-middle">{{props.title}}</view>
		<view class="q-nav-bar-right" :style="`width: ${menuButtonInfo.width}px`"></view>
	</view>
</template>

<script lang="ts" setup>
	import { ref, computed } from 'vue'
	const props = defineProps({
		/** 标题文字 */
		title: { type: String, default: '' },
		/** 左侧按钮图标 */
		leftIcon: { type: String, default: '' },
		/** 文字颜色 */
		color: { type: String, default: '#ffffff' },
		/** 是否固定顶部 */
		fixed: { type: Boolean, default: true },
	})
	/** 胶囊信息{width: 87, height: 32, left: 281, top: 51, right: 368} */
	const menuButtonInfo = uni.getMenuButtonBoundingClientRect()
	/** 设备信息 */
	let systemInfo : UniApp.GetSystemInfoResult
	uni.getSystemInfo({
		success: function (res) {
			systemInfo = res
		}
	});
	/** 计算头部高度和内边距 */
	const style = computed(() => {
		let padding = systemInfo.screenWidth - menuButtonInfo.right
		return `height: ${10 + menuButtonInfo.bottom}px; padding: 0 ${padding}px 10px ${padding}px; color: ${props.color}`
	})
	/** 关闭当前页面,返回上一个页面 */
	function handlerReverseBack() {
		uni.navigateBack({
			delta: 1
		});
	}
</script>