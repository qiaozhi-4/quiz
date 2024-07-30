<style lang="scss">
	.page {
		background: linear-gradient(180deg, #2F1969 48.5%, #5830C7 78%, #A729E2 100%);

		.main {

			.swiper {
				margin-top: 30px;
				height: auto;
				height: 43vh;

				.swiper-item {
					align-items: center;

					.text {
						font-family: 'Inter';
						font-style: normal;
						font-weight: 700;
						font-size: 11px;
						line-height: 13px;
						text-align: center;

						color: #FFFFFF;
					}

					.unknown {
						display: flex;
						align-items: center;
						justify-content: center;
						/* ？ */
						width: 180px;
						height: 180px;

						box-sizing: border-box;
						border: 1.03077px dashed #FFFFFF;
						border-radius: 15.4615px;

						background: rgba(255, 255, 255, 0.5);
						font-family: 'Inter';
						font-style: normal;
						font-weight: 700;
						font-size: 140px;
						text-align: center;

						color: #FFFFFF;

						opacity: 0.3;
					}
				}
			}

			.t1 {
				/* 更多奖章🎖️ */
				padding: 10px 20px;

				font-family: 'Inter';
				font-style: normal;
				font-weight: 800;
				font-size: 20px;
				line-height: 24px;

				color: #FFFFFF;
			}

			.list {
				display: grid;
				grid-template-columns: repeat(3, 1fr);
				justify-content: center;
				justify-items: center;
				gap: 12px;

				padding: 10px 20px;

				.item {
					align-items: center;

					.text {
						font-family: 'Inter';
						font-style: normal;
						font-weight: 700;
						font-size: 10px;
						line-height: 12px;
						text-align: center;

						color: #FFFFFF;
					}

					.unknown {
						display: flex;
						align-items: center;
						justify-content: center;
						/* ？ */
						width: 84.52px;
						height: 84.52px;

						box-sizing: border-box;
						border: 1.03077px dashed #FFFFFF;
						border-radius: 15.4615px;

						background: rgba(255, 255, 255, 0.5);
						font-family: 'Inter';
						font-style: normal;
						font-weight: 700;
						font-size: 50.5263px;
						line-height: 61px;
						text-align: center;

						color: #FFFFFF;

						opacity: 0.3;
					}
				}

			}
		}
	}

	.carousel-indicators {
		display: flex;
		justify-content: center;
		gap: 10px;
		height: 10px;

		.dot {

			width: 10px;
			height: 10px;
			background-color: rgba(#FFFFFF, 0.3);
			border-radius: 50%;
			cursor: pointer;
			transition: transform 0.3s ease;
			/* 添加过渡效果 */
		}

		.active {
			background-color: #FFFFFF;
			transform: scale(1.2);
			/* 放大效果 */
		}
	}
</style>

<template>
	<view class="page">
		<q-nav-bar class="head-sticky" leftIcon="头部导航-返回" title="我的徽章" />
		<view class="main flex-column">
			<template v-for="(item,index) in badgeData" :key="index">
				<swiper class="swiper" v-show="index == activateBadge" :current="currentIndex" @change="onChange"
					:circular="false">
					<template v-if="item?.count == 0">
						<swiper-item class="swiper-item flex-column">
							<text class="unknown">?</text>
							<q-image src="加0分" width="100vw" height="100"></q-image>
							<text class="text">您目前还没有解锁过该徽章呢！</text>
						</swiper-item>
					</template>
					<template v-else>
						<swiper-item class="swiper-item flex-column" v-for="(badge,index) in item?.badges" :key="index">
							<q-image :src="item?.icon" width="180" height="180"></q-image>
							<q-image :src="item?.scoreIcon" width="100vw" height="100"></q-image>
							<text class="text">{{badge?.date}}</text>
							<text class="text">您获得了{{badge?.nickname}}的 “{{item?.name}}”勋章。</text>
						</swiper-item>
					</template>
				</swiper>
			</template>
			<view class="carousel-indicators">
				<text class="dot" v-for="(dot, index) in visibleDots" :key="index"
					:class="{ active: dot === currentIndex }" />
			</view>
			<text class="t1">更多奖章🎖️</text>
			<view class="list">
				<template v-for="(item,index) in badgeData" :key="index">
					<view class="item flex-column" v-if="item?.count > 0" @click="onBadge(index)">
						<q-image :src="item?.icon" width="90" height="90"></q-image>
						<text class="text">{{item?.count}}枚</text>
						<text class="text">{{item?.name}}徽章</text>
					</view>
					<view class="item flex-column" v-else @click="onBadge(index)">
						<text class="unknown">?</text>
						<text class="text">0枚</text>
						<text class="text">待解锁未知徽章</text>
					</view>
				</template>
			</view>
		</view>
	</view>
</template>

<script lang="ts" setup>
	import { ref, onMounted, computed } from 'vue'
	import { formatDate } from '../../utils/utils';
	/** 徽章数据 */
	const badgeData = ref([])
	/** 当前点击的徽章 */
	const activateBadge = ref(0)
	/** 当前徽章下标 */
	const currentIndex = ref(0)

	/** 切换轮播图触发 */
	function onChange(e) {
		currentIndex.value = e.detail.current
	}

	/** 计算应该显示的指示点 */
	const visibleDots = computed(() => {
		let start = currentIndex.value - 1;
		let end = currentIndex.value + 1;
		let totalSlides = badgeData.value[activateBadge.value].count

		// 确保 start 和 end 在合理范围内
		if (start < 0) {
			start = 0;
			end = 2;
		}
		if (end >= totalSlides) {
			end = totalSlides - 1;
			start = totalSlides - 3;
		}
		if (totalSlides <= 3) {
			start = 0;
			end = totalSlides - 1;
		}

		// 返回当前需要显示的指示点数组
		const result = [];
		for (let i = start; i <= end; i++) {
			result.push(i);
		}
		return result;
	});

	/** 点击徽章 */
	function onBadge(index : number) {
		activateBadge.value = index
		currentIndex.value = 0
	}
	onMounted(() => {

		badgeData.value[0] = {
			name: '灵魂之友',
			count: 10,
			badges: [],
			icon: '金徽章',
			scoreIcon: '加100分',
		}
		badgeData.value[1] = {
			name: '蓝颜知己',
			count: 3,
			badges: [],
			icon: '银徽章',
			scoreIcon: '加90分',
		}
		badgeData.value[2] = {
			name: '意气相投',
			count: 1,
			badges: [],
			icon: '铜徽章',
			scoreIcon: '加80分',
		}
		for (var i = 0; i < 3; i++) {
			for (var j = 0; j < badgeData.value[i].count; j++) {
				badgeData.value[i].badges.push({
					date: formatDate(new Date, 'YYYY/MM/DD'),
					nickname: `乔治${j + 1}`,
				})
			}
		}
		for (var i = 0; i < 6; i++) {
			badgeData.value.push({
				count: 0,
			})
		}
	})
</script>