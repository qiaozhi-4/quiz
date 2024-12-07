import { api } from "../service"
/** 题目表 前端控制器 */

/** 随机获取10个题目(不要权限) */
export function getRandomQuestionsTest(): Promise<Quiz.Result<Array<Quiz.Question>>> {
	return api.request({
		url: `/question/get-random-test`,
		method: 'GET',
	})
}
/** 随机获取 num 个该用户没有出过的题目 */
export function getRandomQuestions(num : number, userId : number): Promise<Quiz.Result<Array<Quiz.Question>>> {
	return api.request({
		url: `/question/get-random/{userId}/{num}`,
		method: 'GET',
		path: {
			num: num,
			userId: userId,
		},
	})
}