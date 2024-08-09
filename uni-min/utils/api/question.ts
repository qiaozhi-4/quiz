import { request } from "../request"

/** 获取随机10个题目 */
export function getRandomQuestions() {
	return request({
		url: '/question/get-random-questions',
		method: 'GET'
	})
}

/** 获取随机10个题目 */
export function getRandomQuestionsTest() {
	return request({
		url: '/question/get-random-questions-test',
		method: 'GET'
	})
}