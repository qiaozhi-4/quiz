<template>
    <view class="page">
        <image class="bg" src="/static/img/主页背景.png"></image>
        <q-nav-bar fixed></q-nav-bar>
        <view class="content">
            <view class="card row explain flex-column">
                <image src="/static/img/装饰.png"></image>
                <text class="t1 text-gradual-change">爱情盲盒</text>
                <text class="t2">选一个盒子 </text>
                <text class="t2">留下你的微信 or 拿一个微信！</text>
                <text class="t3"> 1元留一个🌟 2元拿一个💎 </text>
            </view>
            <view class="card item" v-for="(item, index) in classs" :key="index" @click="onClass(index)">
                <text :style="{ color: item.color }">{{ item.name }}</text>
            </view>
            <view class="card row record">
                <image src="/static/img/我留下的.png"></image>
                <text>我留下的</text>
            </view>
            <view class="card row record">
                <image src="/static/img/我拿走的.png"></image>
                <text>我拿走的</text>
            </view>
        </view>
    </view>
</template>

<script setup lang="ts">
import { objectToPathParams } from '@/utils/service';
import { ref } from 'vue';
const classs = ref([{
    name: 'Les',
    color: 'rgba(101, 156, 255, 1)'
}, {
    name: 'Gay',
    color: 'rgba(231, 63, 36, 1)'
}, {
    name: 'Boy',
    color: 'rgba(58, 215, 177, 1)'
}, {
    name: 'Girl',
    color: 'rgba(255, 110, 214, 1)'
}]);
/** 点类别跳转页面 */
function onClass(index: number) {
    uni.navigateTo({
        url: `/pages/exhibition-list/exhibition-list` + objectToPathParams(classs.value[index])
    });
}
</script>

<style lang="scss" scoped>
.page {
    background: linear-gradient(180deg, #FFFFFF 0%, #FFCCF1 100%);

    .bg {
        position: absolute;
        width: 100vw;
        height: 14.6vh;
    }

    .content {
        height: 100%;
        display: grid;
        grid-template-columns: repeat(2, 1fr);
        grid-template-rows: auto repeat(2, 1fr) repeat(2, auto);
        gap: 10px;
        padding: 20px 10px 40px 10px;


        .card {
            padding: 10px 20px 10px 20px;
            border-radius: 15px;
            background: #fff;
        }

        .row {
            grid-column: span 2;
        }

        .explain {
            display: flex;
            gap: 8px;
            position: relative;
            background: rgba(255, 255, 255, 0.6);
            border: 3px solid rgba(255, 255, 255, 1);

            image {
                position: absolute;
                top: 0;
                right: 0;
                width: 87px;
                height: 87px;
            }

            .t1 {
                font-size: 48px;
                font-weight: 700;
            }

            .t2,
            .t3 {
                font-size: 20px;
                font-weight: 600;
            }

            .t2 {
                color: var(--, rgba(255, 110, 214, 1));
            }

            .t3 {
                color: rgba(250, 43, 144, 1);
            }
        }

        .item {
            display: grid;
            place-items: end start;

            text {
                font-size: 48px;
                font-weight: 900;
            }
        }

        .record {
            display: flex;
            gap: 10px;
            align-items: center;

            image {
                width: 39px;
                height: 39px;
            }

            text {
                color: var(---, rgba(107, 107, 107, 1));
                font-size: 20px;
                font-weight: 900;
            }
        }
    }
}
</style>
