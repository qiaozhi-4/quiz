// app.js
App({
  onLaunch() {
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
  },
  globalData: {
    userInfo: {}
  },
  
  onLoad() {
    console.log('生命周期:onLoad-加载');
  },
  onShow() {
    console.log('生命周期:onShow-显示');
  },
  onReady() {
    console.log('生命周期:onReady');
  },
  onHide() {
    console.log('生命周期:onHide-隐藏');
  },
  onUnload() {
    console.log('生命周期:onUnload-卸载');
  }
})
