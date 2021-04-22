const api = require("../../config/config.js");
import request from "../../utils/request";
import Dialog from "@vant/weapp/dialog/dialog";
import Toast from '@vant/weapp/toast/toast';

Page({
  onShareAppMessage() {
    return {
      title: "设置",
      path: "/pages/setting/index",
    };
  },

  data: {
    show: false,
    userInfo: {},
  },

  onClickConfirm: function() {
    let message = "确认退出登录？";
    let that = this;
    Dialog.confirm({
      title: '注销',
      message,
    }).then(() => {
      that.logout();
    }).catch(() => {});
  },

  logout: function () {
    Toast.loading({
      message: "退出中",
      duration: 5000,
    });
    wx.clearStorageSync();
    Toast.clear();
    Toast.success("退出成功");
    setTimeout(function(){
      wx.navigateBack();
    },1000);
  },

  openSetting: function(){
    wx.openSetting();
  },

  onLoad:function(){
    this.setData({
      userInfo: wx.getStorageSync('userInfo'),
    });
  },

  onShow: function () {
    let that = this;
    setTimeout(function(){
      that.onLoad();
    },200);
  },

  onPullDownRefresh: function(){
    this.onLoad();
    wx.stopPullDownRefresh();
  },

});
