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
	}
	interface Question {
		/** 创建时间 */
		createdAt : string
		/** 选项字符串,以 @@ 切割 */
		options : string
		/** 题目id */
		questionId : number
		/** 题目 */
		title : string
		/** 更新时间 */
		updatedAt : string
	}
	interface Device {
		/** 安卓设备ID */
		deviceId : string
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
}