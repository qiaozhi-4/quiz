const app = getApp()
Component({
  behaviors: [],
  properties: {

  },
  data: {
    navHeight:app.globalData.navHeight,
    nav:app.globalData.nav,
    navBotHeight:app.globalData.navBotHeight,
    menuLeft:app.globalData.menuLeft,
    menuHeight:app.globalData.menuHeight,
    menuBotHeight:app.globalData.menuBotHeight,
    menuBotRight:app.globalData.menuBotRight,
    windowWidth:app.globalData.windowWidth,
  },
  lifetimes: {
    created() {

    },
    attached() {

    },
    moved() {

    },
    detached() {

    },
  },
  methods: {

  },
});