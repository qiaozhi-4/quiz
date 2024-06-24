// pages/home/personal-center/personal-center.ts

Component({

  /**
   * 组件的属性列表
   */
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {
    /** 头像Url */
    avatarUrl: '',
    /** 昵称 */
    nickname: '乔治',
    /** 统计 */
    statistics: [
      { name: '出题', value: 100 },
      { name: '答题', value: 100 },
      { name: '密友', value: 100 },
    ],
    /** 排行榜数据 */
    rankingData: [] as any[],
    /** 当前激活的排行榜 */
    rankingIndex: 0,
    /** 头部高度,通过胶囊计算 */
    headHeight: "",
    /** 是否触发沾醒定位 */
    isSticky: false,
    /** 当切换榜单时,滚动条直接滚动到榜单位置 */
    clickRanking: false,

  },

  /**
   * 组件的方法列表
   */
  methods: {
    /** 滚动时触发 */
    handlScroll() {
      this.createIntersectionObserver({
        thresholds: [0.2, 0.5]
      }).relativeTo('.ranking').observe('.class', (res) => {
        let top = res.intersectionRect.top
        this.setData({
          isSticky: top < 1
        })
      })

    },
    /** 切换排行榜 */
    changeRankingIndex(e: WechatMiniprogram.TouchEvent) {
      let index = e.currentTarget.dataset.index
      this.setData({
        rankingIndex: index,
        clickRanking: true
      })
    },

  },
  /** 生命周期函数 */
  lifetimes: {
    attached: function () {
      // 在组件实例进入页面节点树时执行
      /** 生产排行榜数据 */
      let rankingData = []
      for (let index = 0; index < 20; index++) {
        rankingData.push({
          nickname: `用户${index + 1}`,
          score: (20 - index) * 1000
        })
      }
      this.setData({
        rankingData: rankingData,
        headHeight: wx.getMenuButtonBoundingClientRect().bottom + 10 + 'px',
      })
    },
    detached: function () {
      // 在组件实例被从页面节点树移除时执行
    },
  },
})