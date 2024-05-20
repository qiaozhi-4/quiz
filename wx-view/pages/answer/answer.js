const app = getApp();
const { request } = require('../../utils/service.js');
Page({
  data: {
    avatarUrl: app.globalData.userInfo.avatarUrl ,
    questions: [],
    index: 0,
    showInput: false,
    bottom:0,
  },
  onLoad() {
    request({
      // url: '/question/get-random-questions',
      url: '/question/get-random-questions-test',
      method: 'GET',
    }).then((res) => {
      wx.showToast({
        title: '成功',
        icon: 'success',
        duration: 2000
      });
      this.setData({
        questions: res.data
      });
    }).catch((err) => {
      console.log('请求失败:' + err.message);
    });
  },
  handleOptionClick() {
    let index = this.data.index;
    if (index >= this.data.questions.length - 1) return;
    this.setData({
      index: index + 1
    });
  },
  handleFooterClick() {
    this.setData({
      showInput: true,
    });
  },
  onHideInput() {
    this.setData({
      showInput: false,
    });
  },
  getBottom(event){
    console.log(event);
    this.setData({
      bottom:event.detail.height
    });
  },
});