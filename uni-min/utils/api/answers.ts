import { request } from "../request"

/** 保存试卷 */
export function saveAnswers(answers : Quiz.Answers) {
	return request({
		url: `/answers/save`,
		method: 'POST',
		data: answers,
	})
}