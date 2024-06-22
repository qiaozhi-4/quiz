// components/svg/svg.ts
Component({

  /**
   * 组件的属性列表
   */
  properties: {
    src: String,
    width: String,
    height: String,
    colors: Array,
    opacitys: Array,
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

  },
  /**
   * 组件数据字段监听器
   */
  observers: {
    'src, colors, opacitys': function (src, colors, opacitys) {
      // 读取 src 链接资源
      wx.getFileSystemManager().readFile({
        filePath: src,
        encoding: 'binary',
        success: res => {
          let basestr: any = res.data
          // 设置默认宽度
          // if (!this.data.width) {
          try {
            let width = basestr.match(/width="\d+"/)[0].match(/\d+/)[0]
            this.setData({
              width: `${width}px`,
            })
          } catch (error) { }
          // }
          // 设置默认高度
          // if (!this.data.height) {
          try {
            let height = basestr.match(/height="\d+"/)[0].match(/\d+/)[0]
            this.setData({
              height: `${height}px`,
            })
          } catch (error) { }
          // }
          // 替换参数对应的值
          const fun = (parmName: string, paemVals: any[]) => {
            let index = 0
            basestr = basestr.replace(new RegExp(`${parmName}=".+?"`, 'g'), (word: string) => {
              const newColor = paemVals[index]
              index++
              return newColor ? word.replace(/".*"/, (_) => `"${newColor}"`) : word
            })
          }
          // 匹配颜色，并进行替换
          if (colors && colors.length)
            fun('(fill|stroke)', colors)
          // 匹配透明度，并进行替换
          if (opacitys && opacitys.length)
            fun('(fill|stroke)-opacity', opacitys)
          // 将 svg 数据进行 URL 编码
          basestr = encodeURIComponent(basestr)
          this.setData({
            svgStyle: `background-image: url("data:image/svg+xml,${basestr}");`
          })
        }
      })
    }
  }
})