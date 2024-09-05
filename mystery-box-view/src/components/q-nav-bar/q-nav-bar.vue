<style lang="scss" scoped>
.q-fixed {
    position: sticky;
    top: 0;
    /* 当滚动到距离顶部 0 像素时粘住 */
    z-index: 1;
    /* 确保粘性元素在其他元素上方 */
}

.q-nav-bar {
    display: flex;
    width: 100vw;
    justify-content: space-between;
    align-items: flex-end;

    .q-nav-bar-left {
        display: grid;
        place-content: end start;
        align-items: end;
    }

    .q-nav-bar-middle {
        display: grid;
        place-content: end center;

        font-family: 'Inter';
        font-style: normal;
        font-weight: 800;
        font-size: 32px;
        line-height: 39px;
    }
}
</style>

<template>
    <view class="q-nav-bar" :class="{ 'q-fixed': props.fixed }" :style="style">
        <slot>
            <view class="q-nav-bar-left" :style="`width: ${menuButtonInfo.width}px`">
                <slot name="left">
                    <q-svg icon="头部导航-返回" size="32" @click="back" />
                </slot>
            </view>
            <view :style="`font-size: ${props.titleSize}px;`" class="q-nav-bar-middle text-overflow">
                <slot name="middle">{{ props.title }} </slot>
            </view>
            <view class="q-nav-bar-right" :style="`width: ${menuButtonInfo.width}px`"></view>
        </slot>
    </view>
</template>

<script lang="ts" setup>
import { computed } from 'vue';

const props = defineProps({
    /** 标题文字 */
    title: { type: String, default: '' },
    /** 标题文字大小 */
    titleSize: { type: String, default: '32' },
    /** 文字颜色 */
    color: { type: String, default: '#ffffff' },
    /** 是否固定顶部 */
    fixed: { type: [Boolean, String], default: false },
    /** 是否需要返回图标 */
    needBack: { type: [Boolean, String], default: false },
});
/** 胶囊信息{width: 87, height: 32, left: 281, top: 51, right: 368} */
const menuButtonInfo = uni.getMenuButtonBoundingClientRect();
/** 设备信息 */
let systemInfo: UniApp.GetSystemInfoResult;
uni.getSystemInfo({
    success: function (res) {
        systemInfo = res;
    }
});
/** 计算头部高度和内边距 */
const style = computed(() => {
    let padding = systemInfo.screenWidth - menuButtonInfo.right;
    return `min-height: ${10 + menuButtonInfo.bottom}px; padding: 0 ${padding}px 10px ${padding}px; color: ${props.color}`;
});
/** 返回上一页 */
function back() {
    uni.navigateBack({
        delta: 1,
        fail: () => {
            uni.reLaunch({
                url: '/pages/index/index'
            });
        }
    });
}
</script>