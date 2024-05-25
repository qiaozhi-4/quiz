Component({
    options: {
        multipleSlots: true // 在组件定义时的选项中启用多slot支持
    },
    properties: {
        // Avatar 图片的源地址
        src: String,
        //Avatar 的大小
        size: {
            type: String,
            value: '100px',
        },
        //Avatar 图片的形状 'circle' | 'square'
        shape: {
            type: String,
            value: 'circle',
        },
        //Avatar 图片的边框颜色
        wrapperColor: {
            type: String,
            value: '',
        },
        //Avatar 图片的边框大小
        wrapperSize: {
            type: String,
            value: '0px',
        }
    },
    data: {
        // 这里是一些组件内部数据
        someData: {}
    },
    methods: {
        // 这里是一个自定义方法
        customMethod: function () { }
    },
})