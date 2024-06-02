var app = getApp<IAppOption>()

/** 封装一个request请求 */
export const request = (options: WechatMiniprogram.RequestOption<Quiz.Result>): Promise<Quiz.Result> => {
  // 判断全局变量是否存在
  if (!app) app = getApp<IAppOption>()

  // 返回一个promise对象
  return new Promise((resolve, reject) => {
    /** 设置基础请求头 */
    const baseUrl = "https://qzwdyz.top/quiz"
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
        else {
          console.error(res.data.message)
          wx.showToast({
              title: '响应错误!',
              icon: 'error',
              duration: 2000
          })
          reject(res.data)
        }
      },
      fail: (err) => {
        console.error(err)
        wx.showToast({
            title: '请求失败!',
            icon: 'error',
            duration: 2000
        })
        reject(err)
      },
    })
  })
}