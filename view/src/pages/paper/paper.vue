<template>
    <!-- 提示消息 -->
    <q-alert ref="refAlert"></q-alert>
    <view class="page">
        <q-nav-bar title="Quiz" fixed />
        <view class="main flex-column">
            <view v-if="isAnswer" style="position: relative; margin-left: auto; margin-right: 25px;"
                @click="useHintGem">
                <template>
                    <q-svg v-if="hintGem.number < 1" class="corner-mark flex-center" icon="广告" size="11" />
                    <view v-else class="corner-mark flex-center">{{ hintGem.number > 99 ? '99+' : hintGem.number }}
                    </view>
                    <q-svg icon="提示宝石" size="45" />
                </template v-else>
                <template>

                </template>
            </view>
            <view v-else @click="switchQuestion">换一题</view>
            <q-avatar class="avatar" :src="paper.creatorUserAvatarUrl" size="62" borderWidth="3"></q-avatar>
            <view class="statistics-text">
                {{ `Question ${currentQuestionIndex + 1}/${paper.questions?.length}` }}
            </view>
            <view class="progress-wrap">
                <view class="progress"
                    :style="{ width: `${((currentQuestionIndex + 1) / paper.questions?.length * 100)}%` }">
                </view>
            </view>
            <view class="question-wrap">
                <swiper class="swiper" :current="currentQuestionIndex" @change="onChange">
                    <swiper-item class="swiper-item flex-column" v-for="(question, index) in paper.questions"
                        :key="index">
                        <view class="title">{{ question?.title }}</view>
                        <view class="options flex-column">
                            <button class="option-button"
                                :class="{ hint: i == answerIndex, activity: i == infos[index].select }"
                                v-for="(option, i) in question.options.split('@@')" :key="i"
                                @click="onButtonClick(i)">{{
                                    option }}</button>
                        </view>
                    </swiper-item>
                </swiper>
                <!-- 指示点 -->
                <q-indicator-position :current="currentQuestionIndex"
                    :amount="paper.questions?.length"></q-indicator-position>
            </view>
        </view>
        <view class="footer flex-column">
            <view class="input-wrap" :style="style" v-if="paper.questions">
                <q-svg class="svg" :icon="`答题测试-底部-泡泡${currentExtraDescribe ? '-激活' : ''}`" size="19" />
                <input class="input" placeholder-class="input-placeholder" placeholder="说说具体的想法"
                    :adjust-position="false" @input="onInput" @blur="onBlur"
                    @keyboardheightchange="keyboardheightchange" :value="currentExtraDescribe" />
            </view>
            <view class="footer-svg">
                <q-svg class="svg" :icon="`答题测试-底部-总览${activityPopup == '总览' ? '-激活' : ''}`" size="19"
                    @click="onChangeActivityPopup('总览')" />
                <q-svg class="svg" :icon="`答题测试-底部-电脑${activityPopup == '电脑' ? '-激活' : ''}`" size="19"
                    @click="onChangeActivityPopup('电脑')" />
                <!-- <button v-show="isFinish" class="submit-but" @click="submit"> 提交 </button> -->
            </view>
        </view>
    </view>

    <!-- 弹窗 -->
    <q-dialog ref="popupRef" extraClass="popup-wrap" location="bottom" :safe-area="false" maskHideDialog
        @maskCallback="activityPopup = ''">
        <view class="popup">
            <view class="title">{{ paper.creatorUserNickname }} 的 {{ paper?.order }}号测试</view>
            <view class="questions flex-column" v-show="activityPopup == '总览'">
                <view class="question flex-column" v-for="(question, index) in paper.questions" :key="index"
                    @click="onClickPopupQuestion(index)">
                    <view class="question-title">
                        <q-svg icon="左双引号" size="10" />
                        <view class="title text-overflow">{{ question?.title }}</view>
                    </view>
                    <view class="question-info">
                        <view class="option text-overflow">{{
                            question.options.split('@@')[infos[index].select] }}
                        </view>
                        <view class="input-value text-overflow">{{ infos[index].extraDescribe }}</view>
                    </view>
                </view>
            </view>
            <view class="describe" v-show="activityPopup == '电脑'">你好！🌟 开始你的自我探索之旅吧！凭回忆和直觉的选择回答就好。</view>
            <view style="min-height: 63px;"></view>
        </view>
    </q-dialog>

</template>

<script lang="ts" setup>
import { computed, onMounted, ref } from 'vue';
import { onLoad } from '@dcloudio/uni-app';
import { createPaper, getPaper, getPaperAndAnswerDTO, paperSwitchQuestion, updatePaper } from '@/utils/api/paper';
import { saveAnswer } from '@/utils/api/answer';
import { useStore } from "@/stores/store";
import { getUser } from '@/utils/api/user';
import { objectToPathParams } from '@/utils/service';
import { gainProp, useProp } from '@/utils/api/prop';
import { objCope } from '@/utils/utils';

onLoad((option: Option) => {
    isAnswer.value = option?.isAnswer == 'true';
    if (isAnswer.value && option?.paperId) {
        getPaperAndAnswerDTO(option.paperId, store.user.userId).then(res => {
            paper.value = res.data;
            /** 清除上一次的记录 */
            res.data.questions.forEach(q => {
                // @ts-expect-error
                q.aqSelectIndex = null;
                // @ts-expect-error
                q.aqExtraDescribe = null;
            });
        });
    } else if (option?.paperId) {
        getPaper(option.paperId).then(res => paper.value = res.data);
    } else {
        createPaper(10, store.user.userId).then((res => paper.value = res.data));
    }
});
const store = useStore();
/** 本页路径参数 */
type Option = AnyObject & {
    /** 出题人id(答题才有) */
    isAnswer?: string;
    /** 试卷id(答题才有,继续出题也可能有) */
    paperId?: number;
} | undefined;
const refAlert = ref();
/** 试卷信息 */
const paper = ref<Quiz.PaperAndAnswerDTO>({} as Quiz.PaperAndAnswerDTO);
/** 当前题目下标 */
const currentQuestionIndex = ref<number>(0);
/** 正确答案下表 */
const answerIndex = ref<number>(-1);
/** 是不是答题 */
const isAnswer = ref<boolean>(false);
/** 是否全部选择完 */
const isFinish = computed<boolean>(() => infos.value?.every(e => e.select != null));
/** 提示宝石 */
const hintGem = computed<Quiz.PropDTO>(() => store.getPropById(3) || {} as Quiz.PropDTO);
const infos = computed<{ select: number, extraDescribe: string; }[]>(() => {
    return paper.value.questions?.map(e => {
        return {
            select: isAnswer.value ? e.aqSelectIndex : e.pqSelectIndex,
            extraDescribe: isAnswer.value ? e.aqExtraDescribe : e.pqExtraDescribe
        };
    });
});
const currentSelect = computed({
    get() {
        return isAnswer.value ? paper.value?.questions[currentQuestionIndex.value].aqSelectIndex : paper.value?.questions[currentQuestionIndex.value].pqSelectIndex;
    },
    set(newValue) {
        if (isAnswer.value) {
            paper.value.questions[currentQuestionIndex.value].aqSelectIndex = newValue;
        } else {
            paper.value.questions[currentQuestionIndex.value].pqSelectIndex = newValue;
        }
    }
});
const currentExtraDescribe = computed({
    get() {
        return isAnswer.value ? paper.value?.questions[currentQuestionIndex.value].aqExtraDescribe : paper.value?.questions[currentQuestionIndex.value].pqExtraDescribe;
    },
    set(newValue) {
        if (isAnswer.value) {
            paper.value.questions[currentQuestionIndex.value].aqExtraDescribe = newValue;
        } else {
            paper.value.questions[currentQuestionIndex.value].pqExtraDescribe = newValue;
        }
    }
});

/** 使用提示宝石 */
function useHintGem() {
    if (hintGem.value.number < 1) {
        refAlert.value?.show({ msg: '假装你看完了视频' });
        gainProp(1, hintGem.value.propId, store.user.userId).then(res => {
            store.addPropNumberById(hintGem.value.propId, 1);
            setTimeout(() => {
                useProp(1, hintGem.value.propId, store.user.userId).then(res => {
                    store.usePropNumberById(hintGem.value.propId);
                    refAlert.value?.show({ msg: '已使用提示宝石', showTime: 1000 });
                    answerIndex.value = paper.value.questions[currentQuestionIndex.value].pqSelectIndex;
                    setTimeout(() => { answerIndex.value = -1; }, 2000);
                }).catch(err => refAlert.value?.show({ msg: err.message }));
            }, 2000);
        });
    } else {
        useProp(1, hintGem.value.propId, store.user.userId).then(res => {
            store.usePropNumberById(hintGem.value.propId);
            refAlert.value?.show({ msg: '已使用提示宝石', showTime: 1000 });
            answerIndex.value = paper.value.questions[currentQuestionIndex.value].pqSelectIndex;
            setTimeout(() => { answerIndex.value = -1; }, 2000);
        }).catch(err => refAlert.value?.show({ msg: err.message }));
    }
}
/** 换一题 */
function switchQuestion() {
    paperSwitchQuestion(paper.value.questions[currentQuestionIndex.value].pqId, store.user.userId)
        .then(res => {
            objCope(res.data, paper.value.questions[currentQuestionIndex.value]);
        });
}

/** 键盘输入触发 */
function onInput(e: any) {
    currentExtraDescribe.value = e.detail.value;
}
/** 键盘失去焦点触发 */
function onBlur(e: any) {
    currentExtraDescribe.value = e.detail.value;
}
/** 点击选项 */
function onButtonClick(index: number) {
    /** 同步到试卷的信息 */
    currentSelect.value = index;
    if (!infos.value[infos.value.length - 1].select) {
        currentQuestionIndex.value++;
        return;
    }
    infos.value.some((e, i) => {
        if (e.select == null) {
            currentQuestionIndex.value = i;
            return true;
        }
        return false;
    });
    submit();
}
/** 提交 */
function submit() {
    if (infos.value.find(e => e.select == null)) return;
    if (isAnswer.value) {
        paper.value.responderUserId = store.user.userId;
        saveAnswer(paper.value).then((res) => {
            if (paper.value.answerId) {
                store.usePropNumberById(2);
            }
            paper.value = res.data;
            store.addPropNumberById(1, res.data.score - paper.value.score);
            uni.reLaunch({
                url: `/pages/paper-answer-finish/paper-answer-finish` + objectToPathParams({
                    paperId: paper.value.paperId,
                    userId: paper.value.creatorUserId,
                    avatarUrl: paper.value.creatorUserAvatarUrl,
                    nickname: paper.value.creatorUserNickname,
                    score: paper.value.score
                })
            });
        });
    } else {
        updatePaper(paper.value).then(res => {
            uni.reLaunch({
                url: `/pages/paper-set-finish/paper-set-finish` + objectToPathParams({ paperId: paper.value.paperId, userId: paper.value.creatorUserId })
            });
        });
    }
}




/** 输入框高度 */
const style = ref();
/** 当前活动弹框 */
const activityPopup = ref<string>("");
/** 弹出层ref */
const popupRef = ref();
/** 弹出层高度 */
const popupStyle = ref({ height: `${uni.getMenuButtonBoundingClientRect().bottom + 10}px` });
/** 切换题目 */
function onChange(e: any) {
    currentQuestionIndex.value = e.detail.current;
}
/** 键盘高度发生变化 */
function keyboardheightchange(e: any) {
    style.value = {
        top: `-${e.detail.height - 83}px`,
    };
}
/** 点击底部svg切换活动弹窗 */
function onChangeActivityPopup(v: string) {
    if (activityPopup.value && activityPopup.value == v) {
        activityPopup.value = '';
        popupRef.value.hide();
    } else {
        activityPopup.value = v;
        popupRef.value.show();
    }
}
/** 点击弹出框题目触发,跳转到点击题目并且关闭弹出框 */
function onClickPopupQuestion(index: number) {
    currentQuestionIndex.value = index;
    activityPopup.value = '';
    popupRef.value.hide();
}
</script>

<style lang="scss">
.page {
    background: linear-gradient(180deg, #BC5CF8 0%, #6F3ED3 29.5%, #2F1969 100%);

    .main {
        gap: 6px;
        align-items: center;
        // padding: 0 15px;
        height: 100%;

        .corner-mark {
            padding: 0, 6px;
            position: absolute;
            top: 0;
            right: 0;
            // transform: translate(30%, -30%);
            height: 20px;
            min-width: 20px;

            // left: 34.88px;
            // top: -7px;

            border-radius: 50%;

            font-family: 'Manrope';
            font-style: normal;
            font-weight: 800;
            font-size: 16px;
            line-height: 22px;

            background-color: #FFFFFF;

            color: #A143FF;

        }

        .avatar {
            margin-top: 40px;
        }


        .statistics-text {
            font-family: 'Inter';
            font-style: normal;
            font-weight: 500;
            font-size: 12px;
            line-height: 15px;

            color: #FFFFFF;
        }

        .progress-wrap {
            box-sizing: border-box;
            position: relative;
            border-radius: 15px;
            background: linear-gradient(90deg, #BE53FF 0%, #5830C7 100%);
            height: 17px;
            width: 92%;
            box-sizing: border-box;
            border: 3px solid #FFFFFF;

            .progress {
                position: absolute;
                background: #FFFFFF;
                border-radius: 15px;
                height: 17px;
                top: -3px;
                left: 0;

                transition: width 0.5s linear;
            }
        }

        .question-wrap {
            position: relative;
            flex-grow: 1;
            width: 100%;

            .swiper {
                width: 100%;
                height: 100%;

                .swiper-item {
                    box-sizing: border-box;
                    padding: 0 15px;
                    gap: 10px;

                    .title {
                        font-family: 'Manrope';
                        font-style: normal;
                        font-weight: 700;
                        font-size: 32px;
                        line-height: 44px;
                        letter-spacing: -0.04em;

                        color: #FFFFFF;
                    }

                    .options {
                        padding: 20px 0;
                        overflow: auto;
                        gap: 10px;

                        // 提示
                        .hint {
                            box-sizing: border-box;
                            background: linear-gradient(90deg, #BE53FF 0%, #7756EC 100%);
                            border: 3px solid #FFFFFF;
                            box-shadow: 0px 0px 15px rgba(255, 255, 255, 0.25);
                            border-radius: 30px;
                        }

                        .option-button {
                            background: rgba(255, 255, 255, 0.3);
                            border-radius: 30px !important;

                            min-height: 43px;

                            font-family: 'Manrope';
                            font-style: normal;
                            font-weight: 700;
                            font-size: 20px;
                            line-height: 27px;
                            letter-spacing: -0.04em;

                            color: #FFFFFF;
                        }

                        .activity {
                            background: linear-gradient(90deg, #BE53FF 0%, #7756EC 100%);
                        }

                        .option-button:hover {
                            /* 粉紫渐变 选项 */
                            background: linear-gradient(90deg, #BE53FF 0%, #7756EC 100%) !important;
                        }
                    }
                }
            }
        }
    }

    .footer {
        // width: 100vw;
        min-height: 137px;
        background: #2F1969;

        .input-wrap {
            align-items: center;
            gap: 5px;
            position: relative;
            top: 0;
            height: 54px;
            background: #2F1969;
            padding: 12px 20px;
            border-bottom: 1px solid rgba(255, 255, 255, 0.15);

            transition: top 0.3s linear;

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

        .footer-svg {
            flex-grow: 1;
            position: relative;
            z-index: 103;
            box-sizing: border-box;
            display: flex;
            flex-direction: row;
            align-items: flex-start;
            padding: 17px 20px;
            gap: 41px;

            background: #2F1969;

            .svg {
                width: 19px;
                height: 19px;
            }

            .submit-but {
                margin-left: auto;
                padding: 5px 15px;
                gap: 10px;

                min-width: 78.15px;
                height: 25.48px;

                background: rgba(255, 255, 255, 0.3);
                backdrop-filter: blur(25px);
                border-radius: 30px;

                font-family: 'Inter';
                font-style: normal;
                font-weight: 700;
                font-size: 12px;
                line-height: 15px;
                color: #FFFFFF;

                transform: rotate(0.58deg);
            }
        }
    }
}

.popup-wrap {
    height: 86vh !important;
    border-radius: 30px 30px 0 0;
}

.popup {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px 12px;
    padding-bottom: 0;
    gap: 20px;

    width: 100vw;
    height: 100%;


    background-color: #2F1969;
    border-radius: 15px 15px 0px 0px;

    .title {
        font-family: 'Manrope';
        font-style: normal;
        font-weight: 700;
        font-size: 20px;
        line-height: 27px;
        letter-spacing: -0.04em;

        color: #FFFFFF;
    }

    .questions {
        flex-grow: 1;
        gap: 8px;
        width: 100%;

        overflow: auto;

        .question {
            padding: 11px 13px;
            min-height: 59px;
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
        }
    }

    .describe {
        flex-grow: 1;
        padding: 13px;
        margin-bottom: 20px;

        background: rgba(255, 255, 255, 0.1);
        border-radius: 15px;

        font-family: 'Inter';
        font-style: normal;
        font-weight: 500;
        font-size: 16px;
        line-height: 19px;
        letter-spacing: -0.04em;

        color: #FFFFFF;
    }
}
</style>