<template>
    <view class="page">
        <q-nav-bar :title="option?.name" needBack fixed>
            <template #middle>
                <text :style="{ color: option?.color, fontSize: '32px' }">{{ option?.name }}</text>
            </template>
        </q-nav-bar>
        <view class="content flex-column">
            <view class="card add">
                <text class="text-gradual-change">1元留一个</text>
                <q-svg icon="编辑" size="18"></q-svg>
            </view>
            <view class="card item" v-for="(item, index) in list" :key="index">
                <text :style="{ color: option?.color }">{{ item.nickname }}</text>
                <text class="wrap" :style="{ color: option?.color }">{{ item.sex }}</text>
                <text>{{ item.age }}</text>
                <text>{{ item.MBTI }}</text>
                <text>{{ item.constellation }}</text>
                <text class="wrap">{{ item.location.city + item.location.region }}</text>
                <text class="wrap">{{ item.describe }}</text>
                <template v-if="false">
                    <view>
                        <text>ta的微信</text>
                        <input></input>
                    </view>
                    <button>一键复制</button>
                </template>
                <button v-else>2元拿一个</button>
            </view>
        </view>
    </view>
</template>

<script lang="ts" setup>
import { ref, computed } from 'vue';
import { onLoad, onShareAppMessage } from '@dcloudio/uni-app';
/** 本页路径参数 */
type Option = AnyObject & {
    /** 类型 */
    name?: string;
    /** 类型颜色 */
    color?: string;
} | undefined;
onLoad((option_: Option) => {
    option.value = option_;
    for (let index = 0; index < 10; index++) {
        list.value.push(list.value[0]);
    }
});
const option = ref<Option>({});
const list = ref([{
    nickname: 'Abcd',
    weChatNumber: '12345678',
    sex: '男',
    age: 18,
    constellation: '白羊座',
    location: {
        city: '北京',
        region: '海定',
    },
    MBTI: 'INFJ',
    describe: '两只老虎,没耳朵',
}]);
</script>

<style lang="scss" scoped>
.page {
    .q-nav-bar {
        background-color: #fff !important;
    }

    .content {
        gap: 10px;
        padding: 10px;

        text {
            font-size: 16px;
            font-weight: 700;
        }

        .card {
            padding: 8px 15px 8px 15px;
            border-radius: 15px;
            background: #fff;
        }

        .add {
            min-height: 73px;

            text {
                // flex-grow: 1;
                flex-basis: max-content;
            }
        }

        .item {
            /* 允许内容换行 */
            flex-wrap: wrap;
            gap: 10px;

            text {
                color: rgba(107, 107, 107, 1);
            }

            button {
                padding: 5px 15px;

                width: 95.17px;
                height: 27.66px;

                /* 按钮粉 */
                background: #FA2B90;
                border-radius: 30px;

                font-weight: 700;
                font-size: 14px;
                text-align: center; /* 水平居中 */
                line-height: 27.66px; /* 设置与按钮高度相同，实现垂直居中 */

                color: #FFFFFF;
            }

            .wrap {
                margin-right: auto;
            }
        }
    }
}
</style>