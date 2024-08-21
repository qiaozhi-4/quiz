<style lang="scss" scoped>
.carousel-indicators {
    position: absolute;
    bottom: 10px;
    left: 50%;
    /* 距离左边50% */
    transform: translate(-50%, 0);

    display: flex;
    justify-content: center;
    gap: 10px;
    height: 10px;

    .dot {

        width: 10px;
        height: 10px;
        background-color: rgba(#FFFFFF, 0.3);
        border-radius: 50%;
        cursor: pointer;
        transition: transform 0.3s ease;
        /* 添加过渡效果 */
    }

    .active {
        background-color: #FFFFFF;
        transform: scale(1.2);
        /* 放大效果 */
    }

    .active2 {

        transform:   translateX(10px);
    }

    .offset {

        transform: scale(0.8) translateX(10px);
    }

    @keyframes offset {
        0% {
            transform: translateX(0);
        }

        50% {
            transform: translateX(10px);
        }

        100% {
            transform: translateX(0);
        }

    }
}
</style>

<template>
    <view class="carousel-indicators">
        <text class="dot" v-for="(dot, index) in visibleDots" :key="index" :class="{ active: dot === props.current }" />
    </view>
</template>

<script lang="ts" setup>
import { computed, ref } from 'vue';
/** 传递的属性 */
const props = defineProps({
    /** 当前指示点索引 */
    current: { type: Number, default: 0 },
    /** 总数 */
    amount: { type: Number, default: 1 },
});
const offset = ref(false);
/** 计算应该显示的指示点 */
// const visibleDots = computed(() => {
//     console.log(JSON.stringify(props));
//     offset.value = true;
//     setTimeout(() => {
//         offset.value = false;
//     }, 300);
//     let start = props.current - 2;
//     let end = props.current + 2;
//     let totalSlides = props.amount;

//     // 确保 start 和 end 在合理范围内
//     if (start < 0) {
//         start = 0;
//         end = 4;
//     }
//     if (end >= totalSlides) {
//         end = totalSlides - 1;
//         start = totalSlides - 5;
//     }
//     if (totalSlides <= 5) {
//         start = 0;
//         end = totalSlides - 1;
//     }

//     // 返回当前需要显示的指示点数组
//     const result = [];
//     for (let i = start; i <= end; i++) {
//         result.push(i);
//     }
//     return result;
// });
const visibleDots = computed(() => {
    let start = props.current - 1;
    let end = props.current + 1;
    let totalSlides = props.amount;

    // 确保 start 和 end 在合理范围内
    if (start < 0) {
        start = 0;
        end = 2;
    }
    if (end >= totalSlides) {
        end = totalSlides - 1;
        start = totalSlides - 3;
    }
    if (totalSlides <= 3) {
        start = 0;
        end = totalSlides - 1;
    }

    // 返回当前需要显示的指示点数组
    const result = [];
    for (let i = start; i <= end; i++) {
        result.push(i);
    }
    return result;
});
</script>