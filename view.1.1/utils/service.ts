/** 亲求参数暂时不用 */
export interface RequestOption {
	url ?: string
	data ?: object
	header ?: object
	method ?: string
	timeout ?: number
	withCredentials ?: string
	success ?: Function
	fail ?: Function
	complete ?: Function
}
/** 请求成功参数 */
export interface RequestSuccess<T> {
	data ?: T
	statusCode ?: number | string | any
	header ?: object
	cookies ?: object
}

/** 封装一个request请求 */
export interface IApi {
	/** 基础url */
	readonly baseURL : string;
	/** 请求地址 */
	domain : string;
	/** 请求前缀 */
	prefix : string;
	request<T>(options : WechatMiniprogram.RequestOption<Quiz.Result>) : Promise<Quiz.Result<T>>;
}
export class Api implements IApi {
	constructor(private _domain : string, private _prefix : string) { }

	public get baseURL() : string {
		const baseUrl = this._domain.replace(/\/$/, "") + (!/^\//.test(this._prefix) ? "/" : "") + this._prefix;
		return baseUrl.replace(/\/$/, "");
	}

	public set domain(domain : string) {
		this._domain = domain;
	}

	public set prefix(prefix : string) {
		this._prefix = prefix;
	}

	request<T>(options : WechatMiniprogram.RequestOption<Quiz.Result>) : Promise<Quiz.Result<T>> {
		options.header = {
			// token: getApp().globalData.token
		}
		options.url = this.baseURL + options.url

		return new Promise<Quiz.Result<T>>((resolve, reject) => {
			star()
			uni.request({
				...options,
				success: (res : RequestSuccess<Quiz.Result<T>>) => {
					// 请求成功，就将成功的数据返回出去
					if (res.data.code == 200) {
						console.log(`请求路径:${options.url}`, res.data)
						resolve(res.data)
					}
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
				complete: () => { end() }
			})
		})
	}
}
/** 还未完成的请求个数 */
let ajaxCount = 0;
/** 请求开始 */
const star = () => {
	ajaxCount++;
	// 显示加载中loading效果
	uni.showLoading({
		title: "加载中",
		mask: true  //开启蒙版遮罩
	});
}
/** 请求结束 */
const end = () => {
	ajaxCount--;
	// 隐藏 loading 提示框。
	if (ajaxCount == 0)
		uni.hideLoading();
}
// export const api = new Api("https://qzwdyz.top", "quiz");
export const api = new Api("http://localhost:1888", "");