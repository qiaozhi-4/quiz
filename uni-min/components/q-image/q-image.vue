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

	watch(props, (newValue, oldValue) => {
		if (newValue.width) {
			style.value.width = isNaN(newValue.width) ? newValue.width : `${newValue.width}px`
		}
		if (newValue.height) {
			style.value.height = isNaN(newValue.height) ? newValue.height : `${newValue.height}px`
		}
	})
	onMounted(() => {
		if (props.width) {
			style.value.width = isNaN(props.width) ? props.width : `${props.width}px`
		}
		if (props.height) {
			style.value.height = isNaN(props.height) ? props.height : `${props.height}px`
		}

		src.value = /http/i.test(props.src) ? props.src : `/static/img/${props.src}.png`
	})
</script>