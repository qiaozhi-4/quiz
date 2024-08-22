<template>
    <view class="q-loading" v-if="showLoading">
        <view class="loading-spinner">
        </view>
    </view>
</template>

<script lang="ts" setup>
import { ref } from 'vue';

const props = defineProps();
/** 显示加载中 */
const showLoading = ref<boolean>(true);
const show = () => {
    showLoading.value = true;
};
const hide = () => {
    showLoading.value = false;
};
defineExpose({
    show, hide
});
</script>

<style lang="scss" scoped>
.q-loading {
    display: grid;
    place-content: center;

    position: fixed;
    background-color: rgba(#000, 0.5);
    top: 0;
    right: 0;
    width: 100vw;
    height: 100vh;
    z-index: 201;

    .loading-spinner {
        $size: 50px;
        $border-width: 5px;

        // 设置加载圈的尺寸和边框样式
        width: $size;
        height: $size;
        border: $border-width solid #f3f3f3; // 灰色背景边框
        border-top: $border-width solid #3498db; // 蓝色旋转边框
        border-radius: 50%; // 圆形

        // 使用CSS动画实现旋转效果
        animation: spin 1s linear infinite; // 1秒完成一圈，线性动画，无限循环
    }

    // 定义spin动画
    @keyframes spin {
        0% {
            transform: rotate(0deg); // 起始位置
        }

        100% {
            transform: rotate(360deg); // 终止位置
        }

    }
}
</style>