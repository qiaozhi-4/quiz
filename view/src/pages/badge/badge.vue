<template>
    <view class="page">
        <q-nav-bar class="head-sticky" needBack title="我的徽章" fixed />
        <view class="main flex-column">
            <template v-for="(item, index) in badgeData" :key="index">
                <swiper class="swiper" v-show="index == activateBadge" :current="currentIndex" @change="onChange"
                    :circular="false">
                    <template v-if="item?.badges.length == 0">
                        <swiper-item class="swiper-item flex-column">
                            <text class="unknown">?</text>
                            <q-image src="/static/img/加0分.png" width="100vw" height="100"></q-image>
                            <text class="text">您目前还没有解锁过该徽章呢！</text>
                        </swiper-item>
                    </template>
                    <template v-else>
                        <swiper-item class="swiper-item flex-column" v-for="(badge, index) in item?.badges"
                            :key="index">
                            <q-image :src="'/static/img/' + item?.badgeName + '.png'" width="180"
                                height="180"></q-image>
                            <q-image :src="`/static/img/加${badge.score}分.png`" width="100vw" height="100"></q-image>
                            <text class="text">{{ badge.createdAt }}</text>
                            <text class="text">您获得了{{ badge.creatorUserNickname }}的 “{{ item?.name }}”徽章。</text>
                        </swiper-item>
                    </template>
                </swiper>
                <view style="position: relative;">
                    <q-indicator-position :current="currentIndex" :amount="item?.badges.length" />
                </view>
            </template>
            <text class="t1">更多奖章🎖️</text>
            <view class="list">
                <template v-for="(item, index) in badgeData" :key="index">
                    <view class="item flex-column" v-if="item?.badges.length > 0" @click="onBadge(index)">
                        <q-image :src="'/static/img/' + item?.badgeName + '.png'" width="90" height="90"></q-image>
                        <text class="text">{{ item?.badges.length }}枚</text>
                        <text class="text">{{ item?.badges[0].creatorUserNickname }}徽章</text>
                    </view>
                    <view class="item flex-column" v-else @click="onBadge(index)">
                        <text class="unknown">?</text>
                        <text class="text">0枚</text>
                        <text class="text">待解锁未知徽章</text>
                    </view>
                </template>
            </view>
        </view>
    </view>
</template>

<script lang="ts" setup>
import { ref } from 'vue';
import { onLoad } from '@dcloudio/uni-app';
import { getBadgeList } from '@/utils/api/answers';
import { ScoreInfo, scoreInfo } from '@/utils/constant';
import { useStore } from "@/stores/store";

const store = useStore();
/** 徽章数据 */
const badgeData = ref<ScoreInfo[]>([]);
/** 当前点击的徽章 */
const activateBadge = ref<number>(0);
/** 当前徽章下标 */
const currentIndex = ref<number>(0);

/** 切换轮播图触发 */
function onChange(e: { detail: { current: number; }; }) {
    currentIndex.value = e.detail.current;
}


/** 点击徽章 */
function onBadge(index: number) {
    activateBadge.value = index;
    currentIndex.value = 0;
}
onLoad(() => {
    getBadgeList(store.user.userId).then(res => {
        /** 防止修改全局变量 */
        badgeData.value = scoreInfo();
        /** 把徽章信息添加的对应数组 */
        res.data.forEach((e: Quiz.AnswersDTO) => {
            badgeData.value[e.score / 10].badges.push(e);
        });
        /** 过滤没用获取到的徽章 */
        badgeData.value = badgeData.value.filter(e => e.badges.length > 0);
    });
});
</script>

<style lang="scss" scoped>
.page {
    background: linear-gradient(180deg, #2F1969 48.5%, #5830C7 78%, #A729E2 100%);

    .main {

        .swiper {
            margin-top: 30px;
            height: auto;
            height: 43vh;

            .swiper-item {
                align-items: center;

                .text {
                    font-family: 'Inter';
                    font-style: normal;
                    font-weight: 700;
                    font-size: 11px;
                    line-height: 13px;
                    text-align: center;

                    color: #FFFFFF;
                }

                .unknown {
                    display: flex;
                    align-items: center;
                    justify-content: center;
                    /* ？ */
                    width: 180px;
                    height: 180px;

                    box-sizing: border-box;
                    border: 1.03077px dashed #FFFFFF;
                    border-radius: 15.4615px;

                    background: rgba(255, 255, 255, 0.5);
                    font-family: 'Inter';
                    font-style: normal;
                    font-weight: 700;
                    font-size: 140px;
                    text-align: center;

                    color: #FFFFFF;

                    opacity: 0.3;
                }
            }
        }

        .t1 {
            /* 更多奖章🎖️ */
            padding: 10px 20px;

            font-family: 'Inter';
            font-style: normal;
            font-weight: 800;
            font-size: 20px;
            line-height: 24px;

            color: #FFFFFF;
        }

        .list {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            justify-content: center;
            justify-items: center;
            gap: 12px;

            padding: 10px 20px;

            .item {
                align-items: center;

                .text {
                    font-family: 'Inter';
                    font-style: normal;
                    font-weight: 700;
                    font-size: 10px;
                    line-height: 12px;
                    text-align: center;

                    color: #FFFFFF;
                }

                .unknown {
                    display: flex;
                    align-items: center;
                    justify-content: center;
                    /* ？ */
                    width: 84.52px;
                    height: 84.52px;

                    box-sizing: border-box;
                    border: 1.03077px dashed #FFFFFF;
                    border-radius: 15.4615px;

                    background: rgba(255, 255, 255, 0.5);
                    font-family: 'Inter';
                    font-style: normal;
                    font-weight: 700;
                    font-size: 50.5263px;
                    line-height: 61px;
                    text-align: center;

                    color: #FFFFFF;

                    opacity: 0.3;
                }
            }

        }
    }
}
</style>