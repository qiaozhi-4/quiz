<template>
    <view class="page">
        <q-nav-bar fixed></q-nav-bar>
        <view class="main">
            <q-avatar :class="{ shake: avatarShake }" :src="store.user.avatarUrl" size="130" borderWidth="5"
                :isChooseAvatar="true" />
            <view class="label">昵称</view>
            <input class="nickname-input" :class="{ shake: nicknameShake }"
                placeholder-class="nickname-input-placeholder" type="nickname" placeholder="请输入昵称"
                :value="store.user.nickname" @input="onInput" @blur="onBlur" />
            <button class="submit-button" @click="onSubmit">
                <text class="submit-button-text">完成</text>
            </button>
        </view>
    </view>
</template>

<script lang="ts" setup>
import { ref } from 'vue';
import { updateUser } from '@/utils/api/wxUser';
import { getPaperTotal } from '@/utils/api/paper';
import { onLoad } from '@dcloudio/uni-app';
import { useStore } from "@/stores/store";
const store = useStore();
/** 是否需要动画 */
const avatarShake = ref<boolean>(false);
/** 是否需要动画 */
const nicknameShake = ref<boolean>(false);
/** 获取微信头像触发 */
function onChooseAvatar(e: { detail: { avatarUrl: any; }; }) {
    console.log("获取微信头像触发", e);
    const { avatarUrl } = e.detail;
}

/** 键盘输入触发 */
function onInput(e: { detail: { value: string; }; }) {
    console.log("键盘输入触发", e);
    store.$patch(partialState => partialState.user.nickname = e.detail.value);
}

/** 键盘失去焦点触发 */
function onBlur(e: { detail: { value: any; }; }) {
    console.log("键盘失去焦点触发", e);
    store.$patch(partialState => partialState.user.nickname = e.detail.value);
}

/** 修改个人信息 */
function onSubmit() {
    if (!store.user.avatarUrl) {
        avatarShake.value = true;
        setTimeout(() => {
            avatarShake.value = false;
        }, 500); // 动画持续时间应与CSS中定义的相同
    }
    if (!store.user.nickname) {
        nicknameShake.value = true;
        setTimeout(() => {
            nicknameShake.value = false;
        }, 500); // 动画持续时间应与CSS中定义的相同
    }
    if (store.user.avatarUrl && store.user.nickname) {
        updateUser(store.user).then(e => {
            console.log("第一次设置个人信息");
            // 判断是不是通过分享进来的
            let route = getCurrentPages()[0].route;
            if ("pages/paper-start/paper-start" == route) {/** 不是分享进来的 */
                uni.navigateBack({ delta: 1 });
            } else {/**分享进来的 */
                if (store.user.paperTotal == 0) {/** 没有出过题 */
                    uni.reLaunch({
                        url: `/pages/paper-start/paper-start`
                    });
                } else {
                    uni.reLaunch({
                        url: `/pages/home/home`
                    });
                }
            }
        });
    }
}
</script>

<style lang="scss" scoped>
.main {
    display: grid;
    justify-items: center;
    gap: 5px;
    padding: 0 36px;


    .label {
        justify-self: start;

        padding-left: 26px;
        /* 昵称 */
        font-family: 'Inter';
        font-style: normal;
        font-weight: 500;
        font-size: 16px;
        line-height: 19px;

        color: rgba(255, 255, 255, 0.5);
    }

    .nickname-input {
        display: flex;
        // align-items: center;
        width: 100%;
        height: 54px;
        padding: 15px 26px;
        box-sizing: border-box;

        background: #7756EC;
        border-radius: 30px;

        /* 乔治 */
        font-family: 'Inter';
        font-style: normal;
        font-weight: 900;
        font-size: 20px;
        line-height: 24px;

        color: #FFFFFF;
    }

    .nickname-input-placeholder {
        /* 请输入昵称 */
        font-family: 'Inter';
        font-style: normal;
        font-weight: 500;
        font-size: 20px;
        line-height: 24px;

        color: rgba(255, 255, 255, 0.3);

    }

    .submit-button {
        justify-self: end;
        display: grid;
        place-items: center;

        margin-top: 5px;
        width: 105px;
        height: 41px;

        background: #FFFFFF;
        border-radius: 30px;

        .submit-button-text {
            /* 完成 */
            font-family: 'Inter';
            font-style: normal;
            font-weight: 700;
            font-size: 20px;
            line-height: 24px;
            /* 粉紫渐变 */
            background: linear-gradient(90deg, #BE53FF 0%, #7756EC 100%);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            background-clip: text;
            text-fill-color: transparent;
        }
    }
}

/* 抖动动画 */
@keyframes shake {

    0%,
    100% {
        transform: translateX(0);
    }

    25% {
        transform: translateX(-5px);
    }

    50% {
        transform: translateX(5px);
    }

    75% {
        transform: translateX(-5px);
    }
}

.shake {
    animation: shake 0.5s;
    animation-timing-function: ease-in-out;
}
</style>