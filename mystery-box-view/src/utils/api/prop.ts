import { api } from "../service"
/** 道具表 前端控制器 */

/** 获取道具 */
export function gainProp(num : number, propId : number, userId : number): Promise<Quiz.Result<Quiz.UserProp>> {
	return api.request({
		url: `/prop/gain/{userId}/{propId}/{num}`,
		method: 'PUT',
		path: {
			num: num,
			propId: propId,
			userId: userId,
		},
	})
}
/** 获取用户道具信息 */
export function getProps(userId : number): Promise<Quiz.Result<Array<Quiz.PropDTO>>> {
	return api.request({
		url: `/prop/get/{userId}`,
		method: 'GET',
		path: {
			userId: userId,
		},
	})
}
/** 使用道具 */
export function useProp(num : number, propId : number, userId : number): Promise<Quiz.Result<Quiz.UserProp>> {
	return api.request({
		url: `/prop/use/{userId}/{propId}/{num}`,
		method: 'PUT',
		path: {
			num: num,
			propId: propId,
			userId: userId,
		},
	})
}