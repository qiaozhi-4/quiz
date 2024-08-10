import { request } from "../request"

/** 获取全部任务 */
export function getAllTask() {
	return request({
		url: `/task/get-all`,
		method: 'GET',
	})
}

/** 完成任务,并获取奖励 */
export function finishTask(taskId:number) {
	return request({
		url: `/task/finish/${taskId}`,
		method: 'POST',
	})
}