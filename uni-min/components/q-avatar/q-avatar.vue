<style lang="scss" scoped>
	.avatar-border {
		box-sizing: border-box;
		border: 5px solid #FFFFFF;
	}

	.circle {
		border-radius: 50%;
	}

	.square {
		border-radius: 5px;
	}
</style>

<template>
	<view class="avatar-wrap">
		<image v-if="props?.src" :class="props.shape" :src="props?.src" :mode="props?.mode" :style="style" />
		<q-svg v-else :icon="props.icon" :size="props.size"></q-svg>
	</view>
</template>

<script lang="ts" setup>
	import { ref, onMounted } from 'vue'
	/** 传递的属性 */
	const props = defineProps({
		/** Avatar 图片的源地址 */
		src: { type: String, default: '' },
		/** Avatar 大小 */
		size: { type: [String,Number], default: '32' },
		/** 图片裁剪、缩放的模式 */
		mode: { type: String, default: 'aspectFit' },
		/** Avatar 形状  'circle' | 'square'*/
		shape: { type: String, default: 'circle', },
		/** Avatar 形状  'circle' | 'square'*/
		icon: { type: String, default: '默认头像', },
		/** 边框颜色 */
		borderColor: { type: String, default: '#FFFFFF', },
		/** 边框粗细 */
		borderWidth: { type: [String,Number], default: '', },
	})
	const style = ref()
	onMounted(() => {
		style.value = {
			width: `${props.size}px`,
			height: `${props.size}px`
		}
		if (props.borderWidth) {
			style.value.border = `${props.borderWidth}px solid ${props.borderColor}`
			style.value.boxSizing = `border-box`
		}
	})
</script>