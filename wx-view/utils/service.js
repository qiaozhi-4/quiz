const app = getApp();
const request = (parmas) => {
    // 返回一个promise对象
    return new Promise((resolve, reject) => {
        //设置基础请求头
        const baseUrl = "https://qzwdyz.top/quiz";
        wx.request({
            url: baseUrl + parmas.url, //仅为示例，并非真实的接口地址
            data: parmas.data,
            method: parmas.method,
            header: {
                'content-type': 'application/json', // 默认值
                'token': app.globalData.token
            },
            success: (res) => {
                console.log(res.data);
                // 请求成功，就将成功的数据返回出去
                if (res.data.code == 200) resolve(res.data);
                else reject(res.data);
            },
            fail: (err) => {
                console.log(err);
                reject(err);
            },
        });
    });
};


module.exports = {
    request
};