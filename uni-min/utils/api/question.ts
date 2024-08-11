import {request} from "../request"

/** 获取随机10个题目 */
export function getRandomQuestions(userId: number) {
	return request({
        url: '/question/get-random/${userId}',
		method: 'GET'
	})
}

/** 获取随机10个题目 */
export function getRandomQuestionsTest() {
	return request({
        url: '/question/get-random-test',
		method: 'GET'
	})
}