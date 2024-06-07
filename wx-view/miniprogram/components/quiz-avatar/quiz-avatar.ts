Component({

  /**
   * 组件的属性列表
   */
  properties: {
    /** Avatar 图片的源地址 */
    src: String,
    /** Avatar 的大小 */
    size: {
      type: String,
      value: '100px',
    },
    /** Avatar 图片的形状 'circle' | 'square' */
    shape: {
      type: String,
      value: 'circle',
    },
    /** Avatar 图片的边框颜色 */
    wrapperColor: {
      type: String,
      value: '',
    },
    /** Avatar 图片的边框大小 */
    wrapperSize: {
      type: String,
      value: '0px',
    }

  },

  /**
   * 组件的初始数据
   */
  data: {

  },

  /**
   * 组件的方法列表
   */
  methods: {

  }
})