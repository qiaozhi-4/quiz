<style lang="scss">
	.page {
		background: linear-gradient(180deg, #2F1969 48.5%, #5830C7 78%, #A729E2 100%);

		.main {
			align-items: center;
			justify-content: start;
			padding: 10px 12px;
			// flex-grow: 1;
			gap: 10px;

			.v1 {
				width: 100%;
				justify-content: flex-end;
				align-items: center;
				gap: 8px;

				.ball {
					width: 15px;
					height: 15px;
					border-radius: 50%;
				}

				.right {
					background: linear-gradient(180deg, #A4FF48 0%, #CFF33C 100%);
					box-shadow: 4px 0px 4px rgba(112, 184, 39, 0.25);
				}

				.wrong {
					background: linear-gradient(180deg, #FF4848 0%, #F3733C 100%);
					box-shadow: 4px 0px 4px rgba(231, 40, 28, 0.25);
				}
			}

			.avatar-group {
				gap: 8px;
				justify-content: center;
			}

			.t1 {
				font-family: 'Inter';
				font-style: normal;
				font-weight: 400;
				font-size: 20px;
				line-height: 24px;
				text-align: center;

				color: #FFFFFF;
			}

			.t2 {
				font-family: 'Inter';
				font-style: normal;
				font-weight: 700;
				font-size: 60px;
				line-height: 73px;
				text-align: center;

				color: #FFFFFF;
			}

			.t3 {
				font-family: 'Inter';
				font-style: normal;
				font-weight: 400;
				font-size: 10px;
				line-height: 12px;
				text-align: center;

				color: rgba(255, 255, 255, 0.75);
			}

			.but-group {
				align-items: center;
				justify-content: start;
				// height: 220px;
				gap: 10px;

				.but {
					justify-content: center;
					align-items: center;
					padding: 9px;

					width: 276px;

					border-radius: 30px;

					font-family: 'Inter';
					font-style: normal;
					font-weight: 700;
					font-size: 28px;
					line-height: 34px;
					/* identical to box height */

					color: #FFFFFF;
				}

				.b1 {
					background: rgba(255, 255, 255, 0.1);
				}

				.b2 {
					background: linear-gradient(90deg, #7756EC 0%, #AE2FDB 27.5%, #A729E2 98.5%);
				}

				.svg {
					width: 100%;
					position: relative;
					justify-content: flex-end;

					.badge {
						display: flex;
						justify-content: center;
						align-items: center;
						width: 21px;
						height: 21px;
						position: absolute;
						right: 0;
						top: 0;

						border-radius: 50%;
						background-color: #FFFFFF;

						font-family: 'Manrope';
						font-style: normal;
						font-weight: 800;
						font-size: 16px;
						line-height: 22px;

						color: #A143FF;

						// transform: translate(50%, -50%);

					}
				}
			}

		}

		.but-group {
			padding: 10px 12px;
			align-items: center;
			justify-content: start;
			height: 220px;
			gap: 10px;

			.but {
				margin: 0 38px;
				justify-content: center;
				align-items: center;
				padding: 9px;

				width: 100%;

				border-radius: 30px;

				font-family: 'Inter';
				font-style: normal;
				font-weight: 700;
				font-size: 28px;
				line-height: 34px;
				/* identical to box height */

				color: #FFFFFF;
			}

			.b1 {
				background: rgba(255, 255, 255, 0.1);
			}

			.b2 {
				background: linear-gradient(90deg, #7756EC 0%, #AE2FDB 27.5%, #A729E2 98.5%);
			}

			.svg {
				width: 100%;
				position: relative;
				justify-content: flex-end;

				.badge {
					display: flex;
					justify-content: center;
					align-items: center;
					width: 21px;
					height: 21px;
					position: absolute;
					right: 0;
					top: 0;

					border-radius: 50%;
					background-color: #FFFFFF;

					font-family: 'Manrope';
					font-style: normal;
					font-weight: 800;
					font-size: 16px;
					line-height: 22px;

					color: #A143FF;

					// transform: translate(50%, -50%);

				}
			}
		}

		.footer {
			position: sticky;
			bottom: 0;
			align-items: center;
			justify-content: start;
			box-sizing: border-box;
			padding: 11px 31px;
			gap: 6px;
			isolation: isolate;

			width: 100%;
			height: 124px;

			background: #2F1969;
			border: 1px solid rgba(255, 255, 255, 0.3);
			border-radius: 15px 15px 0px 0px;

			.t1 {
				font-family: 'Inter';
				font-style: normal;
				font-weight: 600;
				font-size: 12px;
				line-height: 15px;
				/* identical to box height */

				color: rgba(255, 255, 255, 0.75);

			}

			.but {
				display: flex;
				align-items: center;
				justify-content: center;
				gap: 5px;

				width: 310px;
				height: 51px;

				background: linear-gradient(89.13deg, #C84EED 0.25%, #873CFE 48.54%, #5623CD 96.47%);
				box-shadow: 0px 6px 20px #2F1969;
				border-radius: 15px;

				.t-t1 {
					font-family: 'Inter';
					font-style: normal;
					font-weight: 700;
					font-size: 24px;
					line-height: 29px;

					color: #FFFFFF;
				}
			}
		}
	}
</style>

<template>
	<view class="page">
		<q-nav-bar />
		<view class="main flex-column">
			<view class="v1">
				<view class="ball right"></view>
				<view>{{statistics?.correct}}</view>
				<view class="ball wrong"></view>
				<view>{{statistics?.total - statistics?.correct}}</view>
			</view>
			<view class="avatar-group">
				<q-avatar :src="userInfo?.avatarUrl" size="97" borderWidth="4"></q-avatar>
				<q-avatar :src="friendInfo?.avatarUrl" size="97" borderWidth="4"></q-avatar>
			</view>
			<text class="t1">恭喜你,你和{{friendInfo?.nickname}}是：</text>
			<text class="t1">{{scoreInfo[statistics?.correct]?.englishName}}</text>
			<text class="t2">{{scoreInfo[statistics?.correct]?.name}}</text>
			<q-image v-if="scoreInfo[statistics?.correct]?.badgeName" width="115" height="115"
				:src="scoreInfo[statistics?.correct]?.badgeName"></q-image>
			<view v-else style="width: 115px; height: 115px;"></view>
			<q-image :src="`加${statistics?.correct/statistics?.total*100}分`" width="195" height="40"></q-image>
			<text>最新亲密值将更新入排行榜中......</text>

			<view class="but-group flex-column">
				<button class="but b1" @click="goHome">查看排名</button>
				<button class="but b2" @click="goSetTest">我也去出题</button>
				<view class="svg">
					<q-svg icon="复活宝石" size="50" @click="showFooter = !showFooter" />
					<view class="badge">{{props.filter(item => item.propName === '复活宝石')[0].number}}</view>
				</view>
			</view>
		</view>
		<view class="footer flex-column" v-if="showFooter">
			<text class="t1">选快了？评分不满意？再答一次试试看！</text>
			<button class="but " @click="goStartTest">
				<q-svg icon="复活宝石" size="34" />
				<text class="b-t1">用复活宝石再次答题！</text>
			</button>
		</view>
	</view>
	<!-- 提示信息弹窗 -->
	<uni-popup ref="message" type="message">
		<uni-popup-message type="error" message="宝石不足" :duration="2000"></uni-popup-message>
	</uni-popup>
</template>

<script lang="ts" setup>
	import { ref } from 'vue';
	import { onLoad } from '@dcloudio/uni-app'
	import { getUserById } from '../../utils/api/user';
	import { scoreInfo } from '../../utils/constant';
	const message = ref()
	/** 路径参数 */
	type Option = {
		/** 题目总数 */
		total : number,
		/** 答对 */
		correct : number,
		/** 出题人id */
		userId : number
		/** 试卷id */
		paperId : number

	}
	/** 路径参数 */
	const statistics = ref<Option>()
	/** 朋友信息 */
	const friendInfo = ref<Quiz.UserInfo>()
	/** 获取登录信息 */
	const userInfo = ref<Quiz.UserInfo>()
	/** 获取道具信息 */
	const props = ref()
	/** 显示底部 */
	const showFooter = ref(false)
	/** 查看排行榜 */
	function goHome() {
		uni.redirectTo({
			url: `/pages/home/home`
		});
	}
	/** 跳转到出题页 */
	function goSetTest() {
		uni.navigateTo({
			url: `/pages/start-test/start-test?isAnswer=false`
		});
	}
	/** 重新答题 */
	function goStartTest() {
		if (props.value.filter(item => item.propName === '复活宝石')[0].number < 1) {
			message.value.open()
			return
		}
		uni.navigateTo({
			url: `/pages/start-test/start-test?isAnswer=true&paperId=${statistics.value.paperId}&userId=${userInfo.value.userId}`
		});
	}
	onLoad((option : Option) => {

		userInfo.value = getApp().globalData.userInfo
		props.value = getApp().globalData.props
		statistics.value = option
		showFooter.value = statistics.value.correct / statistics.value.total * 100 <= 30
		getUserById(option.userId).then(res => {
			friendInfo.value = res.data
		})
	})
</script>