import { request } from "./request"

/** 登录 */
export function login(code: string) {
  return request({
    url: '/wx-user/login?code=' + code,
    method: 'POST',
  })
}
