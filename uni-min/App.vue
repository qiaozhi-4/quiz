<script>
	import {
		login
	} from './utils/api';
	export default {
		globalData: {
			userInfo: null,
			token: "",
			/** 除去导航栏的高度 */
			mainHeight: 700
		},
		onLaunch: function() {
			console.warn('当前组件仅支持 uni_modules 目录结构 ，请升级 HBuilderX 到 3.1.0 版本以上！')
			console.log('App Launch')
			// 计算除去导航栏的高度
			uni.getSystemInfo({
				success: (res) => {
					this.globalData.mainHeight = res.screenHeight - uni
						.getMenuButtonBoundingClientRect().bottom -
						10
				}
			});
			uni.login({
				provider: 'weixin', //使用微信登录
				success: loginRes => {
					console.log("微信登录信息:", loginRes);
					login(loginRes.code).then((res) => {
						this.globalData.userInfo = res.data.userInfo
						this.globalData.token = res.data.token
						if (/^\s*$/.test(res.data.userInfo.nickname) || /^\s*$/.test(res.data
								.userInfo.avatarUrl)) {
							console.log('当前用户还没设置头像和用户名,跳转到设置页面')
							uni.navigateTo({
								url: `/pages/set-user-info/set-user-info`
							});
						} else {
							console.log('已设置头像和用户名')
						}
					})
				}
			});
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style lang="scss">
	/*每个页面公共css */
	@import '@/uni_modules/uni-scss/index.scss';
	/* #ifndef APP-NVUE */
	@import '@/static/customicons.css';

	// 设置整个项目的背景色
	page {
		background-color: #5830C7;
		height: 100vh;
		width: 100vw;
		overflow-y: scroll;
	}

	view {
		display: flex;
		box-sizing: border-box;
		padding: 0;
		margin: 0;
		color: #FFFFFF;
	}

	button {
		display: grid;
		place-items: center;
		margin: 0;
		color: #FFFFFF;
	}

	/** 页面容器 */
	.page {
		display: flex;
		flex-direction: column;
		height: 100vh;
		width: 100vw;
		overflow-y: scroll;
	}

	/** flex布局元素横向排列 */
	.flex-row {
		display: flex;
		flex-direction: row;
	}

	/** flex布局元素纵向排列 */
	.flex-column {
		display: flex;
		flex-direction: column;
	}

	.text-overflow {
		/* 强制文本在同一行显示 */
		white-space: nowrap;
		/* 隐藏超出容器范围的部分 */
		overflow: hidden;
		/* 超出部分显示省略号 */
		text-overflow: ellipsis;
	}

	/* #endif */
	.example-info {
		font-size: 14px;
		color: #333;
		padding: 10px;
	}
</style>