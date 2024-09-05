import { api } from "../service"
/** 题目答卷 前端控制器 */

/** 查询徽章列表 */
export function getBadgeList(userId : number): Promise<Quiz.Result<Array<Quiz.AnswerDTO>>> {
	return api.request({
		url: `/answer/get-badge-list/{userId}`,
		method: 'GET',
		path: {
			userId: userId,
		},
	})
}
/** 查询答卷列表 */
export function getAnswerList(userId : number): Promise<Quiz.Result<Array<Quiz.AnswerDTO>>> {
	return api.request({
		url: `/answer/get-list/{userId}`,
		method: 'GET',
		path: {
			userId: userId,
		},
	})
}
/** 查询答卷详情 */
export function getAnswer(answerId : number): Promise<Quiz.Result<Quiz.AnswerDTO>> {
	return api.request({
		url: `/answer/get/{answerId}`,
		method: 'GET',
		path: {
			answerId: answerId,
		},
	})
}
/** 保存答卷 */
export function saveAnswer(answer : Quiz.PaperAndAnswerDTO): Promise<Quiz.Result<Quiz.PaperAndAnswerDTO>> {
	return api.request({
		url: `/answer/save`,
		method: 'POST',
		data: answer,
	})
}