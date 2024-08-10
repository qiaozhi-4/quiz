import { request } from "../request"

/** 保存试卷 */
export function getAllTask() {
	return request({
		url: `/task/get-all`,
		method: 'GET',
	})
}