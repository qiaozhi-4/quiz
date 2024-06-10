import { userSave } from "../../utils/api"

// pages/set-user-info/set-user-info.ts
const app = getApp<IAppOption>()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    avatarUrl: '',
    nickname: ''
  },
  /* 设置头像 */
  onChooseAvatar(e: WechatMiniprogram.TouchEvent) {
    console.log(e.detail)

    const { avatarUrl } = e.detail
    if (!avatarUrl) return
    this.setData({
      avatarUrl,
    })
    /* 更新全局数据 */
    app.globalData.userInfo.avatarUrl = avatarUrl
  },
  /* 设置昵称 */
  onChangeNickname(e: WechatMiniprogram.TouchEvent) {
    console.log(e.detail)

    const { value } = e.detail
    if (!value) return
    this.setData({
      nickname: value
    })
    /* 更新全局数据 */
    app.globalData.userInfo.nickname = value
  },
  /* 保存个人信息到服务端 */
  handleSubmitClick: function () {
    console.log(app.globalData.userInfo)
    console.log(app.globalData.token)

    const { avatarUrl, nickname } = this.data
    if (!avatarUrl || !nickname) return
    userSave({ avatarUrl, nickname } as Quiz.UserInfo)
      .then((res) => {
        wx.redirectTo({
          url: '/pages/index/index',
        })
      }).catch((err) => {
        console.log('保存个人信息失败!' + err.data.message)
      })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad() {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})