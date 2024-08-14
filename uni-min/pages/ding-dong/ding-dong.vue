<style lang="scss">
	.main {}
</style>

<template>
	<view class="page">
		<q-nav-bar :title="pageArg?.pageName" leftIcon="头部导航-返回"></q-nav-bar>
		<scroll-view :scroll-y="true" @scrolltolower="scrollBottom" class="main flex-column" :style="height">
			<uni-card v-for="(device,index) in devices" :key="index">
				<uni-forms ref="deviceRef" :model="device" :rules="rules" label-width="150">
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

					<button type="primary" @click="submitForm(device,index)">修改</button>
				</uni-forms>
			</uni-card>
			<uni-load-more :status="status" />
		</scroll-view>
	</view>
	<!-- 提示消息 -->
	<uni-popup ref="popupRef" type="message">
		<uni-popup-message :type="popup.type" :message="popup.message" :duration="2000"></uni-popup-message>
	</uni-popup>
</template>

<script lang="ts" setup>
import {ref} from 'vue'
import {onLoad} from '@dcloudio/uni-app'
import {getDeviceList, updateDevice} from '../../utils/api/device';
import {DateUnit, Lv} from '../../utils/dindong';

/** 计算滚动框的高度 */
	const height = `height: ${getApp().globalData.mainHeight}px;`

	/** 页面跳转过来的参数 */
	const pageArg = ref()
	/** 设备列表 */
	const devices = ref<Quiz.Device[]>([])
	/** 设备表单ref */
	const deviceRef = ref()
	/** 设备分页信息 */
	const page = ref({
		/** 当前页 */
		current: 1,
		/** 每页大小 */
		size: 10,
		/** 总条数 */
		total: 0,
	})
	/** 弹出框信息 */
	const popup = ref({ type: "", message: "" })
	/** 弹出框ref */
	const popupRef = ref();
	/** 下拉加载样式 */
	const status = ref("more")
	/** 表单验证规则 */
	const rules = {
		amountToAdd: {
			rules: [{
				required: true,
				errorMessage: '请填写要添加的数量',
			}, {
				pattern: /-?\d+/,
				errorMessage: '只能是数字',
			}]
		},
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
	/** 提交表单 */
	function submitForm(device : any, index : number) {
		deviceRef.value[index].validate().then((res : any) => {
			updateDevice(device, getApp().globalData.userInfo.nickname).then((res) => {
				device.endTime = res.data.endTime
				popup.value.type = "success"
				popup.value.message = "修改成功"
			}).catch(res => {
				popup.value.type = "error"
				popup.value.message = res.message
			}).finally(() => popupRef.value.open())
		})
	}
	/** 请求设备数据 */
	function handleGetDevices() {
		status.value = 'loading'
		console.log(`加载第${page.value.current}页`);
		getDeviceList(page.value.current, page.value.size).then(rse => {
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
	onLoad((option) => {
		pageArg.value = option
		handleGetDevices()
	})
</script>