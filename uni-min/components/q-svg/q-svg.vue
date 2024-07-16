<template>
	<view class="q-svg">
		<img class="img-icon" v-if="icon" :src="src" :style="{ width:props.size,height:props.size  }" />
	</view>
</template>

<script lang="ts" setup>
	import { ref } from 'vue'
	const props = defineProps({
		/** svg文件名称 */
		icon: { type: String, default: '' },
		/** svg大小 */
		size: { type: [String, Number], default: '32' }
	})
	const src = ref(`/static/svg/${props.icon}.svg`)
</script>

<style lang="scss" scoped>

</style>