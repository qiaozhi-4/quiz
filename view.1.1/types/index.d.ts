/// <reference path="./api.d.ts" />


declare namespace Quiz {
	/** 全局属性 */
	interface GlobalData {
		/** 当前用户信息 */
		userInfo : Quiz.UserInfo,
		/** 当前用户token */
		token : string,
	}
	/** 用户信息 */
	interface UserInfo {
		/** 头像Url */
		avatarUrl : string
		/** 创建时间 */
		createdAt : string
		/** 是否启用 */
		enabled : number
		/** 最后登录时间 */
		lastLoginAt : string
		/** 邮箱 */
		mail : string
		/** 昵称 */
		nickname : string
		/** 权限集合 */
		permissions : string[]
		/** 电话号码 */
		phone : string
		/** 更新时间 */
		updatedAt : string
		/** 用户id */
		userId : number
		/** 用户名 */
		username : string
		/** 排行榜分数 */
		totalScore : number
	}
	interface Device {
		/** 安卓设备ID */
		deviceId : number
		/** 用户名 */
		username : string
		/** 过期时间 */
		endTime : Date
		/** 设备等级[-1封禁,0试用,1vip,2svip,3max] */
		lv : number
		/** 要添加到结果中的单位数量，可能是负数 */
		amountToAdd : number
		/** 要添加的时间单位，不为空 */
		amountUnit : string
	}
	interface Question {
		/** 创建时间 */
		createdAt : string
		/** 选项字符串,以 @@ 切割 */
		options : string | string[]
		/** 题目id */
		questionId : number
		/** 题目 */
		title : string
		/** 更新时间 */
		updatedAt : string
	}
	/** 试卷所有信息 */
	interface Paper {
		/** 唯一主键 */
		paperId : number
		/** 出题用户id */
		creatorUserId : number
		/** 序号 */
		order : number
		/** 状态 */
		state : number
		/** 封面url */
		coverUrl : string
		/** 标题 */
		title : string
		/** 描述 */
		describe : string
		/** 答案下标集合,以@@分隔 */
		answers : string | string[]
		/** 创建时间 */
		createdAt : string
		/** 试卷题目集合 */
		questions : Quiz.Question[]
		/** 用户答题总数,被多少人做过 */
		answersTotal : number
	}
	/** 题目答卷 */
	interface Answers {
		/** 唯一主键 */
		answerId : number
		/** 关联试卷id */
		paperId : number
		/** 答题用户id */
		responderUserId : number
		/** 选择下标集合,以@@分隔 */
		selects : string | string[]
		/** 得分 */
		score : number
		/** 创建时间 */
		createdAt : string
		/** 试卷题目集合 */
		questions : Quiz.Question[]
		/** 出题用户id */
		creatorUserId : number
		/** 出题用户昵称 */
		creatorUserNickname : string
		/** 出题用户头像 */
		creatorUserAvatarUrl : string
	}
	/** 道具 */
	interface Prop {
		/** 道具id */
		propId : number
		/** 道具名称 */
		propName : string
		/** 道具描述 */
		propDescribe : string
		/** 道具数量 */
		number : number
	}
	/** 任务数据 */
	interface Task {
		/** 任务ID */
		taskId : number
		/** 奖励id */
		awardId : number
		/** 奖励数量 */
		awardNumber : number
		/** 任务描述 */
		describe : string
		/** 任务条件类型,1:出题:string2:答题 */
		conditionType : number
		/** 任务条件计数 */
		conditionNumber : number
		/** 创建时间 */
		createdAt : string
		/** 更新时间 */
		updatedAt : string
		/** 奖励名称 */
		awardName : string
		/** 任务完成计数 */
		finishNumber : number
		/** 是否领取奖励 */
		isReceiveAward : boolean
	}
}