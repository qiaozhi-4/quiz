<style lang="scss">
	.page {
		background: linear-gradient(180deg, #2F1969 48.5%, #5830C7 78%, #A729E2 100%);

		.main {
			align-items: center;

			.v1 {
				padding-top: 50px;
				/* Done! */
				font-family: 'Inter';
				font-style: normal;
				font-weight: 700;
				font-size: 40px;
				line-height: 48px;
				text-align: center;
			}

			.v2 {
				/* 完成答题 */
				font-family: 'Inter';
				font-style: normal;
				font-weight: 800;
				font-size: 60px;
				line-height: 73px;
				text-align: center;
			}

			.v3 {
				padding-bottom: 20px;
				/* 🥳 */
				font-family: 'Inter';
				font-style: normal;
				font-weight: 400;
				font-size: 160px;
				line-height: 194px;
				text-align: center;

				text-shadow: 0 0 10px rgba(201, 88, 255, 0.5),
					0 0 20px rgba(201, 88, 255, 0.5),
					0 0 30px rgba(201, 88, 255, 0.5),
					0 0 40px rgba(201, 88, 255, 0.5),
					0 0 70px rgba(201, 88, 255, 0.5),
					0 0 80px rgba(201, 88, 255, 0.5),
					0 0 100px rgba(201, 88, 255, 0.5),
					0 0 150px rgba(201, 88, 255, 0.5);
			}

			.v4 {
				align-items: center;

				.t1 {
					/* + */
					font-family: 'Inter';
					font-style: normal;
					font-weight: 700;
					font-size: 20px;
					line-height: 24px;
				}

				.t2 {
					/* 100 */
					font-family: 'Inter';
					font-style: normal;
					font-weight: 900;
					font-size: 45px;
					line-height: 54px;

					background: linear-gradient(142.35deg, #8C421D 22.68%, #FBE67B 42.5%, #FCFBE7 55.31%, #F7D14E 69.6%, #D4A041 83.62%), linear-gradient(128.64deg, #7A96AC 14.45%, #EAEFF3 27.29%, #C2D4E1 36.93%, #FFFFFF 49.55%, #D4DEE5 58.33%, #ABBDC8 70.46%, #BCCAD7 82.6%), radial-gradient(70.31% 37.22% at 30.94% 23.13%, #FF9FEA 0%, rgba(255, 255, 255, 0) 100%)
						/* warning: gradient uses a rotation that is not supported by CSS and may not behave as expected */
						, radial-gradient(156.71% 156.71% at -16.25% 73.33%, #A349EF 42.19%, #FF65E6 100%)
						/* warning: gradient uses a rotation that is not supported by CSS and may not behave as expected */
						, #FFFFFF;
					-webkit-background-clip: text;
					-webkit-text-fill-color: transparent;
					background-clip: text;
					text-fill-color: transparent;

					text-shadow: 0px 2.2664px 45.0201px rgba(255, 255, 255, 0.25), 0px 2.2664px 37.8126px rgba(203, 218, 230, 0.5);
				}

				.t3 {
					padding-left: 5px;
					/* 分 */
					font-family: 'Inter';
					font-style: normal;
					font-weight: 900;
					font-size: 16px;
					line-height: 19px;
				}
			}

			.v5 {
				.text {
					/* 这是您第1次自我探索！ 🌟自我探索值增加100分！ */
					font-family: 'Inter';
					font-style: normal;
					font-weight: 700;
					font-size: 20px;
					line-height: 24px;
					text-align: center;

					transform: rotate(0.18deg);
				}
			}

			.b1 {
				margin-top: 20px;
				margin-bottom: 10px;
				/* 让朋友测测！ */
				width: 279px;
				height: 52px;

				/* 按钮烟色渐变 */
				background: linear-gradient(90deg, #7756EC 0%, #AE2FDB 27.5%, #A729E2 98.5%);
				border-radius: 30px;
				color: #FFFFFF;

				font-family: 'Inter';
				font-style: normal;
				font-weight: 700;
				font-size: 28px;
				line-height: 34px;
				/* identical to box height */
				text-align: center;
			}

			.b2 {
				width: 279px;
				height: 52px;
				background: rgba(255, 255, 255, 0.1);
				border-radius: 30px;

				/* 领取奖励 */
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
			<view class="v1">Done!</view>
			<view class="v2">完成出题</view>
			<view class="v3">🥳</view>
			<view class="v5 flex-column">
				<text class="text">这是您第1次自我探索！</text>
				<text class="text">🌟转发给朋友看看你们之间有多少默契吧！</text>
			</view>
			<button class="b1" open-type="share">让朋友测测！</button>
			<button class="b2" @click="getAward">领取奖励</button>
		</view>
	</view>
</template>

<script lang="ts" setup>
	import { onLoad, onShareAppMessage } from '@dcloudio/uni-app'
	import { ref } from 'vue';

	const paperId = ref<boolean>()
	const userId = ref<boolean>()
	
	/** 分享试卷 */
	onShareAppMessage((res) => {
		if (res.from === 'button') {// 来自页面内分享按钮
			return {
				title: '我们之间有多亲密？',
				path: `/pages/start-test/start-test?isAnswer=true&paperId=${paperId.value}&userId=${userId.value}`,
				imageUrl: '/static/img/小转发窗.png'
			}
		}
		return {
			title: '这测试',
			path: '/pages/start-test/start-test?isAnswer=true',
			imageUrl: '/static/img/小转发窗.png'
		}
	})
	/** 领取奖励 */
	function getAward() {
		console.log("领取奖励");
		uni.reLaunch({
			url: `/pages/home/home`
		});
	}

	onLoad((option) => {
		paperId.value = option.paperId
		userId.value = option.userId
	})
</script>