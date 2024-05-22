const app = getApp()
const { request } = require('../../utils/service.js')
Page({
    data: {
        avatarUrl: app.globalData.userInfo.avatarUrl,
        questions: [],
        index: 0,
        showInput: false,
        bottom: 0,
        inputValues: [],
        activityPreview: '',
    },
    onLoad() {
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
    handleOptionClick() {
        let index = this.data.index
        if (index >= this.data.questions.length - 1) return
        this.setData({
            index: index + 1
        })
    },
    onHideInput() {
        this.setData({
            showInput: false,
        })
    },
    getBottom(event) {
        console.log(event)
        this.setData({
            bottom: event.detail.height
        })
    },
    inputChange: function (e) {
        let inputValues = this.data.inputValues
        inputValues[this.data.index] = e.detail.value
        this.setData({
            inputValues: inputValues//e.detail.value表示输入框的当前值
        })
        console.log(this.data.inputValues, this.data.index)
    },
    handleFooterClick() {
        this.setData({
            showInput: true,
        })
    },
    // 修改当前活动的预览
    changeActivityPreview(e) {
        this.setData({
            activityPreview: e.target.dataset.activity,
        })
    },
})