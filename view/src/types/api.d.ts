declare namespace Quiz {
	/** AnswersDTO对象,题目答卷 */
	interface AnswersDTO {
		/** 唯一主键 */
		answerId:number
		/** 创建时间 */
		createdAt:string
		/** 出题用户头像 */
		creatorUserAvatarUrl:string
		/** 出题用户id */
		creatorUserId:number
		/** 出题用户昵称 */
		creatorUserNickname:string
		/** 关联试卷id */
		paperId:number
		/** 试卷题目集合 */
		questions:Quiz.Question
		/** 答题用户id */
		responderUserId:number
		/** 得分 */
		score:number
		/** 选择下标集合,以@@分隔 */
		selects:string
	}
	/** Answers对象,题目答卷 */
	interface Answers {
		/** 唯一主键 */
		answerId:number
		/** 创建时间 */
		createdAt:string
		/** 关联试卷id */
		paperId:number
		/** 答题用户id */
		responderUserId:number
		/** 得分 */
		score:number
		/** 选择下标集合,以@@分隔 */
		selects:string
	}
	/** DeviceDto对象,设备相关信息 */
	interface DeviceDto {
		/** 要添加到结果中的单位数量，可能是负数 */
		amountToAdd:number
		/** 要添加的时间单位，不为空 */
		amountUnit:string
		/** 安卓设备ID */
		deviceId:string
		/** 过期时间 */
		endTime:string
		/** -1封禁,0试用,1vip,2svip,3max */
		lv:number
		/** 用户名 */
		username:string
	}
	/** Device对象,设备卡密信息 */
	interface Device {
		/** 安卓设备ID */
		deviceId:string
		/** 过期时间 */
		endTime:string
		/** -1封禁,0试用,1vip,2svip,3max */
		lv:number
		/** 用户名 */
		username:string
	}
	/** File */
	interface File {
		/**  */
		absolute:any
		/**  */
		absoluteFile:Quiz.File
		/**  */
		absolutePath:string
		/**  */
		canonicalFile:Quiz.File
		/**  */
		canonicalPath:string
		/**  */
		directory:any
		/**  */
		file:any
		/**  */
		freeSpace:number
		/**  */
		hidden:any
		/**  */
		name:string
		/**  */
		parent:string
		/**  */
		parentFile:Quiz.File
		/**  */
		path:string
		/**  */
		totalSpace:number
		/**  */
		usableSpace:number
	}
	/** InputStream */
	interface InputStream {
	}
	/** PaperDto对象,试卷所有信息 */
	interface PaperDto {
		/** 答案下标集合,以@@分隔 */
		answers:string
		/** 用户答题总数,被多少人做过 */
		answersTotal:number
		/** 封面url */
		coverUrl:string
		/** 创建时间 */
		createdAt:string
		/** 出题用户id */
		creatorUserId:number
		/** 描述 */
		describe:string
		/** 序号 */
		order:number
		/** 唯一主键 */
		paperId:number
		/** 试卷题目集合 */
		questions:Quiz.Question
		/** 状态 */
		state:number
		/** 标题 */
		title:string
	}
	/** Paper对象,题目试卷 */
	interface Paper {
		/** 答案下标集合,以@@分隔 */
		answers:string
		/** 封面url */
		coverUrl:string
		/** 创建时间 */
		createdAt:string
		/** 出题用户id */
		creatorUserId:number
		/** 描述 */
		describe:string
		/** 序号 */
		order:number
		/** 唯一主键 */
		paperId:number
		/** 状态 */
		state:number
		/** 标题 */
		title:string
	}
	/** Path对象,路径表 */
	interface Path {
		/** 路径描述。 */
		describe:string
		/** HTTP 方法,如 POST, GET */
		httpMethod:string
		/** 主键，路径ID。 */
		pathId:number
		/** 路径模式，如/api/users/**，/device/list/{current:d+}/{size:d+}。 */
		pattern:string
		/** 路径权限名称 */
		permissionName:string
	}
	/** PropDTO对象,道具表 */
	interface PropDTO {
		/** 道具数量 */
		number:number
		/** 道具描述 */
		propDescribe:string
		/** 道具id。 */
		propId:number
		/** 道具名称 */
		propName:string
	}
	/** Question对象,题目表 */
	interface Question {
		/** 创建时间 */
		createdAt:string
		/** 选项 */
		options:string
		/** 唯一主键 */
		questionId:number
		/** 题目 */
		title:string
		/** 修改时间 */
		updatedAt:string
	}
	/** Resource */
	interface Resource {
		/**  */
		description:string
		/**  */
		file:Quiz.File
		/**  */
		filename:string
		/**  */
		inputStream:Quiz.InputStream
		/**  */
		open:any
		/**  */
		readable:any
		/**  */
		uri:Quiz.URI
		/**  */
		url:Quiz.URL
	}
	/** Result«object»,统一的响应体 */
	interface Resultobject {
		/** 状态码 */
		code:number
		/** 数据 */
		data:any
		/** 描述 */
		message:string
	}
	/** TaskDTO对象,任务数据 */
	interface TaskDTO {
		/** 奖励id */
		awardId:number
		/** 奖励名称 */
		awardName:string
		/** 奖励数量 */
		awardNumber:number
		/** 任务条件计数 */
		conditionNumber:number
		/** 任务条件类型,1:答题;2:出题 */
		conditionType:number
		/** 创建时间 */
		createdAt:string
		/** 任务描述 */
		describe:string
		/** 任务完成计数 */
		finishNumber:number
		/** 是否领取奖励 */
		isReceiveAward:any
		/** 任务ID */
		taskId:number
		/** 更新时间 */
		updatedAt:string
	}
	/** URI */
	interface URI {
		/**  */
		absolute:any
		/**  */
		authority:string
		/**  */
		fragment:string
		/**  */
		host:string
		/**  */
		opaque:any
		/**  */
		path:string
		/**  */
		port:number
		/**  */
		query:string
		/**  */
		rawAuthority:string
		/**  */
		rawFragment:string
		/**  */
		rawPath:string
		/**  */
		rawQuery:string
		/**  */
		rawSchemeSpecificPart:string
		/**  */
		rawUserInfo:string
		/**  */
		scheme:string
		/**  */
		schemeSpecificPart:string
		/**  */
		userInfo:string
	}
	/** URL */
	interface URL {
		/**  */
		authority:string
		/**  */
		content:any
		/**  */
		defaultPort:number
		/**  */
		file:string
		/**  */
		host:string
		/**  */
		path:string
		/**  */
		port:number
		/**  */
		protocol:string
		/**  */
		query:string
		/**  */
		ref:string
		/**  */
		userInfo:string
	}
	/** UseInfo对象,脚本使用信息 */
	interface UseInfo {
		/** 账号(游戏的) */
		account:string
		/** 勇者id */
		braveId:number
		/** 使用时间 */
		dateTime:string
		/** 调用的方法名称 */
		functionName:string
		/** 密码(游戏的) */
		password:string
		/** 玩家key */
		playerKey:string
		/** 玩家昵称 */
		playerName:string
		/** 玩家角色id */
		roleId:number
		/** 玩家角色名称 */
		roleName:string
		/** 区服id */
		serverId:number
		/** 区服名称 */
		serverName:string
		/** 唯一主键 */
		useId:number
		/** 卡号 */
		username:string
	}
	/** UserDto对象,用户基本信息,添加权限列表 */
	interface UserDto {
		/** 头像地址 */
		avatarUrl:string
		/** 创建时间 */
		createdAt:string
		/** 账号是否启用:0=正常,-1=禁用 */
		enabled:number
		/** 用户上次登录时间 */
		lastLoginAt:string
		/** 邮箱 */
		mail:string
		/** 用户昵称 */
		nickname:string
		/** 用户权限集合 */
		permissions:Array<string>
		/** 手机号 */
		phone:string
		/** 排行榜分数 */
		totalScore:number
		/** 更新时间 */
		updatedAt:string
		/** 主键，用户ID。 */
		userId:number
		/** 用户账号 */
		username:string
	}
	/** UserProp对象,用户关联道具表 */
	interface UserProp {
		/** 主键，权限ID。 */
		id:number
		/** 道具数量 */
		number:number
		/** 道具id。 */
		propId:number
		/** 用户id。 */
		userId:number
	}
	/** User对象,用户信息表 */
	interface User {
		/** 头像地址 */
		avatarUrl:string
		/** 创建时间 */
		createdAt:string
		/** 账号是否启用:0=正常,-1=禁用 */
		enabled:number
		/** 用户上次登录时间 */
		lastLoginAt:string
		/** 邮箱 */
		mail:string
		/** 用户昵称 */
		nickname:string
		/** 手机号 */
		phone:string
		/** 更新时间 */
		updatedAt:string
		/** 主键，用户ID。 */
		userId:number
		/** 用户账号 */
		username:string
	}
}