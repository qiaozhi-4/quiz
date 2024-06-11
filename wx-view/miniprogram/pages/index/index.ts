// index.ts

Page({
  data: {
    urls: ['set-user-info', 'start-game',]
  },
  clickButton(e: WechatMiniprogram.TouchEvent) {
    let url = e.currentTarget.dataset.url
    wx.navigateTo({ url: `/pages/${url}/${url}` })
  }
})
