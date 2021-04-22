const api = require("../config/config.js");
import request from "../utils/request";
import Toast from "@vant/weapp/toast/toast";
const app = getApp();
Page({
  onShareAppMessage() {
    return {
      title: "主页",
      path: "pages/home/index",
    };
  },

  data: {
    hasLogin: false,
    notice: null,
    list: [
      {
        id: "temperature",
        name: "体温日报",
        open: false,
        pages: [
          {
            zh: "日报",
            url: "temperature/temperature/temperature",
          },
          {
            zh: "查看记录",
            url: "temperature/temperature-record/temperature-record",
          },
        ],
      },
      {
        id: "trip",
        name: "行程登记",
        open: false,
        pages: [
          {
            zh: "登记",
            url: "trip/trip/trip",
          },
          {
            zh: "查看记录",
            url: "trip/trip-record/trip-record",
          },
        ],
      },
      {
        id: "leave",
        name: "请假申请",
        open: false,
        pages: [
          {
            zh: "申请",
            url: "leave/leave/leave",
          },
          {
            zh: "查看记录",
            url: "leave/leave-record/leave-record",
          },
        ],
      },
      {
        id: "back",
        name: "返校申请",
        open: false,
        pages: [
          {
            zh: "申请",
            url: "back/back/back",
          },
          {
            zh: "查看记录",
            url: "back/back-record/back-record",
          },
        ],
      },
      {
        id: "setting",
        name: "个人设置",
        open: false,
        pages: [
          {
            zh: "登录",
            url: "login/login",
          },
          {
            zh: "个人中心",
            url: "setting/index",
          },
        ],
      },
    ],
  },

  kindToggle: function (e) {
    const id = e.currentTarget.id;
    const list = this.data.list;
    for (let i = 0, len = list.length; i < len; ++i) {
      if (list[i].id === id) {
        if (list[i].url) {
          wx.navigateTo({
            url: list[i].url,
          });
          return;
        }
        list[i].open = !list[i].open;
      } else {
        list[i].open = false;
      }
    }
    this.setData({
      list,
    });
  },

  getNotice: function () {
    let that = this;
    const _request = new request();
    _request
      .getRequest(api.getNoticeUrl)
      .then((res) => {
        let records = res.data.data;
        let str = "";
        for (let i = 0; i < records.length; i++) {
          if (records[i].status === 0) {
            str +=
              records[i].title +
              "：" +
              records[i].content +
              "      发布时间：" +
              records[i].createTime 
              +"  ";
          }
        }
        that.setData({
          notice: str,
        });
      })
      .catch(() => {});
  },

  onLoad: function () {
    let that = this;
    this.getloginStatus();
    setTimeout(function () {
      if(that.data.hasLogin){
        that.getNotice();
      }  
    }, 500);
  },

  onShow: function () {
    this.onLoad();
  },

  getloginStatus: function () {
    this.setData({
      hasLogin: wx.getStorageSync('hasLogin'),
    });
  },

  onPullDownRefresh: function () {
    app.checkLoginStatus();
    this.getloginStatus();
    wx.stopPullDownRefresh();
  },
});
