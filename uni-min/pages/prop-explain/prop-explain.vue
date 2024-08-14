<style lang="scss">
	.page {
		/* 动画超黑渐变 */
		background: linear-gradient(180deg, #371E7A 33.79%, #3E228B 67.59%, #8836C1 100%);

		.main {
			padding-top: 70px;
			align-items: center;

			.t1 {
				margin: 0 90px;
				/* 复活宝石让尊贵的您获得重新再次答题的机会！ */
				font-family: 'Inter';
				font-style: normal;
				font-weight: 500;
				font-size: 16px;
				line-height: 19px;
				text-align: center;

				color: #FFFFFF;
			}

			.t2 {
				/* 复活宝石 */
				font-family: 'Inter';
				font-style: normal;
				font-weight: 700;
				font-size: 40px;
				line-height: 48px;
				text-align: center;

				color: #FFFFFF;
			}

			.img {
				mix-blend-mode: color-dodge;
			}

			.count {
				font-family: 'Inter';
				font-style: normal;
				font-weight: 900;
				font-size: 40px;
				line-height: 48px;
				text-align: center;

				color: #FFFFFF;
			}

			.unit {
				/* 颗 */
				font-family: 'Inter';
				font-style: normal;
				font-weight: 700;
				font-size: 16px;
				line-height: 19px;
				text-align: center;

				color: #FFFFFF;
			}
		}

		.footer {
			align-items: center;
			gap: 10px;

			padding-top: 65px;

			.b1 {
				box-sizing: border-box;
				border: 1px solid rgba(255, 255, 255, 0.3);

				width: 294px;
				height: 48px;

				/* 三层发光渐变按钮 */
				background: linear-gradient(90deg, #933DDA 0%, #4B48EB 41.19%, #C547EA 100%);
				border-radius: 30px;

				font-family: 'Inter';
				font-style: normal;
				font-weight: 700;
				font-size: 28px;
				line-height: 34px;
				/* identical to box height */
				text-align: center;

				color: #FFFFFF;
			}

			.b2 {
				box-sizing: border-box;
				border: 1px solid rgba(255, 255, 255, 0.3);

				width: 294px;
				height: 48px;

				background: rgba(255, 255, 255, 0.1);
				border-radius: 30px;

				font-family: 'Inter';
				font-style: normal;
				font-weight: 800;
				font-size: 28px;
				line-height: 34px;
				/* identical to box height */
				text-align: center;

				color: #FFFFFF;
			}

			.t1 {
				/* 宝石领取后30天后将失去光泽，请及时使用。 */
				font-family: 'Inter';
				font-style: normal;
				font-weight: 500;
				font-size: 12px;
				line-height: 15px;
				/* identical to box height */
				text-align: center;

				color: rgba(255, 255, 255, 0.3);
			}
		}
	}
</style>

<template>
	<view class="page">
		<q-nav-bar leftIcon="头部导航-返回" />
		<view class="main flex-column">
			<text class="t1">{{prop?.propDescribe}}</text>
			<text class="t2">{{prop?.propName}}</text>
			<q-image :src="'/static/img/' +prop?.propName+'.png'" width="170" height="170"></q-image>
			<view>
				<text class="count">{{prop?.number}}</text>
				<text class="unit">颗</text>
			</view>
		</view>
		<view class="footer flex-column">
			<button class="b1">免费领取1颗</button>
			<button class="b2">做任务领宝石！</button>
			<text class="t1">宝石领取后30天后将失去光泽，请及时使用。</text>
		</view>
	</view>
</template>

<script lang="ts" setup>
	import { ref, onMounted } from 'vue'
	import { onLoad } from '@dcloudio/uni-app'
	/** 道具信息 */
	const prop = ref<Quiz.Prop>()

	onLoad((option) => {
		console.log(option);
		prop.value = getApp().globalData.props.filter((e : Quiz.Prop) => option.id == e.propId)[0]
		console.log(prop.value);
		/** 页面跳转过来的参数 */
		// switch (option.id) {
		// 	case '1':
		// 		prop.value = {
		// 			name: '复活宝石',
		// 			icon: '复活宝石',
		// 			count: 1,
		// 			explain: '复活宝石让尊贵的您获得重新再次答题的机会！'
		// 		}
		// 		break;
		// 	case '2':
		// 		prop.value = {
		// 			name: '提示宝石',
		// 			icon: '提示宝石',
		// 			count: 1,
		// 			explain: '提示宝石让尊贵的您答题时获得答案相关的提示！'
		// 		}
		// 		break;
		// 	default:
		// 		prop.value = {
		// 			name: '复活宝石',
		// 			icon: '复活宝石',
		// 			count: 1,
		// 			explain: '复活宝石让尊贵的您获得重新再次答题的机会！'
		// 		}
		// 		break;
		// }
	})
</script>