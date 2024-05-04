const app = getApp();
Page({
  data: {
    avatarUrl: '/images/avatar.svg',
    avatarHeight: 71,
    avatarWrapperBgColoc:'#7857D2'
  },
  onChooseAvatar(e) {
    console.log('修改头像');
    console.log(e);
    console.log(e.detail);
    const { avatarUrl } = e.detail;
    this.setData({
      avatarUrl,
      avatarHeight: 125,
      avatarWrapperBgColoc:'#fff'
    });
    app.globalData.userInfo.avatarUrl = avatarUrl;
  },
  onLoad() {
    console.log('生命周期:onLoad-加载');
  },
  onShow() {
    console.log('生命周期:onShow-显示');
  },
  onReady() {
    console.log('生命周期:onReady');
  },
  onHide() {
    console.log('生命周期:onHide-隐藏');
  },
  onUnload() {
    console.log('生命周期:onUnload-卸载');
  }
});