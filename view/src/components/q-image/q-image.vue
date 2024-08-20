<style lang="scss" scoped>
.q-image-wrap {

    .q-image-fail {
        align-items: center;
        justify-content: center;
        background-color: #f5f7fa;
    }
}
</style>

<template>
    <view class="q-image-wrap">
        <image v-if="succeed" class="q-image" :src="propsComputed.src" :mode="propsComputed.mode" :style="style"
            @error="succeed = false" @load="succeed = true" />
        <view v-else class="q-image-fail" :style="style">
            <q-svg :icon="propsComputed.icon" :size="propsComputed.size"></q-svg>
        </view>
    </view>
</template>

<script lang="ts" setup>
import { ref, computed } from 'vue';
import { formatValue } from '@/utils/utils';
/** 传递的属性 */
const props = defineProps({
    /** 图片源地址，同原生属性一致 */
    src: { type: String, default: '' },
    /** 加载失败显示图标 */
    icon: { type: String, default: '图片加载失败', },
    /** 宽度 */
    width: { type: [String, Number], default: '320' },
    /** 高度 */
    height: { type: [String, Number], default: '240' },
    /** 加载失败图标大小 */
    size: { type: [String, Number], default: '32' },
    /** 图片裁剪、缩放的模式 */
    mode: { type: String, default: 'aspectFit' },
});
const propsComputed = computed(() => props);
/** 加载是否成功 */
const succeed = ref<boolean>(true);
/** 计算样式 */
const style = computed(() => {
    return { width: formatValue(props.width), height: formatValue(props.height), };
});
</script>