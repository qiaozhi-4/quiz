import {api} from "../service"

/** 用户信息表 前端控制器 */

/** 通过用户ID,获取用户密友信息 */
export function getIntimateFriends(userId : number) {
	return api.request({
		url: `/user/get-intimate-friends/{userId}`,
		method: 'GET',
		path: {
			userId: userId,
		},
	})
}
/** 通过用户ID,获取用户排行榜信息 */
export function getIntimateRanking(userId : number) {
	return api.request({
		url: `/user/get-intimate-ranking/{userId}`,
		method: 'GET',
		path: {
			userId: userId,
		},
	})
}
/** 通过用户ID,获取用户信息 */
export function getUser(userId : number) {
	return api.request({
		url: `/user/get/{userId}`,
		method: 'GET',
		path: {
			userId: userId,
		},
	})
}
/** 验证试卷是不是自己的,或者已经答过的 */
export function verifyPaper(paperId : number, userId : number) {
	return api.request({
		url: `/user/verify-paper/{userId}/{paperId}`,
		method: 'GET',
		path: {
			paperId: paperId,
			userId: userId,
		},
	})
}