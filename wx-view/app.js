// app.js
App({
  onLaunch() {
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || [];
    logs.unshift(Date.now());
    wx.setStorageSync('logs', logs);

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    });

    // 获取胶囊信息
    let menuButtonObject = wx.getMenuButtonBoundingClientRect();
    // 获取设备信息
    wx.getSystemInfo({
      success: res => {

        // 边距
        let margin = menuButtonObject.top - res.statusBarHeight;
        // 整个导航栏的高度
        let navHeight = menuButtonObject.top + menuButtonObject.height + margin * 2;
        this.globalData.navHeight = navHeight;
        // 导航栏的高度
        let nav = navHeight - res.statusBarHeight;
        this.globalData.nav = nav;
        // 导航栏距离顶部的高度
        this.globalData.navBotHeight = menuButtonObject.top - margin;

        // 胶囊与左边的距离
        this.globalData.menuLeft = menuButtonObject.left;
        // 胶囊的高度
        this.globalData.menuHeight = menuButtonObject.height;
        // 胶囊距离顶部的高度
        this.globalData.menuBotHeight = margin;
        // 胶囊距离右边的距离
        this.globalData.menuBotRight = res.windowWidth - menuButtonObject.right;

        // 整个设备的宽度
        this.globalData.windowWidth = res.windowWidth;
      },
      fail: err => {
        console.log(err);
      }
    });
  },
  globalData: {
    userInfo: {}
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
