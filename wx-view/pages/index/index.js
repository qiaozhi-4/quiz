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
              if (res.data.code != 200) {
                console.log('登录失败!' + res.data.message);
                return;
              }
              var data = res.data.data;
              app.globalData.userInfo = data.userInfo;
              app.globalData.token = data.token;
              /* 4秒后跳转页面 */
              setTimeout(() => {
                if (data.userInfo.nickname == null) {
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
            }
          });
        } else {
          console.log('登录失败！' + res.errMsg);
        }
      },
    });
  },
});