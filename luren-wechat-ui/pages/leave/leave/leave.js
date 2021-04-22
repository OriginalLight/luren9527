const api = require("../../../config/config.js");
import request from "../../../utils/request";
import Toast from "@vant/weapp/toast/toast";
const app = getApp();

Page({
  onShareAppMessage() {
    return {
      title: "行程",
      path: "/pages/leave/leave/leave",
    };
  },

  data: {
    date: "",
    show: false,
    isAgree: true,
    userInfo: {},
    wxLeave: {},
    errorList: {},
  },

  onDisplay() {
    this.setData({ show: true });
  },

  onClose() {
    this.setData({ show: false });
  },

  formatDate: function (date) {
    date = new Date(date);
    return `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;
  },

  onConfirm: function (e) {
    let that = this;
    const [start, end] = e.detail;
    let startDate = that.formatDate(start);
    let endDate = that.formatDate(end);
    let formatStartDate = startDate.replace(/-/g,"/");
    let formatEndDate = endDate.replace(/-/g,"/");
    this.setData({
      show: false,
      date: startDate + "~" + endDate,
      [`wxLeave.fromTime`]: new Date(formatStartDate).getTime(),
      [`wxLeave.toTime`]: new Date(formatEndDate).getTime(),
    });
  },

  onChange: function (e) {
    this.setData({
      isAgree: e.detail,
    });
  },

  inputChange: function (e) {
    const { field } = e.currentTarget.dataset;
    this.setData({
      [`wxLeave.${field}`]: e.detail,
    });
  },

  doValid: function () {
    let that = this;
    let wxLeave = that.data.wxLeave;
    that.setData({ errorList: null });
    if (!wxLeave.fromTime || wxLeave.fromTime == null) {
      that.setData({
        [`errorList.time`]: "请选择时间区间",
      });
      return false;
    } else {
      if (!wxLeave.toTime || wxLeave.toTime == null) {
        that.setData({
          [`errorList.time`]: "请选择时间区间",
        });
        return false;
      } else {
        if (!wxLeave.remark || wxLeave.remark == null) {
          that.setData({
            [`errorList.remark`]: "输入请假理由",
          });
          return false;
        } else {
          return true;
        }
      }
    }
  },

  submitForm() {
    let that = this;
    if (that.doValid()) {
      if (app.isPerfect()) {
        if (that.data.isAgree) {
          Toast.loading({
            message: "提交中",
            duration: 5000,
          });
          setTimeout(function () {
            that.submit();
          }, 1000);
        } else {
          Toast.clear();
          Toast("请同意相关条款");
        }
      }
    }
  },

  submit: function () {
    let that = this;
    const _request = new request();
    _request
      .postRequest(api.addLeaveUrl, {
        wxUser: wx.getStorageSync("userInfo"),
        wxLeave: that.data.wxLeave,
      })
      .then((res) => {
        if (res.data.code === 200) {
          Toast.clear();
          Toast.success("提交成功");
          setTimeout(function () {
            wx.redirectTo({
              url: '/pages/leave/leave-record/leave-record',
            });
          }, 500);
        } else {
          Toast.clear();
          Toast.fail("提交失败");
        }
      })
      .catch(() => {
        Toast.clear();
        Toast.fail("服务器错误");
      });
  },

  onLoad: function () {
    if(!wx.getStorageSync('hasLogin')){
      app.checkLoginStatus();
    }
  },

  onShow: function () {
    this.onLoad();
  },

  onPullDownRefresh: function () {
    wx.stopPullDownRefresh();
  },
});
