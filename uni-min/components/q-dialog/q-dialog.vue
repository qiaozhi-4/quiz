<style lang="scss" scoped>
	.confirm-dialog {
		align-items: center;
		
		background-color: #1F1146;
		padding: 20px;
		border-radius: 5px;
		box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	}

	.confirm-dialog-header {
		font-weight: bold;
		margin-bottom: 10px;
	}

	.confirm-dialog-body {
		margin-bottom: 20px;
	}

	.confirm-dialog-footer {
		display: flex;
		justify-content: flex-end;
	}

	.confirm-dialog-footer button {
		margin-left: 10px;
		padding: 5px 10px;
		border: none;
		border-radius: 3px;
		cursor: pointer;
	}

	.confirm-dialog-footer button:first-of-type {
		background: #ccc;
	}

	.confirm-dialog-footer button:last-of-type {
		background: #007bff;
		color: white;
	}
</style>

<template>
	<page-container :show="props.show" round>
		<view class="confirm-dialog flex-column">
			<view class="confirm-dialog-header">
				<slot name="header">确认操作</slot>
			</view>
			<view class="confirm-dialog-body">
				<slot>你确定要执行此操作吗？</slot>
			</view>
			<view class="confirm-dialog-footer">
				<button @click="$emit('onCancel')">取消</button>
				<button @click="$emit('onConfirm')">确认</button>
			</view>
		</view>
	</page-container>
</template>

<script lang="ts" setup>
	import { ref, computed } from 'vue'
	const props = defineProps({
		/** 标题文字 */
		show: { type: Boolean, default: true },
	})
</script>