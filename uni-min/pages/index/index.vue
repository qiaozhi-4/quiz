<template>
	<q-nav-bar title="Quiz"></q-nav-bar>
	<view class="container">
		<button v-for="(page,index) in pages" :key="index" class="button" type="primary"
			@click="handleTo(page.name)">{{page.name}}</button>
	</view>
</template>

<script lang="ts" setup>
	const pages = [
		{ name: 'ding-dong' },
		{ name: 'set-user-info' },
	]
	function handleTo(pageName : String) {
		uni.navigateTo({
			url: `/pages/${pageName}/${pageName}?id=1&pageName=${pageName}`
		});
	}
</script>

<style lang="scss">
	.container {
		display: grid;
		grid-template-columns: repeat(2, 1fr);
		place-content: start center;
		place-items: center;
		gap: 10px;
	}
</style>