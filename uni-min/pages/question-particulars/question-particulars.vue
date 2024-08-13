<style lang="scss">
	.page {
		background: #2F1969;

		.q-nav-bar-middle {
			font-size: 20px !important;
		}

		.main {
			padding: 0 12.5px;
			padding-bottom: 150px;

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

		.footer {
			width: 100vw;
			position: absolute;
			bottom: 0;
			/* 当滚动到距离顶部 0 像素时粘住 */
			z-index: 1;
			/* 确保粘性元素在其他元素上方 */
			/* 出题按钮 */

			box-sizing: border-box;
			gap: 6px;
			flex-direction: column;
			padding: 30px 31px;

			background: #1F1146;
			border-top: 1px solid rgba(255, 255, 255, 0.3);
			border-radius: 15px 15px 0px 0px;

			.text {
				/* 意外错失正确答案？再答一次试试看！ */
				font-family: 'Inter';
				font-style: normal;
				font-weight: 600;
				font-size: 12px;
				line-height: 15px;
				/* identical to box height */

				color: rgba(255, 255, 255, 0.75);
			}

			.button {
				padding: 6px 16px;
				background: linear-gradient(89.13deg, #C84EED 0.25%, #873CFE 48.54%, #5623CD 96.47%);
				box-shadow: 0px 6px 20px #2F1969;
				border-radius: 15px;

				font-family: 'Inter';
				font-style: normal;
				font-weight: 700;
				font-size: 24px;
				line-height: 29px;

				color: #FFFFFF;
			}
		}
	}
</style>

<template>
	<view class="page">
		<q-nav-bar class="head-sticky" leftIcon="头部导航-返回" :title="`${userInfo?.nickname}的${paper?.order}号测试`" />
		<view class="main">
			<view class="questions flex-column">
				<view class="question flex-column" v-for="(question,index) in paper?.questions" :key="index">
					<view class="question-title">
						<q-svg icon="左双引号" size="10" />
						<view class="title text-overflow">{{question?.title}}</view>
					</view>
					<view class="question-info">
						<view v-if="paper?.answers[index] == pageArg.selects[index] || (!pageArg.isFriendHome)" class="option text-overflow">
							{{question?.options[paper?.answers[index]]}}
						</view>
						<view v-else class="option text-overflow">???????</view>
					</view>
				</view>
			</view>
		</view>

		<view v-if="pageArg?.isFriendHome" class="footer flex-column">
			<text class="text">意外错失正确答案？再答一次试试看！</text>
			<button class="button flex-row text-overflow" @click="anewTast"><q-svg icon="复活宝石"
					size="34" />使用复活宝石再测一次！</button>
		</view>
	</view>
</template>

<script lang="ts" setup>
import {ref} from 'vue'
import {onLoad} from '@dcloudio/uni-app'
import {getPaper} from '../../utils/api/paper';
import {getUserById} from '../../utils/api/user';

/** 获取登录信息 */
	const userInfo = ref<Quiz.UserInfo>()
	/** 页面跳转过来的参数 */
	const pageArg = ref()
	/** 获取测试题目信息 */
	const paper = ref<Quiz.Paper>()
	/** 重新答题 */
	function anewTast() {
		uni.redirectTo({
			url: `/pages/start-test/start-test?isAnswer=true&paperId=${pageArg.value.paperId}&userId=${pageArg.value.userId}`
		});
	}
	onLoad((option) => {
		pageArg.value = option
		pageArg.value.isFriendHome = option.isFriendHome == 'true'
		if (pageArg.value.isFriendHome) {

			pageArg.value.selects = option.selects.split("@@")
			getUserById(pageArg.value.userId).then(res => userInfo.value = res.data)
		}else{
			 userInfo.value = getApp().globalData.userInfo
		}
		getPaper(pageArg.value.paperId).then(res => {
			paper.value = res.data
			paper.value.questions.forEach(e => {
				e.options = e.options.split('@@')
			})
			paper.value.answers = paper.value.answers.split("@@")
		})
	})
</script>