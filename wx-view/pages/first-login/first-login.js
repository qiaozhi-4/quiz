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
    const { value } = e.detail;
    this.setData({
      nicknameValue: value
    });
    app.globalData.userInfo.nickname = value;
  },
  handleSubmitClick: function () {
    console.log(app.globalData.userInfo);
    console.log(app.globalData.token);
    console.log(this.data);
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

    const { nicknameValue, isSetAvatar } = this.data;
    if (nicknameValue == '' || !isSetAvatar) return;
    console.log('头像和昵称都设置了,保存个人信息');
    wx.request({
      url: 'https://qzwdyz.top/quiz/wx-user/save',
      data: app.globalData.userInfo,
      header: { token: app.globalData.token },
      method: 'POST',
      success(res) {
        console.log(res.data);
        if (res.data.code != 200) {
          console.log('保存个人信息失败!' + res.data.message);
          return;
        }
        wx.redirectTo({
          url: '/pages/start-game/start-game',
        });
      }
    });

  }
});