Component({
    properties: {
        src: String,
        colors: Array,
        opacitys: Array,
    },
    observers: {
        'src, colors, opacitys': function (src, colors, opacitys) {
            // 读取 src 链接资源
            wx.getFileSystemManager().readFile({
                filePath: src,
                encoding: 'binary',
                success: res => {
                    let basestr = res.data
                    // 替换参数对应的值
                    const fun = (parmName,paemVals) => {
                        let index = 0
                        basestr = basestr.replace(new RegExp(`${parmName}=".+?"`, 'g'), (word) => {
                            const newColor = paemVals[index]
                            index++
                            return newColor ? `${parmName}="${newColor}"` : word
                        })
                    }
                    // 匹配颜色，并进行替换
                    if (colors && colors.length)
                        fun('fill',colors)
                    // 匹配透明度，并进行替换
                    if (opacitys && opacitys.length)
                        fun('fill-opacity',opacitys)

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