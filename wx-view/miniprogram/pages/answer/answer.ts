import { getRandomQuestions } from "../../utils/api"

const app = getApp<IAppOption>()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    avatarUrl: '',
    /** 题目 */
    questions: [] as Quiz.Question[],
    /** 答案 */
    options: [] as number[],
    /** 输入框的值 */
    inputValues: [] as string[],
    /** 当前题目索引 */
    index: 0,
    /** 活动预览窗口 */
    activityContainer: '',
  },
  /** 滑动触发 */
  handleSwiperChange(e: WechatMiniprogram.TouchEvent) {
    this.setData({
      index: e.detail.current,
    })
  },
  /** 点击选项触发 */
  handleOptionClick(e: WechatMiniprogram.TouchEvent) {
    let index = this.data.index
    let options = this.data.options
    let option = e.currentTarget.dataset.option
    options[index] = option
    if (index < this.data.questions.length - 1) {
      this.setData({
        index: index + 1,
        options: options,
      })
    }
  },
  /** 修改当前活动的容器 */
  changeActivityContainer(e: WechatMiniprogram.TouchEvent) {
    let currentActivityContainer = this.data.activityContainer
    let activity = e.currentTarget.dataset.activity
    this.setData({
      activityContainer: currentActivityContainer == activity ? '' : activity,
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad() {
    getRandomQuestions().then(res => {

      this.setData({
        avatarUrl: app.globalData.userInfo.avatarUrl,
        questions: res.data,
        options: new Array(res.data.length),
        inputValues: new Array(res.data.length).fill('')
      })
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