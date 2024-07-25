<style lang="scss">
	.page {
		background: #1F1146;

		.main {
			padding: 0 12.5px;

			.input-wrap {
				align-items: center;
				gap: 5px;
				background: rgba(255, 255, 255, 0.1);
				padding: 7px 18px;
				border-radius: 30px;

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

			.data {
				padding: 10px 0;
				gap: 8px;

				.item {
					padding: 11px 13px;
					gap: 3px;
					background: rgba(255, 255, 255, 0.1);
					border-radius: 15px;

					.topic {
						gap: 6px;

						.title {
							font-family: 'Inter';
							font-style: normal;
							font-weight: 500;
							font-size: 12px;
							line-height: 15px;

							color: #FFFFFF;

							opacity: 0.7;
						}

						.v1 {
							gap: 6px;
							justify-content: space-between;

							.answer {
								font-family: 'Inter';
								font-style: normal;
								font-weight: 700;
								font-size: 16px;
								line-height: 19px;
								letter-spacing: -0.04em;

								color: #FFFFFF;
							}

							.input-v {
								align-self: center;
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
	}
</style>

<template>
	<view class="page">
		<q-nav-bar leftIcon="头部导航-返回" title="选择一道题" titleSize="16"></q-nav-bar>
		<view class="main flex-column">
			<view class="input-wrap">
				<q-svg icon="搜索" size="14.5" />
				<input class="input" placeholder-class="input-placeholder" placeholder="搜索" :adjust-position="false"
					@input="onInput" @blur="onBlur" :value="input" />
			</view>

			<view class="data flex-column">
				<view class="item " v-for="(item,index) in data" :key="index" @click="onClickPopupQuestion(index)">
					<q-svg class="svg" icon="左双引号" size="10" />
					<view class="topic flex-column">
						<text class="title text-overflow">{{item?.title}}</text>
						<view class="v1">
							<text class="answer text-overflow">{{item?.answer}}</text>
							<text class="input-v text-overflow">{{item?.input}}</text>
						</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script lang="ts" setup>
	import { ref, onMounted } from 'vue'
	/** 搜索框输入 */
	const input = ref('')
	/** 朋友看法数据 */
	const data = ref([])
	/** 键盘输入触发 */
	function onInput(e : any) {
		input.value = e.detail.value
	}
	/** 键盘失去焦点触发 */
	function onBlur(e : any) {
		input.value = e.detail.value
	}
	/** 点击某条数据触发 */
	function onClickPopupQuestion(id) {
		console.log(id);
		/** 关闭当前页面,跳转到home页面,并且传递topicId[后续要改为插入数据库] */
		uni.redirectTo({
			url: `/pages/home/home?topicId=${id}`
		});

	}
	onMounted(() => {
		for (var i = 0; i < 20; i++) {
			data.value.push({
				title: '喜欢哪一种天气？',
				answer: '喜欢不冷不热不雨不晴，阴天最好',
				input: '哈哈'
			})
		}
	})
</script>