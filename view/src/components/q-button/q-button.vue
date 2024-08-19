<template>
    <view class="q-button text-overflow">
        <slot>按钮</slot>
    </view>
</template>

<script lang="ts" setup>
const props = defineProps();
</script>

<style lang="scss" scoped>
.q-button {
    height: 32px;
}

.q-button:active {
    /* 缩小按钮 */
    transform: scale(0.92);
    /* 添加阴影以模拟发光效果 */
    box-shadow: 0 0 15px rgba(98, 0, 234, 0.6);
}
</style>