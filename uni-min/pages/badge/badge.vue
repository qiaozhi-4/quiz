<style lang="scss">
	.page {
		background: linear-gradient(180deg, #2F1969 48.5%, #5830C7 78%, #A729E2 100%);

		.src {
			/* 徽章 */

			/* Auto layout */
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
			padding: 0px;
			isolation: isolate;
		}
	}
</style>

<template>
	<view class="page">
		<q-nav-bar leftIcon="头部导航-返回" title="我的徽章" />
		<view class="main">
			<q-image src="金徽章" width="200" height="200"></q-image>
			<q-image src="加100分" width="200" height="200"></q-image>
			<text>2024/03/23 意气相投徽章+1</text>
			<text>恭喜 您获得了乔治的 “蓝颜知己”勋章。</text>
		</view>
	</view>
</template>

<script lang="ts" setup>
	import { ref, onMounted } from 'vue'
	/** 徽章数据 */
	const badgeData = ref()
	/** 当前点击的徽章 */
	const activateItem = ref(0)
</script>