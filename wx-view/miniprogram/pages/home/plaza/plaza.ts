// pages/home/plaza/plaza.ts
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

  }
})