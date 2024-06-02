/// <reference path="./types/index.d.ts" />

interface IAppOption {
  globalData: {
    /** 当前用户信息 */
    userInfo?: WechatMiniprogram.UserInfo,
    /** 当前用户token */
    token?: string,
  }
  userInfoReadyCallback?: WechatMiniprogram.GetUserInfoSuccessCallback,
}