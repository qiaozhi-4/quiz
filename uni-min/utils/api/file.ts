import { api } from "../service"
/** 文件上传下载相关 */

/** 获取头像 */
export function downloadAvatar(filename : string) {
	return api.request({
		url: `/file/download-avatar/{filename}`,
		method: 'GET',
		path: {
			filename: filename,
		},
	})
}
/** 文件上传 */
export function handleFileUpload(file : any) {
	return api.request({
		url: `/file/upload`,
		method: 'POST',
		formData: {
			file: file,
		},
	})
}
/** 用户上传头像 */
export function uploadAvatar(file : any, userId : number) {
	return api.request({
		url: `/file/upload-avatar`,
		method: 'POST',
		formData: {
			file: file,
			userId: userId,
		},
	})
}