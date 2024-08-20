import { api } from "../service"
/** 脚本使用信息 前端控制器 */

/** 保存用户使用功能日志 */
export function saveLog(useInfo : Quiz.UseInfo): Promise<Quiz.Result<string>> {
	return api.request({
		url: `/use-info/send-log`,
		method: 'POST',
		data: useInfo,
	})
}