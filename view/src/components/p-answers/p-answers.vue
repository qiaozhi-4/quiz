<template>
    <view class="list flex-column">
        <view class="item flex-column" v-for="(paper, index) in papers" :key="index"
            :style="paper?.answerId ? '' : 'background-color: #352858;'">
            <view class="v1">
                <text class="title">{{ paper?.creatorUserNickname }}的{{ paper?.order }}号测试</text>
                <text class="correct"></text>
                <text class="num">{{ paper?.answerId ? paper?.score / 10 : 0 }}</text>
                <text class="error"></text>
                <text class="num">{{ paper?.answerId ? 10 - paper?.score / 10 : 0
                    }}</text>
            </view>
            <view class="progress-warp">
                <view :style="`width: ${paper?.score}%;`" class="progress">
                </view>
            </view>
            <view class="v2">
                <template v-if="paper?.answerId">
                    <button class="button" @click="onParticulars(paper?.paperId)">查看详情</button>
                    <button v-show="paper?.score != 100" class="button flex-row" @click="goTest(paper?.paperId)">
                        <q-svg icon="复活宝石" size="16" />使用复活宝石再测一次！
                    </button>
                </template>
                <button v-else class="button" @click="goTest(paper?.paperId)">开始测试</button>
            </view>
        </view>
    </view>
</template>

<script lang="ts" setup>
import { computed } from 'vue';
import { objectToPathParams } from '@/utils/service';
const props = defineProps({
    papers: { type: Object, default: [] },
});

const papers = computed<Quiz.PaperAndAnswerDTO[]>(() => props.papers as Quiz.PaperAndAnswerDTO[]);


/** 试卷详情 */
function onParticulars(paperId: number) {
    uni.navigateTo({
        url: `/pages/paper-generalize/paper-generalize` + objectToPathParams({ paperId: paperId, isFriendPaper: true })
    });
}
/** 从朋友题库开始答题 */
function goTest(paperId: number) {
    uni.navigateTo({
        url: `/pages/paper-start/paper-start` + objectToPathParams({ paperId: paperId })
    });
}
</script>

<style lang="scss" scoped>
.list {
    .item {

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
}
</style>