<!-- 气泡卡片 -->
<style lang="scss" scoped>
	/* 按钮样式 */
	.btn-with-bubble {
		position: relative;
		/* 让气泡相对于按钮定位 */
		// padding: 10px 20px;
		font-size: 16px;
	}

	/* 气泡样式 */
	.bubble {
		position: absolute;
		bottom: 100%;
		/* 将气泡定位到按钮的上方 */
		left: 50%;
		transform: translateX(-50%);
		background-color: #000;
		color: white;
		padding: 5px 10px;
		border-radius: 5px;
		font-size: 14px;
		white-space: nowrap;
		/* 防止气泡内容换行 */
		opacity: 1;
		visibility: visible;
		transition: opacity 0.3s ease;
	}

	/* 气泡的箭头 */
	.bubble::after {
		content: '';
		position: absolute;
		top: 100%;
		/* 定位到气泡的底部 */
		left: 50%;
		transform: translateX(-50%);
		border-width: 5px;
		border-style: solid;
		border-color: #000 transparent transparent transparent;
		/* 创建箭头 */
	}
</style>

<template>
	<view class="btn-with-bubble" @click="toggleBubble">
		<slot></slot>
		<view class="bubble" v-if="isBubbleVisible">
			<slot name="bubble-content"></slot>
		</view>
	</view>
</template>

<script lang="ts" setup>
	import { ref } from 'vue'

	const isBubbleVisible = ref<boolean>(false)

	function toggleBubble() {
		// 切换气泡的显示状态
		isBubbleVisible.value = !isBubbleVisible.value;
	}
</script>