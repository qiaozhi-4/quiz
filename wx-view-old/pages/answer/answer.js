const app = getApp()
const { request } = require('../../utils/service.js')
Page({
    data: {
        avatarUrl: app.globalData.userInfo?.avatarUrl,
        questions: [], // 题目
        options: [], // 选项
        inputValues: [], // 输入框的值
        index: 0, // 当前题目索引
        activityContainer: '', // 活动预览窗口
        showInput: false, // 是否显示输入框
    },
    onLoad() {
        // 获取随机题目
        request({
            // url: '/question/get-random-questions',
            url: '/question/get-random-questions-test',
            method: 'GET',
        }).then((res) => {
            wx.showToast({
                title: '成功',
                icon: 'success',
                duration: 2000
            })
            this.setData({
                questions: res.data
            })
        }).catch((err) => {
            console.log('请求失败:' + err.message)
        })
    },
    // 题目变化触发
    handleSwiperChange(e) {
        this.setData({
            index: e.detail.current,
        })
    },
    // 点击选项触发
    handleOptionClick(e) {
        let index = this.data.index
        let options = this.data.options
        let option = e.currentTarget.dataset.option
        options[index] = option
        if (index >= this.data.questions.length - 1) {
            this.setData({
                options: options,
            })
        } else {
            this.setData({
                index: index + 1,
                options: options,
            })
        }
    },
    // 修改当前活动的容器
    changeActivityContainer(e) {
        let currentActivityContainer = this.data.activityContainer
        let activity = e.currentTarget.dataset.activity
        this.setData({
            activityContainer: currentActivityContainer == activity ? '' : activity,
        })
    },
    // 隐藏容器时触发
    hiddenContainer(e) {
        this.setData({
            activityContainer: ''
        })
    },
    // 隐藏容器时触发
    jumpQuestion(e) {
        let index = e.currentTarget.dataset.index
        this.setData({
            index: index,
            activityContainer: ''
        })
    },


    // 键盘高度发生变化的时候触发此事件，event.detail = {height: height, duration: duration}
    getKeyboardBottom(event) {
        this.setData({
            bottom: event.detail.height
        })
    },
    // 键盘输入时触发，event.detail = {value, cursor, keyCode}
    inputChange: function (e) {
        let inputValues = this.data.inputValues
        inputValues[this.data.index] = e.detail.value
        this.setData({
            inputValues: inputValues//e.detail.value表示输入框的当前值
        })
    },
    // 显示输入框
    onShowInput() {
        this.setData({
            showInput: true,
            activityContainer: '',
        })
    },
    // 隐藏输入框
    onHideInput() {
        this.setData({
            showInput: false,
            bottom: 0,
        })
    },
})