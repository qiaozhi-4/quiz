import { api } from "../service"
/** 未知异常处理 */

/** error */
export function error(): Promise<Quiz.Result<any>> {
	return api.request({
		url: `/error`,
		method: 'PATCH',
	})
}