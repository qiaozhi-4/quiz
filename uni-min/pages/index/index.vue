<style lang="scss">
	.main {
		display: grid;
		grid-template-columns: repeat(2, 1fr);
		place-content: start center;
		place-items: center;
		gap: 10px;
	}

	.main2 {
		align-items: center;
		justify-content: center;
		gap: 20px;

		.svg {
			padding: 280px 0;
		}

		.progress-wrap,
		.progress {
			box-sizing: border-box;
			border-radius: 15px;
			border: 3px solid #FFFFFF;
		}

		.progress-wrap {
			position: relative;
			background: linear-gradient(90deg, #BE53FF 0%, #5830C7 100%);
			height: 28px;
			width: 92%;

			.progress {
				position: absolute;
				background: #FFFFFF;
				height: 100%;
				top: 0;
				left: 0;

				animation: expandWidth 1s ease-in-out forwards;
				/* 动画效果 */
			}

			@keyframes expandWidth {
				from {
					width: 0;
				}

				to {
					width: 100%;
				}
			}
		}

		.text {
			font-family: 'Inter';
			font-style: normal;
			font-weight: 400;
			font-size: 10px;
			line-height: 12px;

			color: rgba(255, 255, 255, 0.5);
		}
	}
</style>

<template>
	<view class="page">
		<view class="main" v-show="false">
			<button class="button text-overflow" v-for="(page,index) in pages" :key="index" type="primary"
				@click="handleTo(page.name)">{{page.name}}</button>
		</view>
		<view class="main2 flex-column">
			<q-svg class="svg" icon="log" size="104"></q-svg>
			<view class="progress-wrap">
				<view class="progress" :style="{width: `${((questionIndex + 1) / questions?.length * 100)}%`}"></view>
			</view>
			<text class="text">“我不关心你来自何处，我只关心你要去向何方”——Quiz</text>
		</view>
	</view>
</template>

<script lang="ts" setup>
	import { ref, onMounted } from 'vue'
	import { login } from '../../utils/api/user';
	import { getAll } from '../../utils/api/paper';
	const pages = [
		{ name: 'ding-dong' },
		{ name: 'set-user-info' },
		{ name: 'start-test' },
		{ name: 'answer-test' },
		{ name: 'answer-finish' },
		{ name: 'award' },
		{ name: 'home' },
		{ name: 'friend-home' },
		{ name: 'select-topic' },
		{ name: 'select-friend-view' },
		{ name: 'question-particulars' },
		{ name: 'task' },
		{ name: 'prop-explain' },
		{ name: 'badge' },
	]
	const globalData = ref<Quiz.GlobalData>(getApp().globalData as Quiz.GlobalData)
	function handleTo(pageName : String) {
		uni.navigateTo({
			url: `/pages/${pageName}/${pageName}?id=1&pageName=${pageName}`
		});
	}
	onMounted(() => {
		uni.login({
			provider: 'weixin', //使用微信登录
			success: loginRes => {
				console.log("微信登录信息:", loginRes);
				login('wxf2f9d21291120320', loginRes.code).then((res) => {
					// login('wx0f4e873ad758a586', loginRes.code).then((res) => {
					globalData.value.userInfo = res.data.userInfo
					globalData.value.token = res.data.token
					if (res.data.userInfo.nickname == null || res.data.userInfo.avatarUrl ==
						null || /^\s*$/.test(res.data.userInfo.nickname) || /^\s*$/.test(res
							.data
							.userInfo.avatarUrl)) {
						console.log('当前用户还没设置头像和用户名,跳转到设置页面')
						// uni.navigateTo({
						uni.redirectTo({
							url: `/pages/set-user-info/set-user-info`
						});
					} else {
						console.log('已设置头像和用户名')
						getAll().then((res) => {
							if (res.data.length == 0) {
								uni.redirectTo({
									url: `/pages/start-test/start-test?isAnswer=${false}`
								})
							} else {
								uni.redirectTo({
									url: `/pages/home/home`
								});
							}
						})
					}
				})
			}
		});
	})
</script>