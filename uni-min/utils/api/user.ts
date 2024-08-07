import { request } from "../request"

/** 登录 */
export function login(miniappId : 'wxf2f9d21291120320' | 'wx0f4e873ad758a586', code : string) {
	return request({
		url: `/wx-user/login?miniappId=${miniappId}&code=${code}`,
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
/** 通过用户ID,获取用户信息 */
export function getUserById(userId : number) {
	return request({
		url: `/user/get/${userId}`,
		method: 'GET',
	})
}