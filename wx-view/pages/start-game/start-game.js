const app = getApp();
Page({
    data: {
        date: new Date(Date.parse(new Date())+ 60*60*1000*8).toLocaleDateString(),
        avatarUrl: app.globalData.userInfo.avatarUrl,
        nickname: app.globalData.userInfo.nickname
        // avatarUrl: 'https://img2.imgtp.com/2023/10/26/rQkEoyFQ.jpg',
        // nickname:'乔治'
    },
    /* 点击开始路由到答题页 */
    handleStartClick(){
        wx.redirectTo({
            url: '/pages/answer/answer',
          });
    },
});