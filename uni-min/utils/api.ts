import { request } from "./request"

/** 登录 */
export function login(code : string) {
	return request({
		url: '/wx-user/login?code=' + code,
		method: 'POST',
	})
}
/** 保存个人信息 */
export function userUpdate(data : Quiz.UserInfo) {
	return request({
		url: '/wx-user/update',
		method: 'POST',
		data,
	})
}
/** 获取随机10个题目 */
export function getRandomQuestions() {
	return request({
		url: '/question/get-random-questions-test',
		method: 'GET'
	})
}
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