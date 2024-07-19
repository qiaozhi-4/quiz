<style lang="scss" scoped>
	.q-svg {
		background-repeat: no-repeat;
		background-size: contain;
		border-radius: 0px;
	}
</style>

<template>
	<view v-if="svgStyle" class="q-svg" :style="{backgroundImage: svgStyle,width: size, height: size}" />
</template>

<script lang="ts" setup>
	import { ref, computed } from 'vue'
	/** 传递的属性 */
	const props = defineProps({
		/** svg文件名称 */
		icon: { type: String, default: '' },
		/** svg大小 */
		size: { type: [String, Number], default: '32' },
		/** 颜色 */
		colors: Array,
		/** 透明度 */
		opacitys: Array,
	})
	/** svg */
	const svgStyle = ref()
	/** 大小 */
	const size = computed(() => {
		return isNaN(Number(props.size)) ? props.size : `${props.size}px`
	})
	uni.getFileSystemManager().readFile({
		filePath: `/static/svg/${props.icon}.svg`,
		encoding: 'binary',
		success: (res) => {
			let basestr : any = res.data
			// 替换参数对应的值
			const fun = (parmName : string, paemVals : any[]) => {
				let index = 0
				basestr = basestr.replace(new RegExp(`${parmName}=".+?"`, 'g'), (word : string) => {
					const newColor = paemVals[index]
					index++
					return newColor ? word.replace(/".*"/, (_) => `"${newColor}"`) : word
				})
			}
			// 匹配颜色，并进行替换
			if (props.colors && props.colors.length)
				fun('(fill|stroke)', props.colors)
			// 匹配透明度，并进行替换
			if (props.opacitys && props.opacitys.length)
				fun('(fill|stroke)-opacity', props.opacitys)
			// 将 svg 数据进行 URL 编码
			basestr = encodeURIComponent(basestr)
			svgStyle.value = `url("data:image/svg+xml,${basestr}");`
		}
	})
</script>