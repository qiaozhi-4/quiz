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
/** 通过用户ID,获取用户排行榜信息 */
export function getIntimateRanking(userId : number) {
	return request({
		url: `/user/get-intimate-ranking/${userId}`,
		method: 'GET',
	})
}
/** 通过用户ID,获取用户排行榜信息 */
export function getIntimateFriends(userId : number) {
	return request({
		url: `/user/get-intimate-friends/${userId}`,
		method: 'GET',
	})
}
/** 验证试卷是不是自己的,或者已经答过的 */
export function verifyPaper(userId : number, paperId : number) {
	return request({
		url: `/user/verify-paper/${userId}/${paperId}`,
		method: 'GET',
	})
}