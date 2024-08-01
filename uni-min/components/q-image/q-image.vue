<style lang="scss" scoped>

</style>

<template>
	<view>
		<image v-if="props.src" :src="src" :mode="props?.mode" :style="style" />
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
		/** 图片裁剪、缩放的模式 */
		mode: { type: String, default: 'aspectFit' },
	})
	/** 图片样式 */
	const style = ref({})
	/** 图片源地址 */
	const src = ref('')

	function setImg(width_, height_, src_) {

		if (width_) {
			style.value.width = isNaN(width_) ? width_ : `${width_}px`
		}
		if (height_) {
			style.value.height = isNaN(height_) ? height_ : `${height_}px`
		}
		src.value = /http/i.test(src_) ? src_ : `/static/img/${src_}.png`
	}

	watch(props, (newValue, oldValue) => {
		setImg(newValue.width, newValue.height, props.src)
	})
	onMounted(() => {
		setImg(props.width, props.height, props.src)
	})
</script>