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
    actions: {
        /** 根据道具id获取道具 */
        getPropById(propId: number): Quiz.PropDTO | undefined {
            return this.props.find(e => e.propId == propId);
        },
        /** 根据道具id添加道具数量 */
        addPropNumberById(propId: number, num: number) {
            let prop = this.props.find(e => e.propId == propId);
            if (prop) { prop.number += num; }
        },
        /** 使用指定道具 */
        usePropNumberById(propId: number, num = 1) {
            this.addPropNumberById(propId, -num);
        }
    },
});
