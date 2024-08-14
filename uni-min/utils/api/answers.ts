import {api} from "../service"

/** 题目答卷 前端控制器 */

/** 查询徽章列表 */
export function getBadgeList(userId : number) {
	return api.request({
		url: `/answers/get-badge-list/{userId}`,
		method: 'GET',
		path: {
			userId: userId,
		},
	})
}
/** 查询答卷列表 */
export function getAnswersList(userId : number) {
	return api.request({
		url: `/answers/get-list/{userId}`,
		method: 'GET',
		path: {
			userId: userId,
		},
	})
}
/** 查询答卷详情 */
export function getAnswers(answerId : number) {
	return api.request({
		url: `/answers/get/{answerId}`,
		method: 'GET',
		path: {
			answerId: answerId,
		},
	})
}
/** 保存答卷 */
export function saveAnswers(answers : Quiz.Answers) {
	return api.request({
		url: `/answers/save`,
		method: 'POST',
		data: answers,
	})
}