import { api } from "../service"
/** 文件上传下载相关 */

/** 获取头像 */
export function downloadAvatar(filename : string): Promise<Quiz.Result<Quiz.Resource>> {
	return api.request({
		url: `/file/download-avatar/{filename}`,
		method: 'GET',
		path: {
			filename: filename,
		},
	})
}
/** 文件上传 */
export function handleFileUpload(file : any): Promise<Quiz.Result<string>> {
	return api.request({
		url: `/file/upload`,
		method: 'POST',
		data: {
			file: file,
		},
	})
}
/** 用户上传头像 */
export function uploadAvatar(file : any, userId : number): Promise<Quiz.Result<Quiz.User>> {
	return api.request({
		url: `/file/upload-avatar`,
		method: 'POST',
		data: {
			file: file,
			userId: userId,
		},
	})
}