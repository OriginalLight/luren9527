const api = require("../../../config/config.js");
import request from "../../../utils/request";
import Toast from "@vant/weapp/toast/toast";
const app = getApp();
Page({
  onShareAppMessage() {
    return {
      title: "行程记录",
      path: "/pages/trip/trip-record/trip-record",
    };
  },

  data: {
    activeName: null,
    records: {},
    showRecords: {},
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
      .postRequest(api.getTripUrl, {
        wxUser: wx.getStorageSync("userInfo"),
      })
      .then((res) => {
        let records = res.data.data;
        for (let i = 0; i < records.length; i++) {
          records[i].time =
            that.formatDate(records[i].fromTime) +
            "~" +
            that.formatDate(records[i].toTime);
        }
        if(records.length <= 7){
          that.setData({
            records: records,
            showRecords: records,
          });
        }else{
          let showRecords = [];
          for(let i = 0; i < 7; i++){
            showRecords.push(records[i]);
          }
          that.setData({
            records: records,
            showRecords: showRecords,
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

  dropdownLeftChange: function (e) {
    let that = this;
    let records = that.data.records;
    if(e.detail === 0){
      if(records.length <= 7){
        that.setData({
          showRecords: records,
        });
      }else{
        let showRecords = [];
        for(let i = 0; i < 7; i++){
          showRecords.push(records[i]);
        }
        that.setData({
          showRecords: showRecords,
        });
      } 
    }else if(e.detail === 1){
      if(records.length <= 14){
        that.setData({
          showRecords: records,
        });
      }else{
        let showRecords = [];
        for(let i = 0; i < 14; i++){
          showRecords.push(records[i]);
        }
        that.setData({
          showRecords: showRecords,
        });
      } 
    }else{
      that.setData({
        showRecords: records,
      });
    }
    
  },

  dropdownRightChange: function (e) {
    let that = this;
    that.setData({
      showRecords: that.data.showRecords.reverse()
    });
  },

  onChange: function (e) {
    this.setData({
      activeName: e.detail,
    });
  },

  onLoad: function () {
    if(!wx.getStorageSync('hasLogin')){
      app.checkLoginStatus();
    }
    this.getRecords();
  },

  onShow: function(){
    this.onLoad();
  },

  onPullDownRefresh: function () {
    this.onLoad();
    wx.stopPullDownRefresh();
  },
});
