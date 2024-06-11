import { request } from "./request"

/** 登录 */
export function login(code: string) {
  return request({
    url: '/wx-user/login?code=' + code,
    method: 'POST',
  })
}
/** 保存个人信息 */
export function userSave(data: Quiz.UserInfo) {
  return request({
    url: '/wx-user/save',
    method: 'POST',
    data,
  })
}
/** 获取随机10个题目 */
export function getRandomQuestions() {
  return request({
    url: '/question/get-random-questions-test',
    method: 'GET'
  })
}