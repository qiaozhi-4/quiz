<style lang="scss">
	.page {
		background: #1F1146;

		.q-nav-bar-middle {
			/* my Quiz */
			font-family: 'Inter';
			font-style: italic !important;
			font-weight: 900 !important;
			font-size: 24px !important;
			line-height: 29px;
		}

		.main {
			padding: 10px;
			padding-bottom: 50px;

			.v-global {
				align-items: center;

				background: rgba(78, 63, 246, 0.15);
				backdrop-filter: blur(2px);
				border-radius: 15px;
			}

			.v1 {
				display: grid;
				grid-template-columns: 1fr auto;
				gap: 12px;
				width: 100%;


				.achievement-score {
					grid-row: 1 / 3;
					align-items: start;
					gap: 8px;
					padding: 17px;

					.t1 {
						font-family: 'Inter';
						font-style: normal;
						font-weight: 900;
						font-size: 16px;
						line-height: 19px;

						color: #4E3FF6;
					}

					.t2 {
						font-family: 'Inter';
						font-style: normal;
						font-weight: 900;
						font-size: 36px;
						line-height: 44px;

						color: #FFFFFF;
					}
				}
				.gem{
					padding: 8px 22px;
				}

				.badge {
					grid-column: 1/3;
					gap: 10px;
					padding: 9px 22px;

					.count {
						flex-grow: 1;
					}
				}

				.count {
					font-family: 'Inter';
					font-style: normal;
					font-weight: 700;
					font-size: 16px;
					line-height: 19px;

					color: #FFFFFF;
				}
			}
		}
	}
</style>

<template>
	<view class="page">
		<q-nav-bar titleExtraClass="head-title" leftIcon="头部导航-返回" title="my Quiz" />
		<view class="main">
			<view class="v1">
				<view class="v-global achievement-score flex-column">
					<text class="t1">目前成就</text>
					<text class="t2">{{score}}分</text>
				</view>
				<view class="v-global gem">
					<q-svg icon="复活宝石" size="33" />
					<text class="count">{{prop.resurgenceGem.count}}</text>
				</view>
				<view class="v-global gem">
					<q-svg icon="提示宝石" size="33" />
					<text class="count">{{prop.hintGem.count}}</text>
				</view>
				<view class="v-global badge">
					<q-svg icon="徽章" size="33" />
					<text class="count">一共获得{{prop.badge.count}}枚徽章</text>
					<q-svg icon="空心向右箭头" size="14" />
				</view>
			</view>
			<view class="v2">

			</view>
		</view>
	</view>
</template>

<script lang="ts" setup>
	import { ref, onMounted } from 'vue'
	/** 成就总分数 */
	const score = ref()
	/** 道具信息 */
	const prop = ref({
		resurgenceGem: {
			count: 12,
		},
		hintGem: {
			count: 12,
		},
		badge: {
			count: 17,
		},
	})
	/** 任务数据 */
	const tasks = ref({

	})

	onMounted(() => {
		score.value = 545120
	})
</script>