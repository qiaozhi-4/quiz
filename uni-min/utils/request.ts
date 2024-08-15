/** 封装一个request请求 */
export const request = (options : WechatMiniprogram.RequestOption<Quiz.Result>) : Promise<Quiz.Result> => {
	// 返回一个promise对象
	return new Promise((resolve, reject) => {
		/** 设置基础请求头 */
		const baseUrl = "https://qzwdyz.top/quiz"
		// const baseUrl = "http://localhost:1888"
		uni.request<Quiz.Result>({
			url: baseUrl + options.url,
			data: options.data,
			method: options.method,
			header: {
				'content-type': 'application/json', // 默认值
				'token': getApp().globalData.token,
				...options.header
			},
			success: (res : { data : Quiz.Result<any> | PromiseLike<Quiz.Result<any>> }) => {
				console.log(`请求路径:${options.url}`, res.data)
				// 请求成功，就将成功的数据返回出去
				if (res.data.code == 200) resolve(res.data)
				else {
					console.error(`错误的请求路径:${options.url}`)
					uni.showToast({
						title: res.data.message,
						icon: 'error',
						duration: 2000
					})
					reject(res.data)
				}
			},
			fail: (err : any) => {
				console.error(err)
				uni.showToast({
					title: '请求失败!',
					icon: 'error',
					duration: 2000
				})
			},
		})
	})
}