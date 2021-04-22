const api = require("../../../config/config.js");
import request from "../../../utils/request";
import Dialog from "@vant/weapp/dialog/dialog";
import Toast from '@vant/weapp/toast/toast';
const app = getApp();

Page({
  onShareAppMessage() {
    return {
      title: "日报",
      path: "/pages/home/temperature/temperature",
    };
  },

  data: {
    hasRecord: false,
    isAgree: true,
    userInfo: {},
    wxTemperature: {},
    temperature: 37.0,
    showPicker: false,
    columns:[],
    temperatureArray: [
      [34, 35, 36, 37, 38, 39, 40],
      [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
    ],
    temperatureIndex: [3, 0],
  },

  onChange: function (e) {
    this.setData({
      isAgree: e.detail,
    });
  },

  getLocation: function () {
    let that = this;
    wx.getLocation({
      type: "wgs84",
      success: res => {
        const url = "https://apis.map.qq.com/ws/geocoder/v1/";
        const key = "GBYBZ-PJ3CJ-JQWF4-K6G46-GEK2T-ETBII";
        wx.request({
          url: url,
          method: "GET",
          data: {
            location: res.latitude + "," + res.longitude,
            key: key,
            get_poi: 0,
          },
          success: locationRes => {
            that.setData({
              [`wxTemperature.location`]: locationRes.data.result.address,
            });
          },
          fail: () => {
            Toast.fail("自动获取位置失败，请刷新重试。");
          }
        })
      },
      fail: () => {
        wx.getSetting({
          success: res => {
            if (!res.authSetting["scope.userLocation"]) {
              that.onClickConfirm();
            } 
          }
        });
      }
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
      .catch(() => {
        wx.navigateBack();
      });
  },

  openSetting: function(){
    wx.openSetting();
  },

  showPicker: function () {
    let that = this;
    that.setData({
      showPicker: true,
      columns: [
        {
          values: that.data.temperatureArray[0],
          className: "column1",
          defaultIndex: that.data.temperatureIndex[0],
        },
        {
          values: that.data.temperatureArray[1],
          className: "column2",
          defaultIndex: that.data.temperatureIndex[1],
        },
      ],
    });
  },

  onPickerConfirm: function () {
    let that = this;
    that.setData({
      [`wxTemperature.temperature`]: that.data.temperature,
    });
    that.onPickerCancel();
  },

  onPickerCancel:function () {
    this.setData({
      showPicker: false,
    });
  },

  onPickerChange: function (e) {
    const { value } = e.detail;
    this.setData({
      temperature: value[0] + "." + value[1],
    })

  },

  formInputChange(e) {
    this.setData({
      [`wxTemperature.remark`]: e.detail,
    });
  },

  submitForm() {
    let that = this;
    if (app.isPerfect()) {
      if (that.data.isAgree) {
        Toast.loading({
          message: "提交中",
          duration: 1000,
        })
        setTimeout(function () {
          that.submit();
        }, 1000);
      } else {
        Toast("请同意相关条款");
      }
    }
  },

  submit: function () {
    let that = this;
    const _request = new request();
    _request
      .postRequest(api.addTemperatureUrl, {
        wxUser: wx.getStorageSync("userInfo"),
        wxTemperature: that.data.wxTemperature,
      })
      .then((res) => {
        if (res.data.code === 200) {
          Toast.success("提交成功");
          setTimeout(function () {
            that.today();
            wx.redirectTo({
              url: '/pages/temperature/temperature-record/temperature-record',
            });
          }, 500);
        } else {
          Toast.fail("提交失败");
        }
      })
      .catch(() => {
        Toast.fail("服务器错误");
      });
  },

  today: function () {
    let that = this;
    const _request = new request();
    _request
      .postRequest(api.toDayTemperatureUrl, {
        wxUser: wx.getStorageSync("userInfo"),
      })
      .then((res) => {
        if (res.data.code != 200) {
          that.setData({
            hasRecord: false,
          });
        } else {
          that.setData({
            hasRecord: true,
          });
        }
      });
  },

  onLoad: function () {
    if(!wx.getStorageSync('hasLogin')){
      app.checkLoginStatus();
    }
    let that = this;
    that.getLocation();
    that.today();
    that.setData({
      [`wxTemperature.temperature`]: that.data.temperature,
    });
  },

  onShow: function () {
    this.onLoad();
  },

  onPullDownRefresh: function () {
    this.onLoad();
    wx.stopPullDownRefresh();
  },
});