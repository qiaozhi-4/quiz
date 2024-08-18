<script setup lang="ts">
import { onLaunch, onShow, onHide } from "@dcloudio/uni-app";
import { getPaperTotal } from "@/utils/api/paper";
import { login } from "@/utils/api/wxUser";
import { useStore } from "@/stores/store";
const store = useStore();
onLaunch(() => {
    console.log("App Launch");
    uni.login({
        provider: "weixin", //使用微信登录
        success: (loginRes) => {
            console.log("微信登录信息:", loginRes);
            login(loginRes.code, "wxf2f9d21291120320").then((res) => {
                // login('wx0f4e873ad758a586', loginRes.code).then((res) => {
                store.$patch(partialState =>{
                    partialState.user = res.data.userInfo
                    partialState.token = res.data.token
                    partialState.props = res.data.props
                })

                /** 获取第一个页面路由信息 */
                let route = getCurrentPages()[0].route;
                /** 判断是不是没有设置头像或者昵称 */
                if (
                    res.data.userInfo.nickname == null ||
                    res.data.userInfo.avatarUrl == null ||
                    /^\s*$/.test(res.data.userInfo.nickname) ||
                    /^\s*$/.test(res.data.userInfo.avatarUrl)
                ) {
                    console.log("当前用户还没设置头像和用户名,跳转到设置页面");
                    if ("pages/index/index" == route) {
                        uni.redirectTo({
                            url: `/pages/set-user-info/set-user-info`,
                        });
                    } else {
                        // 分享进来的要保留分享页
                        uni.navigateTo({
                            url: `/pages/set-user-info/set-user-info`,
                        });
                    }
                } else {
                    console.log("已设置头像和用户名");
                    getPaperTotal(res.data.userInfo.userId).then((res) => {
                        if ("pages/index/index" == route) {
                            // 不是分享进来的就跳转
                            if (res.data == 0) {
                                uni.redirectTo({
                                    url: `/pages/start-test/start-test?isAnswer=${false}`,
                                });
                            } else {
                                uni.redirectTo({
                                    url: `/pages/home/home`,
                                });
                            }
                        }
                    });
                }
            });
        },
    });
});
onShow(() => {
    console.log("App Show");
});
onHide(() => {
    console.log("App Hide");
});
</script>
<style lang="scss">
/*每个页面公共css */
@import "@/quiz.scss";
</style>
