Component({
  options: {
    multipleSlots: true // 在组件定义时的选项中启用多slot支持
  },
  /**
   * 组件的属性列表
   */
  properties: {
    /** 额外的样式类 */
    extClass: {
      type: String,
      value: ''
    },
    /** 导航栏标题 */
    title: {
      type: String,
      value: ''
    },
    /** 背景颜色 */
    background: {
      type: String,
      value: ''
    },
    /** 内容颜色 */
    color: {
      type: String,
      value: ''
    },
    /** 返回上一页 */
    back: {
      type: Boolean,
      value: true
    },
    /** 返回主页 */
    homeButton: {
      type: Boolean,
      value: false,
    },
    /** 显示加载中 */
    loading: {
      type: Boolean,
      value: false
    },
    /** back为true的时候，返回的页面深度 */
    delta: {
      type: Number,
      value: 1
    },
  },
  /**
   * 组件的初始数据
   */
  data: {
    /** 是否为IOS */
    ios: false,
    // 导航栏样式
    /**  */
    innerPaddingRight: '',
    // 导航栏高度
    /**  */
    leftWidth: '',
    // 导航栏标题
    /**  */
    safeAreaTop: ''
  },
  // 组件生命周期声明对象
  lifetimes: {
    attached() {
      const rect = wx.getMenuButtonBoundingClientRect()
      wx.getSystemInfo({
        success: (res) => {
          const isAndroid = res.platform === 'android'
          const isDevtools = res.platform === 'devtools'
          this.setData({
            ios: !isAndroid,
            innerPaddingRight: `padding-right: ${res.windowWidth - rect.left}px`,
            leftWidth: `width: ${res.windowWidth - rect.left}px`,
            safeAreaTop: isDevtools || isAndroid ? `height: calc(var(--height) + ${res.safeArea.top}px); padding-top: ${res.safeArea.top}px` : ``
          })
          console.log('胶囊:', rect)
          console.log('屏幕宽度:', res.windowWidth, ' 屏幕高度:', res.windowHeight)
          console.log(this.data.ios)
          console.log(this.data.innerPaddingRight)
          console.log(this.data.leftWidth)
          console.log(this.data.safeAreaTop)

        }
      })
    },
  },
  /**
   * 组件的方法列表
   */
  methods: {
    /** 返回上一页 */
    back() {
      const data = this.data
      if (data.delta) {
        wx.navigateBack({
          delta: data.delta
        })
      }
    }
  },
})