import {api} from "../service"

/** 未知异常处理 */

/** error */
export function error() {
	return api.request({
		url: `/error`,
		method: 'PATCH',
	})
}