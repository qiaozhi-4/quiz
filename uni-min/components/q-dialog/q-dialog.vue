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

.q-dialog {
  width: 86vw;

  background: #2F1969;
  border-radius: 30px;

  z-index: 102;
	}
</style>

<template>
  <view v-if="showDialog">
    <view class="q-dialog absolute-center ">
      <slot></slot>
    </view>
    <!-- 点击遮罩层是否关闭提示框 -->
    <view class="q-dialog-mask" @click="showDialog = !props.maskHideDialog">
    </view>
  </view>
</template>

<script lang="ts" setup>
import {ref} from 'vue'

/** 传递的属性 */
	const props = defineProps({
  /** 点击遮罩层是否关闭提示框 */
  maskHideDialog: {type: Boolean, default: true},
})

/** 显示提示框 */
const showDialog = ref(false)
const show = () => {
  console.log(show);
  showDialog.value = true
}
const hide = () => {
  console.log(hide);
  showDialog.value = false
}
defineExpose({
  show, hide
	})
</script>