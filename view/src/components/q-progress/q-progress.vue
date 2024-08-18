<template>
    <view class="q-progress-wrap border" :style="{
        border: `3px solid ${bgColor}`,
        background: bgColor, width: width, height: height,
        borderRadius: isRadius ? '15px' : '',
    }">
        <view class="q-progress" :style="{
            background: color, transition: ` width ${duration} ease`, width: temp,
            borderRadius: isRadius ? '15px' : '',
        }">
        </view>
    </view>
</template>

<script lang="ts" setup>
import { ref } from 'vue';
const props = defineProps({
    /** 进度条背景颜色 */
    bgColor: {
        type: String,
        default: 'linear-gradient(90deg, #BE53FF 0%, #5830C7 100%)',
    },
    /** 进度条颜色 */
    color: {
        type: String,
        default: '#FFFFFF',
    },
    /** 是否需要圆角 */
    isRadius: {
        type: Boolean,
        default: false,
    },
    /** 进度条宽度 */
    width: {
        type: String,
        default: '90vw',
    },
    /** 进度条高度 */
    height: {
        type: String,
        default: '28px',
    },
    /** 进度条动画时间 */
    duration: {
        type: String,
        default: '1s',
    },
});
/** 为了动画效果，需要将进度条的宽度设置为0，然后通过定时器将其设置为100%。 */
const temp = ref("0px");
setTimeout(()=> temp.value = "100%", 10)
</script>

<style lang="scss" scoped>
.border {
    box-sizing: border-box;
    border: 3px solid #FFFFFF;
}

.q-progress-wrap {
    position: relative;
    background: linear-gradient(90deg, #BE53FF 0%, #5830C7 100%);
    height: 28px;
    width: 90%;
    overflow: 'hidden';

    .q-progress {
        position: absolute;
        background: #FFFFFF;
        height: 100%;
        width: 0;
        top: 0;
        left: 0;
    }
}
</style>