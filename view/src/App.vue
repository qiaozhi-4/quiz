<script setup lang="ts">
import { onLaunch, onShow, onHide } from "@dcloudio/uni-app";
import { getPaperTotal } from "@/utils/api/paper";
import { login } from "@/utils/api/wxUser";
import { useStore } from "@/stores/store";
import { objectToPathParams } from "@/utils/service";
const store = useStore();
onLaunch(() => {
    console.log("App Launch");
    uni.login({
        provider: "weixin", //使用微信登录
        success: (loginRes) => {
            login(loginRes.code, "wxf2f9d21291120320").then((res) => {
                // login('wx0f4e873ad758a586', loginRes.code).then((res) => {
                store.$patch(partialState => {
                    partialState.user = res.data.userInfo;
                    partialState.token = res.data.token;
                    partialState.props = res.data.props;
                });

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
                        uni.reLaunch({
                            url: `/pages/user-set/user-set`,
                        });
                    } else {
                        // 分享进来的要保留分享页
                        uni.navigateTo({
                            url: `/pages/user-set/user-set`,
                        });
                    }
                } else {
                    console.log("已设置头像和用户名");
                    if ("pages/index/index" == route) { // 不是分享进来的就跳转
                        if (store.user.paperTotal == 0) {
                            uni.reLaunch({
                                url: `/pages/paper-start/paper-start`
                            });
                        } else {
                            uni.reLaunch({
                                url: `/pages/home/home`,
                            });
                        }
                    }
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
