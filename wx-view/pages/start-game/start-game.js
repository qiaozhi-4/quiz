const app = getApp();
Page({
    data: {
        avatarUrl: app.globalData.userInfo.avatarUrl,
        nickname: app.globalData.userInfo.nickname
        // avatarUrl: '/images/avatar-small.svg',
        // nickname:'22222'
    },
});