const app = getApp();
Page({
    data: {
        date: new Date(Date.parse(new Date()) + 60 * 60 * 1000 * 8).toLocaleDateString(),
        avatarUrl: app.globalData.userInfo.avatarUrl ? app.globalData.userInfo.avatarUrl : '/images/avatar.svg',
        nickname: app.globalData.userInfo.nickname
    },
    /* 点击开始路由到答题页 */
    handleStartClick() {
        wx.redirectTo({
            url: '/pages/answer/answer',
        });
    },
});