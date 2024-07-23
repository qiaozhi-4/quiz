<style lang="scss">
	.page {
		background: #1F1146;

		.head {
			display: grid;
			place-content: end start;
		}

		.main {

			.v1 {
				justify-content: center;
				align-items: center;
				padding: 10px;

				.avatar-button::after {
					border: none;
				}

				.avatar-button {

					background-color: transparent;
				}

				.nickname {
					font-family: 'Inter';
					font-style: normal;
					font-weight: 700;
					font-size: 20px;
					line-height: 24px;
				}
			}

			.v2 {
				justify-content: center;
				align-items: center;
				padding: 10px;
				gap: 10px;

				.statistics {
					justify-content: center;
					align-items: flex-end;
					padding: 6px 26px 9px;
					gap: 6px;

					width: 110px;
					height: 32px;

					background: rgba(255, 255, 255, 0.05);
					border-radius: 15px;

					.text1 {
						font-family: 'Inter';
						font-style: normal;
						font-weight: 800;
						font-size: 20px;
						line-height: 24px;
					}

					.text2 {
						font-family: 'Inter';
						font-style: normal;
						font-weight: 500;
						font-size: 12px;
						line-height: 15px;
						color: rgba(255, 255, 255, 0.3);
					}
				}
			}

			.v3 {
				background: #5830C7;
				border-radius: 15px 15px 0px 0px;

				.task {
					display: grid;
					grid-template-columns: 1fr 1fr;
					margin: 0 15px 15px 15px;

					padding: 10px 13px;
					gap: 10px;

					background: #46269E;
					border-radius: 0px 0px 15px 15px;

					.t1 {
						/* 10答0问 */
						font-family: 'Inter';
						font-style: normal;
						font-weight: 800;
						font-size: 20px;
						line-height: 24px;

						color: #FFFFFF;
					}

					.t2 {
						/* 本周连续回答10题 */
						font-family: 'Inter';
						font-style: normal;
						font-weight: 700;
						font-size: 12px;
						line-height: 15px;

						color: rgba(255, 255, 255, 0.5);
					}

					.b1 {
						grid-column: 2;
						grid-row: 1 / span 2;
						place-self: center end;

						width: 89px;
						height: 30px;
						/* 淡紫 粉色渐变 */
						background: linear-gradient(90deg, #5830C7 0%, #BE53FF 100%);
						border-radius: 30px;

						/* 领取奖励 */
						font-family: 'Inter';
						font-style: normal;
						font-weight: 700;
						font-size: 12px;
						line-height: 15px;
						text-align: center;

						color: #FFFFFF;
					}
				}

				.table {

					.table-tags {
						position: sticky;
						/* 当滚动到距离顶部 0 像素时粘住 */
						top: 0;
						/* 确保粘性元素在其他元素上方 */
						z-index: 1;
						padding-top: 0;
						transition: padding-top 0.2s linear;
						background: #5830C7;

						justify-content: space-between;

						.tag {
							width: 100%;
							padding: 15px 0px;
							justify-content: center;
							align-items: center;

							/* 亲密排行 */
							font-family: 'Inter';
							font-style: normal;
							font-weight: 700;
							font-size: 16px;
							line-height: 19px;
							text-align: center;

							background: #1F1146;
							color: rgba(255, 255, 255, 0.3);
						}

						.left {
							border-radius: 20px 0 0px 0px;
						}

						.right {
							border-radius: 0 20px 0px 0px;
						}

						.active {
							position: relative;
							color: #fff;
							background: #5830C7;
							border-radius: 0px 0px 20px 20px;
						}

						.active::after {
							left: 0;
							background: radial-gradient(circle at 100% 0%, transparent 20px, #1F1146 0);
						}

						.active::before {
							right: 0;
							background: radial-gradient(circle at 0% 0%, transparent 20px, #1F1146 0);
						}

						.active::after,
						.active::before {
							content: '';
							position: absolute;
							bottom: 0;
							width: 20px;
							height: 20px;
							/* 设置过渡效果 */
							animation: moveAndFadeIn 0.5s ease;
						}

						@keyframes moveAndFadeIn {
							0% {
								height: 0;
							}

							100% {
								height: 20px;
							}
						}
					}

					.table-content {
						background: #1F1146;

						justify-content: start;
						align-items: center;

						padding-top: 10px;

						.table-null-text {
							/* 您的亲密排行空空如也 🌟快去邀请朋友吧！ */
							font-family: 'Inter';
							font-style: normal;
							font-weight: 700;
							font-size: 32px;
							line-height: 39px;
							text-align: center;

							color: #FFFFFF;
						}

						.t1 {
							padding-top: 50px;
						}

						.table-null-button {
							margin-top: 20px;
							box-sizing: border-box;
							display: flex;
							flex-direction: row;
							justify-content: center;
							align-items: center;
							padding: 9px 0px;

							width: 244px;
							height: 52px;

							/* 淡紫 粉色渐变 */
							background: linear-gradient(90deg, #5830C7 0%, #BE53FF 100%);
							border: 1px solid rgba(255, 255, 255, 0.3);
							box-shadow: 0px 0px 14px 4px rgba(255, 255, 255, 0.25);
							border-radius: 30px;

							font-family: 'Inter';
							font-style: normal;
							font-weight: 700;
							font-size: 28px;
							line-height: 34px;

							color: #FFFFFF;
						}

						.table-item {
							display: grid;
							grid-template-columns: auto auto 1fr auto auto;
							align-items: center;
							width: 100%;
							height: 54px;
							padding: 0 15px;

							.ranking {
								padding-right: 15px;

								font-family: 'Inter';
								font-style: normal;
								font-weight: 700;
								font-size: 16px;
								line-height: 19px;
							}

							.nickname {
								justify-self: start;
								padding-left: 5px;

								font-family: 'Inter';
								font-style: normal;
								font-weight: 700;
								font-size: 16px;
								line-height: 19px;
							}

							.score {
								font-family: 'Inter';
								font-style: normal;
								font-weight: 800;
								font-size: 20px;
								line-height: 24px;
								text-align: center;

								color: #A729E2;
							}

							.svg {
								justify-self: end;

								padding-left: 15px;
							}

							.no1 {
								background: linear-gradient(251.44deg, #F5A022 19.28%, #FFCA10 80.72%);
								background-clip: text;
								-webkit-background-clip: text;
								color: transparent;
								text-shadow: 0px 0px 20px #FFAD55;
							}

							.no2 {
								background: linear-gradient(180deg, #B3B3B3 0%, #F5F5F5 100%);
								background-clip: text;
								-webkit-background-clip: text;
								color: transparent;
								text-shadow: 0px 0px 20px #F5F5F5;
							}

							.no3 {
								background: linear-gradient(180deg, #B37F43 0%, #D0995A 100%);
								background-clip: text;
								-webkit-background-clip: text;
								color: transparent;
								text-shadow: 0px 0px 20px #D0995A;
							}
						}
					}
				}
			}
		}
	}
</style>

<template>
	<!-- <view class="page"> -->
	<scroll-view class="page" scroll-y="true" @scroll="onScroll" :scroll-top="scrollTop" scroll-with-animation>
		<view class="head" :style="headStyle">
			<q-svg icon="个人主页-头部-加号" size="32" />
		</view>
		<view class="main flex-column">
			<view class="v1 flex-column">
				<button class="avatar-button" open-type="chooseAvatar" @chooseavatar="onChooseAvatar">
					<q-avatar :src="userInfo?.avatarUrl" size="69" borderWidth="3"></q-avatar>
				</button>
				<view class="nickname">{{userInfo?.nickname}}</view>
			</view>
			<view class="v2">
				<view class="statistics">
					<text class="text1">0</text>
					<text class="text2">出题</text>
				</view>
				<view class="statistics">
					<text class="text1">0</text>
					<text class="text2">答题</text>
				</view>
				<view class="statistics">
					<text class="text1">0</text>
					<text class="text2">密友</text>
				</view>
			</view>
			<view class="v3 flex-column">
				<view class="task">
					<text class="t1">10答0问</text>
					<text class="t2">本周连续回答10题</text>
					<button class="b1">领取奖励</button>
				</view>
				<view class="table flex-column">
					<view class="table-tags" :style="{paddingTop:`${tagPaddingTop}px`}">
						<view class="tag"
							:class="{active:activeTag==index, left:index==activeTag+1, right:index==activeTag-1 }"
							v-for="(tag,index) in tags" :key="index" @click="onTag(index)">
							{{tag}}
						</view>
					</view>
					<view v-show="activeTag==0" class="table-content flex-column">
						<template v-if="friends.length!=0">
							<view class="table-item" v-for="(friend, index) in friends" :key="index">
								<view class="ranking" :class="`no${index+1}`">{{index+1}}</view>
								<q-avatar :src="friend.avatarUrl" size="42" />
								<view class="nickname" :class="`no${index+1}`">{{friend.nickname}}</view>
								<view class="score" :class="`no${index+1}`">{{friend.score}}</view>
								<q-svg class="svg" icon="个人主页-排行-转到好友" size="22" />
							</view>
						</template>
						<template v-else>
							<text class="table-null-text t1">您的亲密排行空空如也</text>
							<text class="table-null-text t2">🌟快去邀请朋友吧！</text>
							<button class="table-null-button b1">让朋友测测！</button>
						</template>
					</view>
					<view v-show="activeTag==1" class="table-content flex-column">
						<view class="table-null" style="min-height: 100vh;">测试信息</view>
					</view>
					<view v-show="activeTag==2" class="table-content flex-column">
						<view class="table-null" style="min-height: 100vh;">测试记录</view>
					</view>
				</view>
			</view>
		</view>
	</scroll-view>
	<!-- </view> -->
</template>

<script lang="ts" setup>
	import { ref, onMounted } from 'vue'
	/** 获取登录信息 */
	const userInfo = ref<Quiz.UserInfo>()
	/** 头部样式 */
	const headStyle = getApp().globalData.headStyle
	/** 头部高度 */
	const headHeight = getApp().globalData.headHeight
	/** 主体高度 */
	const mainHeight = getApp().globalData.mainHeight
	/** 标签 */
	const tags = ref(['亲密排行', '测试信息', '测试记录'])
	/** 当前激活的标签 */
	const activeTag = ref(0)
	/** 滚动条位置 */
	const scrollTop = ref(0)
	/** 标签触发粘性定位的上边距 */
	const tagPaddingTop = ref(0)
	/** 亲密排行榜数据 */
	const friends = ref([])

	/** 获取微信头像触发 */
	function onChooseAvatar(e) {
		console.log("获取微信头像触发", e);
		const { avatarUrl } = e.detail
		userInfo.value.avatarUrl = avatarUrl
	}
	/** 页面滚动触发 */
	function onScroll(e) {
		if (e.detail.scrollTop > 250) {
			tagPaddingTop.value = headHeight
		} else {
			tagPaddingTop.value = 0
		}
	}
	/** 点击tag触发 */
	function onTag(index) {
		scrollTop.value = 0
		scrollTop.value = 351
		activeTag.value = index
	}
	onMounted(() => {
		userInfo.value = getApp().globalData.userInfo
		for (var i = 0; i < 20; i++) {
			friends.value.push({
				nickname: `用户${i + 1}`,
				score: (20 - i) * 1000,
				avatarUrl: ''
			})
		}
	})
</script>