Component({
    options: {
        multipleSlots: true // 在组件定义时的选项中启用多slot支持
    },
    properties: {
        // Avatar 图片的源地址
        src: {
            type: String,
            value: '/images/log.svg',
        },
        //Avatar 的大小
        size: {
            type: String,
            value: '100',
        },
        //Avatar 图片的形状 'circle' | 'square'
        shape: {
            type: String,
            value: 'circle',
        },
    },
    data: {
        // 这里是一些组件内部数据
        someData: {}
    },
    methods: {
        // 这里是一个自定义方法
        customMethod: function () { }
    }
});