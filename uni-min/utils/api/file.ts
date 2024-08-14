import { api } from "../service"
/** 文件上传下载相关 */

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