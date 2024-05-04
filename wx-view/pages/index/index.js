const app = getApp();
Page({
  data: {
  },
  onLoad() {
    console.log('生命周期:onLoad-加载');
    wx.login({
      success: (res) => {
        if (res.code) {
          //发起网络请求
          wx.request({
            url: 'https://qzwdyz.top/quiz/wx-user/login?code=' + res.code,
            method: 'POST',
            success(res) {
              console.log(res.data);
              var data = res.data.data;
              if (data.userInfo.nickname == null) {
                console.log('当前用户还没设置头像和用户名,跳转到设置页面');
              }
              else {
                console.log('已设置头像和用户名');
              }
            }
          });
        } else {
          console.log('登录失败！' + res.errMsg);
        }
      },
    });
  },
});