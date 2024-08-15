<style lang="scss" scoped>
	.circle {
		border-radius: 50%;
	}

	.square {
		border-radius: 5px;
	}

	.avatar-button::after {
		border: none;
	}

	.avatar-button {
		padding: 0;
		background-color: transparent;
		position: relative;

		.change-avatar {
			position: absolute;
			bottom: 0;
			right: 0;
		}
	}
</style>

<template>
	<view class="q-avater-wrap">
		<button class="avatar-button" :open-type="isChooseAvatar ? 'chooseAvatar' : ''" @chooseavatar="onChooseAvatar">
			<image v-show="succeed" class="q-avater" :class="shape" :src="src" :mode="mode" :style="style"
				@error="succeed = false" @load="succeed = true" />
			<q-svg v-show="!succeed" :icon="icon" :size="size"></q-svg>
			<q-svg class="change-avatar" v-show="isChooseAvatar" icon="更换头像" :size="Number(size)*0.28" />
		</button>
	</view>
</template>

<script lang="ts" setup>
	import { ref, computed } from 'vue'
	import { addPxIfNumeric } from '../../utils/utils';
	import { getImgUrl, baseUrl } from '../../utils/service';

	/** 传递的属性 */
	const props = defineProps({
		/** Avatar 图片的源地址 */
		src: { type: String, default: '' },
		/** Avatar 大小 */
		size: { type: [String, Number], default: '32' },
		/** 图片裁剪、缩放的模式 */
		mode: { type: String, default: 'aspectFit' },
		/** Avatar 形状  'circle' | 'square'*/
		shape: { type: String, default: 'circle', },
		/** 加载失败显示图标 */
		icon: { type: String, default: '默认头像', },
		/** 边框颜色 */
		borderColor: { type: String, default: '#FFFFFF', },
		/** 边框粗细 */
		borderWidth: { type: [String, Number], default: '', },
		/** 是否使用切换头像 */
		isChooseAvatar: { type: Boolean, default: false, }
	})
	/** 图片路径 */
	const src = computed(() => getImgUrl(props.src))
	/** 样式 */
	const style = computed(() => {
		return {
			width: addPxIfNumeric(props.size),
			height: addPxIfNumeric(props.size),
			boxSizing: `border-box`,
			border: props.borderWidth ? `${addPxIfNumeric(props.borderWidth)} solid ${props.borderColor}` : ''
		}
	})
	/** 加载是否成功 */
	const succeed = ref<boolean>(true)
	/** 获取微信头像触发 */
	function onChooseAvatar(e : { detail : { avatarUrl : any; }; }) {
		const { avatarUrl } = e.detail
		console.log(111);
		wx.uploadFile({
			url: `${baseUrl}/file/upload-avatar`,
			filePath: avatarUrl,
			name: 'file',
			formData: {
				userId: getApp().globalData.userInfo.userId
			},
			success(res) {
				console.log(222, '成功', JSON.parse(res.data));
				getApp().globalData.userInfo.avatarUrl = JSON.parse(res.data).data.avatarUrl
			},
			fail(e) {
				console.log(222, '失败', e);
			},
			complete(e) {
				console.log(333, e);
			}
		})
	}
</script>