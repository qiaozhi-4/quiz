// components/quiz-progress/quiz-progress.ts
Component({

  /**
   * 组件的属性列表
   */
  properties: {
    /** 进度条宽度 */
    strokeWidth: {
      type: String,
      value: '100%',
    },
    /** 进度条高度 */
    strokeHeight: {
      type: String,
      value: '10px',
    },
    /** 进度条百分比 */
    percentage: {
      type: Number,
      value: 0,
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