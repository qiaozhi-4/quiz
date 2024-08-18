<template>
    <!-- 提示消息 -->
    <q-alert ref="refAlert"></q-alert>
    <!-- 设置 -->
    <q-dialog extraClass="dialog" ref="refDialog" maskHideDialog>
        <q-svg class="svg" icon="home-设置-关闭" size="38" @click="() => refDialog.hide()"></q-svg>
        <view class="v1 text">设置</view>
        <button class="but text" @click="() => refAlert.show({ msg: '该功能未实现哦' })">问题反馈</button>
        <button class="but text" open-type="contact">联系客服</button>
    </q-dialog>
    <view class="page">
        <scroll-view class="scroll-view" scroll-y="true" @scroll="onScroll" :scroll-top="scrollTop"
            scroll-with-animation>
            <q-nav-bar v-if="!isFriendHome" fixed>
                <template #left>
                    <q-svg icon="设置" size="32" @click="() => refDialog.show()" />
                </template>
            </q-nav-bar>
            <q-nav-bar v-else fixed></q-nav-bar>
            <view class="main flex-column">
                <view class="v1 flex-column">
                    <q-avatar :isChooseAvatar="!isFriendHome" :src="own?.avatarUrl" size="69" borderWidth="3" />
                    <view class="nickname">{{ own?.nickname }}</view>
                </view>
                <view class="v2">
                    <view class="statistics">
                        <text class="text1">{{ questionBank?.length }}</text>
                        <text class="text2">出题</text>
                    </view>
                    <view class="statistics">
                        <text class="text1">{{ testBank?.length }}</text>
                        <text class="text2">答题</text>
                    </view>
                    <view class="statistics">
                        <text class="text1">{{ intimateFriends?.length }}</text>
                        <text class="text2">密友</text>
                    </view>
                </view>
                <view class="v3 flex-column">
                    <view v-if="isFriendHome" style="height: 30px;"></view>
                    <view v-else class="task">
                        <text class="t1">{{ prop[0]?.number }}分</text>
                        <text class="t2">本周连续回答10题</text>
                        <button class="b1" @click="clickTask">领取奖励</button>
                    </view>
                    <view class="table flex-column">
                        <view class="table-tags" :style="{ paddingTop: `${tagPaddingTop}px` }">
                            <view class="tag"
                                :class="{ active: activeTag == index, left: index == activeTag + 1, right: index == activeTag - 1 }"
                                v-for="(tag, index) in tags" :key="index" @click="onTag(index)">
                                {{ tag }}
                            </view>
                        </view>
                        <view v-show="activeTag == 0" class="table-content flex-column">
                            <template v-if="intimateRanking?.length != 0">
                                <view v-if="myRanking?.totalScore" class="my-ranking">
                                    <view class="nickname">{{ myRanking?.nickname }}</view>
                                    <view class="score-grou">
                                        <text class="score">{{ myRanking?.totalScore }}</text>
                                        <text class="t1">分</text>
                                    </view>
                                    <view class="ranking">位于第{{ myRanking?.ranking }}名</view>
                                </view>
                                <view v-for="(friend, index) in intimateRanking" :key="index" class="table-item"
                                    style="position: relative;" @click="goFriendHome(friend.userId)">
                                    <view
                                        style="position: absolute;top: 0;right: 0;width: 76vw; height: 1px;background-color: rgba(121, 109, 255, 0.5);">
                                    </view>
                                    <view class="ranking" :class="`no${index + 1}`">{{ index + 1 }}</view>
                                    <q-avatar :src="friend.avatarUrl" size="42" />
                                    <view class="nickname" :class="`no${index + 1}`">{{ friend.nickname }}</view>
                                    <view class="score" :class="`no${index + 1}`">{{ friend.totalScore }}</view>
                                    <q-svg class="svg" icon="个人主页-排行-转到好友" size="22" />
                                </view>
                            </template>
                            <template v-else>
                                <text class="table-null-text t1">您的亲密排行空空如也</text>
                                <text class="table-null-text t2">🌟快去邀请朋友吧！</text>
                                <!-- <button class="table-null-button b1">让朋友测测！</button> -->
                            </template>
                        </view>
                        <view v-show="activeTag == 1" class="test-info flex-column">
                            <template v-if="true">
                                制作中
                            </template>
                            <template v-else>
                                <view v-for="(e, index) in testInfo" :key="index" class="class flex-column">
                                    <template v-if="index < 2">
                                        <view class="name1">{{ e?.name }}</view>
                                        <template v-for="(item, i) in e.items" :key="i">
                                            <view v-if="item?.title && index == 0" class="item1 flex-column">
                                                <view class="question-title">
                                                    <q-svg icon="左双引号" size="10" />
                                                    <view class="title text-overflow">{{ item?.title }}</view>
                                                </view>
                                                <view class="question-info">
                                                    <view class="option text-overflow">{{ item?.select }}</view>
                                                    <view class="input-value text-overflow">{{ item?.input }}</view>
                                                </view>
                                                <q-svg class="svg" icon="个人主页-测试信息-删除" size="22"
                                                    @click="remove(index, i)"></q-svg>
                                            </view>

                                            <view v-if="item?.title && index == 1" class="item1-1">
                                                <view class="user-info flex-column">
                                                    <q-avatar :src="item?.avatarUrl" size="41"></q-avatar>
                                                    <view class="nickname">{{ item?.nickname }}</view>
                                                </view>
                                                <view class="topic flex-column">
                                                    <q-svg class="svg" icon="左双引号" size="10" />
                                                    <text class="title text-overflow">{{ item?.title }}</text>
                                                    <text class="answer text-overflow">{{ item?.select }}</text>
                                                </view>
                                                <q-svg class="svg" icon="个人主页-测试信息-删除" size="22"
                                                    @click="remove(index, i)"></q-svg>
                                            </view>
                                        </template>
                                        <view class="add flex-column" @click="add(index)">
                                            <text class="t1">{{ e?.extra?.t1 }}</text>
                                            <text class="t2">{{ e?.extra?.t2 }}</text>
                                            <q-svg class="svg" icon="个人主页-测试信息-添加" size="22"></q-svg>
                                        </view>
                                    </template>
                                    <template v-else>
                                        <view class="name1">{{ e.name }}</view>
                                        <view class="items flex-column">
                                            <template v-for="(item, i) in e.items" :key="i">
                                                <view v-if="i < 4 || e.spread" class="item2">
                                                    <view class="title text-overflow">{{ item?.title }}</view>
                                                    <view class="option text-overflow">{{ item?.select }}</view>
                                                    <view class="input-value text-overflow">{{ item?.input }}</view>
                                                </view>
                                            </template>
                                        </view>
                                        <text v-if="e.spread" class="t1" @click="hidden(index)">收起</text>
                                        <text v-else class="t1" @click="showAll(index)">查看全部</text>
                                    </template>
                                </view>
                            </template>
                        </view>
                        <view v-show="activeTag == 2" class="table-record flex-column">
                            <view class="input-wrap">
                                <q-svg class="svg" icon="搜索" size="14.5" />
                                <input class="input" placeholder-class="input-placeholder" placeholder="搜索"
                                    :adjust-position="false" @input="onInput" @blur="onBlur" :value="input" />
                            </view>
                            <template v-if="isFriendHome">
                                <template v-for="(question, index) in questionBank" :key="index">
                                    <view :style="question?.isAnswer ? '' : 'background-color: #352858;'"
                                        class="test-paper-friend flex-column">
                                        <view class="v1">
                                            <text class="title">{{ own?.nickname }}的{{ question?.order }}号测试</text>
                                            <text class="correct"></text>
                                            <text class="num">{{ question?.isAnswer ? question?.score / 10 : 0 }}</text>
                                            <text class="error"></text>
                                            <text class="num">{{ question?.isAnswer ? 10 - question?.score / 10 : 0 }}</text>
                                        </view>
                                        <view class="progress-warp">
                                            <view :style="`width: ${question?.score}%;`" class="progress">
                                            </view>
                                        </view>
                                        <view class="v2">
                                            <template v-if="question?.isAnswer">
                                                <button class="button"
                                                    @click="onParticulars(question?.paperId, question?.selects)">查看详情</button>
                                                <button v-show="question?.score != 100" class="button flex-row"
                                                    @click="goTest(question?.paperId)">
                                                    <q-svg icon="复活宝石" size="16" />使用复活宝石再测一次！
                                                </button>
                                            </template>
                                            <button v-else class="button"
                                                @click="goTest(question?.paperId)">开始测试</button>
                                        </view>
                                    </view>
                                </template>
                            </template>
                            <template v-else>
                                <view v-for="(question, index) in questionBank" :key="index"
                                    class="test-paper-my flex-column">
                                    <view class="v1">
                                        <text class="title">{{ own?.nickname }}的{{ question?.order }}号测试</text>
                                        <text class="count">{{ question?.answersTotal }}个朋友测过</text>
                                    </view>
                                    <view class="v2">
                                        <view class="extra">
                                            <q-bubble>
                                                <reference>
                                                    <button class="button"
                                                        @click="onRemorPafer(question?.paperId, index)">删除</button>
                                                </reference>
                                            </q-bubble>
                                        </view>
                                        <button class="button"
                                            @click="onParticulars(question?.paperId, null)">查看详情</button>
                                        <button :data-paperId="question?.paperId" class="button"
                                            open-type="share">分享给朋友</button>
                                    </view>
                                </view>
                            </template>
                        </view>
                        <view style="min-height: 160px; background-color: #1F1146;"></view>
                    </view>

                </view>
            </view>
        </scroll-view>
        <view v-if="isFriendHome" v-show="activeTag != 2" class="footer">
            <button class="b1" @click="activeTag = 2">测测看我和Ta的默契！</button>
        </view>
        <view v-else class="footer">
            <button class="b1" @click="goSetTest">去出题</button>
        </view>
    </view>
</template>

<!-- <script lang="ts" setup>
import { ref } from "vue";
import { onLoad, onShareAppMessage } from "@dcloudio/uni-app";
import { useStore } from "@/stores/store";
/** 获取用户基本信息 */
const store = useStore();
/** 提示消息ref */
const refAlert = ref();
/** 对话框ref */
const refDialog = ref();
/** 自己的信息 */
const own = ref<Quiz.UserDto>(store.user);
/** 好友的信息(只有进入好友主页才有值) */
const friend = ref<Quiz.UserDto>();
/** 是否是好友主页 */
const isFriend = ref<boolean>(false);
onLoad((option) => {
    isFriend.value = option?.isFriend;
});
</script> -->

<script lang="ts" setup>
import { ref } from 'vue';
import { onLoad, onShareAppMessage } from '@dcloudio/uni-app';
import { getPaperList, removePaper } from '@/utils/api/paper';
import { getIntimateFriends, getIntimateRanking, getUser } from '@/utils/api/user';
import { getAnswersList } from '@/utils/api/answers';
import { useStore } from "@/stores/store";
import { homeTestInfo } from '@/utils/constant';

/** 提示消息ref */
const refAlert = ref();
/** 设置 */
const refDialog = ref();
/** 自己的信息 */
const own = ref<Quiz.UserDto>(useStore().user);
/** 好友的信息(只有进入好友主页才有值) */
const friend = ref<Quiz.UserDto>();
/** 获取道具信息 */
const prop = ref<any[]>();
/** 标签 */
const tags = ref(['亲密排行', '问答展示', '我的题库']);
/** 当前激活的标签 */
const activeTag = ref(0);
/** 滚动条位置 */
const scrollTop = ref(0);
/** 标签触发粘性定位的上边距 */
const tagPaddingTop = ref(0);
/** 亲密排行榜数据 */
const intimateRanking = ref<Quiz.UserInfo[]>();
/** 密友数据 */
const intimateFriends = ref<Quiz.UserInfo[]>();
/** 测试信息数据 */
const testInfo = ref(homeTestInfo);
/** 搜索框输入 */
const input = ref('');
/** 题库数据 */
const questionBank = ref([]);
/** 测试记录 */
const testBank = ref([]);
/** 是否时朋友主页 */
const isFriendHome = ref<boolean>(false);
/** 我在排行榜的排名 */
const myRanking = ref();
/** 关闭当前页面,返回上一个页面 */
function handlerReverseBack() {
    uni.navigateBack({
        delta: 1
    });
}
/** 点击朋友触发 */
function goFriendHome(id) {
    if (id == getApp().globalData.userInfo.userId) {
        refAlert.value.show({ msg: '自己都不认识了吗?' });
        return;
    }
    uni.navigateTo({
        // url: `/pages/friend-home/friend-home?id=${id}`
        url: `/pages/home/home?userId=${id}&isFriendHome=${true}`
    });
}
/** 点击领取奖励触发,跳转到任务页面 */
function clickTask() {
    uni.navigateTo({
        url: `/pages/task/task`
    });
}
/** 页面滚动触发 */
function onScroll(e) {
    if (e.detail.scrollTop > 230) {
        tagPaddingTop.value = 97;
    } else {
        tagPaddingTop.value = 0;
    }
}
/** 点击tag触发 */
function onTag(index) {
    scrollTop.value = 0;
    scrollTop.value = 325;
    activeTag.value = index;
}
/** 删除展示关键题 */
function remove(infoIindex, itemIndx) {
    delete testInfo.value[infoIindex].items[itemIndx];
}
/** 添加展示关键题 */
function add(infoIindex) {
    console.log('添加展示关键题:', infoIindex);
    if (testInfo.value[infoIindex].name == "关键题") {
        uni.navigateTo({
            url: `/pages/select-topic/select-topic?index=${infoIindex}`
        });
    } else if (testInfo.value[infoIindex].name == "朋友的看法") {
        uni.navigateTo({
            url: `/pages/select-friend-view/select-friend-view?index=${infoIindex}`
        });
    }
}
/** 展示全部测试信息 */
function showAll(infoIindex) {
    if (testInfo.value[infoIindex].items.length < 5) {
        for (let i = 0; i < 5; i++) {
            testInfo.value[infoIindex].items.push({
                title: '你最喜欢在周末做什么？',
                select: '在家进行个人活动',
                input: '吃饭睡觉游戏代码'
            });
        }
    }
    testInfo.value[infoIindex].spread = true;
}
/** 收起全部测试信息 */
function hidden(infoIindex) {
    testInfo.value[infoIindex].spread = false;
}
/** 键盘输入触发 */
function onInput(e: any) {
    input.value = e.detail.value;
}
/** 键盘失去焦点触发 */
function onBlur(e: any) {
    input.value = e.detail.value;
}
/** 删除试卷 */
function onRemorPafer(paperId: number, index: number) {
    removePaper(paperId).then(res => {
        if (res.data)
            questionBank.value.splice(index, 1);
    });
}
/** 跳转题库的试卷详情 */
function onParticulars(paperId: number, selects: string) {
    if (isFriendHome.value) {
        uni.navigateTo({
            url: `/pages/question-particulars/question-particulars?isFriendHome=${true}&paperId=${paperId}&selects=${selects}&userId=${own.value.userId}`
        });

    }
    else {
        uni.navigateTo({
            url: `/pages/question-particulars/question-particulars?isFriendHome=${false}&paperId=${paperId}&selects=${selects}&userId=${own.value.userId}`
        });
    }
}
/** 从朋友题库开始答题 */
function goTest(paperId: number) {
    uni.redirectTo({
        url: `/pages/start-test/start-test?isAnswer=true&paperId=${paperId}&userId=${own.value.userId}`
    });
}
/** 分享试卷 */
onShareAppMessage((res) => {
    if (res.from === 'button') {// 来自页面内分享按钮
        return {
            title: '我们之间有多亲密？',
            path: `/pages/start-test/start-test?isAnswer=true&paperId=${res.target.dataset.paperid}&userId=${own.value.userId}`,
            imageUrl: '/static/img/小转发窗.png'
        };
    }
    return {
        title: '这测试',
        path: '/pages/start-test/start-test?isAnswer=true',
        imageUrl: '/static/img/小转发窗.png'
    };
});
/** 跳转到出题页 */
function goSetTest() {
    uni.navigateTo({
        url: `/pages/start-test/start-test?isAnswer=false`
    });
}
onLoad((option) => {
    if (option.topicId) {
        activeTag.value = 1;
        console.log('这是从选择题目来的参数', option);
        testInfo.value[0].items.push({
            title: `题目${option.topicId}`,
            select: '后续从数据查询',
            input: '吃饭睡觉游戏代码'
        });
    } else if (option.friendId) {
        activeTag.value = 1;
        console.log('这是从选择朋友看法来的参数', option);
        testInfo.value[1].items.push({
            nickname: `用户${option.friendId}`,
            avatarUrl: '',
            title: '后续从数据查询',
            select: '喜欢不冷不热不雨不晴，阴天最好',
        });
    }

    /* 如果是朋友主页 */
    if (option.isFriendHome == 'true') {
        isFriendHome.value = true;
        getUser(option.userId).then(res => {
            own.value = res.data;
            getIntimateRanking(own.value.userId).then(res => {
                intimateRanking.value = res.data;
                let userId = getApp().globalData.userInfo.userId;
                for (var i = 0; i < intimateRanking.value.length; i++) {
                    if (userId == intimateRanking.value[i].userId) {
                        myRanking.value = intimateRanking.value[i];
                        myRanking.value.ranking = i + 1;
                        break;
                    }
                }
            });
            getIntimateFriends(own.value.userId).then(res => {
                intimateFriends.value = res.data;
            });
            getPaperList(own.value.userId).then((res) => {
                questionBank.value = res.data;
                const intervalId = setInterval(() => {
                    if (testBank.value.length > 0) {
                        questionBank.value.forEach(el => {
                            let temp = testBank.value.filter(e => el?.paperId == e.paperId)[0];
                            if (temp) {
                                el.score = temp.score;
                                el.selects = temp.selects;
                                el.isAnswer = true;
                            }
                        });
                        clearInterval(intervalId); // 停止定时器
                    }
                }, 2000); // 2000毫秒，即2秒
            });
        });
    } else {
        prop.value = getApp().globalData.props;
        getIntimateRanking(own.value.userId).then(res => {
            intimateRanking.value = res.data;
        });
        getIntimateFriends(own.value.userId).then(res => {
            intimateFriends.value = res.data;
        });
        getPaperList(own.value.userId).then((res) => {
            questionBank.value = res.data;
        });
    }
    getAnswersList(getApp().globalData.userInfo.userId).then(res => {
        testBank.value = res.data;
    });
});
</script>

<style lang="scss">
.page {
    background: #1F1146;

    .scroll-view {

        height: 100vh;
    }

    .head {
        display: grid;
        place-content: end start;
    }

    .main {

        .v1 {
            justify-content: center;
            align-items: center;
            padding: 10px;
            gap: 8px;


            .nickname {
                font-family: 'Inter';
                font-style: normal;
                font-weight: 700;
                font-size: 20px;
                line-height: 24px;
            }
        }

        .v2 {
            justify-content: center;
            align-items: center;
            padding: 10px;
            gap: 10px;

            .statistics {
                justify-content: center;
                align-items: center;
                padding: 6px 26px;
                gap: 6px;

                width: 110px;
                height: 32px;

                background: rgba(255, 255, 255, 0.05);
                border-radius: 15px;

                .text1 {
                    font-family: 'Inter';
                    font-style: normal;
                    font-weight: 800;
                    font-size: 20px;
                    line-height: 24px;
                }

                .text2 {
                    font-family: 'Inter';
                    font-style: normal;
                    font-weight: 500;
                    font-size: 12px;
                    line-height: 15px;
                    color: rgba(255, 255, 255, 0.3);
                }
            }
        }

        .v3 {
            background: #5830C7;
            border-radius: 15px 15px 0px 0px;

            .task {
                display: grid;
                grid-template-columns: 1fr 1fr;
                margin: 0 15px 15px 15px;

                padding: 10px 13px;
                gap: 10px;

                background: #46269E;
                border-radius: 0px 0px 15px 15px;

                .t1 {
                    /* 10答0问 */
                    font-family: 'Inter';
                    font-style: normal;
                    font-weight: 800;
                    font-size: 20px;
                    line-height: 24px;

                    color: #FFFFFF;
                }

                .t2 {
                    /* 本周连续回答10题 */
                    font-family: 'Inter';
                    font-style: normal;
                    font-weight: 700;
                    font-size: 12px;
                    line-height: 15px;

                    color: rgba(255, 255, 255, 0.5);
                }

                .b1 {
                    grid-column: 2;
                    grid-row: 1 / span 2;
                    place-self: center end;

                    padding: 6px 15px;

                    /* 淡紫 粉色渐变 */
                    background: linear-gradient(90deg, #5830C7 0%, #BE53FF 100%);
                    border-radius: 30px;

                    /* 领取奖励 */
                    font-family: 'Inter';
                    font-style: normal;
                    font-weight: 700;
                    font-size: 12px;
                    line-height: 19px;
                    text-align: center;

                    color: #FFFFFF;
                }
            }

            .table {

                .table-tags {
                    position: sticky;
                    /* 当滚动到距离顶部 0 像素时粘住 */
                    top: 0;
                    /* 确保粘性元素在其他元素上方 */
                    z-index: 1;
                    padding-top: 0;
                    transition: padding-top 0.2s linear;
                    background: #5830C7;

                    justify-content: space-between;

                    .tag {
                        width: 100%;
                        padding: 9px 0px;
                        justify-content: center;
                        align-items: center;

                        /* 亲密排行 */
                        font-family: 'Inter';
                        font-style: normal;
                        font-weight: 700;
                        font-size: 16px;
                        line-height: 19px;
                        text-align: center;

                        background: #1F1146;
                        color: rgba(255, 255, 255, 0.3);
                    }

                    .left {
                        border-radius: 20px 0 0px 0px;
                    }

                    .right {
                        border-radius: 0 20px 0px 0px;
                    }

                    .active {
                        position: relative;
                        color: #fff;
                        background: #5830C7;
                        border-radius: 0px 0px 20px 20px;
                    }

                    .active::after {
                        left: 0;
                        background: radial-gradient(circle at 100% 0%, transparent 20px, #1F1146 0);
                    }

                    .active::before {
                        right: 0;
                        background: radial-gradient(circle at 0% 0%, transparent 20px, #1F1146 0);
                    }

                    .active::after,
                    .active::before {
                        content: '';
                        position: absolute;
                        bottom: 0;
                        width: 20px;
                        height: 20px;
                        /* 设置过渡效果 */
                        animation: moveAndFadeIn 0.5s ease;
                    }

                    @keyframes moveAndFadeIn {
                        0% {
                            height: 0;
                        }

                        100% {
                            height: 20px;
                        }
                    }
                }

                .table-content {
                    background: #1F1146;

                    justify-content: start;
                    align-items: center;

                    padding-top: 10px;

                    .my-ranking {
                        box-sizing: border-box;
                        align-items: center;
                        padding: 20px;
                        gap: 10px;
                        width: 100%;
                        border-bottom: 1px solid #7756EC;

                        .nickname {
                            flex-grow: 1;
                            /* 网名 */
                            font-family: 'Inter';
                            font-style: normal;
                            font-weight: 700;
                            font-size: 20px;
                            line-height: 24px;

                            color: #E2E2E2;
                        }

                        .score-grou {
                            box-sizing: border-box;
                            justify-content: center;
                            align-items: center;
                            padding: 1px 7px;
                            gap: 2px;

                            background: linear-gradient(180deg, #AF48FF 0%, #5C42FF 100%);
                            border: 1px solid #C958FF;
                            border-radius: 15px;

                            .score {
                                font-family: 'Inter';
                                font-style: normal;
                                font-weight: 800;
                                font-size: 16px;
                                line-height: 19px;

                                color: #FFFFFF;
                            }

                            .t1 {
                                // text-align: center;
                                padding: 0;
                                /* 分 */
                                font-family: 'Inter';
                                font-style: normal;
                                font-weight: 800;
                                font-size: 12px;
                                line-height: 15px;


                                color: #FFFFFF;
                            }
                        }

                        .ranking {
                            /* 位于第2名 */
                            font-family: 'Inter';
                            font-style: normal;
                            font-weight: 800;
                            font-size: 16px;
                            line-height: 19px;

                            color: #FFFFFF;
                        }
                    }

                    .table-null-text {
                        /* 您的亲密排行空空如也 🌟快去邀请朋友吧！ */
                        font-family: 'Inter';
                        font-style: normal;
                        font-weight: 700;
                        font-size: 32px;
                        line-height: 39px;
                        text-align: center;

                        color: #FFFFFF;
                    }

                    .t1 {
                        padding-top: 50px;
                    }

                    .table-null-button {
                        margin-top: 20px;
                        box-sizing: border-box;
                        display: flex;
                        flex-direction: row;
                        justify-content: center;
                        align-items: center;
                        padding: 9px 0px;

                        width: 244px;
                        height: 52px;

                        /* 淡紫 粉色渐变 */
                        background: linear-gradient(90deg, #5830C7 0%, #BE53FF 100%);
                        border: 1px solid rgba(255, 255, 255, 0.3);
                        box-shadow: 0px 0px 14px 4px rgba(255, 255, 255, 0.25);
                        border-radius: 30px;

                        font-family: 'Inter';
                        font-style: normal;
                        font-weight: 700;
                        font-size: 28px;
                        line-height: 34px;

                        color: #FFFFFF;
                    }

                    .table-item {
                        display: grid;
                        grid-template-columns: auto auto 1fr auto auto;
                        align-items: center;
                        width: 100%;
                        height: 54px;
                        padding: 0 15px;

                        .ranking {
                            padding-right: 15px;

                            font-family: 'Inter';
                            font-style: normal;
                            font-weight: 700;
                            font-size: 16px;
                            line-height: 19px;
                        }

                        .nickname {
                            justify-self: start;
                            padding-left: 5px;

                            font-family: 'Inter';
                            font-style: normal;
                            font-weight: 700;
                            font-size: 16px;
                            line-height: 19px;
                        }

                        .score {
                            font-family: 'Inter';
                            font-style: normal;
                            font-weight: 800;
                            font-size: 20px;
                            line-height: 24px;
                            text-align: center;

                            color: #A729E2;
                        }

                        .svg {
                            justify-self: end;

                            padding-left: 15px;
                        }

                        .no1 {
                            background: linear-gradient(251.44deg, #F5A022 19.28%, #FFCA10 80.72%);
                            background-clip: text;
                            -webkit-background-clip: text;
                            color: transparent;
                            text-shadow: 0px 0px 20px #FFAD55;
                        }

                        .no2 {
                            background: linear-gradient(180deg, #B3B3B3 0%, #F5F5F5 100%);
                            background-clip: text;
                            -webkit-background-clip: text;
                            color: transparent;
                            text-shadow: 0px 0px 20px #F5F5F5;
                        }

                        .no3 {
                            background: linear-gradient(180deg, #B37F43 0%, #D0995A 100%);
                            background-clip: text;
                            -webkit-background-clip: text;
                            color: transparent;
                            text-shadow: 0px 0px 20px #D0995A;
                        }
                    }
                }

                .test-info {
                    background: #1F1146;
                    gap: 20px;
                    padding: 0 20px;

                    .class {
                        gap: 10px;

                        .name1 {
                            font-family: 'Inter';
                            font-style: normal;
                            font-weight: 400;
                            font-size: 14px;
                            line-height: 17px;
                            /* identical to box height */

                            color: #FFFFFF;
                        }

                        .item1 {
                            position: relative;

                            padding: 11px 13px;
                            gap: 3px;

                            background: rgba(255, 255, 255, 0.1);
                            border-radius: 15px;

                            .question-title {
                                gap: 3px;

                                .svg {
                                    width: 10px;
                                    height: 10px;
                                }

                                .title {
                                    font-family: 'Inter';
                                    font-style: normal;
                                    font-weight: 500;
                                    font-size: 12px;
                                    line-height: 15px;

                                    color: #FFFFFF;

                                    opacity: 0.7;
                                }
                            }

                            .question-info {
                                justify-content: space-between;
                                align-items: center;
                                gap: 3px;
                                padding-left: 10px;

                                .option {
                                    font-family: 'Inter';
                                    font-style: normal;
                                    font-weight: 700;
                                    font-size: 16px;
                                    line-height: 19px;
                                    letter-spacing: -0.04em;

                                    color: #FFFFFF;
                                }

                                .input-value {
                                    font-family: 'Inter';
                                    font-style: normal;
                                    font-weight: 500;
                                    font-size: 12px;
                                    line-height: 15px;
                                    /* identical to box height */
                                    letter-spacing: -0.04em;

                                    color: rgba(255, 255, 255, 0.75);
                                }
                            }

                            .svg {
                                position: absolute;
                                right: -11px;
                                top: -11px;
                            }
                        }

                        .item1-1 {
                            position: relative;
                            padding: 8px 11px;
                            gap: 20px;

                            background: rgba(255, 255, 255, 0.07);
                            border-radius: 15px;

                            .user-info {
                                justify-content: center;
                                align-items: center;
                                gap: 2px;

                                .nickname {
                                    font-family: 'Inter';
                                    font-style: normal;
                                    font-weight: 600;
                                    font-size: 12px;
                                    line-height: 15px;
                                    text-align: center;

                                    color: #FFFFFF;
                                }
                            }

                            .topic {
                                position: relative;
                                gap: 4px;
                                justify-content: center;

                                .svg {
                                    position: absolute;
                                    left: -10px;
                                    top: 10px;
                                }

                                .title {
                                    font-family: 'Inter';
                                    font-style: normal;
                                    font-weight: 300;
                                    font-size: 12px;
                                    line-height: 15px;

                                    color: #FFFFFF;
                                }

                                .answer {
                                    font-family: 'Inter';
                                    font-style: normal;
                                    font-weight: 900;
                                    font-size: 16px;
                                    line-height: 19px;

                                    color: #FFFFFF;
                                }
                            }

                            .svg {
                                position: absolute;
                                right: -11px;
                                top: -11px;
                            }
                        }

                        .add {
                            justify-content: center;
                            gap: 4px;
                            position: relative;

                            box-sizing: border-box;
                            padding: 13px 30px;
                            border: 1px dashed #FFFFFF;
                            border-radius: 15px;

                            background: #2F1969;

                            .t1 {
                                font-family: 'Inter';
                                font-style: normal;
                                font-weight: 900;
                                font-size: 16px;
                                line-height: 19px;

                                color: #FFFFFF;
                            }

                            .t2 {
                                font-family: 'Inter';
                                font-style: normal;
                                font-weight: 300;
                                font-size: 12px;
                                line-height: 15px;

                                color: rgba(255, 255, 255, 0.3);
                            }

                            .svg {
                                position: absolute;
                                right: -11px;
                                top: -11px;
                            }
                        }

                        .items {
                            position: relative;
                            right: 20px;
                            width: 100vw;
                            background: rgba(255, 255, 255, 0.07);


                            .item2 {
                                display: grid;
                                grid-template-columns: 1fr auto;
                                gap: 10px;
                                align-items: center;
                                padding: 10px 20px;

                                .title {
                                    font-family: 'Inter';
                                    font-style: normal;
                                    font-weight: 400;
                                    font-size: 14px;
                                    line-height: 17px;
                                    color: #FFFFFF;
                                }

                                .option {
                                    font-family: 'Inter';
                                    font-style: normal;
                                    font-weight: 600;
                                    font-size: 14px;
                                    line-height: 17px;

                                    color: #A143FF;
                                }

                                .input-value {
                                    font-family: 'Inter';
                                    font-style: normal;
                                    font-weight: 500;
                                    font-size: 12px;
                                    line-height: 15px;

                                    color: rgba(255, 255, 255, 0.4);
                                }
                            }
                        }

                        .t1 {
                            /* 查看全部 */
                            font-family: 'Inter';
                            font-style: normal;
                            font-weight: 600;
                            font-size: 12px;
                            line-height: 15px;

                            color: #A729E2;

                        }
                    }
                }



                .table-record {
                    background: #1F1146;
                    gap: 10px;

                    .input-wrap {
                        align-items: center;
                        gap: 5px;
                        margin: 10px;
                        background: rgba(255, 255, 255, 0.1);
                        padding: 7px 18px;
                        border-radius: 30px;

                        .input {
                            width: 100%;
                            font-family: 'Inter';
                            font-style: normal;
                            font-weight: 700;
                            font-size: 16px;
                            line-height: 19px;

                            color: #FFFFFF;
                        }

                        .input-placeholder {
                            font-family: 'Inter';
                            font-style: normal;
                            font-weight: 400;
                            font-size: 16px;
                            line-height: 19px;

                            color: rgba(255, 255, 255, 0.3);
                        }
                    }

                    .test-paper-my {
                        margin: 0 12.5px;
                        padding: 11px 13px;
                        gap: 12px;

                        background: #5830C7;
                        border-radius: 15px;

                        .v1 {
                            padding: 0;
                            align-items: center;
                            justify-content: space-between;

                            .title {
                                font-family: 'Inter';
                                font-style: normal;
                                font-weight: 700;
                                font-size: 16px;
                                line-height: 19px;
                                letter-spacing: -0.04em;

                                color: #FFFFFF;
                            }

                            .count {
                                font-family: 'Inter';
                                font-style: normal;
                                font-weight: 500;
                                font-size: 12px;
                                line-height: 15px;
                                /* identical to box height */
                                letter-spacing: -0.04em;

                                color: rgba(255, 255, 255, 0.7);

                            }
                        }

                        .v2 {
                            padding: 0;
                            gap: 10px;
                            align-items: center;
                            justify-content: flex-end;

                            .extra {
                                flex-grow: 1;
                            }

                            .button {
                                justify-content: center;
                                align-items: center;
                                padding: 5px 15px;
                                gap: 10px;

                                background: rgba(255, 255, 255, 0.3);
                                backdrop-filter: blur(25px);
                                border-radius: 30px;

                                font-family: 'Inter';
                                font-style: normal;
                                font-weight: 700;
                                font-size: 12px;
                                line-height: 15px;
                                /* identical to box height */

                                color: #FFFFFF;

                            }
                        }
                    }
                }
            }
        }
    }

    .test-paper-friend {
        margin: 0 12.5px;
        padding: 11px 13px;
        gap: 12px;

        background: #5830C7;
        border-radius: 15px;

        .v1 {
            padding: 0;
            align-items: center;
            justify-content: space-between;

            .title {
                flex-grow: 1;
                font-family: 'Inter';
                font-style: normal;
                font-weight: 700;
                font-size: 16px;
                line-height: 19px;
                letter-spacing: -0.04em;

                color: #FFFFFF;
            }

            .error,
            .correct {
                width: 15px;
                height: 15px;
                border-radius: 50%;
            }

            .correct {
                background: linear-gradient(180deg, #A4FF48 0%, #CFF33C 100%);
                box-shadow: 4px 0px 4px rgba(112, 184, 39, 0.25);
            }

            .error {
                background: linear-gradient(180deg, #FF4848 0%, #F3733C 100%);
                box-shadow: 4px 0px 4px rgba(231, 40, 28, 0.25);
            }

            .count {
                font-family: 'Inter';
                font-style: normal;
                font-weight: 600;
                font-size: 16px;
                line-height: 19px;

                color: #FFFFFF;
            }
        }

        .progress-warp {
            width: 100%;
            height: 9px;

            background: rgba(255, 255, 255, 0.1);
            border-radius: 15px;

            .progress {

                height: 9px;
                background: #FFFFFF;
                border-radius: 15px;

            }
        }

        .v2 {
            padding: 0;
            gap: 10px;
            align-items: center;
            justify-content: flex-end;

            .button {
                justify-content: center;
                align-items: center;
                padding: 5px 15px;
                gap: 10px;

                background: rgba(255, 255, 255, 0.3);
                backdrop-filter: blur(25px);
                border-radius: 30px;

                font-family: 'Inter';
                font-style: normal;
                font-weight: 700;
                font-size: 12px;
                line-height: 15px;
                /* identical to box height */

                color: #FFFFFF;

            }
        }
    }

    .footer {
        width: 100vw;
        position: absolute;
        bottom: 0;
        /* 当滚动到距离顶部 0 像素时粘住 */
        z-index: 1;
        /* 确保粘性元素在其他元素上方 */
        /* 出题按钮 */

        box-sizing: border-box;
        flex-direction: column;
        align-items: center;
        padding: 30px 31px;

        background: #1F1146;
        border-top: 1px solid rgba(255, 255, 255, 0.3);
        border-radius: 15px 15px 0px 0px;

        .b1 {
            flex-direction: column;
            align-items: center;
            padding: 10px;

            width: 299px;

            box-sizing: border-box;

            background: linear-gradient(90deg, #8163FB 0%, #AE2EDA 23.19%, #A627E1 89.69%);
            border-radius: 30px;

            font-family: 'Inter';
            font-style: normal;
            font-weight: 700;
            font-size: 24px;
            line-height: 29px;

            color: #FFFFFF;
        }
    }
}

.dialog {
    padding: 15px;
    gap: 15px;
    align-items: center;
    justify-content: center;
    background-color: #1f1146 !important;

    .svg {
        position: absolute;
        top: 0;
        right: 0;
        transform: translate(30%, -30%);
    }

    .v1 {
        margin-bottom: 20px;
    }

    .text {
        font-family: 'Inter';
        font-style: normal;
        font-weight: 700;
        font-size: 16px;
        line-height: 19px;

        color: #FFFFFF;
    }

    .but {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        padding: 10px;
        gap: 10px;

        width: 100%;

        background: rgba(255, 255, 255, 0.1);
        border-radius: 15px;
    }
}
</style>
