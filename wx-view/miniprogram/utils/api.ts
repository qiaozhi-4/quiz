const app = getApp<IAppOption>()

// 封装一个request请求
const request = (options: WechatMiniprogram.RequestOption<Quiz.Result>) => {
  // 返回一个promise对象
  return new Promise((resolve, reject) => {
    /** 设置基础请求头 */
    const baseUrl = "https://qzwd yz.top/quiz"
    wx.request<Quiz.Result>({
      url: baseUrl + options.url,
      data: options.data,
      method: options.method,
      header: {
        'content-type': 'application/json', // 默认值
        'token': app.globalData.token
      },
      success: (res) => {
        console.log(res.data)
        // 请求成功，就将成功的数据返回出去
        if (res.data.code == 200) resolve(res.data)
        else reject(res.data)
      },
      fail: (err) => {
        console.error(err)
        reject(err)
      },
    })
  })
}
module.exports = {
  request
}