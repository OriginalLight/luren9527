const api = require("../../../config/config.js");
import request from "../../../utils/request";
import Dialog from "@vant/weapp/dialog/dialog";
import Toast from "@vant/weapp/toast/toast";
const app = getApp();

Page({
  onShareAppMessage() {
    return {
      title: "行程",
      path: "/pages/home/trip/trip",
    };
  },

  data: {
    date: "",
    show: false,
    isAgree: true,
    userInfo: {},
    wxBack: {},
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
    let date = that.formatDate(e.detail);
    let formatDate = date.replace(/-/g,"/");
    this.setData({
      show: false,
      date: date,
      [`wxBack.fromTime`]: new Date(formatDate).getTime(),
    });
  },

  onChange: function (e) {
    this.setData({
      isAgree: e.detail,
    });
  },

  setFromPlace: function () {
    let that = this;
    wx.chooseLocation({
      success(res) {
        that.setData({
          [`wxBack.fromPlace`]: res.address,
        });
      },
      fail: () => {
        wx.getSetting({
          success: (res) => {
            if (!res.authSetting["scope.userLocation"]) {
              that.onClickConfirm();
            }
          },
        });
      },
    });
  },

  onClickConfirm: function () {
    let message = "您拒绝了位置权限，是否去打开权限？";
    let that = this;
    Dialog.confirm({
      title: "位置信息",
      message,
    })
      .then(() => {
        that.openSetting();
      })
      .catch(() => {});
  },

  openSetting: function () {
    wx.openSetting();
  },

  inputChange: function (e) {
    const { field } = e.currentTarget.dataset;
    this.setData({
      [`wxBack.${field}`]: e.detail,
    });
  },

  doValid: function () {
    let that = this;
    let wxBack = that.data.wxBack;
    that.setData({ errorList: null });
    if (!wxBack.fromTime || wxBack.fromTime == null) {
      that.setData({
        [`errorList.time`]: "请选择返校时间",
      });
      return false;
    } else {
      if (!wxBack.fromPlace || wxBack.fromPlace == null) {
        that.setData({
          [`errorList.fromPlace`]: "请选择或输入返校地点",
        });
        return false;
      } else {
        return true;
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
      .postRequest(api.addBackUrl, {
        wxUser: wx.getStorageSync("userInfo"),
        wxBack: that.data.wxBack,
      })
      .then((res) => {
        if (res.data.code === 200) {
          Toast.clear();
          Toast.success("提交成功");
          setTimeout(function () {
            wx.redirectTo({
              url: '/pages/back/back-record/back-record',
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
