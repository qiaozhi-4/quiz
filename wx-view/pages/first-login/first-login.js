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
      isSetAvatar: true
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
    this.setData({
      shakeClass: 'shake-element'
    });
    this.setData({
      shakeClass: ''
    });
  }
});