// pages/home/home.ts
Page({

  /**
   * 页面的初始数据
   */
  data: {
    /** 当前下标 */
    currentIndex: 2,
    /** 头像Url */
    avatarUrl: '',
    /** 昵称 */
    nickname: '乔治',
    /** 统计 */
    statistics: [
      { name: '出题', value: 100 },
      { name: '答题', value: 100 },
      { name: '密友', value: 100 },
    ],
    /** 排行榜数据 */
    rankingData: [] as any[],
    /** 当前激活的排行榜 */
    rankingIndex: 0,
  },
  /** 切换排行榜 */
  changeRankingIndex(e: WechatMiniprogram.TouchEvent) {
    let index = e.currentTarget.dataset.index
    this.setData({
      rankingIndex: index
    })
  },
  /** 切换页面 */
  changeCurrentIndex(e: WechatMiniprogram.TouchEvent) {
    let index = e.currentTarget.dataset.index
    this.setData({
      currentIndex: index
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad() {
    /** 生产排行榜数据 */
    let rankingData = []
    for (let index = 0; index < 20; index++) {
      rankingData.push({
        nickname: `用户${index + 1}`,
        score: (20 - index) * 1000
      })
    }

    this.setData({
      rankingData: rankingData
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