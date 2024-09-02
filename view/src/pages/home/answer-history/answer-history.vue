<template>
    <view class="page">
        <q-nav-bar fixed needBack title="答题历史" titleSize="20" />
        <view class="main">
            <p-answers :papers="paperList"></p-answers>
        </view>
    </view>
</template>

<script lang="ts" setup>
import { ref, computed } from 'vue';
import { onLoad, onShareAppMessage } from '@dcloudio/uni-app';
import { getPaperAndAnswerDTOList, getPaperList, removePaper } from '@/utils/api/paper';
import { getIntimateRanking, getUser } from '@/utils/api/user';
import { useStore } from "@/stores/store";
import { homeTestInfo } from '@/utils/constant';
import { objectToPathParams } from '@/utils/service';

/** 试卷记录 */
const paperList = ref<Quiz.PaperAndAnswerDTO[]>([]);
</script>

<style lang="scss" scoped>
.page {
    background-color: #2f1969;
}
</style>