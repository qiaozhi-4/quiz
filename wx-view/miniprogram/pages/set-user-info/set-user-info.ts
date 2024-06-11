import { userSave } from "../../utils/api"

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
    console.log('设置头像: ', e.detail)

    const { avatarUrl } = e.detail
    this.setData({
      avatarUrl,
    })
    if (!avatarUrl) return
    /* 更新全局数据 */
    app.globalData.userInfo.avatarUrl = avatarUrl
  },
  /* 设置昵称 */
  onChangeNickname(e: WechatMiniprogram.TouchEvent) {
    console.log('设置昵称: ', e.detail)

    const { value } = e.detail
    this.setData({
      nickname: value
    })
    if (!value) return
    /* 更新全局数据 */
    app.globalData.userInfo.nickname = value
  },
  /* 保存个人信息到服务端 */
  handleSubmitClick: function () {
    const { avatarUrl, nickname } = this.data

    console.log(app.globalData.userInfo)
    console.log(app.globalData.token)
    console.log('头像url: ', avatarUrl)
    console.log('昵称: ', nickname)

    if (!avatarUrl || !nickname) {
      wx.showToast({
        title: '头像或者昵称未设置!',
        icon: 'error',
        duration: 2000
      })
      return
    }
    userSave({ avatarUrl, nickname, userId: app.globalData.userInfo.userId } as Quiz.UserInfo)
      .then((res) => {
        console.log('保存个人信息成功!', res)
        wx.redirectTo({
          url: '/pages/index/index',
        })
      })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad() {
    this.setData({
      avatarUrl: app.globalData.userInfo.avatarUrl,
      nickname: app.globalData.userInfo.nickname
    })
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