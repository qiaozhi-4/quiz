const app = getApp();
Page({
  data: {
    avatarUrl: '/images/avatar-small.svg',
    avatarHeight: 130,
    avatarBgColoc: '#7857D2',
    select: true
  },
  onChooseAvatar(e) {
    console.log('修改头像');
    console.log(e);
    console.log(e.detail);
    const { avatarUrl } = e.detail;
    this.setData({
      avatarUrl,
      avatarHeight: 125,
      avatarBgColoc: '#fff',
      select: false
    });
    app.globalData.userInfo.avatarUrl = avatarUrl;
  },
});