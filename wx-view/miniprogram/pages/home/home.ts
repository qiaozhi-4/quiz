// pages/home/home.ts
Page({

  /**
   * 页面的初始数据
   */
  data: {
    /** 当前下标 */
    currentIndex: 0,
  },
  /** 切换页面 */
  changeCurrentIndex(e: WechatMiniprogram.TouchEvent) {
    let index = e.currentTarget.dataset.index
    this.setData({
      currentIndex: index
    })
  },
  /** current 改变时会触发 change 事件，event.detail = {current, source} */
  bindChange(e: WechatMiniprogram.TouchEvent){
    this.setData({
      currentIndex: e.detail.current
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