<style lang="scss">
	.page {
		/* 动画超黑渐变 */
		background: linear-gradient(180deg, #371E7A 33.79%, #3E228B 67.59%, #8836C1 100%);

		.main {
			align-items: center;
			padding: 0 97.5px;

			.v1 {
				margin-top: 70px;
				/* 复活宝石让尊贵的您获得重新再次答题的机会 */
				font-family: 'Inter';
				font-style: normal;
				font-weight: 500;
				font-size: 16px;
				line-height: 19px;
				text-align: center;
			}

			.v2 {
				margin-bottom: 10px;
				/* 复活宝石 */
				font-family: 'Inter';
				font-style: normal;
				font-weight: 700;
				font-size: 40px;
				line-height: 48px;
				text-align: center;
			}

			// .v3 {
			// 	/* 宝石和发光 */

			// 	/* Auto layout */
			// 	display: flex;
			// 	flex-direction: column;
			// 	justify-content: flex-end;
			// 	align-items: center;
			// 	padding: 0px;
			// 	isolation: isolate;

			// 	width: 100%;
			// 	height: 161px;
			// 	transform: rotate(0.44deg);
			// }

			.v4 {
				padding: 10px;
				/* 1 */
				font-family: 'Inter';
				font-style: normal;
				font-weight: 900;
				font-size: 40px;
				line-height: 48px;
				text-align: center;
			}

			.v5 {
				/* 2024/03/23 复活宝石+1 恭喜 您是获得“复活”机会1次。 */
				font-family: 'Inter';
				font-style: normal;
				font-weight: 500;
				font-size: 12px;
				line-height: 15px;
				text-align: center;

				color: rgba(255, 255, 255, 0.67);
			}

			.v6 {
				box-sizing: border-box;
				margin-top: 128px;

				width: 180px;
				height: 47.94px;

				background: linear-gradient(90deg, #5830C7 0%, #A729E2 23.19%, #A729E2 89.69%);
				border-radius: 30px;
				/* 继续 */

				font-family: 'Inter';
				font-style: normal;
				font-weight: 700;
				font-size: 28px;
				line-height: 34px;

				color: #FFFFFF;
			}
		}
	}
</style>

<template>
	<view class="page">
		<q-nav-bar leftIcon="头部导航-返回" />
		<view class="main flex-column">
			<view class="v1">{{items[itemIndex].describe}}</view>
			<view class="v2">{{items[itemIndex].name}}</view>
			<q-image  class="v3" :src="items[itemIndex].name" height="161" width="161"></q-image>
			<view class="v4">{{items[itemIndex].count}}</view>
			<view class="v5 flex-column">
				<text class="text">
					{{`${now.getFullYear()}/${String(now.getMonth() + 1).padStart(2, '0')}/${String(now.getDate()).padStart(2, '0')} ${items[itemIndex].name}+1`}}
				</text class="text">
				<text>恭喜 您是获得“{{items[itemIndex].effect}}”机会1次。</text>
			</view>
			<button class="v6" @click="onButton">继续</button>
		</view>
	</view>
</template>

<script lang="ts" setup>
	import { ref, onMounted } from 'vue'
	/** 道具集合 */
	const items = ref([{
		name: '复活宝石',
		describe: '让尊贵的您获得重新再次答题的机会',
		effect: '复活',
		count: 1
	}, {
		name: '提示宝石',
		describe: '提示宝石让尊贵的您答题时获得答案相关的提示！',
		effect: '提示',
		count: 1
	},])
	/** 当前展示的道具 */
	const itemIndex = ref(0)
	/** 当前时间 */
	const now = new Date();
	/** 点击按钮触发 */
	function onButton() {
		if (itemIndex.value < items.value.length - 1) {
			itemIndex.value++
		}
		else {
			uni.navigateTo({
				url: `/pages/home/home`
			});
		}
	}
</script>