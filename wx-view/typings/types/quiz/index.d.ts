/// <reference path="./api.d.ts" />
declare namespace Quiz {
  /** 用户信息 */
  interface UserInfo  extends WechatMiniprogram.UserInfo{
    /** 头像Url */
    avatarUrl: string
    /** 创建时间 */
    createdAt: string
    /** 是否启用 */
    enabled: number
    /** 最后登录时间 */
    lastLoginAt: string
    /** 邮箱 */
    mail: string
    /** 昵称 */
    nickname: string
    /** 权限集合 */
    permissions: string[]
    /** 电话号码 */
    phone: string
    /** 更新时间 */
    updatedAt: string
    /** 用户id */
    userId: number
    /** 用户名 */
    username: string
  }
}