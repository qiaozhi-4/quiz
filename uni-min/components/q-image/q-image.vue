<style lang="scss" scoped>
	.img {
		background-repeat: no-repeat;
		background-size: contain;
		border-radius: 0px;
		/* 设置背景图片在中间 */
		background-position: center;
	}
</style>

<template>
	<view>
		<view class="img" :style="style"></view>
	</view>
</template>

<script lang="ts" setup>
	import { ref, onMounted, watch } from 'vue'
	/** 传递的属性 */
	const props = defineProps({
		/** 图片源地址，同原生属性一致 */
		src: { type: String, default: '' },
		width: { type: String, default: '' },
		height: { type: String, default: '' },
	})
	/** 图片样式 */
	const style = ref()

	watch(props, (newValue, oldValue) => {
		style.value = {
			backgroundImage: `url('/static/img/${newValue.src}.png')`,
		}
		if (newValue.width) {
			style.value.width = `${newValue.width}px`
		}
		if (newValue.height) {
			style.value.height = `${newValue.height}px`
		}
	})
	onMounted(() => {
		style.value = {
			backgroundImage: `url('/static/img/${props.src}.png')`,
		}
		if (props.width) {
			style.value.width = `${props.width}px`
		}
		if (props.height) {
			style.value.height = `${props.height}px`
		}
	})
</script>