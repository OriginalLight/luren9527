const api = require("../../../config/config.js");
import request from "../../../utils/request";
import Toast from "@vant/weapp/toast/toast";
const app = getApp();

Page({
  onShareAppMessage() {
    return {
      title: "请假记录",
      path: "/pages/leave/leave-record/leave-record",
    };
  },

  data: {
    activeName: null,
    unCheckedRecords: {},
    checkedRecords: {},
    showCheckedRecords: {},
    option1: [
      { text: '显示7条记录', value: 0 },
      { text: '显示14条记录', value: 1 },
      { text: '显示全部记录', value: 2 },
    ],
    option2: [
      { text: '时间降序', value: 0 },
      { text: '时间升序', value: 1 },
    ],
    value1: 0,
    value2: 0,
  },

  getRecords: function () {
    let that = this;
    const _request = new request();
    _request
      .postRequest(api.getLeaveUrl, {
        wxUser: wx.getStorageSync("userInfo"),
      })
      .then((res) => {
        let records = res.data.data;
        let unCheckedRecords = [];
        let checkedRecords = [];
        for (let i = 0; i < records.length; i++) {
          records[i].time =
            that.formatDate(records[i].fromTime) +
            "~" +
            that.formatDate(records[i].toTime);
            if (records[i].checkStatus === 0) {
              unCheckedRecords.push(records[i]);
            } else {
              checkedRecords.push(records[i]);
            }
        }
        if (checkedRecords.length <= 7) {
          that.setData({
            unCheckedRecords: unCheckedRecords,
            checkedRecords: checkedRecords,
            showCheckedRecords: checkedRecords,
          });
        } else {
          let showCheckedRecords = [];
          for (let i = 0; i < 7; i++) {
            showCheckedRecords.push(checkedRecords[i]);
          }
          that.setData({
            unCheckedRecords: unCheckedRecords,
            checkedRecords: checkedRecords,
            showCheckedRecords: showCheckedRecords,
          });
        }
      }).catch(()=>{
        Toast.fail("请求失败");
      });
  },

  formatDate: function (date) {
    date = new Date(date);
    return `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;
  },

  onChange: function (e) {
    this.setData({
      activeName: e.detail,
    });
  },

  dropdownLeftChange: function (e) {
    let that = this;
    let records = that.data.checkedRecords;
    if (e.detail === 0) {
      if (records.length <= 7) {
        that.setData({
          showCheckedRecords: records,
        });
      } else {
        let showCheckedRecords = [];
        for (let i = 0; i < 7; i++) {
          showCheckedRecords.push(records[i]);
        }
        that.setData({
          showCheckedRecords: showCheckedRecords,
        });
      }
    } else if (e.detail === 1) {
      if (records.length <= 14) {
        that.setData({
          showCheckedRecords: records,
        });
      } else {
        let showCheckedRecords = [];
        for (let i = 0; i < 14; i++) {
          showCheckedRecords.push(records[i]);
        }
        that.setData({
          showCheckedRecords: showRecords,
        });
      }
    } else {
      that.setData({
        showCheckedRecords: records,
      });
    }

  },

  dropdownRightChange: function (e) {
    let that = this;
    that.setData({
      showCheckedRecords: that.data.showCheckedRecords.reverse()
    });
  },

  reSet: function (e){
    const reSetItem = e.currentTarget.dataset;
    const _request = new request();
    _request
      .postRequest(api.reSetLeaveUrl, {
        wxUser: wx.getStorageSync('userInfo'),
        wxLeave: reSetItem.record,
      })
      .then((res) => {

        if(res.data.code === 200){
          Toast.success("撤回成功");
          this.onLoad();
        }else{
          Toast.fail("撤回失败");
        }
      }).catch(()=>{
        Toast.fail("请求失败");
      });
    
  },

  onLoad: function () {
    if(!wx.getStorageSync('hasLogin')){
      app.checkLoginStatus();
    }
    this.getRecords();
  },

  onShow: function() {
    this.onLoad();
  },

  onPullDownRefresh: function () {
    this.onLoad();
    wx.stopPullDownRefresh();
  },
});
