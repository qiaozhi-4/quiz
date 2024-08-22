<template>
    <!-- 弹出框 -->
    <q-dialog ref="refDialog">
        <view class="dialog flex-column">
            <text class="dialog-t1">复活宝石让尊贵的您获得重新再次答题的机会</text>
            <text class="dialog-t2">复活宝石</text>
            <q-image height="170" src="/static/img/复活宝石.png" width="170"></q-image>
            <text class="dialog-t3">失手选太快了吗？评分不满意？再答一次试试看！</text>
            <button class="dialog-b1" @click="onResurrection">
                <view class="dialog-b1-svg">
                    <q-svg icon="复活宝石" size="34" />
                    <q-svg v-if="gemCount < 1" class="dialog-b1-svg-corner" icon="广告" size="11" />
                    <text v-else class="dialog-b1-svg-corner dialog-b1-svg-t1">{{ gemCount }}</text>

                </view>
                <text class="dialog-b1-t1">使用宝石复活！</text>
            </button>
            <button class="dialog-b2" @click="backtrack">返回</button>
        </view>
    </q-dialog>
    <!-- 提示消息 -->
    <q-alert ref="refAlert"></q-alert>
    <view class="page">
        <q-nav-bar fixed></q-nav-bar>
        <view class="main">
            <view class="avatar-group">
                <q-avatar :src="store.user.avatarUrl" size="97" borderWidth="4"></q-avatar>
                <q-avatar class="friend-avatar" v-if="isAnswer" :src="paper.creatorUserAvatarUrl" size="97"
                    borderWidth="4"></q-avatar>
            </view>
            <view class="nickname-group" v-if="isAnswer">
                <text class="polite">您的朋友</text>
                <text class="nickname">{{ paper.creatorUserNickname }}</text>
                <text class="polite">邀请您：</text>
            </view>
            <view class="nickname-group" v-else>
                <text class="nickname">{{ store.user?.nickname }}</text>
                <text class="polite">您好：</text>
            </view>
            <template v-if="isAnswer">
                <view class="explain-text">点击测试！🌟 </view>
                <view class="explain-text">揭秘我们之间关系的亲密程度～</view>
            </template>
            <template v-else>
                <view class="explain-text">点击出题！🌟</view>
                <view class="explain-text">看看他们对您的了解有多深！</view>
            </template>
            <view class="date-statistics-text">
                {{ `${now} Question 10/10` }}
            </view>
            <button class="start-button" @click="goPaper">
                <text v-if="isAnswer" class="button-text">开始测试</text>
                <text v-else class="button-text">开始出题</text>
            </button>
        </view>
    </view>
    <q-loading ref="refLoading" />
</template>


<script lang="ts" setup>
import { ref, computed } from 'vue';
import { formatDate } from '@/utils/utils';
import { onLoad } from '@dcloudio/uni-app';
import { getUser, verifyPaper } from '@/utils/api/user';
import { gainProp } from '@/utils/api/prop';
import { useStore } from "@/stores/store";
import { objectToPathParams } from '@/utils/service';
import { getPaper } from '@/utils/api/paper';
/** 加载框ref */
const refLoading = ref();
/** 对话框ref */
const refDialog = ref();
/** 提示消息ref */
const refAlert = ref();
const store = useStore();
/** 本页路径参数 */
type Option = AnyObject & {
    /** 试卷id */
    paperId?: number;
} | undefined;
onLoad((option: Option) => {
    /** 等待用户登录完成 */
    let tempId = setInterval(() => {
        if (store.user.userId) {
            clearInterval(tempId);
            /** 如果是答题 */
            if (option?.paperId != null) {
                getPaper(option.paperId).then(res => {
                    paper.value = res.data;
                    /** 如果全部都有下标就是答题 */
                    isAnswer.value = res.data.questions.every(e => e.pqSelectIndex != null);
                    refLoading.value.hide();
                });
                verifyPaper(option?.paperId, store.user.userId).then(res => {
                    if (res.data.isMyPaper) {
                        refAlert.value.show({ msg: '不能回答自己的出题,2秒后返回主页' });
                        setTimeout(() => uni.redirectTo({ url: `/pages/home/home` }), 2000);
                    } else if (res.data.isRepeatAnswer) {
                        refDialog.value.show();
                    }
                });
            } else { refLoading.value.hide(); }
        }
    }, 1000);
});
/** 获取复活宝石数量 */
const gemCount = computed<number>(() => store.getPropById(2)?.number || 0);
/** 试卷id */
const paper = ref<Quiz.PaperAndAnswerDTO>({} as Quiz.PaperAndAnswerDTO);
/** 是不是答题 */
const isAnswer = ref<boolean>(false);
/** 当前时间 */
const now = formatDate(new Date, 'YYYY/MM/DD');

/** 使用复活宝石 */
const onResurrection = () => {
    if (gemCount.value < 1) {
        gainProp(1, 2, store.user.userId).then(res => {
            store.addPropNumberById(2, 1);
            refAlert.value.show({ msg: '假装你看完了视频,然后获取了宝石,并开始答题' });
            setTimeout(() => {
                goPaper();
            }, 2000);
        });
    } else {
        goPaper();
    }
};
/** 返回 */
function backtrack() {
    uni.navigateBack({
        delta: 1,
        fail: () => {
            uni.reLaunch({
                url: '/pages/home/home'
            });
        }
    });
}
/** 跳转试卷详情 */
function goPaper() {
    let path: { [key: string]: any; } = { isAnswer: isAnswer.value };
    if (paper.value.paperId) { path.paperId = paper.value.paperId; }
    uni.reLaunch({
        url: `/pages/paper/paper` + objectToPathParams(path)
    });
}
</script>

<style lang="scss" scoped>
.page {
    /* 题目封面页渐变 */
    background: linear-gradient(180deg, #BC5CF8 0%, #5830C7 29.5%, #1F1146 100%);

    .main {
        padding: 0 48px;
        padding-top: 125px;
        display: grid;
        gap: 6px;

        .avatar-group {
            position: relative;

            .friend-avatar {
                position: absolute;
                left: 61px;
            }
        }

        .nickname-group {
            gap: 6px;

            .nickname {
                font-family: 'Inter';
                font-style: normal;
                font-weight: 700;
                font-size: 20px;
                line-height: 24px;

                color: #FFFFFF;
            }

            .polite {
                /* 您好： */
                font-family: 'Inter';
                font-style: normal;
                font-weight: 700;
                font-size: 20px;
                line-height: 24px;
                letter-spacing: -0.04em;

                color: rgba(255, 255, 255, 0.5);
            }
        }

        .explain-text {
            /* 点击答题！🌟 开启您的自我探索之旅吧！ */
            font-family: 'Inter';
            font-style: normal;
            font-weight: 800;
            font-size: 36px;
            line-height: 44px;
            letter-spacing: -0.04em;

            color: #FFFFFF;

        }

        .date-statistics-text {
            /* 2024/04/20 Question 10/10 */
            font-family: 'Manrope';
            font-style: normal;
            font-weight: 600;
            font-size: 16px;
            line-height: 22px;
            /* identical to box height */
            letter-spacing: -0.04em;

            color: rgba(255, 255, 255, 0.71);
        }

        .start-button {
            height: 54px;
            margin-top: 20px;

            background: #FFFFFF;
            border-radius: 30px;

            .button-text {
                /* 开始测试 */
                font-family: 'Inter';
                font-style: normal;
                font-weight: 800;
                font-size: 32px;
                line-height: 39px;

                background: linear-gradient(90deg, #BE53FF 0%, #796DFF 100%);
                -webkit-background-clip: text;
                -webkit-text-fill-color: transparent;
                background-clip: text;
                // text-fill-color: transparent;
            }
        }
    }
}

.dialog {
    padding: 28px;
    gap: 12px;
    align-items: center;

    .dialog-t1 {
        font-family: 'Inter';
        font-style: normal;
        font-weight: 600;
        font-size: 16px;
        line-height: 19px;
        text-align: center;

        color: rgba(255, 255, 255, 0.75);
    }

    .dialog-t2 {
        font-family: 'Inter';
        font-style: normal;
        font-weight: 700;
        font-size: 40px;
        line-height: 48px;
        text-align: center;

        color: #FFFFFF;
    }

    .dialog-t3 {
        font-family: 'Inter';
        font-style: normal;
        font-weight: 600;
        font-size: 16px;
        line-height: 19px;

        color: rgba(255, 255, 255, 0.75);
    }

    .dialog-b1 {
        display: flex;
        justify-content: center;
        align-items: center;
        padding: 8px 10px;
        gap: 10px;

        width: 100%;
        background: linear-gradient(90deg, #BE53FF 0%, #7756EC 100%);
        border-radius: 15px;

        .dialog-b1-svg {
            position: relative;

            .dialog-b1-svg-corner {
                position: absolute;
                top: 0;
                right: 0;
            }

            .dialog-b1-svg-t1 {
                display: flex;
                justify-content: center;
                align-items: center;
                padding: 2px 4px;

                background: #FFFFFF;
                border-radius: 15px;
                font-family: 'Inter';
                font-style: normal;
                font-weight: 600;
                font-size: 6px;
                line-height: 7px;

                color: #A143FF;
            }
        }

        .dialog-b1-t1 {
            font-family: 'Inter';
            font-style: normal;
            font-weight: 700;
            font-size: 20px;
            line-height: 24px;

            color: #FFFFFF;
        }
    }

    .dialog-b2 {
        display: flex;
        justify-content: center;
        align-items: center;
        padding: 8px 10px;
        gap: 10px;

        background: rgba(255, 255, 255, 0.1);
        border-radius: 15px;

        width: 100%;

        font-family: 'Inter';
        font-style: normal;
        font-weight: 700;
        font-size: 20px;
        line-height: 24px;

        color: #FFFFFF;
    }
}
</style>