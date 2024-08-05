<style lang="scss">
	.main {
		display: grid;
		justify-items: center;
		gap: 5px;
		padding: 0 36px;

		.avatar-button::after {
			border: none;
		}

		.avatar-button {
			width: 130px;
			height: 130px;
			padding: 0;
			margin-top: 42px;
			background-color: transparent;

			.change-avatar {
				width: 37px;
				height: 37px;
				position: absolute;
				bottom: 0;
				right: 0;
			}

		}

		.label {
			justify-self: start;

			padding-left: 26px;
			/* 昵称 */
			font-family: 'Inter';
			font-style: normal;
			font-weight: 500;
			font-size: 16px;
			line-height: 19px;

			color: rgba(255, 255, 255, 0.5);
		}

		.nickname-input {
			display: flex;
			// align-items: center;
			width: 100%;
			height: 54px;
			padding: 15px 26px;
			box-sizing: border-box;

			background: #7756EC;
			border-radius: 30px;

			/* 乔治 */
			font-family: 'Inter';
			font-style: normal;
			font-weight: 900;
			font-size: 20px;
			line-height: 24px;

			color: #FFFFFF;
		}

		.nickname-input-placeholder {
			/* 请输入昵称 */
			font-family: 'Inter';
			font-style: normal;
			font-weight: 500;
			font-size: 20px;
			line-height: 24px;

			color: rgba(255, 255, 255, 0.3);

		}

		.submit-button {
			justify-self: end;
			display: grid;
			place-items: center;

			margin-top: 5px;
			width: 105px;
			height: 41px;

			background: #FFFFFF;
			border-radius: 30px;

			.submit-button-text {
				/* 完成 */
				font-family: 'Inter';
				font-style: normal;
				font-weight: 700;
				font-size: 20px;
				line-height: 24px;
				/* 粉紫渐变 */
				background: linear-gradient(90deg, #BE53FF 0%, #7756EC 100%);
				-webkit-background-clip: text;
				-webkit-text-fill-color: transparent;
				background-clip: text;
				text-fill-color: transparent;
			}
		}
	}
</style>

<template>
	<view class="page">
		<q-nav-bar></q-nav-bar>
		<view class="main">
			<button class="avatar-button" open-type="chooseAvatar" @chooseavatar="onChooseAvatar">
				<q-avatar :src="userInfo?.avatarUrl" size="130" borderWidth="5"></q-avatar>
				<q-svg class="change-avatar" v-if="userInfo?.avatarUrl" icon="更换头像" size="37" />
			</button>
			<view class="label">昵称</view>
			<input class="nickname-input" placeholder-class="nickname-input-placeholder" type="nickname"
				placeholder="请输入昵称" :value="userInfo?.nickname" @input="onInput" @blur="onBlur" />
			<button v-if="userInfo?.avatarUrl && userInfo?.nickname" class="submit-button" @click="onSubmit">
				<text class="submit-button-text">完成</text>
			</button>
		</view>
	</view>
</template>

<script lang="ts" setup>
	import { ref, onMounted } from 'vue'
	import { userUpdate } from '../../utils/api/user';
	/** 获取登录信息 */
	const userInfo = ref()
	/** 获取微信头像触发 */
	function onChooseAvatar(e) {
		console.log("获取微信头像触发", e);
		const { avatarUrl } = e.detail
		userInfo.value.avatarUrl = avatarUrl
	}

	/** 键盘输入触发 */
	function onInput(e) {
		console.log("键盘输入触发", e);
		userInfo.value.nickname = e.detail.value
	}

	/** 键盘失去焦点触发 */
	function onBlur(e) {
		console.log("键盘失去焦点触发", e);
		userInfo.value.nickname = e.detail.value
	}

	/** 修改个人信息 */
	function onSubmit() {
		userUpdate(userInfo.value).then(e => {
			console.log("修改个人信息成功", e);
			// uni.navigateBack({
			// 	delta: 1
			// });
			uni.redirectTo({
				url: `/pages/home/home`
			});
		})
	}
	onMounted(() => {
		userInfo.value = getApp().globalData.userInfo
	})
</script>