<template>
    <view class="page">
        <view class="main ">
            <!-- logo展示 -->
            <q-svg icon="logo" size="104"></q-svg>
        </view>
        <!-- 底部 -->
        <view class="bottom flex-column">
            <!-- 进度条 -->
            <view class="progress-wrap">
                <view class="progress"></view>
            </view>
            <text class="text">“我不关心你来自何处，我只关心你要去向何方”——Quiz</text>
        </view>
    </view>
</template>

<script setup lang="ts">
import { ref } from 'vue';
const title = ref('Hello');
</script>

<style lang="scss" scoped>
.page {
    .main {
        display: flex;
        justify-content: center;
        align-items: center;
        flex-grow: 1;
        width: 100%;
    }

    // 底部
    .bottom {
        align-items: center;
        justify-content: start;
        height: 145px;
        gap: 10px;

        // 进度条
        .progress-wrap,
        .progress {
            box-sizing: border-box;
            border-radius: 15px;
            border: 3px solid #FFFFFF;
        }

        .progress-wrap {
            position: relative;
            background: linear-gradient(90deg, #BE53FF 0%, #5830C7 100%);
            height: 28px;
            width: 90%;

            .progress {
                position: absolute;
                background: #FFFFFF;
                height: 100%;
                top: 0;
                left: 0;

                animation: expandWidth 1s ease-in-out forwards;
                /* 动画效果 */
            }

            @keyframes expandWidth {
                from {
                    width: 0;
                }

                to {
                    width: 100%;
                }
            }
        }

        .text {
            font-family: 'Inter';
            font-style: normal;
            font-weight: 400;
            font-size: 10px;
            line-height: 12px;

            color: rgba(255, 255, 255, 0.5);
        }

    }
}
</style>