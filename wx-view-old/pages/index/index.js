const app = getApp();
const { request } = require('../../utils/service.js');
Page({
  data: {
  },
  onLoad() {
    console.log('生命周期:onLoad-加载');
    wx.login({
      success: (res) => {
        if (res.code) {
          //发起网络请求
          request({
            url: '/wx-user/login?code=' + res.code,
            method: 'POST',
          }).then((res) => {
            app.globalData.userInfo =  res.data.userInfo;
            app.globalData.token =  res.data.token;
            /* 4秒后跳转页面 */
            setTimeout(() => {
              if ( res.data.userInfo.nickname == null) {
                console.log('当前用户还没设置头像和用户名,跳转到设置页面');
                wx.redirectTo({
                  url: '/pages/first-login/first-login',
                });
              }
              else {
                console.log('已设置头像和用户名');
                wx.redirectTo({
                  url: '/pages/start-game/start-game',
                });
              }
            }, 4000);
          }).catch((err) => {
            console.log('请求失败:' + err.message);
          });
        } else {
          console.log('登录失败！' + res.errMsg);
        }
      },
    });
  },
});