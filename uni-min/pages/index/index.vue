<style lang="scss">
	.main {
		display: grid;
		grid-template-columns: repeat(2, 1fr);
		place-content: start center;
		place-items: center;
		gap: 10px;
	}

	.svg {
		position: absolute;
		top: 50%;
		/* 使元素的顶部距离父元素顶部 50% */
		left: 50%;
		/* 使元素的左侧距离父元素左侧 50% */
		transform: translate(-50%, -50%);
		/* 使用 transform 将元素中心点移动到定位点 */
	}
</style>

<template>
	<view class="page">
		<q-nav-bar title="Quiz"></q-nav-bar>
		<view class="main" v-show="false">
			<button class="button text-overflow" v-for="(page,index) in pages" :key="index" type="primary"
				@click="handleTo(page.name)">{{page.name}}</button>
		</view>
		<!-- <q-image class="img" src="log" width="83" height="104"></q-image> -->
		<q-svg class="svg" icon="log" size="104"></q-svg>
	</view>
</template>

<script lang="ts" setup>
	import { onMounted } from 'vue'
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
	onMounted(() => {
		uni.redirectTo({
			url: `/pages/home/home`
		});
	})
</script>