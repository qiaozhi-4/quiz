<style lang="scss">
	.page {
		background: #2F1969;

		.q-nav-bar-middle {
			font-size: 20px !important;
		}

		.main {
			padding: 0 12.5px;

			.questions {
				flex-grow: 1;
				gap: 8px;
				width: 100%;

				overflow: auto;

				.question {
					padding: 11px 13px;
					min-height: 59px;
					gap: 3px;

					background: rgba(255, 255, 255, 0.1);
					border-radius: 15px;

					.question-title {
						gap: 3px;

						.svg {
							width: 10px;
							height: 10px;
						}

						.title {
							font-family: 'Inter';
							font-style: normal;
							font-weight: 500;
							font-size: 12px;
							line-height: 15px;

							color: #FFFFFF;

							opacity: 0.7;
						}
					}

					.question-info {
						justify-content: space-between;
						align-items: center;
						gap: 3px;
						padding-left: 10px;

						.option {
							font-family: 'Inter';
							font-style: normal;
							font-weight: 700;
							font-size: 16px;
							line-height: 19px;
							letter-spacing: -0.04em;

							color: #FFFFFF;
						}

						.input-value {
							font-family: 'Inter';
							font-style: normal;
							font-weight: 500;
							font-size: 12px;
							line-height: 15px;
							/* identical to box height */
							letter-spacing: -0.04em;

							color: rgba(255, 255, 255, 0.75);
						}
					}
				}
			}
		}
	}
</style>

<template>
	<view class="page">
		<q-nav-bar leftIcon="头部导航-返回" :title="pageArg?.title" />
		<view class="main">
			<view class="questions flex-column">
				<view class="question flex-column" v-for="(question,index) in questions" :key="index">
					<view class="question-title">
						<q-svg icon="左双引号" size="10" />
						<view class="title text-overflow">{{question?.title}}</view>
					</view>
					<view class="question-info">
						<view class="option text-overflow">{{question?.options.split('@@')[0]}}</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script lang="ts" setup>
	import { ref } from 'vue'
	import { onLoad } from '@dcloudio/uni-app'
	import { getRandomQuestions } from '../../utils/api';
	/** 页面跳转过来的参数 */
	const pageArg = ref()
	/** 获取测试题目信息 */
	const questions = ref<Quiz.Question[]>()
	onLoad((option) => {
		pageArg.value = option
		getRandomQuestions().then(res => {
			questions.value = res.data
		})
	})
</script>