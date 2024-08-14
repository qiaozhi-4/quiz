import {api} from "../service"

/** 设备卡密信息 前端控制器 */

/** 批量删除设备! */
export function deleteDevices(deviceIds : Array<string>) {
	return api.request({
		url: `/device/delete-device`,
		method: 'DELETE',
		data: deviceIds,
	})
}
/** 分页获取数据集合、总条数 */
export function getDeviceList(current : string, size : string) {
	return api.request({
		url: `/device/list/{current}/{size}`,
		method: 'GET',
		path: {
			current: current,
			size: size,
		},
	})
}
/** 更新设备信息,是否被封和过期时间 */
export function updateDevice(deviceDto : Quiz.DeviceDto, nickname : string) {
	return api.request({
		url: `/device/update-device`,
		method: 'PUT',
		query: {
			nickname: nickname,
		},
		data: deviceDto,
	})
}
/** 验证设备是否授权(无需操作) */
export function verifyDevice(data : any) {
	return api.request({
		url: `/device/verify`,
		method: 'POST',
		data: data,
	})
}