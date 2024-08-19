<style lang="scss" scoped>
.q-dialog-mask {
    position: fixed;
    top: 0;
    right: 0;
    background-color: rgba(#000, 0.5);
    width: 100vw;
    height: 100vh;
    z-index: 101;
}

.center {
    width: 86vw;
    height: 70vh;
    top: 50%;
    /* 距顶部50% */
    left: 50%;
    /* 距左侧50% */
    transform: translate(-50%, -50%);
    /* 通过平移实现真正居中 */
}

.left {
    width: 50vw;
    height: 100vh;
    top: 0;
    left: 0;
}

.right {
    width: 50vw;
    height: 100vh;
    top: 0;
    right: 0;
}

.bottom {
    width: 100vw;
    height: 70vh;
    bottom: 0;
    right: 0;
}

.top {
    width: 100vw;
    height: 70vh;
    top: 0;
    right: 0;
}



.q-dialog {
    position: absolute;
    /* 子元素设置为绝对定位 */

    background: #2F1969;
    border-radius: 30px;

    z-index: 102;

}
</style>

<template>
    <view v-if="showDialog">
        <view class="q-dialog flex-column " :class="extraClass + ' ' + location">
            <slot></slot>
        </view>
        <!-- 点击遮罩层是否关闭提示框 -->
        <view class="q-dialog-mask" @click="showDialog = !props.maskHideDialog" >
        </view>
    </view>
</template>

<script lang="ts" setup>
import { ref } from 'vue';

/** 传递的属性 */
const props = defineProps({
    /** 点击遮罩层是否关闭提示框 */
    maskHideDialog: { type: [Boolean, String], default: false },
    /** 容器额外的class */
    extraClass: { type: String, default: '' },
    /** 元素位置 */
    location: { type: String, default: 'center' },
});

/** 显示提示框 */
const showDialog = ref<boolean>(false);
const show = () => {
    showDialog.value = true;
};
const hide = () => {
    showDialog.value = false;
};
defineExpose({
    show, hide
});
</script>