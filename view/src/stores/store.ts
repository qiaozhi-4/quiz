import { defineStore } from "pinia";

export const useStore = defineStore("counter", {
  state: () => {
    return {
      /** 当前登录用户信息 */
      user: {} as Quiz.UserDto,
      /** 当前登录token */
      token: "" as string,
      /** 当前登录道具 */
      props: [] as Quiz.PropDTO[],
    };
  },
  getters: {},
  actions: () => {},
});
