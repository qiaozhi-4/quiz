<template>
    <view class="page">
        <q-nav-bar fixed needBack title="密友列表" titleSize="20" />
        <view class="main">
            <view class="table-item" v-for="(chum, index) in chumList" :key="index" @click="gochumHome(chum.userId)">
                <view
                    style="position: absolute;top: 0;right: 0;width: 76vw; height: 1px;background-color: rgba(121, 109, 255, 0.5);">
                </view>
                <view class="ranking" :class="`no${index + 1}`">{{ index + 1 }}</view>
                <q-avatar :src="chum.avatarUrl" size="42" />
                <view class="nickname" :class="`no${index + 1}`">{{ chum.nickname }}</view>
                <view class="score" :class="`no${index + 1}`">{{ chum.totalScore }}</view>
                <q-svg class="svg" icon="个人主页-排行-转到好友" size="22" />
            </view>
        </view>
    </view>
</template>

<script lang="ts" setup>
import { ref, computed } from 'vue';
import { onLoad, onShareAppMessage } from '@dcloudio/uni-app';
import { useStore } from "@/stores/store";
import { getIntimateRanking } from '@/utils/api/user';
const store = useStore();

onLoad(() => {
    getIntimateRanking(store.user.userId).then(res => chumList.value = res.data);
});
/** 密友列表 */
const chumList = ref<Quiz.UserDto[]>([]);
/** 点击密友触发,跳转到密友主页 */
function gochumHome(id: number) {
    uni.navigateTo({
        url: `/pages/home/home?userId=${id}`
    });
}
</script>

<style lang="scss" scoped>
.page {
    background-color: #2f1969;

    .main {

        .table-item {
            position: relative;
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
}
</style>