<style lang="scss" scoped>
	.q-svg {
		background-repeat: no-repeat;
		background-size: contain;
		border-radius: 0px;
		/* 设置背景图片在中间 */
		background-position: center;
	}
</style>

<template>
	<view @click="$emit('click')" v-if="style" class="q-svg" :style="style" />
</template>

<script lang="ts" setup>
	import { ref, onMounted, watch  } from 'vue'
	import { addPxIfNumeric } from '../../utils/utils';
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
	/** svg样式 */
	const style = ref()
	/** 读取svg并设置样式 */
	function redSvg(icon: string, size: string | number, colors:  any[], opacitys:  any[]) {
		uni.getFileSystemManager().readFile({
			filePath: `/static/svg/${icon}.svg`,
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
				if (colors && colors.length)
					fun('(fill|stroke)', colors)
				// 匹配透明度，并进行替换
				if (opacitys && opacitys.length)
					fun('(fill|stroke)-opacity', opacitys)
				// 将 svg 数据进行 URL 编码
				basestr = encodeURIComponent(basestr)
				style.value = {
					backgroundImage: `url("data:image/svg+xml,${basestr}")`,
					width: addPxIfNumeric(size),
					height: addPxIfNumeric(size)
				}
			}
		})
	}
	/** 监听这些属性 */
	watch([() => props.icon, () => props.size, () => props.colors, () => props.opacitys], ([icon, size, colors, opacitys]) => {
		redSvg(icon, size, colors, opacitys)
	})
	onMounted(() => redSvg(props.icon, props.size, props.colors, props.opacitys))
</script>