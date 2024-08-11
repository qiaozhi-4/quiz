import {request} from "../request"

/** 使用道具 */
export function useProp(userId: number, propId: number, num: number) {
	return request({
        url: `/prop/use/${userId}/${propId}/${num}`,
		method: 'PUT',
	})
}
/** 获取道具 */
export function gainProp(userId: number, propId: number, num: number) {
	return request({
        url: `/prop/gain/${userId}/${propId}/${num}`,
		method: 'PUT',
	})
}