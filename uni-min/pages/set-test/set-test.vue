<style lang="scss">
	.page {
		background: linear-gradient(180deg, #BC5CF8 0%, #6F3ED3 29.5%, #2F1969 100%);

		.main {
			gap: 6px;
			align-items: center;
			// padding: 0 15px;
			height: 100%;

			.avatar {
				margin-top: 40px;
			}


			.statistics-text {
				font-family: 'Inter';
				font-style: normal;
				font-weight: 500;
				font-size: 12px;
				line-height: 15px;

				color: #FFFFFF;
			}

			.progress-wrap {
				box-sizing: border-box;
				position: relative;
				border-radius: 15px;
				background: linear-gradient(90deg, #BE53FF 0%, #5830C7 100%);
				height: 17px;
				width: 92%;
				box-sizing: border-box;
				border: 3px solid #FFFFFF;

				.progress {
					position: absolute;
					background: #FFFFFF;
					border-radius: 15px;
					height: 17px;
					top: -3px;
					left: 0;

					transition: width 0.5s linear;
				}
			}

			.question-wrap {
				position: relative;
				flex-grow: 1;
				width: 100%;

				.swiper {
					width: 100%;
					height: 100%;

					.swiper-item {
						box-sizing: border-box;
						padding: 0 15px;
						gap: 10px;

						.title {
							font-family: 'Manrope';
							font-style: normal;
							font-weight: 700;
							font-size: 32px;
							line-height: 44px;
							letter-spacing: -0.04em;

							color: #FFFFFF;
						}

						.options {
							padding: 20px 0;
							overflow: auto;
							gap: 10px;

							.option-button {
								background: rgba(255, 255, 255, 0.3);
								border-radius: 30px !important;

								min-height: 43px;

								font-family: 'Manrope';
								font-style: normal;
								font-weight: 700;
								font-size: 20px;
								line-height: 27px;
								letter-spacing: -0.04em;

								color: #FFFFFF;
							}

							.option-button:hover {

								/* 粉紫渐变 选项 */
								background: linear-gradient(90deg, #BE53FF 0%, #7756EC 100%) !important;
							}
						}
					}
				}
			}
		}

		.footer {
			// width: 100vw;
			min-height: 137px;
			background: #2F1969;

			.input-wrap {
				align-items: center;
				gap: 5px;
				position: relative;
				top: 0;
				height: 54px;
				background: #2F1969;
				padding: 12px 20px;
				border-bottom: 1px solid rgba(255, 255, 255, 0.15);

				transition: top 0.3s linear;

				.input {
					width: 100%;
					font-family: 'Inter';
					font-style: normal;
					font-weight: 700;
					font-size: 16px;
					line-height: 19px;

					color: #FFFFFF;
				}

				.input-placeholder {
					font-family: 'Inter';
					font-style: normal;
					font-weight: 400;
					font-size: 16px;
					line-height: 19px;

					color: rgba(255, 255, 255, 0.3);
				}
			}

			.footer-svg {
				flex-grow: 1;
				position: relative;
				z-index: 100;
				box-sizing: border-box;
				display: flex;
				flex-direction: row;
				align-items: flex-start;
				padding: 17px 20px;
				gap: 41px;

				background: #2F1969;

				.svg {
					width: 19px;
					height: 19px;
				}
			}
		}
	}

	.popup {
		display: flex;
		flex-direction: column;
		align-items: center;
		padding: 20px 12px;
		padding-bottom: 0;
		gap: 20px;

		width: 100vw;

		background-color: #2F1969;
		border-radius: 15px 15px 0px 0px;

		.title {
			font-family: 'Manrope';
			font-style: normal;
			font-weight: 700;
			font-size: 20px;
			line-height: 27px;
			letter-spacing: -0.04em;

			color: #FFFFFF;
		}

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

		.describe {
			flex-grow: 1;
			padding: 13px;
			margin-bottom: 20px;

			background: rgba(255, 255, 255, 0.1);
			border-radius: 15px;

			font-family: 'Inter';
			font-style: normal;
			font-weight: 500;
			font-size: 16px;
			line-height: 19px;
			letter-spacing: -0.04em;

			color: #FFFFFF;
		}
	}
</style>

<template>
	<view class="page">
		<q-nav-bar needBack title="Quiz" fixed/>
		<view class="main flex-column">
			<q-avatar class="avatar" :src="userInfo?.avatarUrl" size="62" borderWidth="3"></q-avatar>
			<view class="statistics-text">
				{{`Question ${questionIndex+1}/${questions?.length}`}}
			</view>
			<view class="progress-wrap">
				<view class="progress" :style="{width: `${((questionIndex + 1) / questions?.length * 100)}%`}"></view>
			</view>
			<view class="question-wrap flex-column">
				<swiper class="swiper" :current="questionIndex" @change="onChange">
					<swiper-item class="swiper-item flex-column" v-for="question in questions"
						:key="question.questionId">
						<view class="title">{{question?.title}}</view>
						<view class="options flex-column">
							<button class="option-button" v-for="(option,index) in question?.options.split('@@')"
								:key="index" @click="onButtonClick(index)">{{option}}</button>
						</view>
					</swiper-item>
				</swiper>
				<!-- 指示点 -->
				<q-indicator-position :current="questionIndex" :amount="questions?.length"></q-indicator-position>
			</view>
		</view>
		<view class="footer flex-column">
			<view class="input-wrap" :style="style" style="opacity: 0;">
				<q-svg class="svg" :icon="`答题测试-底部-泡泡${inputValues[questionIndex] ? '-激活':''}`" size="19" />
				<input class="input" placeholder-class="input-placeholder" placeholder="说说具体的想法"
					:adjust-position="false" @input="onInput" @blur="onBlur"
					@keyboardheightchange="keyboardheightchange" :value="inputValues[questionIndex]" />
			</view>
			<view class="footer-svg">
				<q-svg class="svg" :icon="`答题测试-底部-总览${activityPopup=='总览' ? '-激活':''}`" size="19"
					@click="onChangeActivityPopup('总览')" />
				<!-- <q-svg class="svg" :icon="`答题测试-底部-电脑${activityPopup=='电脑' ? '-激活':''}`" size="19" -->
				<!-- @click="onChangeActivityPopup('电脑')" /> -->
			</view>
		</view>
	</view>

	<!-- 弹窗 -->
	<uni-popup ref="popupRef" type="bottom" mask-background-color="rgba(0,0,0,0)" @maskClick="onMaskClick"
		:safe-area="false">
		<view class="popup" :style="popupStyle">
			<!-- <view class="title">{{userInfo.nickname}} 的 01号测试</view> -->
			<view class="questions flex-column" v-show="activityPopup=='总览'">
				<view class="question flex-column" v-for="(question,index) in questions" :key="index"
					@click="onClickPopupQuestion(index)">
					<view class="question-title">
						<q-svg icon="左双引号" size="10" />
						<view class="title text-overflow">{{question?.title}}</view>
					</view>
					<view class="question-info">
						<view class="option text-overflow">{{question?.options.split('@@')[options[index]]}}</view>
						<view class="input-value text-overflow">{{inputValues[index]}}</view>
					</view>
				</view>
			</view>
			<!-- <view class="describe" v-show="activityPopup=='电脑'">你好！🌟 开始你的自我探索之旅吧！凭回忆和直觉的选择回答就好。</view> -->
			<view style="min-height: 63px;"></view>
		</view>
	</uni-popup>
</template>

<script lang="ts" setup>
import {onMounted, ref} from 'vue'
import {getRandomQuestions} from '../../utils/api/question';
import {savePaper} from '../../utils/api/paper';

/** 获取登录信息 */
	const userInfo = ref<Quiz.UserInfo>()
	/** 题目 */
	const paper = ref<Quiz.Paper>({} as Quiz.Paper)
	/** 题目 */
	const questions = ref<Quiz.Question[]>()
	/** 选择的答案 */
	const options = ref<number[]>()
	/** 输入框的值 */
	const inputValues = ref<string[]>()
	/** 输入框高度 */
	const style = ref()
	/** 当前题目下标 */
	const questionIndex = ref<number>(0)
	/** 当前活动弹框 */
	const activityPopup = ref<string>("")
	/** 弹出层ref */
	const popupRef = ref()
	/** 弹出层高度 */
	const popupStyle = ref()
	/** 切换题目 */
	function onChange(e : any) {
		questionIndex.value = e.detail.current
	}
	/** 点击选项 */
	function onButtonClick(index : number) {
		console.log(`questionIndex.value:${questionIndex.value}, index:${index}`);
		options.value[questionIndex.value] = index
		if (questionIndex.value < questions.value.length - 1) {
			questionIndex.value++
		} else {
			for (var i = 0; i < options.value.length; i++) {
				if (options.value[i] == -1) {
					questionIndex.value = i
					return
				}
			}
			paper.value.creatorUserId = userInfo.value.userId
			paper.value.answers = options.value.join('@@')
			paper.value.questions = questions.value
      savePaper(paper.value).then((res) => {
				paper.value = res.data
				uni.navigateTo({
					url: `/pages/set-test-finish/set-test-finish?paperId=${res.data.paperId}&userId=${userInfo.value.userId}`
				});
			})
		}
	}
	/** 键盘输入触发 */
	function onInput(e : any) {
		inputValues.value[questionIndex.value] = e.detail.value
	}
	/** 键盘失去焦点触发 */
	function onBlur(e : any) {
		inputValues.value[questionIndex.value] = e.detail.value
	}
	/** 键盘高度发生变化 */
	function keyboardheightchange(e : any) {
		style.value = {
			top: `-${e.detail.height - 83}px`,
		}
	}
	/** 点击底部svg切换活动弹窗 */
	function onChangeActivityPopup(v : string) {
		if (activityPopup.value && activityPopup.value == v) {
			activityPopup.value = ''
			popupRef.value.close()
		} else {
			activityPopup.value = v
			popupRef.value.open()
		}
	}
	/** 点击遮罩层触发 */
	function onMaskClick() {
		activityPopup.value = ''
	}
	/** 点击弹出框题目触发,跳转到点击题目并且关闭弹出框 */
	function onClickPopupQuestion(index : number) {
		questionIndex.value = index
		activityPopup.value = ''
		popupRef.value.close()
	}
	onMounted(() => {
		userInfo.value = getApp().globalData.userInfo
    getRandomQuestions(userInfo.value.userId).then(res => {
			questions.value = res.data
			options.value = new Array(res.data.length).fill(-1)
			inputValues.value = new Array(res.data.length).fill('')
		})
		popupStyle.value = {
			height: `${getApp().globalData.mainHeight}px`
		}
	})
</script>