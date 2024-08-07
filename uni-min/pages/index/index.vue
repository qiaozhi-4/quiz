<style lang="scss">
	.main {
		display: grid;
		grid-template-columns: repeat(2, 1fr);
		place-content: start center;
		place-items: center;
		gap: 10px;
	}

	.main2 {
		align-items: center;
		justify-content: center;
		height: 100%;

		.svg {
			padding: 280px 0;
		}


	}

	.footer {
		align-items: center;
		justify-content: start;
		height: 145px;
		gap: 10px;

		.progress-wrap,
		.progress {
			box-sizing: border-box;
			border-radius: 15px;
			border: 3px solid #FFFFFF;
		}

		.progress-wrap {
			position: relative;
			background: linear-gradient(90deg, #BE53FF 0%, #5830C7 100%);
			height: 28px;
			width: 90%;

			.progress {
				position: absolute;
				background: #FFFFFF;
				height: 100%;
				top: 0;
				left: 0;

				animation: expandWidth 1s ease-in-out forwards;
				/* 动画效果 */
			}

			@keyframes expandWidth {
				from {
					width: 0;
				}

				to {
					width: 100%;
				}
			}
		}

		.text {
			font-family: 'Inter';
			font-style: normal;
			font-weight: 400;
			font-size: 10px;
			line-height: 12px;

			color: rgba(255, 255, 255, 0.5);
		}
	}
</style>

<template>
	<view class="page">
		<view class="main" v-show="false">
			<button class="button text-overflow" v-for="(page,index) in pages" :key="index" type="primary"
				@click="handleTo(page.name)">{{page.name}}</button>
		</view>
		<view class="main2 flex-column">
			<q-svg class="svg" icon="log" size="104"></q-svg>
		</view>
		<view class="footer flex-column">
			<view class="progress-wrap">
				<view class="progress"></view>
			</view>
			<text class="text">“我不关心你来自何处，我只关心你要去向何方”——Quiz</text>
		</view>
	</view>
</template>

<script lang="ts" setup>
	const pages = [
		{ name: 'ding-dong' },
		{ name: 'set-user-info' },
		{ name: 'start-test' },
		{ name: 'answer-test' },
		{ name: 'answer-finish' },
		{ name: 'award' },
		{ name: 'home' },
		{ name: 'friend-home' },
		{ name: 'select-topic' },
		{ name: 'select-friend-view' },
		{ name: 'question-particulars' },
		{ name: 'task' },
		{ name: 'prop-explain' },
		{ name: 'badge' },
	]
	function handleTo(pageName : String) {
		uni.navigateTo({
			url: `/pages/${pageName}/${pageName}?id=1&pageName=${pageName}`
		});
	}
</script>