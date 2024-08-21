import { api } from "../service"
/** 题目试卷 前端控制器 */

/** 创建试卷 */
export function createPaper(questionNumber : number, userId : number): Promise<Quiz.Result<Quiz.PaperAndAnswerDTO>> {
	return api.request({
		url: `/paper/createt/{userId}/{questionNumber}`,
		method: 'GET',
		path: {
			questionNumber: questionNumber,
			userId: userId,
		},
	})
}
/** 查询用户a试卷列表,以及用户b的回答信息 */
export function getPaperAndAnswerDTOList(creatorUserId : number, responderUserId : number): Promise<Quiz.Result<Array<Quiz.PaperAndAnswerDTO>>> {
	return api.request({
		url: `/paper/get-list/{creatorUserId}/{responderUserId}`,
		method: 'GET',
		path: {
			creatorUserId: creatorUserId,
			responderUserId: responderUserId,
		},
	})
}
/** 获取试卷列表 */
export function getPaperList(userId : number): Promise<Quiz.Result<Array<Quiz.PaperAndAnswerDTO>>> {
	return api.request({
		url: `/paper/get-list/{userId}`,
		method: 'GET',
		path: {
			userId: userId,
		},
	})
}
/** 获取用户试卷总数(包括已删除),判断是否出过题 */
export function getPaperTotal(userId : number): Promise<Quiz.Result<number>> {
	return api.request({
		url: `/paper/get-total/{userId}`,
		method: 'GET',
		path: {
			userId: userId,
		},
	})
}
/** 通过试卷ID,获取试卷详情 */
export function getPaper(paperId : number): Promise<Quiz.Result<Quiz.PaperAndAnswerDTO>> {
	return api.request({
		url: `/paper/get/{paperId}`,
		method: 'GET',
		path: {
			paperId: paperId,
		},
	})
}
/** 通过试卷ID,获取试卷详情,以及用户回答信息 */
export function getPaperAndAnswerDTO(paperId : number, responderUserId : number): Promise<Quiz.Result<Quiz.PaperAndAnswerDTO>> {
	return api.request({
		url: `/paper/get/{paperId}/{responderUserId}`,
		method: 'GET',
		path: {
			paperId: paperId,
			responderUserId: responderUserId,
		},
	})
}
/** 删除试卷 */
export function removePaper(paperId : number): Promise<Quiz.Result<any>> {
	return api.request({
		url: `/paper/remove/{paperId}`,
		method: 'DELETE',
		path: {
			paperId: paperId,
		},
	})
}
/** 试卷换题 */
export function paperSwitchQuestion(pqId : number, userId : number): Promise<Quiz.Result<Quiz.Question>> {
	return api.request({
		url: `/paper/switch-question/{userId}/{pqId}`,
		method: 'PUT',
		path: {
			pqId: pqId,
			userId: userId,
		},
	})
}
/** 更新试卷 */
export function updatePaper(paper : Quiz.Paper): Promise<Quiz.Result<any>> {
	return api.request({
		url: `/paper/update`,
		method: 'PUT',
		data: paper,
	})
}