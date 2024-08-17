import { api } from "../service"
/** 任务数据 前端控制器 */

/** 完成任务,并获取奖励 */
export function finishTask(taskId : number, userId : number) {
	return api.request({
		url: `/task/finish/{userId}/{taskId}`,
		method: 'POST',
		path: {
			taskId: taskId,
			userId: userId,
		},
	})
}
/** 获取全部任务 */
export function getAllTask(userId : number) {
	return api.request({
		url: `/task/get-all/{userId}`,
		method: 'GET',
		path: {
			userId: userId,
		},
	})
}