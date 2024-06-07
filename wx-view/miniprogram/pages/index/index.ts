// index.ts
// 获取应用实例
const app = getApp<IAppOption>()

Page({
  data: {
    urls: ['set-user-info',]
  },
  clickButton(e: WechatMiniprogram.TouchEvent) {
    let url = e.currentTarget.dataset.url
    wx.navigateTo({ url: `/pages/${url}/${url}` })
  }
})
