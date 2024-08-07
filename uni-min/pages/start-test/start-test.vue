<style lang="scss">
	.page {
		/* 题目封面页渐变 */
		background: linear-gradient(180deg, #BC5CF8 0%, #5830C7 29.5%, #1F1146 100%);
		padding: 0 48px;

		.main {
			padding-top: 125px;
			display: grid;
			gap: 6px;

			.avatar-group {
				position: relative;

				.friend-avatar {
					position: absolute;
					left: 61px;
				}
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
			<view class="avatar-group">
				<q-avatar :src="userInfo?.avatarUrl" size="97" borderWidth="4"></q-avatar>
				<q-avatar class="friend-avatar" v-if="isAnswer" :src="friendInfo?.avatarUrl" size="97"
					borderWidth="4"></q-avatar>
			</view>
			<view class="nickname-group" v-if="isAnswer">
				<text class="polite">您的朋友</text>
				<text class="nickname">{{friendInfo?.nickname}}</text>
				<text class="polite">邀请您：</text>
			</view>
			<view class="nickname-group" v-else>
				<text class="nickname">{{userInfo?.nickname}}</text>
				<text class="polite">您好：</text>
			</view>
			<template v-if="isAnswer">
				<view class="explain-text">点击测试！🌟 </view>
				<view class="explain-text">揭秘我们之间关系的亲密程度～</view>
			</template>
			<template v-else>
				<view class="explain-text">点击出题！🌟</view>
				<view class="explain-text">看看他们对您的了解有多深！</view>
			</template>
			<!-- 	<view class="date-statistics-text">
				{{`${now.getFullYear()}/${String(now.getMonth() + 1).padStart(2, '0')}/${String(now.getDate()).padStart(2, '0')} Question 10/10`}}
			</view> -->
			<view class="date-statistics-text">
				{{`${now} Question 10/10`}}
			</view>
			<button class="start-button" @click="onButton">
				<text v-if="isAnswer" class="button-text">开始测试</text>
				<text v-else class="button-text">开始出题</text>
			</button>
		</view>
	</view>
</template>

<script lang="ts" setup>
	import { ref, onMounted } from 'vue'
	import { formatDate } from '../../utils/utils';
	import { onLoad } from '@dcloudio/uni-app'
	import { getUserById } from '../../utils/api/user';
	/** 获取登录信息 */
	const userInfo = ref<Quiz.UserInfo>()
	/** 朋友信息 */
	const friendInfo = ref<Quiz.UserInfo>()
	/** 出题还是答题 */
	const isAnswer = ref<boolean>()
	/** 试卷id */
	const paperId = ref<number>()
	/** 当前时间 */
	const now = ref(formatDate(new Date, 'YYYY/MM/DD'));


	/** 点击'开始测试'跳转测试页 */
	function onButton() {
		if (isAnswer.value) {
			uni.redirectTo({
				url: `/pages/answer-test/answer-test?paperId=${paperId.value}&userId=${friendInfo.value.userId}`
			});
		} else {
			uni.redirectTo({
				url: `/pages/set-test/set-test`
			});
		}
	}

	onMounted(() => {
		/** 获取登录信息,直到获取成功 */
		let intervalId = setInterval(() => {
			console.log(userInfo.value);
			userInfo.value = getApp().globalData.userInfo
			if (userInfo.value) {
				clearInterval(intervalId);
			}
		}, 200)
	})
	onLoad((option) => {
		isAnswer.value = option.isAnswer === 'true'
		if (isAnswer.value) {
			paperId.value = option.paperId
			getUserById(option.userId).then(res => {
				friendInfo.value = res.data
			})
		}
	})
</script>