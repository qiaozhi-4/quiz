import { api } from "../service"
/** 路径表 前端控制器 */

/** 更新所有接口路径 */
export function updateAllPath() {
	return api.request({
		url: `/path/update-all`,
		method: 'GET',
	})
}
/** 更新所有接口路径(不要权限) */
export function updateAllPathTest() {
	return api.request({
		url: `/path/update-all-test`,
		method: 'GET',
	})
}