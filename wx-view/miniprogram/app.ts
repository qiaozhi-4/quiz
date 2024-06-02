// app.ts
import { login } from "./utils/api"

App<IAppOption>({
  globalData: {},
  onLaunch() {
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        console.log('code: ', res.code)
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        if (res.code) {
          // 发起网络请求
          login(res.code).then((res) => {
            this.globalData.userInfo = res.data.userInfo
            this.globalData.token = res.data.token
            if (res.data.userInfo.nickname == null) {
              console.log('当前用户还没设置头像和用户名,跳转到设置页面')
              wx.redirectTo({
                url: '/pages/first-login/first-login',
              })
            }
            else {
              console.log('已设置头像和用户名')
              wx.redirectTo({
                url: '/pages/start-game/start-game',
              })
            }
          })
        } else {
          console.error('登录失败！' + res.errMsg)
        }
      },
    })
  },
})