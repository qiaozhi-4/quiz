import { useStore } from "@/stores/store";
export type Stringify = string | number | boolean | null | undefined | void;
/** 路径参数 */
export type SwaggerPath = Record<string, string | number>;
/** 查询参数 */
export type SwaggerQuery = Record<string, Stringify | Stringify[] | Record<string, any>>;
/** 请求头参数 */
export type SwaggerHeaders = Record<string, Stringify | Stringify[]>;
/** 请求参数暂时不用 */
export interface RequestOption<P extends SwaggerPath = SwaggerPath, Q extends SwaggerQuery = SwaggerQuery, H extends SwaggerHeaders = SwaggerHeaders, B = any> {
    url: string;
    path?: P;
    query?: Q;
    data?: B;
    header?: H;
    method?: "OPTIONS" | "GET" | "HEAD" | "POST" | "PUT" | "DELETE" | "TRACE" | "CONNECT";
    // method?: "OPTIONS" | "GET" | "HEAD" | "POST" | "PUT" | "DELETE" | "TRACE" | "CONNECT" | "PATCH";
    timeout?: number;
}
/** 请求成功响应 */
export interface RequestSuccess<T> {
    data?: T;
    statusCode?: number | string | any;
    header?: object;
    cookies?: object;
}
/** 封装一个request请求 */
export interface IApi {
    /** 基础url */
    readonly baseURL: string;
    /** 请求地址 */
    domain: string;
    /** 请求前缀 */
    prefix: string;
    /** 发送请求 */
    request<T>(options: RequestOption): Promise<Quiz.Result<T>>;
}
/**
 * 将一个对象转换为查询参数字符串
 * @param params 对象，键值对将被转换为查询参数
 * @returns 转换后的查询参数字符串
 */
export function objectToPathParams(params: SwaggerQuery): string {
    return '?' + Object.keys(params)
        .map(key => `${key}=${params[key]}`)
        .join("&");
}
/**
 * 动态替换URL中的占位符为实际的参数值(替换路径参数)
 * @param urlTemplate 包含占位符的URL模板
 * @param params 包含参数名和参数值的对象
 * @returns 替换后的完整URL
 */
export function replaceUrlPlaceholders(urlTemplate: string, params: SwaggerPath | undefined): string {
    if (!params) return urlTemplate; // 如果没有参数，直接返回原始URL
    return urlTemplate.replace(/{(\w+)}/g, (match, key) => {
        // 如果params中存在对应的key,则替换为对应的值,否则保留原占位符
        return params[key] !== undefined ? params[key].toString() : match;
    });
}
export class Api implements IApi {
    /** 构造函数 */
    constructor(private _domain: string, private _prefix: string) { }

    public get baseURL(): string {
        const baseUrl = this._domain.replace(/\/$/, "") + (!/^\//.test(this._prefix) ? "/" : "") + this._prefix;
        return baseUrl.replace(/\/$/, "");
    }

    public set domain(domain: string) {
        this._domain = domain;
    }

    public set prefix(prefix: string) {
        this._prefix = prefix;
    }
    request<T = any>(options: RequestOption): Promise<Quiz.Result<T>> {
        /** 请求头处理 */
        options.header = {
            ...options.header,
            token: useStore().token
        };
        /** 路径处理 */
        options.url = this.baseURL + (!/^\//.test(options.url) ? "/" : "") + options.url;
        /** 路径参数处理 */
        options.url = replaceUrlPlaceholders(options.url, options.path);
        /** 查询参数处理 */
        if (options.query) options.url += objectToPathParams(options.query);
        return new Promise<Quiz.Result<T>>((resolve, reject) => {
            ajaxStar();
            uni.request({
                ...options,
                success: (res: UniNamespace.RequestSuccessCallbackResult) => {
                    /** 后端响应 */
                    let data = res.data as Quiz.Result<T>;
                    // 请求成功，就将成功的数据返回出去
                    if (data.code == 200) {
                        // ajaxEnd();
                        // 测试专用
                        console.log(`请求路径:${options.url}`, data);
                        resolve(data);
                    }
                    else {
                        // 测试专用
                        console.error(`错误的请求路径:${options.url}`);
                        uni.showToast({
                            title: data.message,
                            icon: 'error',
                            duration: 2000
                        });
                        reject(data);
                    }
                },
                fail: (err: any) => {
                    // 测试专用
                    // console.error(err);
                    uni.showToast({
                        title: '请求失败!',
                        icon: 'error',
                        duration: 2000
                    });
                },
                complete: () => { ajaxEnd(); }
            });
        });
    }
}
/** 还未完成的请求个数 */
let ajaxCount = 0;
/** 请求开始 */
const ajaxStar = () => {
    ajaxCount++;
    // 显示加载中loading效果
    uni.showLoading({
        title: "加载中",
        mask: true  //开启蒙版遮罩
    });
};
/** 请求结束 */
const ajaxEnd = () => {
    ajaxCount--;
    // 隐藏 loading 提示框。
    if (ajaxCount == 0)
        uni.hideLoading();
};
/** 封装请求 */
export const api = new Api("https://qzwdyz.top", "quiz");
// export const api = new Api("http://localhost:1888", "");
export const miniappId = "wxf2f9d21291120320";//quiz
// export const miniappId = "wx0f4e873ad758a586";
/** 服务器基础url */
export const baseUrl = api.baseURL;