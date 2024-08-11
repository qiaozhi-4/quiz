import {request} from "../request"

/** 保存试卷 */
export function savePaper(paperDto: Quiz.Paper) {
	return request({
		url: `/paper/save`,
		method: 'POST',
		data: paperDto,
	})
}

/** 更新试卷 */
export function updatePaper(paper: Quiz.Paper) {
	return request({
		url: `/paper/update`,
		method: 'PUT',
		data: paper,
	})
}

/** 通过试卷ID,获取试卷详情 */
export function getPaper(paperId: number) {
	return request({
		url: `/paper/get/${paperId}`,
		method: 'GET',
	})
}

/** 获取试卷列表 */
export function getPaperList(userId: number) {
	return request({
        url: `/paper/get-list/${userId}`,
		method: 'GET',
	})
}

/** 获取用户试卷总数(包括已删除),判断是否出过题 */
export function getPaperTotal(userId: number) {
	return request({
        url: `/paper/get-total/${userId}`,
		method: 'GET',
	})
}

/** 删除试卷 */
export function removePaper(paperId: number) {
	return request({
		url: `/paper/remove/${paperId}`,
		method: 'DELETE',
	})
}