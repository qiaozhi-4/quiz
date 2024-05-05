const app = getApp();
Page({
  data: {
    avatarUrl: '/images/avatar-small.svg',
    avatarHeight: 130,
    avatarBgColoc: '#7857D2',
    isSetAvatar: false,
    nicknameValue: '',
    shakeClass: ''
  },
  /* 设置头像 */
  onChooseAvatar(e) {
    const { avatarUrl } = e.detail;
    this.setData({
      avatarUrl,
      avatarHeight: 125,
      avatarBgColoc: '#fff',
      isSetAvatar: true,
      animationShake: null
    });
    app.globalData.userInfo.avatarUrl = avatarUrl;
  },
  /* 设置昵称 */
  onChangeNickname(e) {
    this.setData({
      nicknameValue: e.detail.value
    });
  },
  handleSubmitClick: function () {
    // 创建动画实例
    const animation = wx.createAnimation({
      duration: 60, // 抖动持续时间为 0.6 秒
      timingFunction: 'linear'
    });

    // 应用抖动关键帧
    animation.translateX(-10).step()
      .translateX(10).step()
      .translateX(-10).step()
      .translateX(10).step()
      .translateX(0).step();

    // 更新数据，将动画传递到页面
    this.setData({
      animationShake: animation.export()
    });
  }
});