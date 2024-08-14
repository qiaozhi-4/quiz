// import { URL } from 'url';
import 'url-polyfill';
// Generate by swagger2ts
/** ===== build-in interface start ===== */
export type Stringify = string | number | null | undefined | void

export type SwaggerPath = Record<string, string | number>

export type SwaggerQuery = Record<string, Stringify | Stringify[] | Record<string, any>>

export type SwaggerHeaders = Record<string, Stringify | Stringify[]>

export type SwaggerCommonOptions = Omit<RequestInit, "body" | "method" | "headers"> & { timeout?: number }

export type RequireKeys<T extends Record<string, any>, K extends keyof T> = Required<Pick<T, K>> & Omit<T, K>

export interface SwaggerOptions<P extends SwaggerPath = SwaggerPath, Q extends SwaggerQuery = SwaggerQuery, H extends SwaggerHeaders = SwaggerHeaders, B = any> extends SwaggerCommonOptions {
  path?: P
  query?: Q
  headers?: H
  body?: B
}
/** ===== build-in interface end ===== */

export interface SwaggerApi {
  /**
   * @summary 查询徽章列表
   * @tag 题目答卷 前端控制器
   */
  get(url: '/answers/get-badge-list/{userId}', options?: SwaggerOptions<{userId: unknown}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 查询答卷列表
   * @tag 题目答卷 前端控制器
   */
  get(url: '/answers/get-list/{userId}', options?: SwaggerOptions<{userId: unknown}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 查询答卷详情
   * @tag 题目答卷 前端控制器
   */
  get(url: '/answers/get/{answerId}', options?: SwaggerOptions<{answerId: unknown}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 保存答卷
   * @tag 题目答卷 前端控制器
   */
  post(url: '/answers/save', options?: SwaggerOptions<{}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 批量删除设备!
   * @tag 设备卡密信息 前端控制器
   */
  delete(url: '/device/delete-device', options?: SwaggerOptions<{}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 分页获取数据集合、总条数
   * @tag 设备卡密信息 前端控制器
   */
  get(url: '/device/list/{current}/{size}', options?: SwaggerOptions<{current: unknown,size: unknown}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 更新设备信息,是否被封和过期时间
   * @tag 设备卡密信息 前端控制器
   */
  put(url: '/device/update-device', options?: SwaggerOptions<{}, {nickname: unknown}, {}, unknown>): Promise<unknown>
  /**
   * @summary 验证设备是否授权(无需操作)
   * @tag 设备卡密信息 前端控制器
   */
  post(url: '/device/verify', options?: SwaggerOptions<{}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 文件上传
   * @tag 文件上传下载相关
   */
  post(url: '/file/upload', options?: SwaggerOptions<{}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 获取试卷列表
   * @tag 题目试卷 前端控制器
   */
  get(url: '/paper/get-list/{userId}', options?: SwaggerOptions<{userId: unknown}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 获取用户试卷总数(包括已删除),判断是否出过题
   * @tag 题目试卷 前端控制器
   */
  get(url: '/paper/get-total/{userId}', options?: SwaggerOptions<{userId: unknown}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 通过试卷ID,获取试卷详情
   * @tag 题目试卷 前端控制器
   */
  get(url: '/paper/get/{paperId}', options?: SwaggerOptions<{paperId: unknown}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 删除试卷
   * @tag 题目试卷 前端控制器
   */
  delete(url: '/paper/remove/{paperId}', options?: SwaggerOptions<{paperId: unknown}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 添加试卷
   * @tag 题目试卷 前端控制器
   */
  post(url: '/paper/save', options?: SwaggerOptions<{}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 更新试卷
   * @tag 题目试卷 前端控制器
   */
  put(url: '/paper/update', options?: SwaggerOptions<{}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 更新所有接口路径
   * @tag 路径表 前端控制器
   */
  get(url: '/path/update-all-path', options?: SwaggerOptions<{}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 更新所有接口路径(不要权限)
   * @tag 路径表 前端控制器
   */
  get(url: '/path/update-all-path-test', options?: SwaggerOptions<{}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 获取道具
   * @tag 道具表 前端控制器
   */
  put(url: '/prop/gain/{userId}/{propId}/{num}', options?: SwaggerOptions<{num: unknown,propId: unknown,userId: unknown}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 获取用户道具信息
   * @tag 道具表 前端控制器
   */
  get(url: '/prop/get-props/{userId}', options?: SwaggerOptions<{userId: unknown}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 使用道具
   * @tag 道具表 前端控制器
   */
  put(url: '/prop/use/{userId}/{propId}/{num}', options?: SwaggerOptions<{num: unknown,propId: unknown,userId: unknown}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 随机获取10个题目(不要权限)
   * @tag 题目表 前端控制器
   */
  get(url: '/question/get-random-test', options?: SwaggerOptions<{}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 随机获取10个题目
   * @tag 题目表 前端控制器
   */
  get(url: '/question/get-random/{userId}', options?: SwaggerOptions<{userId: unknown}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 完成任务,并获取奖励
   * @tag 任务数据 前端控制器
   */
  post(url: '/task/finish/{userId}/{taskId}', options?: SwaggerOptions<{taskId: unknown,userId: unknown}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 获取全部任务
   * @tag 任务数据 前端控制器
   */
  get(url: '/task/get-all/{userId}', options?: SwaggerOptions<{userId: unknown}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 保存用户使用功能日志
   * @tag 脚本使用信息 前端控制器
   */
  post(url: '/use-info/send-log', options?: SwaggerOptions<{}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 通过用户ID,获取用户密友信息
   * @tag 用户信息表 前端控制器
   */
  get(url: '/user/get-intimate-friends/{userId}', options?: SwaggerOptions<{userId: unknown}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 通过用户ID,获取用户排行榜信息
   * @tag 用户信息表 前端控制器
   */
  get(url: '/user/get-intimate-ranking/{userId}', options?: SwaggerOptions<{userId: unknown}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 通过用户ID,获取用户信息
   * @tag 用户信息表 前端控制器
   */
  get(url: '/user/get/{userId}', options?: SwaggerOptions<{userId: unknown}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 验证试卷是不是自己的,或者已经答过的
   * @tag 用户信息表 前端控制器
   */
  get(url: '/user/verify-paper/{userId}/{paperId}', options?: SwaggerOptions<{paperId: unknown,userId: unknown}, {}, {}, unknown>): Promise<unknown>
  /**
   * @summary 微信小程序登录
   * @tag 微信相关接口
   */
  post(url: '/wx-user/login', options?: SwaggerOptions<{}, {code: unknown,miniappId: unknown}, {}, unknown>): Promise<unknown>
  /**
   * @summary 修改微信小程序用户信息
   * @tag 微信相关接口
   */
  post(url: '/wx-user/update', options?: SwaggerOptions<{}, {}, {}, unknown>): Promise<unknown>
}


// swagger runtime. generate by swagger2ts
interface IRuntimeHeaderMapString {
  [key: string]: string | string[];
}

interface IRuntimeHeaderConfig {
  common: IRuntimeHeaderMapString;
  [method: string]: IRuntimeHeaderMapString;
}

interface IRuntimeRequestCommonOptions extends Omit<RequestInit, "body" | "method" | "headers"> {
  path?: Record<string, string>;
  query?: Record<string, string | number | any[] | Record<string, any>>;
  headers?: Record<string, string | string[]>;
  body?: any;
  timeout?: number;
}

interface IRuntimeRequestOptions extends IRuntimeRequestCommonOptions {
  url: string;
  method: Uppercase<string>;
}

interface IRequestInterceptor {
  use(fn: IRequestInterceptorFn): IInterceptorCancelFn;
}

interface IResponseInterceptor {
  use(success: IResponseInterceptorSuccessFn<any>, error: IResponseInterceptorErrorFn<any>): IInterceptorCancelFn;
}

type IInterceptorCancelFn = () => void;
type IRequestInterceptorFn = (config: IRuntimeRequestOptions) => Promise<IRuntimeRequestOptions>;
type IResponseInterceptorSuccessFn<T> = (config: IRuntimeRequestOptions, response: Response, data: T) => Promise<T>;
type IResponseInterceptorErrorFn<T> = (config: IRuntimeRequestOptions, Error: RuntimeError) => Promise<T>;

export class RequestInterceptor implements IRequestInterceptor {
  private _fns: IRequestInterceptorFn[] = [];
  public use(fn: IRequestInterceptorFn) {
    this._fns.push(fn);

    return () => {
      const index = this._fns.findIndex((v) => v === fn);

      if (index > -1) {
        this._fns.splice(index, 1);
      }
    };
  }

  async run(config: IRuntimeRequestOptions): Promise<IRuntimeRequestOptions> {
    for (const fn of this._fns) {
      config = await fn(config);
    }

    return config;
  }
}

export class ResponseInterceptor implements IResponseInterceptor {
  private _fnsSuccess: IResponseInterceptorSuccessFn<any>[] = [];
  private _fnsError: IResponseInterceptorErrorFn<any>[] = [];
  public use(successFn: IResponseInterceptorSuccessFn<any>, errorFn: IResponseInterceptorErrorFn<any>) {
    this._fnsSuccess.push(successFn);
    this._fnsError.push(errorFn);

    return () => {
      const successIndex = this._fnsSuccess.findIndex((v) => v === successFn);
      const errorIndex = this._fnsError.findIndex((v) => v === errorFn);

      if (successIndex > -1) {
        this._fnsSuccess.splice(successIndex, 1);
      }

      if (errorIndex > -1) {
        this._fnsError.splice(errorIndex, 1);
      }
    };
  }

  async runSuccess<T>(config: IRuntimeRequestOptions, response: Response, data: T): Promise<T> {
    for (const fn of this._fnsSuccess) {
      data = await fn(config, response, data);
    }

    return data;
  }

  async runError<T>(config: IRuntimeRequestOptions, err: RuntimeError): Promise<T> {
    let res!: T;

    for (const fn of this._fnsError) {
      res = await fn(config, err);
    }

    return res;
  }
}

type TypedFormDataValue = FormDataEntryValue | Blob | Uint8Array;

export class TypedFormData<T extends Record<string, TypedFormDataValue>> {
  constructor(private _form: T) {}
  public formData(): FormData {
    const form = new FormData();

    for (const key in this._form) {
      const value = this._form[key];
      if (value !== undefined) {
        if (value instanceof Uint8Array) {
          form.append(key, new Blob([value]));
        } else {
          // @ts-ignore
          form.append(key, value);
        }
      }
    }

    return form;
  }
}

export class RuntimeError extends Error {
  constructor(message: string, private _resp?: Response) {
    super(message);
  }

  public get response(): Response | undefined {
    return this._resp;
  }

  static fromResponse(resp: Response) {
    return new RuntimeError(resp.statusText, resp);
  }

  static fromError(err: Error) {
    return new RuntimeError(err.message || "unknown error: " + err);
  }
}

interface ISerializer {
  use(fn: Function): void;
}

export class QuerySerializer implements ISerializer {
  use(fn: URLQuerySerializer) {
    this.__fn__ = fn;
  }

  private __fn__: URLQuerySerializer = (query) => {
    const params = new URLSearchParams();
    for (const key in query) {
      const value = query[key];
      if (value !== undefined && value !== null) {
        if (Object.prototype.toString.call(value) === "[object Object]") {
          params.append(key, JSON.stringify(value));
        } else if (Array.isArray(value)) {
          value.forEach((v) => params.append(key, v));
        } else {
          params.append(key, value + "");
        }
      }
    }

    return params
  };
}

type URLQuerySerializer = (query: Record<string, string | number | any[] | Record<string, any>>) => URLSearchParams;

class URL {
  public searchParams: CustomURLSearchParams;
  private _url: string;

  constructor(url: string) {
    this._url = url;

    // 正则表达式解析 URL
    const urlPattern = /^(https?:\/\/)?(([^:/?#]+)(?::([^/?#]*))?@)?([^:/?#]+)(?::(\d+))?(\/[^?#]*)?(\?[^#]*)?(#.*)?$/;
    const matches = url.match(urlPattern);

    if (matches) {
      this._protocol = matches[1] || '';
      this._username = matches[3] || '';
      this._password = matches[4] || '';
      this._hostname = matches[5] || '';
      this._port = matches[6] || '';
      this._pathname = matches[7] || '';
      this._search = matches[8] || '';
      this._hash = matches[9] || '';
      this._host = this._hostname + (this._port ? ':' + this._port : '');
      this.searchParams = new CustomURLSearchParams(this._search);
    } else {
      throw new Error('Invalid URL');
    }
  }

  private _hash: string;

  // Properties with getter and setter
  get hash(): string {
    return this._hash;
  }

  set hash(value: string) {
    this._hash = value.startsWith('#') ? value : `#${value}`;
    this.updateHref();
  }

  private _host: string;

  get host(): string {
    return this._host;
  }

  set host(value: string) {
    const [hostname, port] = value.split(':');
    this._hostname = hostname || '';
    this._port = port || '';
    this._host = value;
    this.updateHref();
  }

  private _hostname: string;

  get hostname(): string {
    return this._hostname;
  }

  set hostname(value: string) {
    this._hostname = value;
    this._host = this._port ? `${value}:${this._port}` : value;
    this.updateHref();
  }

  private _pathname: string;

  get pathname(): string {
    return this._pathname;
  }

  set pathname(value: string) {
    this._pathname = value.startsWith('/') ? value : `/${value}`;
    this.updateHref();
  }

  private _port: string;

  get port(): string {
    return this._port;
  }

  set port(value: string) {
    this._port = value;
    this._host = this._hostname + (value ? ':' + value : '');
    this.updateHref();
  }

  private _protocol: string;

  get protocol(): string {
    return this._protocol;
  }

  set protocol(value: string) {
    this._protocol = value.endsWith(':') ? value : `${value}:`;
    this.updateHref();
  }

  private _search: string;

  get search(): string {
    return this._search;
  }

  set search(value: string) {
    this._search = value.startsWith('?') ? value : `?${value}`;
    this.searchParams = new CustomURLSearchParams(this._search);
    this.updateHref();
  }

  private _username: string;

  get username(): string {
    return this._username;
  }

  set username(value: string) {
    this._username = value;
    this.updateHref();
  }

  private _password: string;

  get password(): string {
    return this._password;
  }

  set password(value: string) {
    this._password = value;
    this.updateHref();
  }

  get href(): string {
    return this._url;
  }

  set href(value: string) {
    this._url = value;
    // 重新解析 URL
    const urlPattern = /^(https?:\/\/)?(([^:/?#]+)(?::([^/?#]*))?@)?([^:/?#]+)(?::(\d+))?(\/[^?#]*)?(\?[^#]*)?(#.*)?$/;
    const matches = value.match(urlPattern);
    if (matches) {
      this._protocol = matches[1] || '';
      this._username = matches[3] || '';
      this._password = matches[4] || '';
      this._hostname = matches[5] || '';
      this._port = matches[6] || '';
      this._pathname = matches[7] || '';
      this._search = matches[8] || '';
      this._hash = matches[9] || '';
      this._host = this._hostname + (this._port ? ':' + this._port : '');
      this.searchParams = new CustomURLSearchParams(this._search);
    } else {
      throw new Error('Invalid URL');
    }
  }

  get origin(): string {
    return `${this._protocol}//${this._host}`;
  }

  // Methods
  toString(): string {
    return this.href;
  }

  toJSON(): object {
    return {
      hash: this.hash,
      host: this.host,
      hostname: this.hostname,
      href: this.href,
      origin: this.origin,
      password: this.password,
      pathname: this.pathname,
      port: this.port,
      protocol: this.protocol,
      search: this.search,
      searchParams: this.searchParams.toString(),
      username: this.username
    };
  }

  private updateHref(): void {
    // 更新 href 的方法
    this._url = `${this._protocol}//${this._username}${this._password ? ':' + this._password : ''}@${this._host}${this._pathname}${this._search}${this._hash}`;
  }
}


class CustomURLSearchParams {
  private params: Map<string, string>;

  constructor(init: string | string[][] = '') {
    this.params = new Map();

    if (typeof init === 'string') {
      this.parse(init);
    } else if (Array.isArray(init)) {
      init.forEach(([key, value]) => {
        this.params.set(key, value);
      });
    }
  }

  append(key: string, value: string): void {
    this.params.set(key, value);
  }

  delete(key: string): void {
    this.params.delete(key);
  }

  entries(): IterableIterator<[string, string]> {
    return this.params.entries();
  }

  forEach(callback: (value: string, key: string) => void): void {
    this.params.forEach(callback);
  }

  get(key: string): string | null {
    return this.params.get(key) || null;
  }

  getAll(key: string): string[] {
    return this.params.has(key) ? [this.params.get(key)!] : [];
  }

  has(key: string): boolean {
    return this.params.has(key);
  }

  keys(): IterableIterator<string> {
    return this.params.keys();
  }

  set(key: string, value: string): void {
    this.params.set(key, value);
  }

  sort(): void {
    const sortedParams = [...this.params.entries()].sort(([key1], [key2]) => key1.localeCompare(key2));
    this.params = new Map(sortedParams);
  }

  toString(): string {
    return [...this.params.entries()]
      .map(([key, value]) => `${encodeURIComponent(key)}=${encodeURIComponent(value)}`)
      .join('&');
  }

  values(): IterableIterator<string> {
    return this.params.values();
  }

  private parse(queryString: string): void {
    queryString = queryString.startsWith('?') ? queryString.slice(1) : queryString;
    const pairs = queryString.split('&');
    pairs.forEach(pair => {
      const [key, value] = pair.split('=');
      if (key) {
        this.params.set(decodeURIComponent(key), decodeURIComponent(value));
      }
    });
  }
}

export interface IRuntime {
  readonly interceptors: { readonly request: IRequestInterceptor; readonly response: IResponseInterceptor };
  readonly serializer: { readonly query: QuerySerializer };
  readonly defaults: { readonly timeout: number; readonly headers: IRuntimeHeaderConfig };
  readonly baseURL: string;
  domain: string;
  prefix: string;
  request<T>(config: IRuntimeRequestOptions): Promise<T>;
  clone(): IRuntime;
}

export class Runtime implements IRuntime {
  private _requestInterceptor = new RequestInterceptor();
  private _responseInterceptor = new ResponseInterceptor();
  private _querySerializer = new QuerySerializer();

  constructor(private _domain: string, private _prefix: string) {
    const methods = ["get", "post", "delete", "put", "head", "options", "trace", "patch"];

    for (const method of methods) {
      // @ts-ignore ignore error
      this[method] = (url: string, config?: IRuntimeRequestCommonOptions = {}) => {
        return this.request({
          method: method.toUpperCase() as Uppercase<string>,
          url,
          ...config,
        });
      };
    }

    this._querySerializer.use((query) => {
      const params = new URLSearchParams();

      for (const key in query) {
        const value = query[key];
        if (value !== undefined && value !== null) {
          if (Object.prototype.toString.call(value) === "[object Object]") {
            params.append(key, JSON.stringify(value));
          } else if (Array.isArray(value)) {
            value.forEach((v) => params.append(key, v));
          } else {
            params.append(key, value + "");
          }
        }
      }

      return params;
    });
  }

  private _defaults = {
    timeout: 60 * 1000, // 60s,
    headers: {
      common: {
        "Content-Type": "application/json",
      },
    } as IRuntimeHeaderConfig,
  };

  public get defaults() {
    return this._defaults;
  }

  public get interceptors() {
    const self = this;
    return {
      get request() {
        return self._requestInterceptor as IRequestInterceptor;
      },
      get response() {
        return self._responseInterceptor as IResponseInterceptor;
      },
    };
  }

  public get serializer() {
    const self = this;

    return {
      get query() {
        return self._querySerializer;
      },
    };
  }

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

  public async request<T>(config: IRuntimeRequestOptions): Promise<T> {
	console.log(JSON.stringify(config));
    config = await this._requestInterceptor.run(config);
    const url = new URL(this.baseURL + config.url);
    config.headers = config.headers || {};

    const headersObject: typeof config.headers = Object.create(null);

    const defaults = this.defaults;

    // set default header
    for (const key in defaults.headers.common) {
      headersObject[key] = defaults.headers.common[key];
    }

    // set header for this method
    for (const key in defaults.headers[config.method] || {}) {
      headersObject[key] = defaults.headers[config.method][key];
    }

    Object.assign(headersObject, config.headers);

    // set query for this method
    if (config.query) {
      // @ts-expect-error ignore
      const params = this.serializer.query.__fn__(config.query);

      url.search = params.toString();
    }

    // set path for this method
    if (config.path) {
      for (const key in config.path) {
        const t1 = encodeURI("{");
        const t2 = encodeURI("}");
        const reg = new RegExp(`${t1}${key}${t2}`, "g");
        url.pathname = url.pathname.replace(reg, config.path[key]);
      }
    }

    const headers = new Headers();

    for (const key in headersObject) {
      const value = headersObject[key];
      if (value !== undefined) {
        if (Array.isArray(value)) {
          headers.delete(key);
          value.forEach((v) => headers.append(key, v));
        } else {
          headers.set(key, value);
        }
      }
    }

    const timeout = config.timeout ?? defaults.timeout;

    const body =
      config.body === undefined
        ? undefined
        : ["GET", "HEAD"].indexOf(config.method.toUpperCase()) > -1
        ? undefined
        : config.body instanceof TypedFormData
        ? config.body.formData()
        : config.body instanceof FormData
        ? config.body
        : config.body instanceof Blob
        ? config.body
        : typeof config.body === "object"
        ? JSON.stringify(config.body)
        : config.body.toString();

    // 如果是 FormData, 删除 Content-Type，让浏览器自动设置
    if (body instanceof FormData) {
      headers.delete("Content-Type");
    }

    const exec = () =>
      fetch(url.toString(), {
        method: config.method,
        body: body,
        headers: headers,

        // common options
        cache: config.cache,
        credentials: config.credentials,
        integrity: config.integrity,
        keepalive: config.keepalive,
        mode: config.mode,
        redirect: config.redirect,
        referrer: config.referrer,
        referrerPolicy: config.referrerPolicy,
        signal: config.signal,
        window: config.window,
      });

    return (timeout ? this._timeout(timeout, exec()) : exec())
      .then(async (resp) => {
        if (!resp.ok) return Promise.reject(RuntimeError.fromResponse(resp));
        const contentType = resp.headers.get("content-type");
        switch (contentType) {
          case "application/json":
            return { data: await resp.json(), resp };
          case "application/x-www-form-urlencoded":
            return { data: await resp.formData(), resp };
          case "application/octet-stream":
            return { data: await resp.blob(), resp };
          default:
            return { data: await resp.text(), resp };
        }
      })
      .then(({ data, resp }) => {
        return this._responseInterceptor.runSuccess<T>(config, resp, data);
      })
      .catch((err) => {
        const runtimeErr =
          err instanceof RuntimeError ? err : err instanceof Error ? RuntimeError.fromError(err as unknown as Error) : new RuntimeError(err + "");

        return this._responseInterceptor.runError<T>(config, runtimeErr);
      });
  }

  public clone() {
    return new Runtime(this._domain, this._prefix);
  }

  private _timeout<T>(ms: number, promise: Promise<T>) {
    if (ms <= 0) return promise

    return new Promise<T>((resolve, reject) => {
      const timer = setTimeout(() => {
        reject(new RuntimeError(`timeout of ${ms}ms`));
      }, ms);

      promise
        .then(resolve)
        .catch(reject)
        .finally(() => {
          clearTimeout(timer);
        });
    });
  }
}

export type IClient = SwaggerApi & IRuntime
// export const defaultApi = new Runtime("https://qzwdyz.top", "quiz") as unknown as IClient
export const defaultApi = new Runtime("http://localhost:1888", "") as unknown as IClient