<template>
    <view class="page">
        <q-nav-bar fixed needBack title="my Quiz" />
        <view class="main flex-column">
            <view class="v1">
                <view class="v-global achievement-score flex-column">
                    <text class="t1">目前成就</text>
                    <text class="t2">{{ props[0]?.number }}分</text>
                </view>
                <view class="v-global gem" @click="onProp(props[1]?.propId)">
                    <q-svg icon="复活宝石" size="33" />
                    <text class="count">{{ props[1]?.number }}</text>
                </view>
                <view class="v-global gem" @click="onProp(props[2]?.propId)">
                    <q-svg icon="提示宝石" size="33" />
                    <text class="count">{{ props[2]?.number }}</text>
                </view>
                <view class="v-global badge" @click="onBadge()">
                    <q-svg icon="徽章" size="33" />
                    <text class="count">一共获得{{ badgeList.length }}枚徽章</text>
                    <q-svg icon="空心向右箭头" size="14" />
                </view>
            </view>
            <text class="class">本周限时奖励</text>
            <view class="v-global tasks flex-column" v-for="(item, index) in tasks" :key="index">
                <text class="title">{{ item.title }}</text>
                <view class="task-global" :class="`task${item.classId}`" v-for="(task, i) in item.list" :key="i">
                    <view class="progress" :style="{ width: `${task.finishNumber / task.conditionNumber * 100}%` }">
                    </view>
                    <view class="svg-grap">
                        <q-svg :icon="task.awardName" size="33" />
                        <text class="count">{{ task.awardNumber }}</text>
                    </view>
                    <text class="title">{{ task.describe }}</text>
                    <button class="but-unfinished" v-if="task.finishNumber < task.conditionNumber">还差{{
                        task.conditionNumber -
                        task.finishNumber }}</button>
                    <button class="but-unfinished" v-else-if="task.isReceiveAward">已领取</button>
                    <button class="but" v-else @click="receiveAward(task.taskId, index, i)">领取奖励</button>
                </view>
            </view>
            <!-- 	<text class="class">本月我最关注的朋友🤩</text>
			<view class="v-global ranking-list flex-column">
				<text class="title">答题数量排行：</text>
				<view class="user" v-for="(item, index) in rankingList" :key="index">
					<view class="progress" :style="{width:`${item.count / rankingList[0].count * 100}%`}"></view>
					<q-avatar :src="item?.avatarUrl" size="42" borderWidth="2"></q-avatar>
					<text class="t1">{{item.count}}答</text>
					<view class="nickname">{{item?.nickname}}</view>
				</view>
			</view> -->
        </view>
    </view>

</template>

<script lang="ts" setup>
import { computed, ref } from 'vue';
import { getBadgeList } from '@/utils/api/answer';
import { finishTask, getAllTask } from '@/utils/api/task';
import { onLoad } from '@dcloudio/uni-app';
import { useStore } from "@/stores/store";

const store = useStore();

/** 道具信息 */
const props = computed<Quiz.PropDTO[]>(() => store.props);
/** 徽章信息 */
const badgeList = ref<Quiz.AnswerDTO[]>([]);
/** 任务数据 */
type TaskInfo = {
    title: string,
    classId: number,
    list: Quiz.TaskDTO[];
};
const tasks = ref<TaskInfo[]>([
    {
        title: '',
        // title: '测测对朋友的了解😊',
        classId: 1,
        list: []
    }, {
        title: '',
        // title: '出题给朋友们～ 😆',
        classId: 2,
        list: []
    }
]);

onLoad((option) => {
    getBadgeList(store.user.userId).then(res => {
        badgeList.value = res.data;
    });

    getAllTask(store.user.userId).then(res => {
        tasks.value.forEach(e => {
            e.list = res.data.filter((task: Quiz.TaskDTO) => task.classId == e.classId);
            e.title = e.list[0].classDescribe;
        });
    });
});


/** 点击道具,跳转道具介绍页面 */
function onProp(propId: any) {
    uni.navigateTo({
        url: `/pages/prop-explain/prop-explain?propId=${propId}`
    });
}
/** 点击徽章,跳转徽章详情 */
function onBadge() {
    uni.navigateTo({
        url: `/pages/badge/badge`
    });
}
/** 领取奖励 */
function receiveAward(taskId: number, index: number, i: number) {
    finishTask(taskId, store.user.userId).then(res => {
        tasks.value[index].list[i] = res.data;
        store.addPropNumberById(tasks.value[index].list[i].awardId, tasks.value[index].list[i].awardNumber);
    });
}
</script>

<style lang="scss" scoped>
.page {
    background: #1F1146;

    .q-nav-bar-middle {
        /* my Quiz */
        font-family: 'Inter';
        font-style: italic !important;
        font-weight: 900 !important;
        font-size: 24px !important;
        line-height: 29px;
    }

    .main {
        padding: 10px;
        padding-bottom: 50px;
        gap: 8px;

        .v-global {
            align-items: center;

            background: rgba(78, 63, 246, 0.15);
            backdrop-filter: blur(2px);
            border-radius: 15px;
        }

        .v1 {
            display: grid;
            grid-template-columns: 1fr auto;
            gap: 12px;
            width: 100%;


            .achievement-score {
                grid-row: 1 / 3;
                align-items: start;
                gap: 8px;
                padding: 17px;

                .t1 {
                    font-family: 'Inter';
                    font-style: normal;
                    font-weight: 900;
                    font-size: 16px;
                    line-height: 19px;

                    color: #4E3FF6;
                }

                .t2 {
                    font-family: 'Inter';
                    font-style: normal;
                    font-weight: 1000;
                    font-size: 36px;
                    line-height: 44px;

                    color: #FFFFFF;
                }
            }

            .gem {
                padding: 8px 22px;
            }

            .badge {
                grid-column: 1/3;
                gap: 10px;
                padding: 9px 22px;

                .count {
                    flex-grow: 1;
                }
            }

            .count {
                font-family: 'Inter';
                font-style: normal;
                font-weight: 700;
                font-size: 16px;
                line-height: 19px;

                color: #FFFFFF;
            }
        }

        .class {
            padding: 10px;
            padding-top: 20px;
            /* 本周限时奖励 */

            font-family: 'Inter';
            font-style: normal;
            font-weight: 800;
            font-size: 20px;
            line-height: 24px;

            color: #FFFFFF;
        }

        .tasks {
            align-items: start;
            padding: 7px 12px;
            gap: 8px;

            .title {
                padding: 8px;
                /* 测测对朋友的了解😊 */
                font-family: 'Inter';
                font-style: normal;
                font-weight: 800;
                font-size: 16px;
                line-height: 19px;

                text-align: center;
            }

            .task-global {
                position: relative;
                width: 100%;
                justify-content: space-between;
                align-items: center;
                padding: 0 10px;

                background: rgba(167, 41, 226, 0.15);
                border-radius: 30px;

                .progress {
                    width: 100%;
                    height: 100%;
                    position: absolute;
                    top: 0;
                    left: 0;

                    border-radius: 30px;
                    z-index: -1;
                }

                .svg-grap {
                    align-items: center;

                    .count {
                        /* 1 */
                        font-family: 'Inter';
                        font-style: normal;
                        font-weight: 900;
                        font-size: 16px;
                        line-height: 19px;
                    }
                }

                .title {
                    /* 做1位朋友的测试！ */
                    font-family: 'Inter';
                    font-style: normal;
                    font-weight: 700;
                    font-size: 15px;
                    line-height: 18px;

                }

                .but {
                    width: 76px;
                    height: 21px;
                    border-radius: 30px;

                    font-family: 'Inter';
                    font-style: normal;
                    font-weight: 700;
                    font-size: 12px;
                    line-height: 15px;
                    /* identical to box height */
                    text-align: center;
                }

                .but-unfinished {
                    width: 76px;
                    height: 21px;
                    border-radius: 30px;

                    /* 还差3 */
                    font-family: 'Inter';
                    font-style: normal;
                    font-weight: 500;
                    font-size: 12px;
                    line-height: 15px;
                    /* identical to box height */
                    text-align: center;

                    color: #FFFFFF;

                    background: rgba(255, 255, 255, 0.15);
                }
            }


            .task1 {
                background: rgba(78, 63, 246, 0.3);

                .but {
                    /* 领取奖励 */
                    color: #4E3FF6;
                }

                .progress {
                    background-color: #4E3FF6;
                }
            }

            .task2 {
                background: rgba(167, 41, 226, 0.3);

                .but {
                    color: #A729E2;
                }

                .progress {
                    background-color: #A729E2;
                }
            }
        }

        .ranking-list {
            align-items: start;
            gap: 15px;
            padding: 12px;

            .title {
                font-family: 'Inter';
                font-style: normal;
                font-weight: 800;
                font-size: 16px;
                line-height: 19px;
            }

            .user {
                gap: 10px;
                align-items: center;
                width: 100%;
                position: relative;


                .progress {
                    width: 100%;
                    height: 100%;
                    position: absolute;
                    top: 0;
                    left: 0;

                    z-index: -1;

                    box-sizing: border-box;
                    background: rgba(78, 63, 246, 0.5);
                    border-right: 3px solid #4536C5;
                    border-radius: 30px;
                }

                .t1 {
                    font-family: 'Inter';
                    font-style: normal;
                    font-weight: 700;
                    font-size: 20px;
                    line-height: 24px;

                    color: #FFFFFF;
                }

                .nickname {
                    /* 网名 */
                    font-family: 'Inter';
                    font-style: normal;
                    font-weight: 700;
                    font-size: 12px;
                    line-height: 15px;

                    color: #FFFFFF;
                }
            }
        }
    }
}
</style>