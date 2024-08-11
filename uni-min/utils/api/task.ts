import {request} from "../request"

/** 获取全部任务 */
export function getAllTask(userId: number) {
	return request({
        url: `/task/get-all/${userId}`,
		method: 'GET',
	})
}

/** 完成任务,并获取奖励 */
export function finishTask(userId: number, taskId: number) {
	return request({
        url: `/task/finish/${userId}/${taskId}`,
		method: 'POST',
	})
}