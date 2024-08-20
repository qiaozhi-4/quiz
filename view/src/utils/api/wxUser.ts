import { api } from "../service"
/** 微信相关接口 */

/** 微信小程序登录 */
export function login(code : string, miniappId : string): Promise<Quiz.Result<any>> {
	return api.request({
		url: `/wx-user/login`,
		method: 'POST',
		query: {
			code: code,
			miniappId: miniappId,
		},
	})
}
/** 修改微信小程序用户信息 */
export function updateUser(user : Quiz.User): Promise<Quiz.Result<Quiz.User>> {
	return api.request({
		url: `/wx-user/update`,
		method: 'POST',
		data: user,
	})
}