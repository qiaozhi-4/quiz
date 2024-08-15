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
				gap: 8px;
				padding: 10px 0;

				.item {
					padding: 8px 11px;
					gap: 20px;

					background: rgba(255, 255, 255, 0.07);
					border-radius: 15px;

					.user-info {
						justify-content: center;
						align-items: center;
						gap: 2px;

						.nickname {
							font-family: 'Inter';
							font-style: normal;
							font-weight: 600;
							font-size: 12px;
							line-height: 15px;
							text-align: center;

							color: #FFFFFF;
						}
					}

					.topic {
						position: relative;
						gap: 4px;
						justify-content: center;

						.svg {
							position: absolute;
							left: -10px;
							top: 10px;
						}

						.title {
							font-family: 'Inter';
							font-style: normal;
							font-weight: 300;
							font-size: 12px;
							line-height: 15px;

							color: #FFFFFF;
						}

						.answer {
							font-family: 'Inter';
							font-style: normal;
							font-weight: 900;
							font-size: 16px;
							line-height: 19px;

							color: #FFFFFF;
						}
					}
				}
			}
		}


	}
</style>

<template>
	<view class="page">
		<q-nav-bar needBack title="选择一条朋友的看法" titleSize="16" fixed></q-nav-bar>
		<view class="main flex-column">
			<view class="input-wrap">
				<q-svg class="svg" icon="搜索" size="14.5" />
				<input class="input" placeholder-class="input-placeholder" placeholder="搜索" :adjust-position="false"
					@input="onInput" @blur="onBlur" :value="input" />
			</view>

			<view class="data flex-column">
				<view class="item" v-for="(item,index) in data" :key="index" @click="onClickPopupQuestion(index)">
					<view class="user-info flex-column">
						<q-avatar :src="item?.avatarUrl" size="41"></q-avatar>
						<view class="nickname">{{item?.nickname}}</view>
					</view>
					<view class="topic flex-column">
						<q-svg class="svg" icon="左双引号" size="10" />
						<text class="title text-overflow">{{item?.title}}</text>
						<text class="answer text-overflow">{{item?.answer}}</text>
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
		/** 关闭当前页面,跳转到home页面,并且传递friendId[后续要改为插入数据库] */
		uni.redirectTo({
			url: `/pages/home/home?friendId=${id}`
		});

	}
	onMounted(() => {
		for (var i = 0; i < 20; i++) {
			data.value.push({
				nickname: `用户${i + 1}`,
				avatarUrl: '',
				title: '喜欢哪一种天气？',
				answer: '喜欢不冷不热不雨不晴，阴天最好',
			})
		}
	})
</script>