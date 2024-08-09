import { request } from "../request"

/** 保存试卷 */
export function saveAnswers(answers : Quiz.Answers) {
	return request({
		url: `/answers/save`,
		method: 'POST',
		data: answers,
	})
}

/** 查询答卷详情 */
export function getAnswers(answerId : number) {
	return request({
		url: `/answers/get-answers/${answerId}`,
		method: 'GET',
	})
}

/** 查询答卷列表 */
export function getAnswersList(userId : number) {
	return request({
		url: `/answers/get-answers-list/${userId}`,
		method: 'GET',
	})
}

/** 查询徽章列表 */
export function getBadgeList(userId : number) {
	return request({
		url: `/answers/get-badge-list/${userId}`,
		method: 'GET',
	})
}