<template>
    <view class="page">
        <q-nav-bar needBack fixed></q-nav-bar>
        <view class="content">
            <view class="card">
                <image class="logo" src="/static/logo.png"></image>
                <text>爱情盲盒</text>
                <text>选一个盒子 </text>
                <text>留下你的微信 or 拿一个微信！</text>
                <view>
                    <text>1元留一个🌟</text>
                    <text> 2元拿一个💎</text>
                </view>
            </view>
            <view class="card" v-for="(item, index) in classs" :key="index">{{ item.name }}</view>
            <view class="card">
                <image class="logo" src="/static/img/我留下的.png"></image>
                <text>我留下的</text>
            </view>
            <view class="card">
                <image class="logo" src="/static/img/我拿走的.png"></image>
                <text>我拿走的</text>
            </view>
        </view>
    </view>
</template>

<script setup lang="ts">
import { ref } from 'vue';
const classs = ref([{
    name: 'Les',
}, {
    name: 'Gay',
}, {
    name: 'Boy',
}, {
    name: 'Girl'
}]);
</script>

<style  lang="scss" scoped>
.page {
    // width: 100vw;
    // height: 100vm;
    background: linear-gradient(180deg, #FFFFFF 0%, #FFCCF1 100%);

    .content {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;

        .logo {
            height: 90px;
            width: 90px;
            margin-left: auto;
            margin-right: auto;
        }
    }
}
</style>
