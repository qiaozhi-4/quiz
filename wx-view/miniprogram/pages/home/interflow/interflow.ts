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
    /** 胶囊信息 */
    capsule: wx.getMenuButtonBoundingClientRect(),
    /** 搜索输入框 */
    searchInput: '',
    /** 多人聊天卡片 */
    multiplayers: [
      {
        borderColor: '#7756EC',
        bottonColor: '#B7A2FF',
        imgUrl: '	https://game.gtimg.cn/images/lol/act/img/skin/big_a8918612-98ba-4822-90b9-331f89a419a5.jpg'
      },
    ] as any[],
    /** 最近消息 */
    messages: [] as any[],
  },

  /**
   * 组件的方法列表
   */
  methods: {
    /** 键盘输入时触发 */
    handlInput(e: WechatMiniprogram.TouchEvent) {
      this.setData({
        searchInput: e.detail.value
      })
    },
  },
  /** 生命周期函数 */
  lifetimes: {
    attached: function () {
      // 在组件实例进入页面节点树时执行
      let multiplayers = []
      let messages = []

      for (let index = 0; index < 20; index++) {
        multiplayers.push({
          percentage: Math.floor(Math.random() * (100 - 1 + 1)) + 1,
          title: '灵魂匹配',
          describe: '各类神奇问题上和你选择相同的人！'
        })
        messages.push({
          nickname: `用户${index + 1}`,
          avatarUrl: '',
          msg: `你好，我是用户${index + 1}，很高兴认识你！很高兴认识你！很高兴认识你！很高兴认识你！很高兴认识你！很高兴认识你！很高兴认识你！很高兴认识你！`,
        })
      }
      this.setData({
        // multiplayers,
        messages
      })
    },
    detached: function () {
      // 在组件实例被从页面节点树移除时执行
    },
  },
})