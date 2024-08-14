declare namespace Quiz {
  /** 后端接口响应数据 */
  interface Result<T = any> {
    /** 状态码 */
    code: number
    /** 提示信息 */
    message: string
    /** 响应数据 */
    data: T
  }
}