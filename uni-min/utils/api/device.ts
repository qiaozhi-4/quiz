import { request } from "../request"

/** 获取设备 */
export function getDevices(current : any, size : any) {
	return request({
		url: `/device/list/${current}/${size}`,
		method: 'GET'
	})
}
/** 修改设备 */
export function updateDevice(data : any, nickname : string) {
	return request({
		url: `/device/update-device?nickname=${nickname}`,
		method: 'PUT',
		data,
	})
}