import { api } from "../service"
/** 题目表 前端控制器 */

/** 随机获取10个题目(不要权限) */
export function getRandomQuestionsTest() {
	return api.request({
		url: `/question/get-random-test`,
		method: 'GET',
	})
}
/** 随机获取10个题目 */
export function getRandomQuestions(userId : number) {
	return api.request({
		url: `/question/get-random/{userId}`,
		method: 'GET',
		path: {
			userId: userId,
		},
	})
}