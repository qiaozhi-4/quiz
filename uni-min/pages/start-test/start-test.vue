<style lang="scss">
	.page {
		/* 题目封面页渐变 */
		background: linear-gradient(180deg, #BC5CF8 0%, #5830C7 29.5%, #1F1146 100%);
		padding: 0 48px;

		.main {
			display: grid;
			gap: 6px;

			.avatar {
				border-radius: 65px;
				width: 97px;
				height: 97px;
				margin-top: 125px;
			}

			.avatar-border {
				box-sizing: border-box;
				border: 4px solid #FFFFFF;
			}

			.nickname-group {
				gap: 6px;

				.nickname {
					font-family: 'Inter';
					font-style: normal;
					font-weight: 700;
					font-size: 20px;
					line-height: 24px;

					color: #FFFFFF;
				}

				.polite {
					/* 您好： */
					font-family: 'Inter';
					font-style: normal;
					font-weight: 700;
					font-size: 20px;
					line-height: 24px;
					letter-spacing: -0.04em;

					color: rgba(255, 255, 255, 0.5);
				}
			}

			.explain-text {
				/* 点击答题！🌟 开启您的自我探索之旅吧！ */
				font-family: 'Inter';
				font-style: normal;
				font-weight: 800;
				font-size: 36px;
				line-height: 44px;
				letter-spacing: -0.04em;

				color: #FFFFFF;

			}

			.date-statistics-text {
				/* 2024/04/20 Question 10/10 */
				font-family: 'Manrope';
				font-style: normal;
				font-weight: 600;
				font-size: 16px;
				line-height: 22px;
				/* identical to box height */
				letter-spacing: -0.04em;

				color: rgba(255, 255, 255, 0.71);
			}

			.start-button {
				height: 54px;
				margin-top: 20px;

				background: #FFFFFF;
				border-radius: 30px;

				.button-text {
					/* 开始测试 */
					font-family: 'Inter';
					font-style: normal;
					font-weight: 800;
					font-size: 32px;
					line-height: 39px;

					background: linear-gradient(90deg, #BE53FF 0%, #796DFF 100%);
					-webkit-background-clip: text;
					-webkit-text-fill-color: transparent;
					background-clip: text;
					text-fill-color: transparent;
				}
			}
		}
	}
</style>

<template>
	<view class="page">
		<q-nav-bar></q-nav-bar>
		<view class="main">
			<cover-image class="avatar avatar-border" :src="userInfo?.avatarUrl" />
			<view class="nickname-group">
				<text class="nickname">{{userInfo?.nickname}}</text>
				<text class="polite">您好：</text>
			</view>
			<view class="explain-text">点击答题！🌟 开启您的自我探索之旅吧！</view>
			<view class="date-statistics-text">
				{{`${now.getFullYear()}/${String(now.getMonth() + 1).padStart(2, '0')}/${String(now.getDate()).padStart(2, '0')} Question ${questions?.length}/${questions?.length}`}}
			</view>
			<button class="start-button" @click="onButton">
				<text class="button-text">开始测试</text>
			</button>
		</view>
	</view>
</template>

<script lang="ts" setup>
	import { ref, onMounted } from 'vue'
	import { getRandomQuestions } from '../../utils/api';
	/** 获取登录信息 */
	const userInfo = ref<Quiz.UserInfo>()

	/** 获取测试题目信息 */
	const questions = ref<Quiz.Question[]>()

	/** 当前时间 */
	const now = new Date();

	/** 点击'开始测试'跳转测试页 */
	function onButton() {
		uni.navigateTo({
			url: `/pages/answer-test/answer-test`
		});
	}

	onMounted(() => {
		userInfo.value = getApp().globalData.userInfo
		getRandomQuestions().then(res => {
			questions.value = res.data
		})
	})
</script>