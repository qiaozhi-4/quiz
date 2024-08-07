import { request } from "../request"

/** 保存试卷 */
export function save(paperDto : Quiz.Paper) {
	return request({
		url: `/paper/save`,
		method: 'POST',
		data: paperDto,
	})
}

/** 更新试卷 */
export function update(paper : Quiz.Paper) {
	return request({
		url: `/paper/update`,
		method: 'PUT',
		data: paper,
	})
}

/** 通过试卷ID,获取试卷详情 */
export function get(paperId : number) {
	return request({
		url: `/paper/get/${paperId}`,
		method: 'GET',
	})
}

/** 获取试卷列表 */
export function getAll() {
	return request({
		url: `/paper/get-all`,
		method: 'GET',
	})
}

/** 获取用户试卷总数(包括已删除) */
export function getPaperTotal() {
	return request({
		url: `/paper/get-total`,
		method: 'GET',
	})
}

/** 删除试卷 */
export function remove(paperId : number) {
	return request({
		url: `/paper/remove/${paperId}`,
		method: 'DELETE',
	})
}