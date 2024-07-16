<template>
	<q-nav-bar :title="pageArg?.pageName" leftIcon="头部导航-返回"></q-nav-bar>
	<scroll-view :scroll-y="true" @scrolltolower="scrollBottom" class="container flex-column" :style="height">
		<uni-card v-for="(device,index) in devices" :key="index">
			<uni-forms ref="device" :model="device" :rules="rules" label-width="150">
				<uni-forms-item label="安卓设备ID">
					<uni-easyinput v-model="device.deviceId" disabled />
				</uni-forms-item>
				<uni-forms-item label="用户名">
					<uni-easyinput v-model="device.username" disabled />
				</uni-forms-item>
				<uni-forms-item label="过期时间">
					<uni-easyinput v-model="device.endTime" disabled />
				</uni-forms-item>
				<uni-forms-item label="设备等级">
					<uni-data-checkbox v-model="device.lv" :localdata="lvs"></uni-data-checkbox>
				</uni-forms-item>
				<uni-forms-item label="添加的时间数量" name="amountToAdd">
					<uni-easyinput type="number" v-model="device.amountToAdd" />
				</uni-forms-item>
				<uni-forms-item label="添加的时间单位">
					<uni-data-checkbox v-model="device.amountUnit" :localdata="dateUnits"></uni-data-checkbox>
				</uni-forms-item>

				<button @click="submitForm(device)">修改</button>
			</uni-forms>
		</uni-card>
		<uni-load-more :status="status" />
	</scroll-view>
</template>

<script lang="ts" setup>
	import { ref } from 'vue'
	import { onLoad } from '@dcloudio/uni-app'
	import { getDevices, updateDevice } from '../../utils/api';
	import { Lv, DateUnit } from '../../utils/dindong';

	/** 计算滚动框的高度 */
	const height = `height: ${getApp().globalData.containerHeight}px;`

	/** 页面跳转过来的参数 */
	const pageArg = ref()
	/** 设备列表 */
	const devices = ref<Quiz.Device[]>([])
	/** 设备分页信息 */
	const page = ref({
		/** 当前页 */
		current: 1,
		/** 每页大小 */
		size: 10,
		/** 总条数 */
		total: 0,
	})
	/** 表单验证规则 */
	const rules = {
		amountToAdd: {
			rules: [{
				required: true,
				errorMessage: '请填写姓名',
			},{
				pattern: /-?\d+/,
				errorMessage: '只能是数字',
			}]
		},
	}
	/** 提交表单 */
	function submitForm(device : any) {
		updateDevice(device).then(() => {
			devices.value = []
			page.value.current = 1
			handleGetDevices()
		})
	}
	/** 设备等级枚举映射 */
	const lvs = Object.keys(Lv)
		.filter((e) => isNaN(Number(e))) // 过滤数字key
		.map((currentValue) => {
			return {
				text: currentValue,
				value: Lv[currentValue]
			}
		})
	/** 时间单位枚举映射 */
	const dateUnits = Object.keys(DateUnit)
		.filter((e) => /[A-Z]/.test(e)) // 过滤中文key
		.map((currentValue) => {
			return {
				text: DateUnit[currentValue],
				value: currentValue
			}
		})
	/** 请求设备数据 */
	function handleGetDevices() {
		status.value = 'loading'
		console.log(`加载第${page.value.current}页`);
		getDevices(page.value.current, page.value.size).then(rse => {
			if (rse.code == 200) {
				rse.data.records.forEach((e : any) => {
					e.amountUnit = 'MONTHS';
					e.amountToAdd = 1;
					devices.value.push(e)

				})

				page.value.total = rse.data.total
				if (rse.data.total <= devices.value.length) {
					status.value = 'no-more';
				} else {
					status.value = 'more';
				}
			}
		})
	}
	/** 滚动到底部触发 */
	function scrollBottom() {
		if (page.value.current * page.value.size <= page.value.total) {
			page.value.current += 1
			handleGetDevices()
		}
	}
	/** 下拉加载样式 */
	const status = ref("more")
	onLoad((option) => {
		pageArg.value = option
		handleGetDevices()
	})
</script>

<style lang="scss">
	.container {}
</style>