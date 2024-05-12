const app = getApp();
const { request } = require('../../utils/service.js');
Page({
  data: {
    avatarUrl: app.globalData.userInfo.avatarUrl ? app.globalData.userInfo.avatarUrl : '/images/avatar.svg',
    questions: [],
    index: 0,
  },
  onLoad() {
    request({
      // url: '/question/get-random-questions',
      url: '/question/get-random-questions-test',
      method: 'GET',
    }).then((res) => {
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
  }
});