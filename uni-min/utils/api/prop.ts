import { request } from "../request"

/** 使用道具 */
export function useProp(propId : number, num : number) {
	return request({
		url: `/prop/use/${propId}/${num}`,
		method: 'PUT',
	})
}
/** 获取道具 */
export function gainProp(propId : number, num : number) {
	return request({
		url: `/prop/gain/${propId}/${num}`,
		method: 'PUT',
	})
}