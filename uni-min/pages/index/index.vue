<style lang="scss">
	.main {
		display: grid;
		grid-template-columns: repeat(2, 1fr);
		place-content: start center;
		place-items: center;
		gap: 10px;
	}
</style>

<template>
	<view class="page">
		<q-nav-bar title="Quiz"></q-nav-bar>
		<view class="main">
			<button v-for="(page,index) in pages" :key="index" class="button" type="primary"
				@click="handleTo(page.name)">{{page.name}}</button>
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
	]
	function handleTo(pageName : String) {
		uni.navigateTo({
			url: `/pages/${pageName}/${pageName}?id=1&pageName=${pageName}`
		});
	}
</script>