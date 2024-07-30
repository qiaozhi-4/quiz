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
			<q-image src="徽章"></q-image>
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