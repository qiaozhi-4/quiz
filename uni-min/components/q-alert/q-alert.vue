<style lang="scss" scoped>
.q-alert {
  width: 86vw;
  box-sizing: border-box;
  justify-content: center;
  align-items: center;
  padding: 16px;
  position: absolute;
  /* 子元素设置为绝对定位 */
  top: 100px;
  /* 距顶部50% */
  left: 50%;
  /* 距左侧50% */
  transform: translate(-50%, 0);
  /* 通过平移实现真正居中 */


  background: #7A47D3;
  backdrop-filter: blur(7.5px);
  border-radius: 15px;

  z-index: 199;


  font-family: 'Manrope';
  font-style: normal;
  font-weight: 700;
  font-size: 16px;
  letter-spacing: -0.04em;

  color: #FFFFFF;

  animation: slideDown 0.5s ease-out, fadeOut 1s ease-out 2s forwards;

  /* 应用动画 */
  @keyframes slideDown {
    0% {
      transform: translate(-50%, -100%);
      /* 元素从上方开始 */
      opacity: 0;
      /* 初始完全透明 */
    }

    100% {
      transform: translate(-50%, 0);
      /* 元素到达原始位置 */
      opacity: 1;
      /* 完全不透明 */
    }
  }

  @keyframes fadeOut {
    0% {
      opacity: 1;
      /* 初始完全不透明 */
    }

    100% {
      opacity: 0;
      /* 最终完全透明 */
    }
  }

}
</style>

<template>
  <view v-if="showAlert" :style="styleAlert" class="q-alert absolute-center ">
    <slot>{{ message }}</slot>
  </view>
</template>

<script lang="ts" setup>
import {ref} from 'vue'

/** 显示提示框 */
const showAlert = ref<boolean>(false)
/** 提示框样式(控制停留时间) */
const styleAlert = ref(null)
const message = ref<string>(null)
let timerId: NodeJS.Timeout
const show = ({msg = '', showTime = 2000}) => {
  styleAlert.value = {animation: `slideDown 0.5s ease- out, fadeOut 1s ease - out ${showTime}ms forwards`};
  message.value = msg;
  if (showAlert.value) {
    clearTimeout(timerId);
    showAlert.value = false
    setTimeout(() => {
      showAlert.value = true
    }, 1)
  } else {
    showAlert.value = true
    timerId = setTimeout(() => {
      showAlert.value = false
    }, showTime + 500 + 1000)
  }
}
defineExpose({
  show
})
</script>