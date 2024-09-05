///////////////////////////////////////这个文件是全局常量
export type ScoreInfo = {
    englishName: string,
    name: string,
    badgeName: string,
    badges: Quiz.AnswerDTO[];
};
/** 得分描述已经信息 */
export const scoreInfo = () => JSON.parse(JSON.stringify([
    {
        englishName: 'Totally different',
        name: '截然不同',
        badgeName: '',
        badges: [],
    },
    {
        englishName: 'poles apart',
        name: '南辕北辙',
        badgeName: '',
        badges: [],
    },
    {
        englishName: 'Vastly different',
        name: '大相径庭',
        badgeName: '',
        badges: [],
    },
    {
        englishName: 'Each is unique',
        name: '各自独特',
        badgeName: '',
        badges: [],
    },
    {
        englishName: 'Different factions',
        name: '各持己见',
        badgeName: '',
        badges: [],
    },
    {
        englishName: 'Hold different views',
        name: '一面之交',
        badgeName: '',
        badges: [],
    },
    {
        englishName: 'Understand',
        name: '心领神会',
        badgeName: '',
        badges: [],
    },
    {
        englishName: 'Congenial',
        name: '如出一辙',
        badgeName: '',
        badges: [],
    },
    {
        englishName: 'Congenial',
        name: '意气相投',
        badgeName: '铜徽章',
        badges: [],
    },
    {
        englishName: 'blue Bosom friend',
        name: '蓝颜知己',
        badgeName: '银徽章',
        badges: [],
    },
    {
        englishName: 'soulmate',
        name: '灵魂之友',
        badgeName: '金徽章',
        badges: [],
    },
]));



/** 临时数据会删除 */
export const homeTestInfo = [
    {
        name: '关键题',
        items: [{
            title: '你最喜欢在周末做什么？',
            select: '在家进行个人活动',
            input: '吃饭睡觉游戏代码'
        }, {
            title: '你更喜欢哪种类型的电影？',
            select: '白开水',
        },],
        extra: {
            t1: '选择一道题',
            t2: '展示你的答案',
        }
    },
    {
        name: '朋友的看法',
        items: [],
        extra: {
            t1: '选择1条朋友的看法',
            t2: '展示回答',
        }
    },
    {
        name: '基础题',
        spread: false,
        items: [{
            title: '你最喜欢在周末做什么？',
            select: '在家进行个人活动',
            input: '吃饭睡觉游戏代码'
        }, {
            title: '你更喜欢哪种类型的电影？',
            select: '白开水',
        }, {
            title: '你最喜欢在周末做什么？',
            select: '在家进行个人活动',
            input: '吃饭睡觉游戏代码'
        }, {
            title: '你更喜欢哪种类型的电影？',
            select: '白开水',
        },]
    },
    {
        name: '进阶题',
        spread: false,
        items: [{
            title: '你最喜欢在周末做什么？',
            select: '在家进行个人活动',
            input: '吃饭睡觉游戏代码'
        }, {
            title: '你更喜欢哪种类型的电影？',
            select: '白开水',
        }, {
            title: '你最喜欢在周末做什么？',
            select: '在家进行个人活动',
            input: '吃饭睡觉游戏代码'
        }, {
            title: '你更喜欢哪种类型的电影？',
            select: '白开水',
        },]
    },
    {
        name: '核心题',
        spread: false,
        items: [{
            title: '你最喜欢在周末做什么？',
            select: '在家进行个人活动',
            input: '吃饭睡觉游戏代码'
        }, {
            title: '你更喜欢哪种类型的电影？',
            select: '白开水',
        }, {
            title: '你最喜欢在周末做什么？',
            select: '在家进行个人活动',
            input: '吃饭睡觉游戏代码'
        }, {
            title: '你更喜欢哪种类型的电影？',
            select: '白开水',
        },]
    },
];