<style lang="scss" scoped></style>

<template>
    <view class="q-navigator" @click="routeJump">
        <slot>路由跳转</slot>
    </view>
</template>

<script lang="ts" setup>
import { objectToPathParams } from '../../utils/service';

/** 传递的属性 */
const props = defineProps({
    /** 跳转模式[navigateTo|redirectTo|reLaunch|navigateBack] */
    mode: { type: String, default: 'navigateTo' },
    /** 需要跳转的应用内页面路径 , 路径后可以带参数。参数与路径之间使用?分隔，参数键与参数值用=相连，不同参数用&分隔；如 'path?key=value&key2=value2' */
    url: { type: String, default: '' },
    /** 路由参数 */
    data: { type: Object, default: {} },
    /** 返回的页面数，如果 delta 大于现有页面数，则返回到首页。 */
    delta: { type: [Number, String], default: 1 },
});
/** 路由跳转函数 */
function routeJump() {
    if (props.mode === 'navigateBack') {
        uni.navigateBack({
            delta: Number(props.delta)
        });
    } else {
        // @ts-ignore
        uni[props.mode]({
            url: props.url + objectToPathParams(props.data)
        });
    }
}
</script>