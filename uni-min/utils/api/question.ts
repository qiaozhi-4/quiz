import { request } from "../request"

/** 获取随机10个题目 */
export function getRandomQuestions() {
	return request({
		url: '/question/get-random-questions-test',
		method: 'GET'
	})
}