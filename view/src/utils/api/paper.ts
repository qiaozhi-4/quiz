import { api } from "../service"
/** 题目试卷 前端控制器 */

/** 查询用户a试卷列表,以及用户b的回答信息 */
export function getPaperAndAnswerInfoList(creatorUserId : number, responderUserId : number) {
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
export function getPaperList(userId : number) {
	return api.request({
		url: `/paper/get-list/{userId}`,
		method: 'GET',
		path: {
			userId: userId,
		},
	})
}
/** 获取用户试卷总数(包括已删除),判断是否出过题 */
export function getPaperTotal(userId : number) {
	return api.request({
		url: `/paper/get-total/{userId}`,
		method: 'GET',
		path: {
			userId: userId,
		},
	})
}
/** 通过试卷ID,获取试卷详情 */
export function getPaper(paperId : number) {
	return api.request({
		url: `/paper/get/{paperId}`,
		method: 'GET',
		path: {
			paperId: paperId,
		},
	})
}
/** 通过试卷ID,获取试卷详情,以及用户回答信息 */
export function getPaperAndAnswerInfo(paperId : number, responderUserId : number) {
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
export function removePaper(paperId : number) {
	return api.request({
		url: `/paper/remove/{paperId}`,
		method: 'DELETE',
		path: {
			paperId: paperId,
		},
	})
}
/** 添加试卷 */
export function savePaper(paperDto : Quiz.PaperDto) {
	return api.request({
		url: `/paper/save`,
		method: 'POST',
		data: paperDto,
	})
}
/** 更新试卷 */
export function updatePaper(paper : Quiz.Paper) {
	return api.request({
		url: `/paper/update`,
		method: 'PUT',
		data: paper,
	})
}